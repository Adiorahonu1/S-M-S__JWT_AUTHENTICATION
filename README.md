# Student Management System with JWT Security

This project is a Java Spring Boot application that manages student records, providing secure, role-based access to data through a RESTful API. It uses JSON Web Tokens (JWT) for user authentication, ensuring that only authorized users can access protected endpoints.

## Features

- **JWT-based Authentication**: Secure endpoints with JWT, preventing unauthorized access.
- **Role-Based Access Control**: Allows different levels of access depending on user roles (e.g., Admin, User).
- **RESTful API**: Provides endpoints for creating, reading, updating, and deleting student records.
- **Error Handling**: Custom error responses to improve user feedback.
- **Database Support**: Connects to a relational database for data storage and retrieval.

## Technologies Used

- **Java 21**
- **Spring Boot 3**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Spring Data JPA**
- **MySQL**

## Prerequisites

- **Java 21**
- **Maven** (for dependency management)
- **MySQL** (or any other preferred relational database)

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/yourusername/student-management-system.git
cd student-management-system
