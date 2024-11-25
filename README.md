# SOS Asistencia

SOS Asistencia is a Spring Boot application for managing orders (`pedidos`). It provides RESTful APIs to create, retrieve, update, and delete orders, as well as to filter orders by status and date range.

## Features

- Create a new order
- Retrieve an order by ID
- Retrieve orders by status
- Retrieve orders within a date range
- Update the status of an order
- Cancel an order

## Technologies Used

- Java
- Spring Boot
- Maven
- JUnit 5
- Mockito
- SQL

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- A SQL database (e.g., PostgreSQL)

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/miamartinezfe/sosAsistencia.git
   cd sosAsistencia
    ```
2. Build the project:
   ```sh
   mvn clean install
   ```
3. Run the application:
   ```sh
   mvn spring-boot:run
   ```
4. The application will be accessible at `http://localhost:8080`.
5. You can use the provided Postman collection to test the APIs at resources/postman.
