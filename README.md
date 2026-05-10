# rePlikant

## 🚀 Overview

The **rePlikant** system is a microservices-based architecture designed for **fraud detection in financial transactions**.

It simulates a real-world distributed system where transactions are processed asynchronously, enriched with event-driven communication, and analyzed by a dedicated fraud detection pipeline.

Each service is responsible for a specific domain, ensuring scalability, resilience, and separation of concerns.

---

## 🧱 Architecture

The system is composed of multiple microservices, including:

- Transaction Service
- Fraud Detection Service
- User Service (optional)
- Notification Service (optional)

Each service communicates through **Apache Kafka events**, enabling asynchronous processing and loose coupling.

---

## ⚙️ Core Responsibilities

### Transaction Service
- Receives and validates transaction requests
- Persists transaction data
- Publishes transaction events to Kafka

### Fraud Detection Service
- Consumes transaction events
- Applies fraud detection rules / heuristics
- Flags suspicious transactions
- Publishes fraud analysis results

### Notification Service (optional)
- Listens for fraud alerts
- Sends notifications (email, logs, or external integrations)

---

## 🔄 Event-Driven Flow

    Client Request
         ↓
    Transaction Service
         ↓
    PostgreSQL (persistence)
         ↓
    Kafka Topic: transaction-created
         ↓
    Fraud Detection Service
         ↓
    Kafka Topic: fraud-analysis
         ↓
    Notification Service (optional)

---

## 🧰 Technologies

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Apache Kafka
- PostgreSQL
- Docker / Docker Compose
- Lombok
- MapStruct (optional)

---

## 📦 Infrastructure

The system runs in a containerized environment and relies on:

- PostgreSQL – persistent storage per service
- Apache Kafka – event streaming backbone
- Kafka UI – topic monitoring and debugging
- Docker Compose – orchestration of all services

---

## 📡 API Endpoints

### Create Transaction

`POST /transactions`

    {
      "userId": 1,
      "amount": 250.75,
      "country": "BR"
    }

### Get All Transactions

`GET /transactions`

### Get Transaction by ID

`GET /transactions/{id}`

---

## 📊 Fraud Detection Output Example

When a transaction is analyzed, the fraud service may emit:

    {
      "transactionId": 1,
      "userId": 1,
      "riskScore": 0.92,
      "fraudDetected": true,
      "reasons": [
        "High amount",
        "Unusual location pattern"
      ]
    }

---

## 📬 Kafka Events

### Transaction Created Event

    {
      "transactionId": 1,
      "userId": 1,
      "amount": 250.75,
      "country": "BR"
    }

### Fraud Analysis Event

    {
      "transactionId": 1,
      "fraudDetected": true,
      "riskScore": 0.92
    }

---

## 🐳 Running the Project

### Start all services

    docker-compose up --build

---

### Access points

- Transaction API: http://localhost:8080
- Fraud Service: http://localhost:8081 (if exposed)
- Kafka UI: http://localhost:8082

---

## 🔐 Security

This project does not implement authentication or authorization.

The focus is on:

- Microservices architecture
- Event-driven systems
- Fraud detection logic simulation
- Distributed data flow

In production systems, this layer would include:

- OAuth2 / JWT authentication
- Service-to-service security
- Encryption of sensitive data

---

## 🧠 Design Notes

- Event-driven architecture using Kafka as the central backbone
- Services are stateless and independently deployable
- Fraud detection is decoupled from transaction processing
- System is designed for horizontal scalability

---

## 🚧 Status

This project is under active development as part of a microservices learning and portfolio system.

### Planned improvements:

- ML-based fraud detection model integration
- Idempotency handling for event processing
- Distributed tracing (OpenTelemetry)
- Centralized logging (ELK / Loki)
- Circuit breakers and resiliency patterns
- Dead-letter queues (DLQ) for Kafka