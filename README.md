# 🛒 Secure E-Commerce Application

A cloud-deployed **Secure E-Commerce Application** built using **Java Spring Boot**, **React**, **Python Flask**, and **PostgreSQL**. The project demonstrates secure online shopping with **JWT Authentication**, **AI-based Fraud Detection**, **Adaptive Authentication**, and a **Microservice Architecture**.

The application is deployed completely on the cloud using **Azure App Service**, **Vercel**, and **Neon PostgreSQL**, with **GitHub Actions** automating backend and AI service deployments.

---

# 🚀 Live Demo

### 🌐 Frontend
https://secure-ecommerce-frontend.vercel.app

### ⚙️ Spring Boot Backend
https://secure-ecommerce-backend-abhishekmtr-g5e5e4dnauargaft.centralindia-01.azurewebsites.net

### 🤖 AI Fraud Detection Service
https://secure-ecommerce-ai-fkfjaucahddsa5a8.centralindia-01.azurewebsites.net

### 📄 Swagger API Documentation
https://secure-ecommerce-backend-abhishekmtr-g5e5e4dnauargaft.centralindia-01.azurewebsites.net/swagger-ui/index.html

---

# 📸 Project Screenshots

> *(Add screenshots here)*

- Home Page
- Login
- Registration
- Products
- Cart
- Checkout
- OTP Verification
- Biometric Verification
- Orders
- Swagger Documentation

---

# ✨ Features

## 👤 User Features

- User Registration
- Secure Login using JWT
- Browse Products
- Product Search
- Product Sorting
- Pagination
- Add to Cart
- Checkout
- View Orders

---

## 🔐 Security Features

- JWT Authentication
- Role-Based Authorization
- Password Encryption
- Protected APIs
- Global Exception Handling
- DTO Validation

---

## 🤖 AI Security

- AI Fraud Detection
- Adaptive Authentication
- Risk Classification
    - LOW Risk
    - MEDIUM Risk
    - HIGH Risk
- OTP Verification
- Biometric Verification

---

## ☁ Cloud Features

- Cloud Hosted Frontend
- Cloud Hosted Backend
- Cloud Hosted AI Service
- Managed PostgreSQL Database
- CI/CD using GitHub Actions

---

# 🛠 Technology Stack

| Layer | Technology |
|--------|------------|
| Frontend | React, Tailwind CSS, Axios |
| Backend | Java, Spring Boot, Spring Security |
| ORM | Spring Data JPA, Hibernate |
| Authentication | JWT |
| Database | Neon PostgreSQL |
| AI Service | Python Flask |
| Cloud Backend | Azure App Service |
| Cloud Frontend | Vercel |
| CI/CD | GitHub Actions |
| Build Tool | Maven |
| API Testing | Postman |
| Documentation | Swagger OpenAPI |

---

# 🏗 High-Level System Architecture

```text
                    User
                      │
                      ▼
           React Frontend (Vercel)
                      │
                 HTTPS REST APIs
                      │
                      ▼
      Spring Boot Backend (Azure App Service)
            │                       │
            │                       │
            ▼                       ▼
Neon PostgreSQL            Flask AI Service
 (Cloud Database)        (Azure App Service)
            │                       │
            └───────────┬───────────┘
                        │
                        ▼
          Adaptive Authentication
       (LOW / MEDIUM / HIGH Risk)
                        │
                        ▼
                 Response to User
```

---

# 🌍 Deployment Architecture

```text
               GitHub Repository
                      │
          ┌───────────┴────────────┐
          │                        │
          ▼                        ▼
 GitHub Actions             Vercel Deployment
          │                        │
          ▼                        ▼
 Azure App Service         React Frontend
(Spring Boot Backend)            │
          │                      │
          ▼                      │
 Azure App Service              │
 (Flask AI Service)             │
          │                      │
          └──────────────┬───────┘
                         ▼
                 Neon PostgreSQL
```

---

# 📂 Project Repositories

## Backend Repository

This repository contains:

- Spring Boot REST APIs
- Spring Security
- JWT Authentication
- Business Logic
- Database Integration
- AI Service Integration

---

## Frontend Repository

Contains:

- React Application
- Tailwind CSS
- Axios API Integration
- Authentication UI
- Cart
- Checkout
- Orders

