# HoCAT 🐾

HoCAT，Hands-on Clean Architecture Template，即可落地的整洁架构模板。旨在成为项目的代码库模板选项之一。

使用了当前最新的技术和工具、推荐的使用/配置方式和最佳实践。

## 项目说明

查看文档[EXPLANATION.md](docs/EXPLANATION.md)。

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

## 技术栈

- 基础
  - Java (21 LTS)
  - Spring Boot (3.4.0)
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

## 数据库

为避免端口冲突，采用动态端口，需要通过Docker Compose查看数据库端口。

- 查看自动化测试数据库

  在`adapter/persistence`目录下执行`docker compose ps`，查看`PORTS`。

- 查看本地运行数据库

  在`configuration`目录下执行`docker compose ps`，查看`PORTS`。

## 契约消费者（例如前端）自动化测试支持

运行Stub Runner Server，执行`scripts/run-stub-runner-server`。

- 使用adapter/web的契约。
- 需要使用Java 1.8或者11，不支持Java 17或更高版本。
- 默认端口是16580。

## 项目结构

![HoCAT Diagram](https://www.plantuml.com/plantuml/svg/ZPF1Rjim38RlUWe-m98768AWhO7kiWv5Yw67e0V6OXEXiYXGz0QZoBiFTk39SkAjr___67vXb9kaADestgHt5o8ADc3f3Lklznpq1pO4zquGAhRq1HuuS8fOavtmJrGhXeEdEnPBygoTfytcZjjWKRhj5Yocuy84rky7wScAFKinzN9qIjU6Uh7B9O6bGV-99V1Di9jm5JUH0mMTSz_TuQXBy5y9sBEtvkWiGU6Jb8HOkqZ6oAW28r2o7mQW7ZyPC4RlQbJ7OMW08el2pqGL9dfaqHm-aYINb48zAohlA6YPFR1aAZlTvdtF9BzSJJqmtGURrMe9omR_GxFJFwdHSfav6IYEj5fjOMUvGHTUJNbAYr59llwcFNpCldR5GOMz9vaLBxI_7UREHUPwexBjJNBGYqpFTjqbveDTE3rHvEIKfIkBwPMdRfdltMPKTYoBx3g9btthuLUxFDyGNmsdXkTaiCjb5Xkpx7HEHftTQB5Uhr_xFZ_Yap5cLtDuSuTMXeTm1iR_bRcXODl6_m40)

```plantuml
@startuml
skinparam defaultFontName Fira Code, Monospaced
skinparam RectangleBorderStyle<<Boundary>> dashed
skinparam RectangleBackgroundColor<<Boundary>> White
skinparam RectangleFontStyle<<Boundary>> normal
skinparam RectangleBackgroundColor Gray
skinparam ComponentBackgroundColor LightGray
skinparam ComponentFontStyle bold
hide <<Boundary>> stereotype

rectangle Boundary <<Boundary>> {
  component application {
    port UseCasePort
    port PersistencePort
    port ClientPort
    port MorePort
    rectangle ApplicationService
    UseCasePort <-- ApplicationService
    PersistencePort <-- ApplicationService
    ClientPort <-- ApplicationService
    MorePort <-- ApplicationService
  }
  application --> [domain]

  component adapter:web {
    rectangle Controller
    rectangle WebAdapter
    Controller <-- WebAdapter
  }
  WebAdapter --> UseCasePort

  component adapter:persistence {
    rectangle Repository
    rectangle PersistenceAdapter
    Repository <-- PersistenceAdapter
  }
  PersistenceAdapter --> PersistencePort

  component adapter:client {
    rectangle Client
    rectangle ClientAdapter
    Client <-- ClientAdapter
  }
  ClientAdapter --> ClientPort

  [adapter:...] --> MorePort
}


[configuration] --> Boundary

@enduml
```

## IDE使用

- Formatter：安装IDE插件[Spotless](https://github.com/diffplug/spotless)。

## 轻量级版本

[HoCATLing](https://github.com/macdao/hands-on-clean-architecture-template-ling)，不拆分多个独立的组件，适用于小型项目。