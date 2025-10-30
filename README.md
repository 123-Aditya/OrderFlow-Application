# 🧩 OrderFlow-Application

**Event-driven microservices project** built using **Java**, **Spring Boot**, **Docker**, and **Apache Kafka**.

---

## 📸 Application Overview

<img width="1323" height="743" alt="Screenshot of OrderFlow Application" src="https://github.com/user-attachments/assets/d515a4fd-703c-44ae-88a4-9a2c9befd876" />

---

## 🚀 Getting Started

Before building the complete OrderFlow application, I started by developing a **simple "Hello World" Kafka application** using Spring Boot.

The goal of this small project was to:
- Understand how to **set up Kafka locally with Docker**
- Create a **Kafka topic**, **Producer**, and **Consumer** in Spring Boot
- Verify the message flow end-to-end

---

## 🐳 Docker Setup

### ▶️ Start the Kafka & Zookeeper containers

docker compose up -d
<br><br>

### ⏹ Stop the containers

docker compose down
<br><br>

### 🏗️ Build Docker image for the Spring Boot application

docker build -t kafka-hello-world-app01 .
<br><br>

### 🌐 Check available Docker networks

docker network ls
<br><br>

### 🏃‍♂️ Run the application container

Make sure the container runs on the same Docker network as Kafka.

docker run --network javakafka_my_custom_network -p 8080:8080 kafka-hello-world-app01

---

## 📤 Sending Messages to Kafka Topic

<img width="1470" height="956" alt="Kafka Producer Screenshot" src="https://github.com/user-attachments/assets/d4ac46c7-9da2-486f-a8bb-2707e1574b1a" />

---


## 📜 Application Logs

<img width="1470" height="956" alt="Kafka Consumer Logs" src="https://github.com/user-attachments/assets/db93f656-f356-4a9d-8610-b52d25363f56" />

---

## 🧠 Next Steps

The next phase will be to extend this simple setup into the full OrderFlow Application, consisting of:

Order Service (Producer)

Stock Service and Email Service (Consumers)

Kafka Topics for asynchronous message communication

---

## 🛠️ Tech Stack

Java 17

Maven

Spring Boot 3

Apache Kafka

Docker & Docker Compose

Postman (for API testing)

---

## 💡 Event-Driven Architecture

<img width="1310" height="730" alt="Event Driven" src="https://github.com/user-attachments/assets/b6232809-f840-46d0-91ff-87f00cafcf48" />

---

## 💡 Issues faced and Resolution

OrderService Application not able to connect to Kafka running in Docker and application startup failing with below error:
<img width="1114" height="630" alt="Screenshot 2025-10-30 at 11 35 17 PM" src="https://github.com/user-attachments/assets/6c23a6e8-2af0-46ff-b928-8d7c69f26306" />

---

## 💡 Author

Aditya Upadhyaya

GitHub: 123-Aditya
