# 🔐 Secure E-Commerce Application with AI Fraud Detection & Adaptive Authentication

A secure backend-focused e-commerce application that integrates **AI-based fraud detection** with **risk-based multi-factor authentication** (OTP & Biometric) to protect online transactions.

Built as a major academic project with real-world architecture and microservice-style integration.

---

## 📌 Project Overview

This project simulates a real-world secure e-commerce system where each transaction is dynamically evaluated using an AI fraud detection service.

Based on the detected fraud risk level (LOW / MEDIUM / HIGH), the system applies adaptive authentication:

* LOW risk → Transaction approved directly
* MEDIUM risk → OTP verification required
* HIGH risk → Biometric authentication required

The backend is built using Spring Boot, while fraud analysis and verification services run as a separate Python microservice communicating via REST APIs.

---

## ✨ Key Features

* User registration and secure login
* Product listing and cart management
* Checkout and order processing
* AI-based fraud risk analysis
* Adaptive multi-factor authentication:

  * **LOW risk** → Direct order placement
  * **MEDIUM risk** → OTP verification
  * **HIGH risk** → Biometric authentication
* Transaction history tracking
* Fraud status logging
* RESTful API design
* MySQL-based persistent storage

---

## 🛠️ Tech Stack

### Backend
* Java
* Spring Boot
* Spring Data JPA / Hibernate
* REST APIs

### Database
* MySQL

### AI / Verification Service
* Python
* Flask

### Frontend (Demo)
* HTML
* CSS
* JavaScript

### Tools
* Postman
* Git
* GitHub

---

## 🏗️ Architecture Overview

```

[ Browser / Postman ]
|
v
[ Spring Boot Backend ]
|
| REST API (JSON)
v
[ Python AI & Verification Service ]

```

# How it works

1. User checks out cart items
2. Backend calculates total transaction amount
3. Backend sends amount to Python fraud service
4. Python service returns risk level
5. Backend applies risk-based authentication:
   - LOW → Direct approval
   - MEDIUM → OTP verification
   - HIGH → Biometric authentication
6. Transaction result stored in database

---

## 🔌 API Endpoints (Main)

### User APIs
```

POST /api/users/register
POST /api/users/login

```

### Product APIs
```

GET /api/products

```

### Cart APIs
```

POST /api/cart/add?userId={id}&productId={id}&quantity={n}
GET  /api/cart/{userId}

```

### Order & Transaction APIs
```

POST /api/orders/checkout/{userId}
GET  /api/transactions/{userId}

````

---

## ⚙️ Fraud Decision Logic

| Amount Range   | Risk Level | Action Taken                |
|----------------|------------|-----------------------------|
| ≤ 50,000       | LOW        | Direct order placement      |
| 50,001–100,000 | MEDIUM     | OTP verification required   |
| > 100,000      | HIGH       | Biometric authentication    |

---

## ▶️ How to Run the Application

### 1️⃣ Start MySQL
Create database: `secure_ecommerce_db`  
Update credentials in `application.properties`

### 2️⃣ Run Spring Boot Backend

```bash
mvn spring-boot:run
````

Backend runs on:

```
http://localhost:8080
```

### 3️⃣ Run Python Service

```bash
python app.py
```

Service runs on:

```
http://localhost:5000
```

---

## 🔄 Backend ↔ Python Communication

Spring Boot uses `RestTemplate` to communicate with the external service.

Backend sends transaction amount → Python service returns risk → Backend enforces authentication.

This mimics real microservice-based security systems used in production.

---

## 📈 Why This Project Matters

* Demonstrates real-world backend architecture
* Shows AI service integration
* Implements adaptive security workflows
* Uses microservice communication
* Suitable for Java Backend / Full Stack roles
