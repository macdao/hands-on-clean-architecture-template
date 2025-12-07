# Implementation Plan: Add Maven and MyBatis Plus Support

**Branch**: `001-add-maven-mybatisplus` | **Date**: 2025-12-07 | **Spec**: [spec.md](spec.md)
**Input**: Feature specification from `/specs/001-add-maven-mybatisplus/spec.md`

**Note**: This template is filled in by the `/speckit.plan` command. See `.specify/templates/commands/plan.md` for the execution workflow.

## Summary

Add Maven build system support and MyBatis Plus persistence adapter to HoCAT template. Provide pom.xml for multi-module build and implement adapter/persistence-mybatisplus module following Clean Architecture principles.

## Technical Context

**Language/Version**: Java 21  
**Primary Dependencies**: Spring Boot 3.5, MyBatis Plus 3.5  
**Storage**: MySQL 8  
**Testing**: JUnit 5, AssertJ, Mockito  
**Target Platform**: JVM  
**Project Type**: Multi-module Java project  
**Performance Goals**: Build completes in under 2 minutes  
**Constraints**: Follow Clean Architecture layers, maintain unidirectional dependencies  
**Scale/Scope**: Template project with domain, application, adapter, configuration modules

## Constitution Check

*GATE: Must pass before Phase 0 research. Re-check after Phase 1 design.*

- ✅ Clean Architecture Layers: Adding Maven and MyBatis Plus as pluggable options in adapter layer
- ✅ Unidirectional Dependencies: New adapter will depend only on application ports
- ✅ Pragmatic Data Structures: MyBatis Plus adapter will have its own entity mappings
- ✅ Deep Decoupling: MyBatis Plus dependencies isolated to adapter module
- ✅ Technology Flexibility: Enables choice between Gradle/Maven and JPA/JDBC/MyBatis Plus

## Project Structure

### Documentation (this feature)

```text
specs/001-add-maven-mybatisplus/
├── plan.md              # This file (/speckit.plan command output)
├── research.md          # Phase 0 output (/speckit.plan command)
├── data-model.md        # Phase 1 output (/speckit.plan command)
├── quickstart.md        # Phase 1 output (/speckit.plan command)
├── contracts/           # Phase 1 output (/speckit.plan command)
└── tasks.md             # Phase 2 output (/speckit.tasks command - NOT created by /speckit.plan)
```

### Source Code (repository root)

```text
# Existing HoCAT structure (unchanged)
domain/
├── src/main/java/com/example/demo/
└── build.gradle.kts

application/
├── src/main/java/com/example/demo/
└── build.gradle.kts

adapter/
├── web/
│   ├── src/main/java/com/example/demo/
│   └── build.gradle.kts
├── persistence/
│   ├── src/main/java/com/example/demo/
│   └── build.gradle.kts
├── persistence-jdbc/
│   ├── src/main/java/com/example/demo/
│   └── build.gradle.kts
└── client/
    ├── src/main/java/com/example/demo/
    └── build.gradle.kts

configuration/
├── src/main/java/com/example/demo/
└── build.gradle.kts

# New additions for Maven and MyBatis Plus support
pom.xml                                    # Root Maven POM
adapter/persistence-mybatisplus/           # New MyBatis Plus adapter
├── src/main/java/com/example/demo/
├── src/main/resources/
│   ├── db/migration/                      # Flyway migrations
│   └── mapper/                            # MyBatis mapper XMLs
├── src/test/java/com/example/demo/
├── src/test/resources/
└── pom.xml                                # Module POM

# Updated build files
buildSrc/                                 # Gradle buildSrc (keep for Gradle users)
├── src/main/kotlin/
└── build.gradle.kts

# Documentation updates
README.md                                 # Add Maven build instructions
docs/                                     # Update architecture docs
```

**Structure Decision**: Extend existing multi-module structure with Maven POMs and new MyBatis Plus adapter module. Maintain Gradle compatibility while adding Maven as alternative build system.

## Complexity Tracking

> **Fill ONLY if Constitution Check has violations that must be justified**

| Violation | Why Needed | Simpler Alternative Rejected Because |
|-----------|------------|-------------------------------------|
| [e.g., 4th project] | [current need] | [why 3 projects insufficient] |
| [e.g., Repository pattern] | [specific problem] | [why direct DB access insufficient] |
