# HoCAT 🐾

[![Java CI with Gradle](https://github.com/macdao/hands-on-clean-architecture-template/actions/workflows/gradle.yml/badge.svg)](https://github.com/macdao/hands-on-clean-architecture-template/actions/workflows/gradle.yml)

[中文](README.zh-CN.md)

HoCAT (Hands-on Clean Architecture Template) is a production-ready clean architecture template that serves as a foundational codebase template for your projects.

A best-practice implementation of Clean Architecture / Hexagonal Architecture / Ports and Adapters Architecture with practical code templates. Built with the latest technologies, tools, recommended configurations, and best practices.

## Project Overview

This document describes how to use this project. For more information about HoCAT, please refer to the [HoCAT documentation](.hocat).

## Getting Started

- Prerequisites
  - Install Java 21
  - Install Docker and Docker Compose

- Build and Test

  Use `./gradlew build` to build the project.

- Local Development

  Use `./gradlew bootRun` to run the application locally.

  If you need to start local third-party services, run `scripts/run-stub-runner-server configuration/src/test/resources/contracts/client 16581`

- Package

  Use `./gradlew bootBuildImage` to build Docker image. This is based on Spring Boot's Gradle plugin.

### Database

To avoid port conflicts, dynamic ports are used. You need to check the database port through Docker Compose.

- Check automated test database

  Run `docker compose ps` in the `adapter/persistence` directory and check the `PORTS` column.

- Check local runtime database

  Run `docker compose ps` in the `configuration` directory and check the `PORTS` column.

### IDE Setup

- Formatter: Install the [Spotless](https://github.com/diffplug/spotless) IDE plugin.

## Technology Stack

- Foundation
  - Java (21 LTS)
  - Spring Boot (3.5)
  - Spring Bean Validation
  - Lombok
  - JUnit 5
  - AssertJ
  - Mockito
  - Docker & Docker Compose
- Build
  - Gradle
  - JaCoCo
  - Spotless
- Web
  - Spring Web MVC
  - Spring Cloud Contract Verifier
  - Spring Security
  - Spring Cloud Contract Stub Runner (for consumer)
- Persistence
  - Spring Data JPA
  - MySQL (8 LTS)
  - Flyway
  - Spring Boot Docker Compose Support
- Client
  - Spring RestClient
  - Spring Cloud Contract Stub Runner
- Documentation
  - Markdown
  - PlantUML

## Architecture

[![HoCAT Diagram](https://www.plantuml.com/plantuml/svg/ZPHHRjim38RVTGeUOCa10iDeQwpRmpeKAOO-1CMWs6mYL1OrYjuMjBtx4XcUR4S8-XJWvqVgvo_5Lq4qIzTQ5LwCyvfr2mq-wyxABJdvhbk4MyCQAchm4zoHe-1rZSs8NsCjskqitX0to0zoi5WKDzIvHlEXBA7HOO_v3bs_xFX4LcI99rsFUoEOQpePEp_44RVQVk0G-CBwCE8gQZqvT3BdlfdTNcRmL_ohT-G-WAQv__t2bcoZzgP1c5WFWema1uzAyU0Q1c3AlYg0VMy2jFVMr5eCkQW3U6A17m4h7V2UM99uZnnC47Jrh51PWqwcsXrnefAZwtJU0_9lKsC4HkT1yRPOPBWLb16TkO0YIqSq-Rf4HQSNcNS5aw0MYn8s3RNQk2TrhDN3DO5kj1VarH_SmkjizOgSC5cBF2iyulRd6dzr6EJu6povq3jB7R0eizZvfElUmRm_XfhAGvYvcL1Cq7x_aH3N7rrOFW6VhkaiYJLw2aQ83xF2PoT6UZ4nfzrJ8T7Zbv2yZZlZ9dcgdvWbqiwZGjhzhPm_mHKK-Gpg-FxE7qAKisB-WllQCOUBr7pMUDjUYTjcYlcX6Jh6ahIKI2-DmjWC6IoNowSZKwOFmhjIQbEJUpQxZkCVgWU6BvHIL-YQhhN_0000)](https://www.plantuml.com/plantuml/uml/ZPHHRjim38RVVGeUOCa10iDeQwpRmpeKAOO-1CMWM6mYL1OrYjuMjBtx4XcUR4S8-XI0_qVgvo_5Lq4KIzVQ8hmOvnMh5ZG-gyxABJdvhbg4MyCAQjBu2Ux8KV2gGJtYrzY8SZkBDyGQ-K4E5iloXZgd5H-g2hGAUyntgEPbnoUo9aiywNhS6y5SqydO-I6Ek5Ns0uV05rQ74LTJwycXapdtpUpsCeE_ub-r8_S1DCtzxnUsP7MnDnfXOZq8Cf8SF1O53wvX0ARiQm6mxqs0yjwQHeaX5-i0dbZWHy1Q1RmdbbIyHmuc2Bfc9jJMe9DfxOuuKSdHTJhl0VctQJ02e_EWV5iiCjmAgeZEN42PfIEQV5sYejEBp7i1aw0MYmescbfbt5Dnr9hXca1fvGLvzGUty3fRlIOdJDOYpuH7tFvSuwyEWnn_WoSNUcUf0nPL7ZkRTDqxcFV7K9CyX9cR2UK4xVSV5UBwemlB9-3JDPqbiKPFmGZnOIPyTcAaBoRcj7iI4Zr-2Sdhk3DkagVwYJaYxJmMqkvlrVm9hg3CPz34ztV-468vMynVSAyTSxX8pMUDj-qhSTkyc1cQeMCigPOIwKkZC3P31ilbykd8LEc3y5uKcuRSizbr7FzFlJvye8hY2ftkMliF)

## Lightweight Version

[HoCATLing](https://github.com/macdao/hands-on-clean-architecture-template-ling) - A simplified version without separate components, suitable for small projects.