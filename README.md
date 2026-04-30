# AI-Powered Event-Driven E-Commerce Microservices

A cloud-native **event-driven microservices architecture** built using **Spring Boot, Kafka, Redis, JWT Authentication, and Spring AI**.
The platform demonstrates scalable backend design with **AI-powered product search using vector embeddings**.

---

## Architecture Overview

This system follows a **distributed microservices architecture** deployed using Docker on AWS.

Client Request Flow:

Client → API Gateway → Auth Service → Microservices

Event Flow:

Order Service → Kafka → Product Service (reduce inventory)
Order Service → Kafka → Notification Service (send email)
Product Service → Kafka → AI Service (store embeddings)

---

## Microservices

### API Gateway

Handles routing and authentication validation using JWT filters.

### Auth Service

Responsible for user authentication and JWT token generation.

### Product Service

Manages product CRUD operations.

Features:

* Redis caching
* Kafka producer (send product data to AI)
* Kafka consumer (update quantity after order)
* Spring AOP (logging)

### Order Service

Handles order creation and order management.

Features:

* Feign client communication
* Kafka event publishing

### Notification Service

Consumes Kafka events and sends email notifications.

### AI Service

Implements AI-powered product search.

Features:

* Spring AI
* Ollama LLM (phi3)
* mxbai-embed-large embeddings
* Vector database search (Mariabd)

### Eureka Server

Service discovery for microservices communication.

---

## Tech Stack

Backend

* Java
* Spring Boot
* Spring Cloud
* Spring Security
* Spring AI
* Spring AOP

Microservices Infrastructure

* Eureka Service Discovery
* API Gateway
* OpenFeign

Messaging & Caching

* Apache Kafka
* Redis

AI Stack

* Ollama
* Phi-3 LLM
* mxbai-embed-large embedding model
* Vector Database

Cloud & Deployment

* Docker
* AWS EC2
* AWS ECR
* AWS RDS
* IAM Roles

Testing

* JUnit 5
* Mockito

---

## Key Concepts Demonstrated

* Event-Driven Architecture
* Distributed Microservices
* AI-Powered Search with Vector Embeddings
* JWT Authentication
* Kafka Messaging
* Redis Caching
* Cloud Deployment on AWS
* Containerization using Docker

---

## Deployment Architecture

Docker Containers are built for each service and pushed to **AWS ECR**.

Deployment flow:

Local Build → Docker Image → AWS ECR → AWS EC2 → Run Containers

Database: AWS RDS

---

## Author

Rizwan Shaik