Repository:
(https://github.com/Abhishekmt16/ecommerce-frontend)

---

## AI Microservice Repository

Contains:

- Python Flask
- Fraud Detection APIs
- OTP Verification API
- Biometric Verification API

Repository: https://github.com/Abhishekmt16/ai-biometric-service

# 🔄 End-to-End Request Flow

The application follows a layered architecture with separate frontend, backend, AI microservice, and cloud database deployments.

```
                User
                  │
                  ▼
      React Frontend (Vercel)
                  │
          Axios REST Request
                  │
                  ▼
      Spring Boot Backend (Azure)
                  │
        Spring Security Filter
                  │
          JWT Authentication
                  │
                  ▼
            Controller Layer
                  │
                  ▼
             Service Layer
          ┌────────┴────────┐
          ▼                 ▼
 Repository Layer     AI Microservice
          │                 │
          ▼                 ▼
 Neon PostgreSQL     Fraud Detection
          │                 │
          └────────┬────────┘
                   ▼
          Adaptive Authentication
                   ▼
          Response to Frontend
```

---

# 🔐 Authentication Flow

The application uses **JWT (JSON Web Token)** for stateless authentication.

## Login Process

```
User Login
      │
      ▼
Spring Boot Backend
      │
Validate Credentials
      │
Generate JWT Token
      │
Return Access Token
      │
Frontend stores JWT
      │
JWT sent with every request
```

Protected endpoints require a valid JWT in the Authorization header.

```
Authorization: Bearer <JWT_TOKEN>
```

Role-based authorization is implemented for admin-specific APIs.

---

# 🤖 AI Fraud Detection Flow

During checkout, the backend communicates with a separate Flask microservice to determine the transaction risk.

```
Checkout
     │
     ▼
Spring Boot
     │
POST /fraud/check
     │
     ▼
Flask AI Service
     │
Analyze Transaction
     │
Return Risk Level
     │
LOW / MEDIUM / HIGH
     │
     ▼
Spring Boot
```

The AI service currently classifies transactions into three categories based on predefined thresholds and is designed to be extended with machine learning models in the future.

---

# 🔒 Adaptive Authentication

The authentication mechanism changes dynamically based on the fraud risk.

```
LOW Risk
    │
    ▼
Order Placed Directly

------------------------

MEDIUM Risk
    │
    ▼
Generate OTP
    │
Send Email
    │
Verify OTP
    │
Place Order

------------------------

HIGH Risk
    │
    ▼
Biometric Verification
    │
Verification Success
    │
Place Order
```

This adaptive approach enhances transaction security while maintaining a smooth user experience for low-risk purchases.

---

# 📧 OTP Verification

For medium-risk transactions:

1. Spring Boot generates an OTP.
2. OTP is sent using Java Mail.
3. User submits the received OTP.
4. Backend validates the OTP.
5. Order is completed upon successful verification.

---

# 👤 Biometric Verification

For high-risk transactions:

1. Spring Boot calls the Flask microservice.
2. User is redirected for biometric verification.
3. Verification status is returned to the backend.
4. Successful verification allows the transaction to proceed.

> **Note:** The current implementation simulates biometric verification and can be extended using OpenCV or Face Recognition libraries.

---

# 🗄 Database Design

The application uses **Neon PostgreSQL** as the cloud-hosted relational database.

Main tables:

- users
- products
- cart_items
- orders
- transactions

Spring Data JPA and Hibernate manage all database interactions.

---

# 📁 Project Structure

```
secure-ecommerce-backend
│
├── config
│      ├── SecurityConfig
│      ├── SwaggerConfig
│
├── controller
│
├── service
│
├── repository
│
├── entity
│
├── dto
│
├── exception
│
├── security
│      ├── JwtFilter
│      ├── JwtUtil
│
├── model
│
├── util
│
└── SecureEcommerceApplication
```

The project follows a clean layered architecture separating presentation, business logic, and persistence layers.

---

# 📡 REST APIs

Major REST APIs implemented include:

### Authentication

- Register User
- Login User

### Products

- Get Products
- Pagination
- Search
- Sorting

### Cart

- Add to Cart
- Update Quantity
- Remove Item

### Orders

- Checkout
- View Orders

### Transactions

- Transaction History

### AI Service

- Fraud Detection
- OTP Verification
- Biometric Verification

---

# 📄 API Documentation

Swagger UI is available at:

```
https://secure-ecommerce-backend-abhishekmtr-g5e5e4dnauargaft.centralindia-01.azurewebsites.net/swagger-ui/index.html
```

All REST APIs can be explored and tested directly using Swagger.

---

# ⚙️ Running the Project Locally

## Prerequisites

Ensure the following software is installed:

- Java 17+
- Maven
- Node.js 20+
- PostgreSQL (optional for local database)
- Python 3.10+
- Git

---

# 📥 Clone the Repositories

## Backend

```bash
git clone https://github.com/Abhishekmt18/secure-ecommerce-backend.git
```

## Frontend

```bash
git clone https://github.com/Abhishekmt16/ecommerce-frontend.git
```

## AI Service

```bash
git clone https://github.com/Abhishekmt16/ai-biometric-service.git
```

---

# 🚀 Backend Setup

Navigate to the backend project.

```bash
cd secure-ecommerce-backend
```

Configure your database connection inside:

```
src/main/resources/application.properties
```

Run the application:

```bash
./mvnw spring-boot:run
```

Backend runs on:

```
http://localhost:8080
```

---

# 🌐 Frontend Setup

Navigate to the frontend project.

```bash
cd ecommerce-frontend
```

Install dependencies:

```bash
npm install
```

Create a `.env` file:

```env
VITE_API_URL=http://localhost:8080
```

Run:

```bash
npm run dev
```

Frontend runs on:

```
http://localhost:5173
```

---

# 🤖 AI Service Setup

Navigate to the AI project.

```bash
cd ai-biometric-service
```

Install dependencies:

```bash
pip install -r requirements.txt
```

Run:

```bash
python app.py
```

AI Service runs on:

```
http://localhost:5000
```

---

# ☁ Cloud Deployment

The application is fully deployed using modern cloud services.

| Component | Platform |
|-----------|----------|
| Frontend | Vercel |
| Backend | Azure App Service |
| AI Service | Azure App Service |
| Database | Neon PostgreSQL |
| CI/CD | GitHub Actions |

---

# 🔄 CI/CD Pipeline

The project uses automated deployments.

## Frontend

```
Git Push
     │
     ▼
GitHub Repository
     │
     ▼
Vercel Build
     │
     ▼
Production Deployment
```

---

## Backend

```
Git Push
     │
     ▼
GitHub
     │
     ▼
GitHub Actions
     │
     ▼
Maven Build
     │
     ▼
Azure App Service
```

---

## AI Service

```
Git Push
     │
     ▼
GitHub
     │
     ▼
GitHub Actions
     │
     ▼
Python Build
     │
     ▼
Azure App Service
```

This workflow ensures that every push to the `main` branch automatically deploys the latest version without manual intervention.

---

# 🔐 Security Highlights

- JWT Authentication
- Role-Based Authorization
- Password Encryption
- Protected REST APIs
- Global Exception Handling
- DTO Validation
- Adaptive Authentication
- Fraud Detection
- OTP Verification
- Biometric Verification

---

# 📈 Future Enhancements

The following improvements are planned:

- Integrate a real Machine Learning fraud detection model (Isolation Forest / Random Forest)
- Implement real biometric authentication using OpenCV and Face Recognition
- Integrate Razorpay or Stripe payment gateway (Test Mode)
- Store refresh tokens using HttpOnly Cookies
- Add Docker support for backend and AI services
- Introduce Redis caching and session management
- Add rate limiting to prevent brute-force attacks
- Implement centralized logging and monitoring
- Add unit and integration tests with improved coverage

---

# 🎯 Learning Outcomes

This project helped strengthen practical knowledge in:

- Java & Spring Boot
- REST API Development
- Spring Security
- JWT Authentication
- Spring Data JPA & Hibernate
- React Integration
- Microservice Communication
- Cloud Deployment
- CI/CD Pipelines
- PostgreSQL
- AI Service Integration
- Layered Architecture
- Exception Handling
- API Documentation

---

# 👨‍💻 Author

**Abhishek M T**

Backend Developer | Java | Spring Boot | React | PostgreSQL

GitHub:
https://github.com/Abhishekmt18


Portfolio:
[https://abhishekmtr16-portfolio.vercel.app/]

---

# ⭐ Support

If you found this project useful, consider giving the repository a ⭐.

Feedback and suggestions are always welcome.

---

## Thank You!

Thank you for checking out this project. Feel free to explore the source code, test the live application, and share your feedback.

(Add AI Repository Link)

---
