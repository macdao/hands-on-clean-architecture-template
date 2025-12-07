<!--
Sync Impact Report:
- Version change: none → 1.0.0
- Added sections: 5 core principles (Clean Architecture Layers, Unidirectional Dependencies, Pragmatic Data Structures, Deep Decoupling, Technology Flexibility), Technology Stack, Development Workflow, Governance
- Templates requiring updates: none
- Follow-up TODOs: none
-->
# HoCAT Constitution

## Core Principles

### I. Clean Architecture Layers
Enforce strict separation with domain (pure business rules), application (use cases and ports), adapter (external technology implementations), and configuration (assembly) layers. Domain is most stable, adapters are pluggable.

### II. Unidirectional Dependencies
Dependencies flow inward only: configuration → adapter → application → domain. Each layer depends only on the minimum necessary, protecting core business logic.

### III. Pragmatic Data Structures
Each adapter owns its data structures (Request/Response models, entities). Avoid single model spanning layers or excessive DTOs while maintaining clear boundaries.

### IV. Deep Decoupling
Within adapters, isolate third-party dependencies to minimal scope. Business logic remains technology-agnostic, enabling flexible technology choices.

### V. Technology Flexibility
Pluggable adapters enable easy replacement of implementations (e.g., JPA vs JDBC, Web MVC vs OpenAPI) without changing business code.

## Technology Stack

Java 21, Spring Boot 3.5, Gradle, JUnit 5, AssertJ, Mockito, JaCoCo, Spotless, Docker, MySQL 8, Flyway, PlantUML, Markdown.

## Development Workflow

Use Gradle for builds and testing. Follow Clean Architecture layers. Implement adapters for external interfaces. Use Docker Compose for local databases. Run contract tests with stub servers. Format code with Spotless.

## Governance

Constitution supersedes all other practices. Amendments require documentation and justification. All PRs must verify compliance with architectural principles. Complexity must be justified. Use README.md and docs/ for runtime guidance.

**Version**: 1.0.0 | **Ratified**: 2025-12-07 | **Last Amended**: 2025-12-07
