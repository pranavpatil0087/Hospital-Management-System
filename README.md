# Hospital Management System

A Spring Boot REST API for managing Patients, Doctors, Appointments, and Insurance records.

## Features

- Patient Management
- Doctor Management
- Appointment Scheduling
- Insurance Management
- JWT Authentication
- Swagger API Documentation
- DTO Pattern
- Input Validation
- Global Exception Handling
- Pagination
- Custom JPQL Queries

## Tech Stack

- Java 21
- Spring Boot 3
- Spring Security
- JWT
- Spring Data JPA
- PostgreSQL
- Swagger OpenAPI
- Lombok
- Maven

## API Endpoints

### Authentication
POST /api/auth/login

### Patients
GET /api/patients
GET /api/patients/{id}
POST /api/patients
PUT /api/patients/{id}
DELETE /api/patients/{id}

### Doctors
GET /api/doctors
GET /api/doctors/{id}
POST /api/doctors
DELETE /api/doctors/{id}

### Appointments
GET /api/appointments
GET /api/appointments/{id}
POST /api/appointments
DELETE /api/appointments/{id}

## Run Project

1. Create PostgreSQL database:
   hospitalDB

2. Configure application.properties

3. Run:
   HospitalManagementSystemApplication

## Swagger

http://localhost:8081/swagger-ui/index.html
