Log Analytics & Observability Platform

A distributed real-time log analytics platform built using Spring Boot, Apache Kafka, PostgreSQL, Grafana, Docker, and Apache Lucene.

The platform ingests streaming logs, processes analytics in real time, detects anomalies, supports full-text log search, and visualizes operational metrics through Grafana dashboards.

Features
Real-time log ingestion using Kafka
Distributed event-driven architecture
Live analytics processing
Error rate anomaly detection
Full-text log search using Apache Lucene
Grafana dashboard visualization
Dockerized multi-service deployment
REST APIs for metrics and search
Unit tested analytics pipeline
Architecture
Producer Service
       │
       ▼
Apache Kafka
       │
       ▼
Processing Engine
 ├── PostgreSQL (log persistence)
 ├── Lucene (search indexing)
 ├── Analytics Engine
 └── REST APIs
       │
       ▼
Grafana Dashboard
Tech Stack
Backend
Java 21
Spring Boot 3
Spring Kafka
Spring Data JPA
Infrastructure
Apache Kafka
Zookeeper
PostgreSQL
Docker & Docker Compose
Observability
Grafana
Search
Apache Lucene
Testing
JUnit 5
Mockito
System Components
Producer Service

Generates and publishes log events to Kafka topics.

Kafka

Acts as the distributed event streaming platform.

Processing Engine

Consumes logs from Kafka and performs:

analytics aggregation
anomaly detection
Lucene indexing
persistence to PostgreSQL
PostgreSQL

Stores processed log data.

Lucene

Provides full-text search capabilities for logs.

Grafana

Visualizes live operational metrics and analytics.

APIs
Metrics Endpoint
GET /metrics

Returns:

log level distribution
service distribution
analytics summaries
Search Endpoint
GET /search?query=database

Example Response:

[
  {
    "service": "payment-service",
    "level": "ERROR",
    "message": "Database connection timeout"
  }
]
Anomaly Detection Endpoint
GET /analytics/anomalies

Example Response:

[
  {
    "type": "HIGH_ERROR_RATE",
    "message": "Error percentage exceeded 40%"
  }
]
Running the Project
Prerequisites
Docker
Docker Compose
Java 21
Maven
Start Entire Platform

From the docker directory:

docker compose up
Stop Platform
docker compose down
Dockerized Services

The platform runs the following containers:

Zookeeper
Kafka
PostgreSQL
Producer Service
Processing Engine
Grafana
Grafana Dashboard

Grafana is available at:

http://localhost:3000

Default credentials:

Username: admin
Password: admin
Testing

Run unit tests:

./mvnw test

Tests include:

analytics service tests
anomaly detection tests
Key Engineering Concepts Demonstrated
Event-driven architecture
Distributed systems
Real-time stream processing
Search indexing
Observability engineering
Container orchestration
REST API design
Backend testing
Future Improvements
Persistent Lucene index
Advanced search filters
Time-series analytics
Alerting system
Kubernetes deployment
Prometheus integration
Screenshots



Author

Monish Gowda
