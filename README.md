# Pismo API

A Spring Boot application for managing bank accounts and financial transactions. Provides endpoints for creating accounts, performing transactions, and retrieving account details and balances.

---

## Table of Contents

- [Technologies](#technologies)
- [Getting Started](#getting-started)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [API Usage](#api-usage)
    - [Accounts](#accounts)
    - [Transactions](#transactions)
- [Error Handling](#error-handling)
- [Testing](#testing)

---

## Technologies

- Java 17
- Spring Boot
- Spring Data JPA
- H2 / MySQL (or any other relational DB)
- Lombok
- Springdoc OpenAPI (Swagger)
- JUnit 5 & Mockito

---

## Getting Started

**Clone the repository**
```bash
git clone https://github.com/varshashastri/pismo.git
cd pismo
```
## Running the application

**Running the Application**
```bash
mvn clean package
docker build -t pismo-app:latest .
docker compose up
```

## API Documentation

**API Documentation**
```
Swagger UI: http://localhost:8080/swagger-ui/index.html
```

## API Usage

**Accounts**
```
Create Account
POST /accounts
Content-Type: application/json

{
"document_number": "12345678900"
}

Response
{
"accountId": 1,
"document_number": "12345678900"
}

Get Account by ID
GET /accounts/{accountId}

Response
{
"accountId": 1,
"document_number": "12345678900"
}
```
Transactions
```
Create Transaction
POST /transactions
Content-Type: application/json

{
"account_id": 1,
"operation_type_id": 1,
"amount": 100.0
}

Response
{
"transactionId": 1,
"accountId": 1,
"operationTypeId": 1,
"amount": -100.0
}
```
Note: For operations other than PAYMENT (code 4), the amount will be stored as negative.

## Error Handling

**Global exception handling provides meaningful responses:**
```
Exception: AccountNotFoundException
HTTP Status: 404
Description: Account ID not found

Exception: OperationTypeNotFoundException
HTTP Status: 404
Description: Operation type ID not found

Exception: DuplicateDocumentNumberException
HTTP Status: 409
Description: Document number already exists

Exception: MethodArgumentNotValidException
HTTP Status: 400
Description: Validation errors on input fields

Example Validation Error
{
"documentNumber": "Document number is required"
}
```
## Testing
```
Unit tests with JUnit 5
Mocking with Mockito
```

