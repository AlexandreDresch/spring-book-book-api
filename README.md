# Spring Boot Book Management API

This is a sample Spring Boot application that provides a RESTful API for managing book records.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- Your favorite IDE (IntelliJ IDEA, Eclipse, etc.)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/AlexandreDresch/spring-book-book-api.git
   cd spring-boot-book-api
   ```
2. Build the project:
    ```bash
   mvn clean install
    ```

3. Run the application:
    ```bash
   mvn spring-boot:run
    ```

### Usage

#### Endpoints
The API provides the following endpoints:

- POST /books: Create a new book record.
- GET /books: Get a list of all books.
- GET /books/{id}: Get details about a specific book.
- PUT /books/{id}: Update details of a specific book.
- DELETE /books/{id}: Delete a specific book.

##### Example Requests

- Create a new book:
```http
POST /books
Content-Type: application/json

{
    "name": "Clean Code: A Handbook of Agile Software Craftsmanshipe",
    "pages": 431
}
```

- Get all books:
```http
GET /books
```

- Get details about a specific book:
```http
GET /books/{id}
```

- Update details of a specific book:
```http
PUT /books/{id}
Content-Type: application/json

{
    "name": "Clean Code: A Handbook of Agile Software Craftsmanshipe",
    "pages": 431
}
```

- Delete a specific book:
```http
DELETE /books/{id}
```
