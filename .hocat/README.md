# HoCAT Project Documentation

[中文](README.zh-CN.md)

HoCAT (Hands-on Clean Architecture Template) is a production-ready clean architecture template designed to serve as a foundational codebase template for projects.

## Features

- Functionality
  - Based on best-practice Clean Architecture / Hexagonal Architecture / Ports and Adapters Architecture.
  - Production-ready code templates.
  - Comprehensive build tooling integration.
  - Built-in data validation (based on Spring Bean Validation).
  - Built-in Docker-based database, no additional startup required (based on Spring Boot Docker Compose Support).
- Automated Testing
  - Supports test-driven development. Maximizes efficiency by completing development without leaving the IDE.
  - Each module has matching test strategies.
  - Contract-based interface automated testing (based on Spring Cloud Contract Verifier).
  - Contract-based third-party system call automated testing (based on Spring Cloud Contract Stub Runner).
  - Provides Mock Server for interface consumer automated testing (based on Spring Cloud Contract Stub Runner).
- Other
  - Convention over configuration. Follows default configurations wherever possible, avoids unnecessary modifications to minimize knowledge burden.
  - Uses Gradle's multi-project build approach to constrain inter-module dependencies.

## Initialization

The project was initially created using Spring Initializr. Parameters: `https://start.spring.io/#!type=gradle-project-kotlin&language=java&platformVersion=3.3.4&packaging=jar&jvmVersion=21&groupId=com.example&artifactId=demo&name=demo&description=Demo%20project%20for%20Spring%20Boot&packageName=com.example.demo&dependencies=lombok,validation,web,cloud-contract-verifier,data-jpa,flyway,mysql,cloud-contract-stub-runner,security,actuator`.

## Gradle Usage Guide

