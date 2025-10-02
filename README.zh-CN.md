# HoCAT 🐾

[![Java CI with Gradle](https://github.com/macdao/hands-on-clean-architecture-template/actions/workflows/gradle.yml/badge.svg)](https://github.com/macdao/hands-on-clean-architecture-template/actions/workflows/gradle.yml)

[English](README.md)

## 什么是 HoCAT？

HoCAT（Hands-on Clean Architecture Template）是一个遵循整洁架构（Clean Architecture）原则的模板项目。它为 Java 应用提供了一个生产就绪的、经过工程实践验证的、可演进的架构骨架，旨在帮助开发者构建高质量、可长期维护的软件系统。

## 核心架构

HoCAT 的核心价值在于其清晰、解耦的架构。它不仅是理论的展示，更是一套可以直接用于生产的工程实践。

[![HoCAT Diagram](https://www.plantuml.com/plantuml/svg/ZPHHRjim38RVTGeUOCa10iDeQwpRmpeKAOO-1CMWs6mYL1OrYjuMjBtx4XcUR4S8-XJWvqVgvo_5Lq4qIzTQ5LwCyvfr2mq-wyxABJdvhbk4MyCQAchm4zoHe-1rZSs8NsCjskqitX0to0zoi5WKDzIvHlEXBA7HOO_v3bs_xFX4LcI99rsFUoEOQpePEp_44RVQVk0G-CBwCE8gQZqvT3BdlfdTNcRmL_ohT-G-WAQv__t2bcoZzgP1c5WFWema1uzAyU0Q1c3AlYg0VMy2jFVMr5eCkQW3U6A17m4h7V2UM99uZnnC47Jrh51PWqwcsXrnefAZwtJU0_9lKsC4HkT1yRPOPBWLb16TkO0YIqSq-Rf4HQSNcNS5aw0MYn8s3RNQk2TrhDN3DO5kj1VarH_SmkjizOgSC5cBF2iyulRd6dzr6EJu6povq3jB7R0eizZvfElUmRm_XfhAGvYvcL1Cq7x_aH3N7rrOFW6VhkaiYJLw2aQ83xF2PoT6UZ4nfzrJ8T7Zbv2yZZlZ9dcgdvWbqiwZGjhzhPm_mHKK-Gpg-FxE7qAKisB-WllQCOUBr7pMUDjUYTjcYlcX6Jh6ahIKI2-DmjWC6IoNowSZKwOFmhjIQbEJUpQxZkCVgWU6BvHIL-YQhhN_0000)](https://www.plantuml.com/plantuml/uml/ZPHHRjim38RVVGeUOCa10iDeQwpRmpeKAOO-1CMWM6mYL1OrYjuMjBtx4XcUR4S8-XI0_qVgvo_5Lq4KIzVQ8hmOvnMh5ZG-gyxABJdvhbg4MyCAQjBu2Ux8KV2gGJtYrzY8SZkBDyGQ-K4E5iloXZgd5H-g2hGAUyntgEPbnoUo9aiywNhS6y5SqydO-I6Ek5Ns0uV05rQ74LTJwycXapdtpUpsCeE_ub-r8_S1DCtzxnUsP7MnDnfXOZq8Cf8SF1O53wvX0ARiQm6mxqs0yjwQHeaX5-i0dbZWHy1Q1RmdbbIyHmuc2Bfc9jJMe9DfxOuuKSdHTJhl0VctQJ02e_EWV5iiCjmAgeZEN42PfIEQV5sYejEBp7i1aw0MYmescbfbt5Dnr9hXca1fvGLvzGUty3fRlIOdJDOYpuH7tFvSuwyEWnn_WoSNUcUf0nPL7ZkRTDqxcFV7K9CyX9cR2UK4xVSV5UBwemlB9-3JDPqbiKPFmGZnOIPyTcAaBoRcj7iI4Zr-2Sdhk3DkagVwYJaYxJmMqkvlrVm9hg3CPz34ztV-468vMynVSAyTSxX8pMUDj-qhSTkyc1cQeMCigPOIwKkZC3P31ilbykd8LEc3y5uKcuRSizbr7FzFlJvye8hY2ftkMliF)

### 核心设计理念

#### 1. 将抽象理论转化为工程实践
整洁架构等理论本身是高度抽象的，HoCAT 则提供了一个可执行、可构建的工程蓝图。它通过 Gradle 多模块结构，在物理上强制执行了架构的依赖规则，回答了“如何组织代码”、“依赖关系如何保证”等具体的工程问题，使抽象理论能够真正落地。

#### 2. 严格的关注点分离与依赖控制
HoCAT 通过强制性的单向依赖原则，保护了最有价值的核心业务逻辑，实现了技术栈的解耦。每个组件都只依赖其必须的最小集合：
*   **`domain`**: 定义纯粹的业务规则，不依赖任何外部框架，是系统中最稳定的部分。
*   **`application`**: 编排业务流程，定义用例（Use Cases）和端口（Ports），同样与具体技术实现无关。
*   **`adapter`**: 实现与外部技术的交互逻辑，是端口的具体实现。**这是唯一依赖如 Spring Web MVC、Spring Data JPA 等具体工具和框架的地方**。
*   **`configuration`**: 负责应用的组装与配置。**它负责引入 Spring IoC 容器**，通过依赖注入将 `adapter` 的具体实现连接到 `application` 的端口上。

这种设计使得技术选型变得极为灵活。由于适配器是可插拔的，你可以轻易地替换实现。例如，项目中同时提供了 `adapter/persistence` (JPA) 和 `adapter/persistence-jdbc` (Spring Data JDBC) 两个持久化适配器，业务代码无需任何改动即可切换。同理，`adapter/web` 和 `adapter/web-openapi` 也展示了API实现方式的可替换性。

#### 3. 务实的数据结构策略
在各层之间传递数据是一个常见的架构难点。HoCAT 在此采取了务实的平衡策略，规避了两种常见的反模式：
1.  **避免单一模型贯穿所有层**：如果将数据库实体（Entity）一直暴露到API接口，会造成内部数据结构泄露，增加安全风险。
2.  **避免为每次交互都创建模型**：过度使用DTO会导致大量的、功能单一的类和冗余的转换代码。

HoCAT 的策略是：**每个适配器都拥有其数据结构**。例如，`web` 适配器有独立的 Request/Response 模型，而 `persistence` 适配器有其持久化实体。这在保证了各层之间清晰边界的同时，也兼顾了开发效率。

#### 4. 适配器内部的深度解耦
一个设计良好的适配器，其内部也应遵循关注点分离原则。HoCAT 将对第三方库的依赖隔离在适配器内部的最小范围。例如，在持久化适配器中，只有被 `@Entity` 注解的实体类（如 `OrderEntity`）和继承了 Spring Data 接口的仓储库（如 `OrderJpaRepository`）等少量组件，才与具体的数据访问技术（如 Spring Data JPA）直接耦合。适配器内部的其他服务或组件则不感知 JPA 的存在，从而实现了更深层次的隔离。

关于架构设计的详细介绍，请参考[整洁架构落地指南](https://zhuanlan.zhihu.com/c_1839245367729864704)。

## 代码库导览

为了帮助你理解架构是如何在代码中实现的，这里是关键模块的导览：

- `domain`: 定义核心业务实体与规则，是应用中最稳定且纯粹的部分。
- `application`: 编排业务流程，定义用例（Use Cases）和端口（Ports）。
- `adapter`: 实现与外部技术（如数据库、Web API）交互的逻辑，是端口的具体实现。
- `configuration`: 负责应用的组装与配置，使用依赖注入将所有模块连接在一起。

## 快速上手

### 前置条件
- 安装 Java 21
- 安装 Docker 和 Docker Compose

### 测试构建
使用 `./gradlew build` 构建并测试整个项目。

### 本地运行
- 使用 `./gradlew bootRun` 运行本地环境。
- 如需启动数据库，请在 `configuration` 目录下运行 `docker compose up -d`。你可以通过 `docker compose ps` 查看动态分配的端口。
- 如需为契约测试启动本地三方服务，运行 `scripts/run-stub-runner-server configuration/src/test/resources/contracts/client 16581`。

### 打包
使用 `./gradlew bootBuildImage` 构建 Docker 镜像。

### IDE 使用
- **Formatter**：安装 [Spotless](https://github.com/diffplug/spotless) IDE 插件以自动格式化代码。

## 技术栈

- **基础**: Java 21, Spring Boot 3.5, Spring Bean Validation, Lombok
- **构建**: Gradle, JaCoCo, Spotless
- 可选适配器
  - **Web适配器**: Spring Web MVC, Spring Security, Spring Cloud Contract
  - **基于OpenAPI的Web适配器**: OpenAPI Generator, Spring Web MVC, Spring Security
  - **持久化**: Spring Data JPA, MySQL 8, Flyway, Spring Boot Docker Compose Support
  - **基于JDBC的持久化**: Spring Data JDBC, MySQL 8, Flyway, Spring Boot Docker Compose Support
  - **客户端**: Spring RestClient, Spring Cloud Contract Stub Runner
- **测试**: JUnit 5, AssertJ, Mockito
- **文档**: Markdown, PlantUML

## 相关项目

- [HoCATLing](https://github.com/macdao/hands-on-clean-architecture-template-ling)：HoCAT 的单模块轻量级版本，适用于小型项目。
