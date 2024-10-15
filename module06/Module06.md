
# Java Module 06: JUnit/Mockito

## Overview
This module focuses on the basics of module and integration testing in Java using JUnit 5 and Mockito.

## General Rules
- Use the latest LTS version of Java
- Use both JVM and GraalVM to run your code
- Follow Oracle's code formatting standards
- Use "System.out" for output
- All tests must be launchable using `mvn clean compile test`

## Dependencies
- maven-surefire-plugin
- junit-jupiter-engine
- junit-jupiter-params
- junit-jupiter-api
- spring-jdbc
- hsqldb
- mockito-core

## Project Structure
```
Tests
└── src
    ├── main
    │   ├── java
    │   │   └── fr
    │   │       └── 42
    │   │           ├── exceptions
    │   │           │   └── AlreadyAuthenticatedException.java
    │   │           ├── numbers
    │   │           │   └── NumberWorker.java
    │   │           ├── models
    │   │           │   ├── Product.java
    │   │           │   └── User.java
    │   │           ├── services
    │   │           │   └── UsersServiceImpl.java
    │   │           └── repositories
    │   │               ├── ProductsRepository.java
    │   │               ├── ProductsRepositoryJdbcImpl.java
    │   │               └── UsersRepository.java
    │   └── resources
    └── test
        ├── java
        │   └── fr
        │       └── 42
        │           ├── services
        │           │   └── UsersServiceImplTest.java
        │           ├── numbers
        │           │   └── NumberWorkerTest.java
        │           └── repositories
        │               ├── EmbeddedDataSourceTest.java
        │               └── ProductsRepositoryJdbcImplTest.java
        └── resources
            ├── data.csv
            ├── schema.sql
            └── data.sql
```

## Exercises

### Exercise 00: First Tests
- Implement `NumberWorker` class with `isPrime` and `digitsSum` methods
- Create `NumberWorkerTest` class with parameterized tests

### Exercise 01: Embedded Database
- Implement `DataSource` creation for HSQL DBMS
- Prepare `schema.sql` and `data.sql` files
- Create `EmbeddedDataSourceTest` class

### Exercise 02: Test for JDBC Repository
- Implement `ProductsRepository` interface and `ProductsRepositoryJdbcImpl` class
- Create `ProductsRepositoryJdbcImplTest` class for testing repository functionality

### Exercise 03: Test for Service
- Implement `UsersServiceImpl` class with authentication logic
- Create `UsersRepository` interface
- Develop module tests for `UsersServiceImpl` using Mockito

## Testing Guidelines
- Ensure 100% code coverage for the day's code (minimum 80% for large projects)
- Write small, fast-executing test methods
- Keep test methods isolated and free of side effects
- Use appropriate naming conventions for test methods
- Consider various scenarios in your tests