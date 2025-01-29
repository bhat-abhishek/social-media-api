# Social Media Application

## Introduction
This is a **Java Spring Boot** application for a social media platform where users can create posts, follow other users, and view their feed.

## Features
- User authentication and authorization
- Post creation and management
- Follow/unfollow functionality
- Feed generation based on followed users
- Exception handling with global exception handler
- Database migrations with Flyway

## Prerequisites
Ensure you have the following installed:
- **Java 17+**
- **Maven** (for dependency management)
- **PostgreSQL** (or another supported database)
- **Flyway** (for database migrations)

## Installation

### 1. Clone the Repository
```sh
git clone https://github.com/your-repository/social-media-app.git
cd social-media-app
```

###. Configure the Database
- Create a PostgreSQL database.
- Update the application.properties (or application.yml) file with your database credentials:
```sh
spring.datasource.url=jdbc:postgresql://localhost:5432/social_media_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=validate
```

### 3. Run Flyway Migrations
- Flyway is used to manage database schema changes. Run the following command to apply migrations:
```sh
mvn flyway:migrate
```
Alternatively, if Flyway is installed globally, you can run:
```sh
flyway -configFiles=flyway.conf migrate
```

### 4.Build the Application
```sh
mvn spring-boot:run
```

### 5. Run the Application
```sh
mvn spring-boot:run
```
The server should now be running at:
```sh
http://localhost:8080
```
