# 🏥 Hospital Management System

A Spring Boot REST API for managing Patients, Doctors, Appointments, and Insurance records with JWT Authentication and PostgreSQL database integration.

## 🚀 Live Demo

API Base URL:
https://hospital-management-system-xb3u.onrender.com

Swagger UI:
https://hospital-management-system-xb3u.onrender.com/swagger-ui/index.html

## ✨ Features

### Authentication & Security
- JWT Authentication
- Spring Security Integration
- Protected API Endpoints
- Role-based API Access

### Patient Management
- Create Patient
- Get Patient by ID
- Get All Patients
- Update Patient
- Delete Patient
- Pagination Support
- Blood Group Management

### Doctor Management
- Create Doctor
- Get Doctor by ID
- Get All Doctors
- Delete Doctor
- Specialization Management

### Appointment Management
- Create Appointment
- Get Appointment by ID
- Get All Appointments
- Delete Appointment
- Doctor Availability Validation
- Appointment Conflict Detection

### Insurance Management
- Assign Insurance to Patient
- Remove Insurance from Patient
- Get Insurance by ID
- Get All Insurance Records

### Additional Features
- DTO Pattern
- Global Exception Handling
- Custom JPQL Queries
- Swagger/OpenAPI Documentation
- Docker Support
- PostgreSQL Integration

---

## 🛠 Tech Stack

### Backend
- Java 21
- Spring Boot 3.5
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate

### Database
- PostgreSQL

### Documentation
- Swagger OpenAPI

### Build Tools
- Maven
- Docker

### Utilities
- Lombok

---

## 📊 Entity Relationships

### Patient ↔ Insurance
- One-to-One Relationship

### Patient ↔ Appointment
- One-to-Many Relationship

### Doctor ↔ Appointment
- One-to-Many Relationship

---

## 🔐 Authentication API

### Login

```http
POST /api/auth/login
```

Returns JWT Token for accessing secured endpoints.

---

## 👨‍⚕️ Doctor APIs

```http
GET    /api/doctors
GET    /api/doctors/{id}
POST   /api/doctors
DELETE /api/doctors/{id}
```

---

## 🧑‍🦽 Patient APIs

```http
GET    /api/patients
GET    /api/patients/{id}
POST   /api/patients
PUT    /api/patients/{id}
DELETE /api/patients/{id}
```

---

## 📅 Appointment APIs

```http
GET    /api/appointments
GET    /api/appointments/{id}
POST   /api/appointments
DELETE /api/appointments/{id}
```

---

## 🛡 Insurance APIs

```http
GET    /api/insurances
GET    /api/insurances/{id}
POST   /api/insurances/{patientId}
DELETE /api/insurances/{patientId}
```

---

## ⚙️ Local Setup

### Clone Repository

```bash
git clone https://github.com/pranavpatil0087/Hospital-Management-System.git
```

### Create Database

```sql
CREATE DATABASE hospitalDB;
```

### Configure Database

Update:

```properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
```

### Run Application

```bash
mvn spring-boot:run
```

or run:

```java
HospitalManagementSystemApplication
```

---

## 📖 Swagger Documentation

Local:

```text
http://localhost:8081/swagger-ui/index.html
```

Production:

```text
https://hospital-management-system-xb3u.onrender.com/swagger-ui/index.html
```

---

## 🐳 Docker

Build Image:

```bash
docker build -t hospital-management-system .
```

Run Container:

```bash
docker run -p 8081:8081 hospital-management-system
```

---

## 👨‍💻 Author

Pranav Patil

Backend Developer focused on:
- Java
- Spring Boot
- REST APIs
- PostgreSQL
- JWT Authentication
- Docker
