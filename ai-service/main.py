# from fastapi import FastAPI
# from pydantic import BaseModel
#
# app = FastAPI()
#
#
# class QuizRequest(BaseModel):
#     category: str
#     questionCount: int
#
#
# @app.get("/health")
# def health():
#     return {
#         "status": "ok"
#     }
#
#
# @app.post("/generate-quiz")
# def generate_quiz(request: QuizRequest):
#
#     questions = []
#
#     for i in range(request.questionCount):
#         questions.append({
#             "questionTitle": f"What is {request.category}? ({i + 1})",
#             "category": request.category,
#             "option1": "Option A",
#             "option2": "Option B",
#             "option3": "Option C",
#             "option4": "Option D",
#             "rightAnswer": "Option A",
#             "difficultyLevel": "Medium"
#         })
#
#     return {
#         "category": request.category,
#         "questionCount": request.questionCount,
#         "questions": questions
#     }

from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from google import genai
import os
import json

app = FastAPI()

# Gemini Client
client = genai.Client(
    api_key=os.environ["GEMINI_API_KEY"]
)


class QuizRequest(BaseModel):
    category: str
    questionCount: int


@app.get("/health")
def health():
    return {
        "status": "ok"
    }


@app.post("/generate-quiz")
def generate_quiz(request: QuizRequest):

    prompt = f"""
Generate {request.questionCount} multiple choice quiz questions about {request.category}.

Requirements:
- Return ONLY valid JSON
- Do NOT use markdown
- Do NOT use ```json blocks
- Each question must have exactly 4 options
- rightAnswer must match one of the options
- difficultyLevel should be Medium

Return this format:

[
  {{
    "questionTitle": "Question text",
    "category": "{request.category}",
    "option1": "Option A",
    "option2": "Option B",
    "option3": "Option C",
    "option4": "Option D",
    "rightAnswer": "Option A",
    "difficultyLevel": "Medium"
  }}
]
"""

    try:
        answerQuestionRequest = client.models.generate_content(
            model="gemini-2.5-flash",
            contents=prompt,
            config={
                "response_mime_type": "application/json"
            }
        )

        response_text = answerQuestionRequest.text

        if not response_text:
            raise HTTPException(
                status_code=500,
                detail="Gemini returned empty answerQuestionRequest"
            )

        raw_response = response_text.strip()

        if raw_response.startswith("```json"):
            raw_response = (
                raw_response
                .replace("```json", "")
                .replace("```", "")
                .strip()
            )

        questions = json.loads(raw_response)

        if not isinstance(questions, list):
            raise ValueError("Gemini did not return a question list")

        return {
            "category": request.category,
            "questionCount": request.questionCount,
            "questions": questions
        }

    except json.JSONDecodeError:
        raise HTTPException(
            status_code=500,
            detail="Failed to parse Gemini answerQuestionRequest as JSON"
        )

    except Exception as e:
        raise HTTPException(
            status_code=500,
            detail=f"AI question generation failed: {str(e)}"
        )