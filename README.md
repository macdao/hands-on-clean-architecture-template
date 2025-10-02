# HoCAT 🐾

[![Java CI with Gradle](https://github.com/macdao/hands-on-clean-architecture-template/actions/workflows/gradle.yml/badge.svg)](https://github.com/macdao/hands-on-clean-architecture-template/actions/workflows/gradle.yml)

[中文](README.zh-CN.md)

## What is HoCAT?

HoCAT (Hands-on Clean Architecture Template) is a template project based on the principles of Clean Architecture. It provides a production-ready, battle-tested, and evolvable architectural foundation for Java applications, designed to help developers build high-quality, maintainable software systems for the long term.

## Core Architecture

The core value of HoCAT lies in its clear, decoupled architecture. It is not just a theoretical showcase but a set of engineering practices ready for production use.

[![HoCAT Diagram](https://www.plantuml.com/plantuml/svg/ZPHHRjim38RVTGeUOCa10iDeQwpRmpeKAOO-1CMWs6mYL1OrYjuMjBtx4XcUR4S8-XJWvqVgvo_5Lq4qIzTQ5LwCyvfr2mq-wyxABJdvhbk4MyCQAchm4zoHe-1rZSs8NsCjskqitX0to0zoi5WKDzIvHlEXBA7HOO_v3bs_xFX4LcI99rsFUoEOQpePEp_44RVQVk0G-CBwCE8gQZqvT3BdlfdTNcRmL_ohT-G-WAQv__t2bcoZzgP1c5WFWema1uzAyU0Q1c3AlYg0VMy2jFVMr5eCkQW3U6A17m4h7V2UM99uZnnC47Jrh51PWqwcsXrnefAZwtJU0_9lKsC4HkT1yRPOPBWLb16TkO0YIqSq-Rf4HQSNcNS5aw0MYn8s3RNQk2TrhDN3DO5kj1VarH_SmkjizOgSC5cBF2iyulRd6dzr6EJu6povq3jB7R0eizZvfElUmRm_XfhAGvYvcL1Cq7x_aH3N7rrOFW6VhkaiYJLw2aQ83xF2PoT6UZ4nfzrJ8T7Zbv2yZZlZ9dcgdvWbqiwZGjhzhPm_mHKK-Gpg-FxE7qAKisB-WllQCOUBr7pMUDjUYTjcYlcX6Jh6ahIKI2-DmjWC6IoNowSZKwOFmhjIQbEJUpQxZkCVgWU6BvHIL-YQhhN_0000)](https://www.plantuml.com/plantuml/uml/ZPHHRjim38RVVGeUOCa10iDeQwpRmpeKAOO-1CMWM6mYL1OrYjuMjBtx4XcUR4S8-XI0_qVgvo_5Lq4KIzVQ8hmOvnMh5ZG-gyxABJdvhbg4MyCAQjBu2Ux8KV2gGJtYrzY8SZkBDyGQ-K4E5iloXZgd5H-g2hGAUyntgEPbnoUo9aiywNhS6y5SqydO-I6Ek5Ns0uV05rQ74LTJwycXapdtpUpsCeE_ub-r8_S1DCtzxnUsP7MnDnfXOZq8Cf8SF1O53wvX0ARiQm6mxqs0yjwQHeaX5-i0dbZWHy1Q1RmdbbIyHmuc2Bfc9jJMe9DfxOuuKSdHTJhl0VctQJ02e_EWV5iiCjmAgeZEN42PfIEQV5sYejEBp7i1aw0MYmescbfbt5Dnr9hXca1fvGLvzGUty3fRlIOdJDOYpuH7tFvSuwyEWnn_WoSNUcUf0nPL7ZkRTDqxcFV7K9CyX9cR2UK4xVSV5UBwemlB9-3JDPqbiKPFmGZnOIPyTcAaBoRcj7iI4Zr-2Sdhk3DkagVwYJaYxJmMqkvlrVm9hg3CPz34ztV-468vMynVSAyTSxX8pMUDj-qhSTkyc1cQeMCigPOIwKkZC3P31ilbykd8LEc3y5uKcuRSizbr7FzFlJvye8hY2ftkMliF)

For a detailed guide on the architecture design, please refer to the [Clean Architecture Implementation Guide (in Chinese)](https://zhuanlan.zhihu.com/c_1839245367729864704).

### Core Design Philosophy

#### 1. From Abstract Theory to Engineering Practice
Theories like Clean Architecture are highly abstract. HoCAT provides an executable, buildable engineering blueprint that translates them into practice. Through its Gradle multi-module structure, it physically enforces architectural dependency rules and answers concrete engineering questions like "how to organize code" and "how to guarantee dependency direction", making abstract theory concrete and actionable.

#### 2. Strict Separation of Concerns and Dependency Control
HoCAT protects the core business logic by enforcing a unidirectional dependency rule, ensuring each component depends only on the bare minimum it needs:
*   **`domain`**: Defines pure business rules, independent of any external frameworks, making it the most stable part of the system.
*   **`application`**: Orchestrates business flows, defines Use Cases and Ports, and is also independent of specific technologies.
*   **`adapter`**: Implements the interaction logic with external technologies. **This is the only place where specific frameworks and tools like Spring Web MVC or Spring Data JPA are introduced.**
*   **`configuration`**: Assembles the application. **It is responsible for introducing the Spring IoC container** and wiring the concrete `adapter` implementations into the `application`'s ports via dependency injection.

This design provides significant flexibility in technology choices. Since adapters are pluggable, you can easily swap implementations. For example, the project provides both `adapter/persistence` (JPA) and `adapter/persistence-jdbc` (Spring Data JDBC). You can switch between them with no changes to the business code. Likewise, `adapter/web` and `adapter/web-openapi` demonstrate that API implementation strategies are also swappable.

#### 3. Pragmatic Data Structure Strategy
HoCAT adopts a balanced and pragmatic strategy for data transfer between layers, avoiding two common anti-patterns:
1.  **Avoiding a single model that spans all layers**: Exposing a database entity directly to the API can leak internal details and create security risks.
2.  **Avoiding a DTO explosion**: Creating a separate model for every interaction leads to a proliferation of boilerplate classes and mapping code.

HoCAT's strategy is that **each layer owns the data structures for its boundaries**. For instance, the `web` adapter has its own Request/Response models, while the `persistence` adapter has its persistence entities. This approach maintains clear boundaries without sacrificing developer productivity.

#### 4. Deep Decoupling within Adapters
A well-designed adapter should also follow the separation of concerns principle internally. HoCAT isolates third-party library dependencies to the smallest possible scope within an adapter. For example, in the persistence adapter, only a few components, such as entity classes annotated with `@Entity` (e.g., `OrderEntity`) and repositories that inherit from Spring Data interfaces (e.g., `OrderJpaRepository`), are directly coupled with the specific data access technology (like Spring Data JPA). The rest of the adapter remains agnostic to the specific persistence technology, achieving a deeper level of isolation.

## Codebase Tour

To understand how the architecture is implemented, here is a tour of the key modules:

- `domain`: Defines the core business entities and rules; the most stable and pure part of the application.
- `application`: Orchestrates business logic by defining Use Cases and the interfaces (Ports) that the outer layers must implement.
- `adapter`: Connects the application to the outside world by providing concrete implementations for the ports defined in the `application` layer.
- `configuration`: The final assembly module. It wires everything together using dependency injection.

## Getting Started

### Prerequisites
- Install Java 21
- Install Docker and Docker Compose

### Build and Test
Use `./gradlew build` to build and test the entire project.

### Local Development
- Use `./gradlew bootRun` to run the application locally.
- To start the database, run `docker compose up -d` in the `configuration` directory. You can check the dynamically assigned port with `docker compose ps`.
- To start local third-party services for contract testing, run `scripts/run-stub-runner-server configuration/src/test/resources/contracts/client 16581`.

### Package
Use `./gradlew bootBuildImage` to build a Docker image.

### IDE Setup
- **Formatter**: Install the [Spotless](https://github.com/diffplug/spotless) IDE plugin to auto-format code.

## Technology Stack

- **Foundation**: Java 21, Spring Boot 3.5, Spring Bean Validation, Lombok
- **Build**: Gradle, JaCoCo, Spotless
- **Optional Adapters**
  - **Web Adapter**: Spring Web MVC, Spring Security, Spring Cloud Contract
  - **OpenAPI-based Web Adapter**: OpenAPI Generator, Spring Web MVC, Spring Security
  - **Persistence (JPA)**: Spring Data JPA, MySQL 8, Flyway, Spring Boot Docker Compose Support
  - **Persistence (JDBC)**: Spring Data JDBC, MySQL 8, Flyway, Spring Boot Docker Compose Support
  - **Client Adapter**: Spring RestClient, Spring Cloud Contract Stub Runner
- **Testing**: JUnit 5, AssertJ, Mockito
- **Documentation**: Markdown, PlantUML

## Related Projects

- [HoCATLing](https://github.com/macdao/hands-on-clean-architecture-template-ling): A simplified, single-module version of HoCAT, suitable for smaller projects.
