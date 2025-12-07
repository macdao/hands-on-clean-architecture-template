# Research Findings: Add Maven and MyBatis Plus Support

## Decision: Add Maven Multi-Module Configuration

**Rationale**: Maven provides a standardized, XML-based build system that complements Gradle. Multi-module setup enforces Clean Architecture boundaries through dependency declarations. Compatible with existing Gradle structure via separate POM files.

**Alternatives Considered**:
- Gradle-only: Maintains current flexibility but limits developer choice.
- Maven migration: Would require abandoning Gradle, potentially disrupting existing workflows.

## Decision: Implement MyBatis Plus as New Persistence Adapter

**Rationale**: MyBatis Plus offers enhanced MyBatis features (code generation, active record, pagination) while maintaining SQL control. Fits Clean Architecture adapter pattern, providing alternative to JPA/JDBC implementations.

**Alternatives Considered**:
- Extend existing JPA adapter: Would add complexity to single adapter.
- Use raw MyBatis: Less productive than MyBatis Plus enhancements.
- Hibernate alternative: Redundant with existing JPA option.

## Decision: Maintain Both Gradle and Maven Support

**Rationale**: Allows developers to choose build system based on preference. Maven POMs can coexist with Gradle files without interference.

**Alternatives Considered**:
- Maven-only migration: Would break existing Gradle users.
- Gradle Maven plugin: Adds complexity without full Maven benefits.

## Decision: Use MyBatis Plus Spring Boot 3 Starter

**Rationale**: Official starter ensures compatibility with Spring Boot 3.5. Provides auto-configuration and reduces manual setup.

**Alternatives Considered**:
- Manual MyBatis integration: More configuration effort.
- MyBatis Plus 2.x: Not compatible with Spring Boot 3.x.

## Decision: Generate Code with MyBatis Plus AutoGenerator

**Rationale**: Reduces boilerplate for entities, mappers, and services. Aligns with adapter implementation needs.

**Alternatives Considered**:
- Manual coding: Time-consuming and error-prone.
- Lombok only: Doesn't generate mappers/services.

## Decision: XML Mappers for Complex Queries

**Rationale**: Provides full SQL control for domain-specific queries while using MyBatis Plus for standard CRUD.

**Alternatives Considered**:
- Annotation-only: Limited for complex joins.
- Stored procedures: Database coupling.

## Decision: MapStruct for Domain-Entity Conversion

**Rationale**: Type-safe, performant mapping between domain objects and MyBatis entities. Maintains Clean Architecture separation.

**Alternatives Considered**:
- Manual mapping: Boilerplate code.
- Dozer: Less modern than MapStruct.