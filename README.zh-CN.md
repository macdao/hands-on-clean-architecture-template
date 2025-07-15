# HoCAT 🐾

[![Java CI with Gradle](https://github.com/macdao/hands-on-clean-architecture-template/actions/workflows/gradle.yml/badge.svg)](https://github.com/macdao/hands-on-clean-architecture-template/actions/workflows/gradle.yml)

[English](README.md)

HoCAT，Hands-on Clean Architecture Template，即可落地的整洁架构模板。旨在成为项目的代码库模板选项之一。

最佳实践的整洁架构/六边形架构/端口适配器架构，可落地的代码模板。使用了当前最新的技术和工具、推荐的使用/配置方式和最佳实践。

## 项目说明

本文档介绍本项目的使用方式。 更多关于HoCAT的信息，请查看[HoCAT文档](.hocat)。

## 项目使用

- 前置条件
  - 安装Java 21。
  - 安装Docker和Docker Compose。

- 测试构建

  使用`./gradlew build`构建项目。

- 本地运行

  使用`./gradlew bootRun`运行本地环境。

  如果需要启动本地三方服务，运行`scripts/run-stub-runner-server configuration/src/test/resources/contracts/client 16581`

- 打包

  使用`./gradlew bootBuildImage`构建Docker镜像。这基于Spring Boot的Gradle插件。

### 数据库

为避免端口冲突，采用动态端口，需要通过Docker Compose查看数据库端口。

- 查看自动化测试数据库

  在`adapter/persistence`目录下执行`docker compose ps`，查看`PORTS`。

- 查看本地运行数据库

  在`configuration`目录下执行`docker compose ps`，查看`PORTS`。

### IDE使用

- Formatter：安装IDE插件[Spotless](https://github.com/diffplug/spotless)。

## 技术栈

- 基础
  - Java (21 LTS)
  - Spring Boot (3.5)
  - Spring Bean Validation
  - Lombok
  - JUnit 5
  - AssertJ
  - Mockito
  - Docker & Docker Compose
- 构建
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
- 文档
  - Markdown
  - PlantUML

## 架构

[![HoCAT Diagram](https://www.plantuml.com/plantuml/svg/ZPHHRjim38RVTGeUOCa10iDeQwpRmpeKAOO-1CMWs6mYL1OrYjuMjBtx4XcUR4S8-XJWvqVgvo_5Lq4qIzTQ5LwCyvfr2mq-wyxABJdvhbk4MyCQAchm4zoHe-1rZSs8NsCjskqitX0to0zoi5WKDzIvHlEXBA7HOO_v3bs_xFX4LcI99rsFUoEOQpePEp_44RVQVk0G-CBwCE8gQZqvT3BdlfdTNcRmL_ohT-G-WAQv__t2bcoZzgP1c5WFWema1uzAyU0Q1c3AlYg0VMy2jFVMr5eCkQW3U6A17m4h7V2UM99uZnnC47Jrh51PWqwcsXrnefAZwtJU0_9lKsC4HkT1yRPOPBWLb16TkO0YIqSq-Rf4HQSNcNS5aw0MYn8s3RNQk2TrhDN3DO5kj1VarH_SmkjizOgSC5cBF2iyulRd6dzr6EJu6povq3jB7R0eizZvfElUmRm_XfhAGvYvcL1Cq7x_aH3N7rrOFW6VhkaiYJLw2aQ83xF2PoT6UZ4nfzrJ8T7Zbv2yZZlZ9dcgdvWbqiwZGjhzhPm_mHKK-Gpg-FxE7qAKisB-WllQCOUBr7pMUDjUYTjcYlcX6Jh6ahIKI2-DmjWC6IoNowSZKwOFmhjIQbEJUpQxZkCVgWU6BvHIL-YQhhN_0000)](https://www.plantuml.com/plantuml/uml/ZPHHRjim38RVVGeUOCa10iDeQwpRmpeKAOO-1CMWM6mYL1OrYjuMjBtx4XcUR4S8-XI0_qVgvo_5Lq4KIzVQ8hmOvnMh5ZG-gyxABJdvhbg4MyCAQjBu2Ux8KV2gGJtYrzY8SZkBDyGQ-K4E5iloXZgd5H-g2hGAUyntgEPbnoUo9aiywNhS6y5SqydO-I6Ek5Ns0uV05rQ74LTJwycXapdtpUpsCeE_ub-r8_S1DCtzxnUsP7MnDnfXOZq8Cf8SF1O53wvX0ARiQm6mxqs0yjwQHeaX5-i0dbZWHy1Q1RmdbbIyHmuc2Bfc9jJMe9DfxOuuKSdHTJhl0VctQJ02e_EWV5iiCjmAgeZEN42PfIEQV5sYejEBp7i1aw0MYmescbfbt5Dnr9hXca1fvGLvzGUty3fRlIOdJDOYpuH7tFvSuwyEWnn_WoSNUcUf0nPL7ZkRTDqxcFV7K9CyX9cR2UK4xVSV5UBwemlB9-3JDPqbiKPFmGZnOIPyTcAaBoRcj7iI4Zr-2Sdhk3DkagVwYJaYxJmMqkvlrVm9hg3CPz34ztV-468vMynVSAyTSxX8pMUDj-qhSTkyc1cQeMCigPOIwKkZC3P31ilbykd8LEc3y5uKcuRSizbr7FzFlJvye8hY2ftkMliF)

## 轻量级版本

[HoCATLing](https://github.com/macdao/hands-on-clean-architecture-template-ling)，不拆分多个独立的组件，适用于小型项目。