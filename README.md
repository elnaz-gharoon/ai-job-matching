# AI Job Matching Backend

A Java Spring Boot application that aims to compare a candidate's CV with job descriptions using AI-based semantic matching.

The project combines a REST API, PostgreSQL persistence, local AI embeddings and OpenSearch vector search.

## Features

- REST API for job matching
- PostgreSQL persistence
- Text embeddings using Ollama and `nomic-embed-text`
- OpenSearch indexing of job descriptions and embedding vectors
- Automated tests with JUnit 5 and Mockito

*Semantic search and match score calculation are currently under development.*

## Tech Stack

- Java 21, Spring Boot, Maven
- Spring Data JPA, Hibernate, PostgreSQL
- Ollama, `nomic-embed-text`, OpenSearch
- Angular, TypeScript, HTML, CSS
- JUnit 5, Mockito, Docker, Git

## Architecture

```text
Angular Frontend
       ↓
Spring Boot REST API
       ↓
Service Layer
       ├── PostgreSQL
       ├── Ollama (Embeddings)
       └── OpenSearch (Vector Index)
```

## Getting Started

Prerequisites: Java 21, Maven, Docker, Ollama and OpenSearch.

Start the required local services, then run:

```bash
mvn spring-boot:run
```

Backend: `http://localhost:8080`

Frontend: `http://localhost:4200`

Run tests:

```bash
mvn test
```

## Roadmap

- [x] Spring Boot REST API
- [x] PostgreSQL integration
- [x] Ollama embedding integration
- [x] OpenSearch embedding indexing
- [ ] k-NN semantic search
- [ ] CV-to-job similarity score
- [ ] Matching and missing skills analysis
- [ ] Complete frontend integration

## Author

Elnaz Gharoon
