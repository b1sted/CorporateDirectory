<h3 align="center">Corporate Directory API</h3>

<p align="center">
    <strong>
    A robust REST API for employee management, built as a target for Contract Testing practice.
    </strong>
</p>
<p align="center">
    <img src="https://img.shields.io/badge/Java-21-blue?style=flat-square&logo=openjdk" alt="Java 21">
    <img src="https://img.shields.io/badge/Spring_Boot-3.5.15-6DB33F?style=flat-square&logo=spring" alt="Spring Boot">
    <a href="#license">
        <img src="https://img.shields.io/badge/License-GNU%20GPL%20v3.0-yellow?style=flat-square" alt="License" />
    </a>
</p>
<p align="center">
    <a href="#features">Features</a> •
    <a href="#tech-stack">Tech Stack</a> •
    <a href="#api-endpoints">API Endpoints</a> •
    <a href="#build-and-run">Build and Run</a> •
    <a href="#license">License</a>
</p>
<hr>

A modern, containerized backend service for managing corporate employees. Developed strictly to serve as a "target application" (a victim) for practicing API Contract Testing, automated QA tools development, and exploring the Spring Boot ecosystem.

<br>
<p align="center">
  <img src=".github/assets/swagger-demo.png" alt="Swagger UI demo" width="600"/>
</p>
<br>

## Features

- **CRUD Operations**: Full management of employee records (Create, Read, Update, Delete).
- **Data Validation**: Strict payload validation using Jakarta Constraints (e.g., proper email formatting, positive IDs, non-blank fields).
- **Centralized Error Handling**: Unified API responses for `400 Bad Request`, `404 Not Found`, and `409 Conflict` (Email uniqueness checks) via `@RestControllerAdvice`.
- **Security / Authentication**: Protected API endpoints using HTTP Basic Authentication. Unauthorized requests automatically receive a `404 Not Found` status.
- **Auto-generated Documentation**: Real-time OpenAPI (Swagger) specification generation.
- **Zero-Config Startup**: Thanks to `spring-boot-docker-compose`, the application automatically spins up and configures the PostgreSQL database without manual Docker commands.

## Tech Stack

- **Core**: Java 21, Spring Boot 3.5.15, Spring Security
- **Database**: PostgreSQL (Dockerized), Spring Data JPA, Hibernate
- **Tools**: Lombok, Maven Wrapper
- **Documentation**: Springdoc OpenAPI (Swagger 3)

## API Endpoints

### Authentication

All API endpoints require **HTTP Basic Authentication**.

To protect sensitive data and prevent resource enumeration, the system uses a secure error-masking strategy:
* **Missing or Invalid Credentials:** Requests without valid authentication or with insufficient role permissions will return an **`404 Not Found`** status instead of exposing the resource's existence via 401/403.
* **Valid Requests:** Only authenticated users with appropriate roles can access the resources.

### Accounts

Base URL: `http://localhost:8080/api/v1/accounts`

| Method   | Endpoint         | Description             | Required Role | Expected Status                                                         |
|:---------|:-----------------|:------------------------|---------------|:------------------------------------------------------------------------|
| `GET`    | `/`              | Get all accounts        | `ADMIN`       | `200 OK` / `404 Not Found`                                              |
| `GET`    | `/{id}`          | Get account by ID       | `ADMIN`       | `200 OK` / `404 Not Found`                                              |
| `POST`   | `/`              | Create a new account    | `ADMIN`       | `201 Created` / `400 Bad Request` / `404 Not Found` / `409 Conflict`    |
| `PATCH`  | `/{id}/password` | Update account password | `ADMIN`       | `204 No Content` / `400 Bad Request` / `404 Not Found`                  |
| `PATCH`  | `/{id}/role`     | Update account role     | `ADMIN`       | `204 No Content` / `400 Bad Request` / `404 Not Found` / `409 Conflict` |
| `DELETE` | `/{id}`          | Remove account by ID    | `ADMIN`       | `204 No Content` / `404 Not Found`                                      |

### Employees

Base URL: `http://localhost:8080/api/v1/employees`

| Method   | Endpoint | Description              | Required Role   | Expected Status                                                      |
|:---------|:---------|:-------------------------|-----------------|:---------------------------------------------------------------------|
| `GET`    | `/`      | Get all employees        | `ADMIN`, `USER` | `200 OK` / `404 Not Found`                                           |
| `GET`    | `/{id}`  | Get employee by ID       | `ADMIN`, `USER` | `200 OK` / `404 Not Found`                                           |
| `POST`   | `/`      | Create a new employee    | `ADMIN`         | `201 Created` / `400 Bad Request` / `404 Not Found` / `409 Conflict` |
| `PUT`    | `/{id}`  | Update existing employee | `ADMIN`         | `200 OK` / `400 Bad Request` / `404 Not Found` / `409 Conflict`      |
| `DELETE` | `/{id}`  | Remove employee by ID    | `ADMIN`         | `204 No Content` / `404 Not Found`                                   |

## Build and Run

### Prerequisites
Make sure you have **Java 21** and **Docker** installed and running on your system. 

You also need to create a `.env` file in the project root with the required environment variables.
You can copy the example file and adjust the values as needed:

```bash
cp .env.example .env
```

See `.env.example` for the full list of required variables.

### 1. Run the Application
You don't need Maven installed locally, and you **don't need to manually start the database**. The application uses Spring Boot's Docker Compose support. Just clone and run:

```bash
git clone https://github.com/b1sted/CorporateDirectory.git
cd CorporateDirectory
./mvnw spring-boot:run
```
*Docker will automatically download the PostgreSQL image, start the container, and Spring Boot will auto-configure the database connection.*

### 2. Access Documentation
Once the application is running, you can access the interactive Swagger UI or the raw OpenAPI JSON specification:

- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **Raw OpenAPI JSON**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## License

Distributed under the **GNU GPL v3.0**.

You are free to use, modify, distribute, and sell this software. However, any derivative works or modifications must be distributed under the same license, the source code must be made openly available, and you must preserve the copyright notice and license file.

Full text of the license: [LICENSE](./LICENSE).