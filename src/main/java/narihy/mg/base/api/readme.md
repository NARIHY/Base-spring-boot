# Base - Spring Boot REST API

## Overview

This project is a **Spring Boot** based REST API that demonstrates the integration of **Spring Data JPA**, **Spring Security**, and **Flyway** for database migrations. It supports multiple databases (H2, MySQL, and MariaDB) and includes various Spring Boot starters for web services, data persistence, and security.

The project also integrates **Lombok** to minimize boilerplate code, and it provides endpoints to manage resources through a RESTful interface.

---

## Table of Contents

- [Project Description](#project-description)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
    - [GET /api/resources](#get-apiresources)
    - [POST /api/resources](#post-apiresources)
    - [PUT /api/resources/{id}](#put-apiresourcesid)
    - [DELETE /api/resources/{id}](#delete-apiresourcesid)
- [Database Configuration](#database-configuration)
- [Testing the API](#testing-the-api)
- [Contributing](#contributing)
- [License](#license)

---

## Project Description

This Spring Boot project provides a REST API for managing **resources**. It demonstrates how to integrate **Spring Data JPA**, **Spring Security**, and **Flyway** in a Spring Boot application. You can perform CRUD (Create, Read, Update, Delete) operations on resources, and the project is ready for deployment with support for MySQL, MariaDB, and H2 databases.

---

## Technologies Used

- **Java 21**: The programming language used to build the backend.
- **Spring Boot 3.3.5**: A framework for building production-ready web applications with minimal configuration.
- **Spring Data JPA**: For database interaction and ORM (Object-Relational Mapping).
- **Spring Security**: Provides authentication and authorization features.
- **Flyway**: For database migrations.
- **Lombok**: For reducing boilerplate code.
- **H2, MySQL, MariaDB**: For database support.
- **Spring Boot Starters**: Includes dependencies for building REST APIs, handling data, and creating web services.

---

## Setup and Installation

### Prerequisites

Before setting up the project, make sure you have the following installed on your system:

- **Java 21 or higher**: Ensure Java is installed by running `java -version`.
- **Maven or Gradle**: Use Maven to manage the project dependencies.
- **IDE**: IntelliJ IDEA, Eclipse, or Visual Studio Code for development.

### Cloning the Repository

First, clone the repository to your local machine:

```bash
git clone https://github.com/NARIHY/Base-spring-boot.git
cd Base-spring-boot
```

### Installing Dependencies

If you're using Maven, run the following command to install all dependencies:

```bash
mvn clean install
```

### Configure Database Connection

This project supports multiple databases. You can configure the connection in `src/main/resources/application.properties` or `application.yml`.

For example, if you want to use MySQL, set the following properties:

```properties
# MySQL Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/yourdb
spring.datasource.username=yourusername
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

If you want to use H2 (for development purposes), you can use the following configuration:

```properties
# H2 In-Memory Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```

---

## Running the Application

To run the application, use the following Maven command:

```bash
mvn spring-boot:run
```

This will start the application on port `8080` by default. You can access the API via:

```
http://localhost:8080
```

Alternatively, you can package the application as a JAR and run it:

```bash
mvn clean package
java -jar target/base-0.0.1-SNAPSHOT.jar
```

---

## API Endpoints

The following are the available API endpoints to interact with the resources.

### `GET /api/resources`

Retrieve a list of all resources.

**Response**:
- Status: `200 OK`
- Body: A JSON array of resources.

**Example Response**:
```json
[
  {
    "id": 1,
    "name": "Resource 1",
    "description": "This is the first resource"
  },
  {
    "id": 2,
    "name": "Resource 2",
    "description": "This is the second resource"
  }
]
```

### `POST /api/resources`

Create a new resource.

**Request Body**:
```json
{
  "name": "New Resource",
  "description": "Description of the new resource"
}
```

**Response**:
- Status: `201 Created`
- Body: The newly created resource, including the `id`.

**Example Response**:
```json
{
  "id": 3,
  "name": "New Resource",
  "description": "Description of the new resource"
}
```

### `PUT /api/resources/{id}`

Update an existing resource by `id`.

**Request Body**:
```json
{
  "name": "Updated Resource",
  "description": "Updated description of the resource"
}
```

**Response**:
- Status: `200 OK`
- Body: The updated resource.

### `DELETE /api/resources/{id}`

Delete a resource by `id`.

**Response**:
- Status: `204 No Content`

---

## Database Configuration

By default, the project supports **H2**, **MySQL**, and **MariaDB** databases.

- **Flyway Migrations**: The project uses **Flyway** to handle database migrations automatically. When you run the application, Flyway will check the database for any pending migrations and apply them.

- **Database Configuration**: You can choose the database you want to use by modifying the `application.properties` or `application.yml` file. Make sure you configure the correct JDBC URL and credentials for your database.

---

## Testing the API

You can use **Postman**, **Insomnia**, or **curl** to test the API.

### Example with `curl`:

- **GET Request**:
  ```bash
  curl -X GET http://localhost:8080/api/resources
  ```

- **POST Request**:
  ```bash
  curl -X POST http://localhost:8080/api/resources -H "Content-Type: application/json" -d '{"name": "Test Resource", "description": "A test resource"}'
  ```

---

## Contributing

We welcome contributions to this project! To contribute:

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Commit your changes (`git commit -am 'Add new feature'`).
4. Push to the branch (`git push origin feature-branch`).
5. Create a new Pull Request.

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