- build.gradle.kts file in the root project

  Uses Gradle's [recommended approach](https://docs.gradle.org/current/userguide/gradle_directories.html), without placing build.gradle.kts in the root project.

  > Some builds may contain a build.gradle(.kts) file in the root project but this is NOT recommended.
  > - [Part 1: Initializing the Project](https://docs.gradle.org/current/userguide/part1_gradle_init.html)

- [Do not use cross-project configuration](https://docs.gradle.org/current/userguide/sharing_build_logic_between_subprojects.html#sec:convention_plugins_vs_cross_configuration)

  > Avoid using subprojects {} and allprojects {}.
  > With cross-configuration, build logic can be injected into a subproject which is not obvious when looking at its build script.

- Uses [Dependency Management Plugin](https://docs.spring.io/dependency-management-plugin/docs/current/reference/html/) to manage dependencies.

  Reference: [Spring Boot/Gradle Plugin](https://docs.spring.io/spring-boot/gradle-plugin/managing-dependencies.html).

- Manual declaration of test framework implementation dependencies

  Adds `junit-platform-launcher` dependency declaration, reference: [Relying on automatic test framework implementation dependencies](https://docs.gradle.org/8.10/userguide/upgrading_version_8.html#test_framework_implementation_dependencies).

- Uses [convention plugins](https://docs.gradle.org/current/userguide/sharing_build_logic_between_subprojects.html) to manage repetitive build scripts.
- Uses [Lazy APIs](https://docs.gradle.org/current/userguide/task_configuration_avoidance.html#sec:old_vs_new_configuration_api_overview) to optimize builds.

## Core Module Description

### domain

Domain layer, containing domain models and domain services.

- Testing Strategy
  - Uses unit testing. Since domain doesn't depend on other modules, it can be easily unit tested.

### application

Application layer, containing application services.

- Testing Strategy
  - Mostly uses unit testing. Application depends on domain, and in most cases can be tested together with domain without using test doubles; at the same time, application defines output ports, which require test doubles during testing.
  - Small portion uses integration testing with framework. Application uses some framework features like validation and transactions, and if these features need to be tested, testing must be done in Spring Boot Application environment.

## Adapter Module Description

Adapter layer, containing Web adapters, persistence adapters, client adapters, etc.

### adapter:web

Uses Spring MVC web components. For error response models, uses Spring's built-in RFC 9457 (Problem Details for HTTP APIs) specification.

- Testing Strategy
  - Controllers use integration testing, scope includes the module itself and Spring MVC framework. Web primarily provides external HTTP interfaces, so the main testing focus is whether the external interfaces meet expectations, therefore contract testing is used. Since web needs to use Spring MVC framework capabilities, testing must be done in Spring Boot Application environment. Its dependencies on Adapters use test doubles.
  - Adapter testing uses unit testing.

#### Contract Consumer (e.g., Frontend) Automated Testing Support

Uses contract definitions and contract testing. Contracts can serve as tests for backend interfaces and also as Stub Server for frontend testing.

Run Stub Runner Server by executing `scripts/run-stub-runner-server`.

- Uses adapter/web contracts.
- Requires Java 1.8 or 11, does not support Java 17 or higher.
- Default port is 16580.

Uses [Stub Runner Server Fat Jar](https://docs.spring.io/spring-cloud-contract/reference/project-features-stubrunner/stub-runner-boot.html#features-stub-runner-boot-how-fat-jar).

Also considered using [Stub Runner Server](https://docs.spring.io/spring-cloud-contract/reference/project-features-stubrunner/stub-runner-boot.html#features-stub-runner-boot-server), as it would eliminate the need for scripts and allow using the latest version. However, the repositoryRoot configuration doesn't support relative paths, so Stub Runner Server Fat Jar approach was chosen.

The highest usable version of `spring-cloud-contract-stub-runner-boot` is 2.2.8.RELEASE, which uses Groovy version 2.5.10, supporting Java 1.8 and 11, but not 17 or higher.

The chosen default port is 16580, which is the first Leyland number exceeding 10000.

### adapter:web-openapi

An alternative implementation of Web adapter. Uses OpenAPI Generator to generate Web interfaces based on OpenAPI specifications. Only requires writing adapter code to connect generated interfaces with application layer use case interfaces. For error response models, uses Spring's built-in RFC 9457 (Problem Details for HTTP APIs) specification.

- Testing Strategy
  - Auto-generated code doesn't need testing.
  - Adapters use unit testing.

### adapter:persistence

Persistence adapter using Spring Data JPA. Integrates Flyway.

- Testing Strategy
  - Repository uses integration testing, scope includes the module itself, persistence framework, and database. Since persistence needs to use framework capabilities, testing must be done in Spring Boot Application environment. Its dependent database is defined using Docker Compose and starts with tests.
  - Adapter uses unit testing.

### adapter:persistence-jdbc

An alternative implementation of persistence adapter. Uses Spring Data JDBC persistence adapter, supporting direct use of domain models as persistence entities. No conversion needed, so only one layer. Integrates Flyway.

- Testing Strategy
  - Uses integration testing, scope includes the module itself, persistence framework, and database. Since persistence needs to use framework capabilities, testing must be done in Spring Boot Application environment. Its dependent database is defined using Docker Compose and starts with tests.

### adapter:client

Client adapter using Spring's recommended RestClient.

- Testing Strategy
  - Client uses integration testing, scope includes the module itself and third-party system interface contracts. Since Stub Runner is used to run third-party system interface contracts, testing is done in Spring Boot Application environment. Its dependent third-party systems are defined using contracts, started via Stub Runner with tests.
  - Adapter uses unit testing.

## configuration

Configuration layer, containing Application and Bean configurations.

- Testing Strategy
  - Belongs to the top layer of the test pyramid, scope includes all modules and frameworks. Testing is done in Spring Boot Application environment. For its dependent database, uses Docker Compose definition, starts with tests. Its dependent third-party systems are defined using contracts, started via Stub Runner with tests.

## Automated Testing Database

Uses [Docker Compose Support](https://docs.spring.io/spring-boot/reference/features/dev-services.html) to automatically start MySQL container during testing. Requires Docker and Docker Compose installation.

Both adapter:persistence and configuration automated tests use the compose.yaml file in the adapter/persistence directory.

For `gradle bootRun`, uses the compose.yaml file in the configuration directory.

## References

- [Clean Architecture](https://book.douban.com/subject/30333919/)
- [Get Your Hands Dirty on Clean Architecture](https://reflectoring.io/book/)
- [Hexagonal Architecture](https://alistair.cockburn.us/hexagonal-architecture/)
- [Clean Architecture Implementation Guide](https://www.zhihu.com/column/c_1839245367729864704)