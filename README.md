# AI Job Matching Backend

A backend application that analyzes a candidate's CV against a job description and calculates how well the candidate matches the position.

The project combines a traditional Spring Boot backend with AI-based semantic matching to identify relevant skills, missing skills, and an overall match score.

## 🚀 Features

* CV and job description comparison
* AI-based semantic similarity
* Match score calculation
* Identification of matching skills
* Identification of missing skills
* Job match recommendation
* REST API
* PostgreSQL persistence
* Automated tests with JUnit and Mockito
* Docker support

## 🛠️ Technologies

* Java 21
* Spring Boot
* Spring Data JPA / Hibernate
* PostgreSQL
* Maven
* REST API
* JUnit 5
* Mockito
* Docker
* Git

### AI

The AI component is designed to use local embedding models instead of paid external APIs.

Planned technology:

* Ollama
  OllamaEmbeddingService
        ↓
HTTP POST
        ↓
http://localhost:11434/api/embed
        ↓
nomic-embed-text
        ↓
Embedding-Vektor

* Sentence Transformers / embedding models
* Semantic similarity using vector embeddings

## 🏗️ Architecture

```text
Client
   │
   ▼
REST Controller
   │
   ▼
Service Layer
   │
   ├──────────────► Repository ──────────► PostgreSQL
   │
   ▼
AI Matching Service
   │
   ▼
Embedding Service
   │
   ▼
Local AI Model
```

The application follows a layered architecture:

```text
controller
service
repository
entity
dto
ai
mapper
exception
```

The AI integration is separated behind an abstraction so that the embedding provider can be replaced without changing the business logic.

## 📡 REST API

### Create Job Match

```http
POST /api/job-matches
```

Example request:

```json
{
  "cvText": "Java developer with experience in Spring Boot, PostgreSQL and Docker.",
  "jobDescription": "We are looking for a Java developer with Spring Boot and database experience."
}
```

Example response:

```json
{
  "id": 1,
  "matchScore": 85.5,
  "recommendation": "Strong match"
}
```

### Get Job Match

```http
GET /api/job-matches/{id}
```

## 🗄️ Database

The application uses PostgreSQL.

For local development, PostgreSQL runs in Docker:

```text
Host: localhost
Port: 5434
Database: ai_job_matching
Username: ai_user
```

The database is isolated from other local projects.

## 🐳 Docker

Start the PostgreSQL container:

```bash
docker start ai-job-matching-postgres
```

Stop it:

```bash
docker stop ai-job-matching-postgres
```

## ▶️ Run the Application

Clone the repository:

```bash
git clone <repository-url>
cd ai-job-matching
```

Start the application with Maven:

```bash
mvn spring-boot:run
```

The application runs on:

```text
http://localhost:8080
```

## 🧪 Tests

Run all tests:

```bash
mvn test
```

The project uses:

* JUnit 5
* Mockito
* Spring Boot Test

## 📋 Development Roadmap

* [x] Spring Boot project setup
* [x] PostgreSQL configuration
* [ ] JobMatch entity
* [ ] Repository
* [ ] DTOs
* [ ] Service layer
* [ ] REST Controller
* [ ] Validation and exception handling
* [ ] Skill extraction
* [ ] AI embedding integration
* [ ] Semantic similarity calculation
* [ ] Automated tests
* [ ] Swagger / OpenAPI
* [ ] Docker Compose
* [ ] GitHub documentation
* [ ] Optional OpenSearch integration

## 🎯 Project Goal

This project is designed as a portfolio project demonstrating practical backend development with Java and Spring Boot, including:

* Clean architecture
* REST API development
* Database design
* JPA/Hibernate
* Testing
* Docker
* AI integration
* Separation of concerns
* SOLID principles

## 👩‍💻 Author

**Elnaz Gharoon**

Java Backend Developer
