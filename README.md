# Description
My first Java project using Spring Boot implementing a REST API. 

It is a Task Management APP that works with CRUD operations. 

My goal was to implement good programming practices from the beginning, that is, having an organized project structure, implementing tests or keeping the code as clean as possible, to continue developing and honing my skills for future projects following good standards.

# Built with:

* Java 17

* Spring Boot 2.7.5

* Lombok to reduce boilerplate code

* Swagger 3.0 to generate the API Documentation

* JPA Repository + H2 in-memory Database 

* Maven to manage dependencies

* JUnit 5 + Mockito for testing


# REST API Methods

| Method | URL | Description |
|--------|-----|-------------|
|GET     |/tasks|Get all tasks|
|GET     |/tasks/{id}|Get task|
|GET     |/tasks/status/{status}|Get all tasks by status|
|POST    |/tasks|Create task|
|PATCH   |/tasks/update-as-completed/{id}/|Update task as completed|
|PATCH   |/tasks/update/{id}/status-as/{status}/|Update task status|
|DELETE  |/tasks/|Delete all tasks|
|DELETE  |/tasks/{id}|Delete task|


# Setup

## Execution
To run the application you can do it in one of the following ways: 
* Import the project into an IDE and run the TaskappApplication class
* Execute the .jar file "taskapp-0.0.1-SNAPSHOT" located inside the /taskapp/target folder

## Usage

* Swagger UI to test the endpoints http://localhost:8080/swagger-ui/
* H2 Console to access the Database http://localhost:8080/h2-console/

## H2 Console Credentials

* Driver Class: org.h2.Driver
* JDBC URL: jdbc:h2:mem:taskapp 
* User Name: user
* Password: password
