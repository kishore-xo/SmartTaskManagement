Here is a complete `README.md` for your `SmartTaskManagement` Spring Boot project. It covers project overview, setup, usage, API documentation, and more.

```markdown
# SmartTaskManagement

A Spring Boot-based task management system supporting user authentication, team management, and task assignment.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Building & Running](#building--running)
- [API Documentation](#api-documentation)
- [Project Structure](#project-structure)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## Overview

SmartTaskManagement is a RESTful web application for managing tasks, users, and teams. It provides secure authentication, role-based access, and email notifications.

## Features

- User registration and authentication (JWT-based)
- Task CRUD operations
- Team creation and management
- Assign tasks to users/teams
- Email notifications
- Global exception handling

## Prerequisites

- Java 17+
- Maven 3.8+
- (Optional) Docker

## Getting Started

1. **Clone the repository:**
   ```sh
   git clone https://github.com/kishore-xo/SmartTaskManagement.git
   cd SmartTaskManagement
   ```

2. **Configure application properties:**
    - Edit `src/main/resources/application.properties` for DB and email settings.

## Building & Running

**Build the project:**
```sh
mvn clean install
```

**Run the application:**
```sh
mvn spring-boot:run
```
or
```sh
java -jar target/SmartTaskManagement-0.0.1-SNAPSHOT.jar
```

## API Documentation

### Authentication

All protected endpoints require a JWT token in the `Authorization` header:

```
Authorization: Bearer <token>
```

### Endpoints

#### 1. User Registration

**POST** `/api/auth/register`

**Request Body:**
```json
{
  "username": "john",
  "email": "john@example.com",
  "password": "password123"
}
```

**Response:**
```json
{
  "message": "User registered successfully"
}
```

#### 2. User Login

**POST** `/api/auth/login`

**Request Body:**
```json
{
  "username": "john",
  "password": "password123"
}
```

**Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

#### 3. Get All Tasks

**GET** `/api/tasks`

**Headers:**  
`Authorization: Bearer <token>`

**Response:**
```json
[
  {
    "id": 1,
    "title": "Design UI",
    "description": "Create wireframes",
    "assignedTo": "john",
    "status": "IN_PROGRESS"
  }
]
```

#### 4. Create Task

**POST** `/api/tasks`

**Headers:**  
`Authorization: Bearer <token>`

**Request Body:**
```json
{
  "title": "Implement API",
  "description": "Develop REST endpoints",
  "assignedTo": "jane"
}
```

**Response:**
```json
{
  "id": 2,
  "title": "Implement API",
  "description": "Develop REST endpoints",
  "assignedTo": "jane",
  "status": "PENDING"
}
```

#### 5. Update Task

**PUT** `/api/tasks/{id}`

**Headers:**  
`Authorization: Bearer <token>`

**Request Body:**
```json
{
  "title": "Implement API",
  "description": "Update endpoints",
  "status": "COMPLETED"
}
```

**Response:**
```json
{
  "id": 2,
  "title": "Implement API",
  "description": "Update endpoints",
  "assignedTo": "jane",
  "status": "COMPLETED"
}
```

#### 6. Delete Task

**DELETE** `/api/tasks/{id}`

**Headers:**  
`Authorization: Bearer <token>`

**Response:**
```json
{
  "message": "Task deleted successfully"
}
```

#### 7. Get All Teams

**GET** `/api/teams`

**Headers:**  
`Authorization: Bearer <token>`

**Response:**
```json
[
  {
    "id": 1,
    "name": "Development",
    "members": ["john", "jane"]
  }
]
```

#### 8. Create Team

**POST** `/api/teams`

**Headers:**  
`Authorization: Bearer <token>`

**Request Body:**
```json
{
  "name": "QA",
  "members": ["alice", "bob"]
}
```

**Response:**
```json
{
  "id": 2,
  "name": "QA",
  "members": ["alice", "bob"]
}
```

#### 9. Get All Users

**GET** `/api/users`

**Headers:**  
`Authorization: Bearer <token>`

**Response:**
```json
[
  {
    "id": 1,
    "username": "john",
    "email": "john@example.com"
  }
]
```

_Refer to controller classes for more endpoints and details._

## Project Structure

```
SmartTaskManagement/
 ├── mvnw, mvnw.cmd
 ├── pom.xml
 └── src/
     └── main/
         ├── java/
         │   └── com/example/SmartTaskManagement/
         │       ├── SmartTaskManagementApplication.java
         │       ├── configure/
         │       ├── controller/
         │       ├── dto/
         │       ├── exception/
         │       ├── filter/
         │       ├── model/
         │       ├── repo/
         │       ├── service/
         │       └── util/
         └── resources/
             └── application.properties
```

## Testing

Run all tests with:
```sh
mvn test
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Open a pull request

## License

This project is licensed under the MIT License.
```

To insert this file, create a new file named `README.md` in the root of your project and paste the above content.