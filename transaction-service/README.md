# Transaction Service

## 🚀 Overview

The Transaction Service is part of the Replikant system and is responsible for handling financial transactions in an event-driven architecture.

It exposes REST endpoints to receive transaction requests, persists data in a relational database, and publishes events to a message broker for downstream services.

This service simulates how real-world financial systems process transactions asynchronously and reliably.

---

## 🧱 Responsibilities

* Receive transaction requests via REST API
* Validate and process incoming data
* Persist transactions in PostgreSQL
* Publish transaction events to Apache Kafka

---

## 🧰 Technologies

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* PostgreSQL
* Apache Kafka
* Docker / Docker Compose

---

## 📦 Infrastructure

The service runs in a containerized environment and depends on:

* PostgreSQL for data persistence
* Apache Kafka for event streaming
* Kafka UI for topic visualization and debugging

---

## 🔄 Data Flow

```text
Client Request
     ↓
Transaction Service
     ↓
PostgreSQL (data persistence)
     ↓
Kafka (event publication)
```

---

## 📡 API Endpoints

### Create Transaction

`POST /transactions`

```json
{
  "userId": 1,
  "amount": 100.50,
  "country": "BR"
}
```

### Get All Transactions

`GET /transactions`

### Get Transaction by ID

`GET /transactions/{id}`

### Update Transaction

`PUT /transactions/{id}`

### Delete Transaction

`DELETE /transactions/{id}`

---

## 🐳 Running the Project

### Start all services with Docker Compose

```bash
docker-compose up --build
```

---

### Access services

* API: http://localhost:8080
* Kafka UI: http://localhost:8081

---

## 📬 Event Example

A message published to Kafka looks like:

```json
{
  "transactionId": 1,
  "userId": 1,
  "amount": 100.50,
  "country": "BR"
}
```

---

## 🔐 Security

This service does not implement authentication or authorization mechanisms.

Security concerns are intentionally out of scope for this project, as the primary focus is on learning and demonstrating:

* Event-driven architecture
* Microservices communication
* Data flow between services

In a production environment, this service would include proper security layers such as authentication, authorization, and data protection.

---

## 🧠 Architecture Notes

* The service follows a producer pattern in an event-driven architecture
* Events are published after successful persistence
* Designed to integrate with downstream services (e.g., fraud detection)

---

## 🚧 Status

This service is currently under development and is part of a larger microservices ecosystem.

Future improvements include:

* Transaction validation rules
* Fraud detection integration
* Idempotency handling
* Observability (logs, metrics, tracing)
