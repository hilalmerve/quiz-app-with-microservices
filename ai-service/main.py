from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI()


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

    questions = []

    for i in range(request.questionCount):
        questions.append({
            "questionTitle": f"What is {request.category}? ({i + 1})",
            "category": request.category,
            "option1": "Option A",
            "option2": "Option B",
            "option3": "Option C",
            "option4": "Option D",
            "rightAnswer": "Option A",
            "difficultyLevel": "Medium"
        })

    return {
        "category": request.category,
        "questionCount": request.questionCount,
        "questions": questions
    }