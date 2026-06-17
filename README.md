# Quiz App with Microservices

# 📖 About Project

This project is a Quiz Application developed using Microservices Architecture with Spring Boot and Spring Cloud.

The system consists of independent services communicating through API Gateway and Eureka Service Discovery.

The project aims to demonstrate modern backend architecture principles such as service decomposition, scalability, and containerization.

### Main Features

- Create and manage questions
- Generate quizzes from existing questions
- Service discovery with Eureka Server
- API routing through API Gateway
- Containerized deployment using Docker

# 🛠 Technologies

- Java
- Spring Boot
- Spring Cloud
- Eureka Server
- API Gateway
- Spring Data JPA
- PostgreSQL
- Docker
- Docker Compose
- Maven

# 🏗 System Architecture

```markdown
Client
   │
   ▼
API Gateway
   │
 ┌─────┴─────┐
 ▼           ▼
Quiz      Question
Service   Service
     \     /
      \   /
       ▼ ▼
   Eureka Server
```

# ⚙️ Microservices

## 📚 Question Service

Responsible for managing the question bank and evaluating quiz responses.

### Features

- Create new questions
- Retrieve all questions
- Filter questions by category
- Generate question sets for quizzes
- Retrieve questions by IDs
- Calculate quiz scores

### Main Endpoints

```markdown
GET    /question/allQuestions
GET    /question/category/{category}
POST   /question/add
GET    /question/generate
POST   /question/getQuestions
POST   /question/getScore
```

## 📝 Quiz Service

Responsible for quiz creation, question retrieval, and result calculation.

### Features

- Create quizzes dynamically
- Retrieve quiz questions
- Submit quiz answers
- Calculate final results
- Communicate with Question Service

### Main Endpoints

```markdown
POST   /quiz/create
GET    /quiz/get/{id}
POST   /quiz/submit/{id}
```

## 🔍 Eureka Server

Responsible for service discovery and registration.

### Features

- Service registration
- Service lookup
- Dynamic discovery
- Decoupled service communication

## 🌐 API Gateway

Acts as the single entry point for all client requests. All client requests are routed through the API Gateway, which uses Eureka Service Discovery to dynamically locate target services.

### Features

- Request routing
- Service abstraction
- Centralized access point
- Load balancing support

# 🔗 API Endpoints

| Endpoint | Method | Description |
| --- | --- | --- |
| `/question/add` | POST | Add a new question |
| `/question/allQuestions` | GET | Retrieve all available questions |
| `/question/category/{category}` | GET | Retrieve questions by category |
| `/question/generate?categoryName={category}&numQuestions={count}` | GET | Generate question IDs for a quiz |
| `/question/getQuestions` | POST | Retrieve question details from question IDs |
| `/question/getScore` | POST | Calculate score based on submitted answers |
| `/quiz/create` | POST | Create a new quiz |
| `/quiz/get/{id}` | GET | Retrieve quiz questions by quiz ID |
| `/quiz/submit/{id}` | POST | Submit quiz answers and calculate score |

# 🔄 Inter-Service Communication

This project uses **OpenFeign** for communication between microservices.

The Quiz Service communicates with the Question Service through a Feign Client interface, allowing declarative REST calls without manually handling HTTP requests.

### Communication Flow

```
Client
   │
   ▼
Quiz Service
   │
   │ OpenFeign
   ▼
Question Service
```

### Feign Operations

| Operation | Target Endpoint | Purpose |
| --- | --- | --- |
| Generate Questions | `/question/generate` | Generate question IDs based on category and count |
| Get Question Details | `/question/getQuestions` | Retrieve question data using question IDs |
| Calculate Score | `/question/getScore` | Evaluate submitted answers and calculate the final score |

### Benefits of OpenFeign

- Simplified REST client implementation
- Service discovery integration with Eureka
- Cleaner and more maintainable code
- Reduced boilerplate HTTP communication logic
- Improved microservice-to-microservice interaction

# 🐳 Docker Deployment

```markdown
docker-compose up --build
```

All services can be started with a single Docker Compose command.

# 🔄 Request Flow

1. Client sends a request to API Gateway.
2. API Gateway routes the request to Quiz Service.
3. Quiz Service communicates with Question Service via OpenFeign.
4. Question Service retrieves data from its database.
5. The response is returned to the client through API Gateway.

# 📸 Screenshots

## Eureka Dashboard

![Ekran Resmi 2026-06-17 17.47.47.png](Quiz%20App%20with%20Microservices/Ekran_Resmi_2026-06-17_17.47.47.png)

## Postman Tests

The following Postman tests demonstrate the complete quiz lifecycle, including quiz creation, question retrieval, and result calculation through communication between microservices.

![Ekran Resmi 2026-06-17 18.09.29.png](Quiz%20App%20with%20Microservices/Ekran_Resmi_2026-06-17_18.09.29.png)

![Ekran Resmi 2026-06-17 18.04.57.png](Quiz%20App%20with%20Microservices/Ekran_Resmi_2026-06-17_18.04.57.png)

![Ekran Resmi 2026-06-17 18.10.54.png](Quiz%20App%20with%20Microservices/Ekran_Resmi_2026-06-17_18.10.54.png)

## Docker Containers

![Ekran Resmi 2026-06-17 17.51.49.png](Quiz%20App%20with%20Microservices/Ekran_Resmi_2026-06-17_17.51.49.png)

## Database Architecture

![Ekran Resmi 2026-06-17 17.54.33.png](Quiz%20App%20with%20Microservices/c6c6544b-4538-4b4f-93ae-dfbd106dfc4d.png)

Each microservice owns its own PostgreSQL database, following the Database per Service pattern.

# 🎯 What I Learned

- Microservice Architecture
- Service Discovery
- API Gateway Pattern
- Inter-Service Communication
- Dockerization
- Spring Cloud Ecosystem

# 🔮 Future Improvements

- 🤖 **AI-Powered Question Generation**
    - Integrate AI models to automatically generate quiz questions, answer choices, and difficulty levels based on user-defined topics.
- 🔐 **JWT Authentication & Authorization**
    - Implement secure user authentication and role-based access control.
- 🛡️ **API Security & Rate Limiting**
    - Protect services against abuse and excessive requests through rate limiting and API security policies.
- 📝 **Audit Logging**
    - Track critical user and administrator actions for monitoring and security purposes.
- 📩 **Kafka Integration**
    - Enable asynchronous communication between microservices using event-driven architecture.
- 📊 **Centralized Logging**
    - Collect and monitor logs from all services in a single location.
- 🔍 **Distributed Tracing**
    - Trace requests across multiple services to improve observability and debugging.
- ☸️ **Kubernetes Deployment**
    - Orchestrate and scale containerized services in a production environment.
- 🚀 **CI/CD Pipeline**
    - Automate build, testing, and deployment processes using modern DevOps practices.

### 🤖 Planned AI Feature

- Users will be able to generate quiz questions automatically by providing a topic (e.g., "Java", "Spring Boot", "Microservices"). The AI service will create questions and store them in the Question Service database, reducing manual question creation effort.

# 🔗 Repository

GitHub Repository:
[https://github.com/hilalmerve/quiz-app-with-microservices](https://github.com/hilalmerve/quiz-app-with-microservices)

# 👩‍💻 Author

Hilal Merve

Software Developer | Java, Spring Boot, Microservices & Docker

GitHub:
[https://github.com/hilalmerve](https://github.com/hilalmerve)

LinkedIn:
[https://linkedin.com/in/hilalmervehancioglu](https://linkedin.com/in/hilalmervehancioglu)