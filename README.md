# AI Job Matching Backend

A Java Spring Boot application for exploring AI-based semantic matching between a candidate's CV and job descriptions.

The project combines a REST API, relational data storage, locally generated text embeddings and OpenSearch vector search.

## Features

- REST API built with Spring Boot
- Relational data management with PostgreSQL and Spring Data JPA
- Text embeddings generated locally using Ollama and `nomic-embed-text`
- OpenSearch indexing and k-NN search for job description embeddings
- Angular frontend for entering CVs and job descriptions
- Automated tests with JUnit 5 and Mockito

**Project status:** The basic API and embedding integration are implemented. Direct CV-to-job similarity scoring and the complete matching workflow are still under development.

## Tech Stack

- **Backend:** Java 21, Spring Boot, Maven
- **Database:** PostgreSQL, Spring Data JPA, Hibernate
- **AI embeddings:** Ollama, `nomic-embed-text`
- **Vector search:** OpenSearch
- **Frontend:** Angular, TypeScript, HTML, CSS
- **Testing and tools:** JUnit 5, Mockito, Docker, Git

## Architecture

```text
Angular Frontend
       |
       v
Spring Boot REST API
       |
       v
Service Layer
       |
       +---- PostgreSQL
       |     Relational data storage
       |
       +---- Ollama
       |     Text-to-vector embeddings
       |
       +---- OpenSearch
             Vector indexing and k-NN search
```

## How It Works

### 1. Text Embeddings with Ollama

Ollama runs the `nomic-embed-text` model locally. The backend sends text to the Ollama API and receives a numerical embedding vector representing semantic characteristics of the text.

Embeddings can be generated for CV text and job descriptions. Similarity calculations require a suitable comparison strategy for the resulting vectors.

### 2. Vector Search with OpenSearch

OpenSearch stores and indexes job description embeddings. Its k-NN search can retrieve stored vectors that are similar to a query vector.

The OpenSearch search score indicates the relevance of a result within the configured search. It is not automatically a calibrated percentage representing a candidate's suitability for a job.

**Direct CV-to-job similarity scoring is still under development.**

### 3. Relational Data with PostgreSQL

PostgreSQL provides relational data storage, accessed through Spring Data JPA and Hibernate. PostgreSQL and OpenSearch serve different purposes: relational persistence and vector-based retrieval, respectively.

## Getting Started

### Prerequisites

- Java 21
- Maven
- Node.js and npm for the Angular frontend
- PostgreSQL
- OpenSearch
- Ollama with the `nomic-embed-text` model

### Start the Backend

Start and configure the required services, then run:

```bash
mvn spring-boot:run
```

Backend: `http://localhost:8080`

Frontend: `http://localhost:4200`

### Run Tests

```bash
mvn test
```

**Configuration note:** Service addresses, ports, database credentials and model settings must match your local configuration. The backend and frontend must be started separately unless an automated setup is provided.

## Roadmap

- [x] Spring Boot REST API
- [x] Ollama embedding integration
- [x] OpenSearch embedding indexing
- [x] Direct CV-to-job similarity scoring
- [ ] Complete end-to-end matching workflow
- [ ] Matching and missing skills analysis
- [ ] Expanded automated test coverage
- [ ] Simplified local setup and documentation

## Author

Elnaz Gharoon
