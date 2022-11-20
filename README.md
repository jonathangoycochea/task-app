# Description
My first Java project using Spring Boot implementing a REST API. 

It is a Task Management APP that works with CRUD operations. 

My goal was to implement good programming practices from the beginning, either by having an organized project structure, implementing tests, or keeping the code as clean as possible, in order to continue developing and honing my skills for future projects following good standards.


## Built with:

* Java 17

* Spring Boot 2.7.5

* Lombok to reduce boilerplate code

* Swagger 3.0 to generate the API Documentation

* JPA Repository + H2 in-memory Database 

* Maven to manage dependencies

* JUnit 5 + Mockito for testing


## REST API Methods

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



