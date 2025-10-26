# HoCAT项目说明

[English](README.md)

HoCAT，Hands-on Clean Architecture Template，即可落地的整洁架构模板。旨在成为项目的代码库模板选项之一。

## 特点

- 功能
  - 基于最佳实践的整洁架构/六边形架构/端口适配器架构。
  - 可落地的代码模板。
  - 整合完备的构建工具。
  - 内嵌数据结构校验功能（基于Spring Bean Validation）。
  - 内置基于Docker的数据库，不需要额外启动（基于Spring Boot Docker Compose Support）。
- 自动化测试
  - 支持测试驱动开发。尽量在不离开IDE的流程中完成开发，以提升效率。
  - 每个模块有匹配的测试策略。
  - 基于契约的接口自动化测试（基于Spring Cloud Contract Verifier）。
  - 基于契约的三方系统调用自动化测试（基于Spring Cloud Contract Stub Runner）。
  - 提供接口消费者自动化测试的Mock Server（基于Spring Cloud Contract Stub Runner）。
- 其他
  - 约定优于配置。尽量遵守默认配置，非必要时不修改默认配置，最小化知识负担。
  - 使用Gradle的multi-project build方式来约束模块间依赖关系。

## 初始化

项目最初使用Spring Initializr创建。具体参数为：`https://start.spring.io/#!type=gradle-project-kotlin&language=java&platformVersion=3.3.4&packaging=jar&jvmVersion=21&groupId=com.example&artifactId=demo&name=demo&description=Demo%20project%20for%20Spring%20Boot&packageName=com.example.demo&dependencies=lombok,validation,web,cloud-contract-verifier,data-jpa,flyway,mysql,cloud-contract-stub-runner,security,actuator`。

## Gradle使用说明

- build.gradle.kts file in the root project

  使用了Gradle[推荐的方式](https://docs.gradle.org/current/userguide/gradle_directories.html)，没有在root project里放置build.gradle.kts。

  > Some builds may contain a build.gradle(.kts) file in the root project but this is NOT recommended.
  > - [Part 1: Initializing the Project](https://docs.gradle.org/current/userguide/part1_gradle_init.html)

- [Do not use cross-project configuration](https://docs.gradle.org/current/userguide/sharing_build_logic_between_subprojects.html#sec:convention_plugins_vs_cross_configuration)

  > Avoid using subprojects {} and allprojects {}.
  > With cross-configuration, build logic can be injected into a subproject which is not obvious when looking at its build script.

- 使用[Dependency Management Plugin](https://docs.spring.io/dependency-management-plugin/docs/current/reference/html/)管理依赖。

  参考[Spring Boot/Gradle Plugin](https://docs.spring.io/spring-boot/gradle-plugin/managing-dependencies.html)。

- 手动声明测试框架实现依赖

  增加`junit-platform-launcher`依赖声明，参考[Relying on automatic test framework implementation dependencies](https://docs.gradle.org/8.10/userguide/upgrading_version_8.html#test_framework_implementation_dependencies)。

- 使用[convention plugins](https://docs.gradle.org/current/userguide/sharing_build_logic_between_subprojects.html)管理重复的构建脚本。
- 使用[Lazy APIs](https://docs.gradle.org/current/userguide/task_configuration_avoidance.html#sec:old_vs_new_configuration_api_overview)，优化构建。

## 核心模块说明

### domain

领域层，包含领域模型和领域服务。

- 测试策略
  - 使用单元测试。因为domain不依赖其他模块，所以可以方便的进行单元测试。

### application

应用层，包含应用服务。

- 测试策略
  - 大部分使用单元测试。application依赖domain，大部分情况可以连带domain一起测试，不需要使用测试替身；同时，application定义了输出端口，测试时需要使用测试替身。
  - 小部分使用包含框架的集成测试。application会使用框架的一些特性，如校验和事务等，如果要测试这个功能，需要在Spring Boot Application的环境里进行测试。

## 适配器模块说明

适配器层，包含Web适配器、持久化适配器、客户端适配器等。

### adapter:web

使用Spring MVC的Web组件。对于错误响应模型，使用了Spring内置的RFC 9457（Problem Details for HTTP APIs）规范。

- 测试策略
  - 对于Controller采用集成测试，范围包含模块本身和Spring MVC框架。web主要提供对外HTTP接口，主要测试其对外提供的接口是否符合预期，所以采用契约测试。因为web需要使用Spring MVC框架的能力，需要在Spring Boot Application的环境里进行测试。其依赖Adapter，采用测试替身。
  - 对于Adapter的测试，采用单元测试。

#### 契约消费者（例如前端）自动化测试支持

使用契约定义和契约测试。契约可以作为对后端接口的测试，也可以作为前端测试的Stub Server。

运行Stub Runner Server，执行`scripts/run-stub-runner-server`。

- 使用adapter/web的契约。
- 需要使用Java 1.8或者11，不支持Java 17或更高版本。
- 默认端口是16580。

使用[Stub Runner Server Fat Jar](https://docs.spring.io/spring-cloud-contract/reference/project-features-stubrunner/stub-runner-boot.html#features-stub-runner-boot-how-fat-jar)。

也曾考虑过使用[Stub Runner Server](https://docs.spring.io/spring-cloud-contract/reference/project-features-stubrunner/stub-runner-boot.html#features-stub-runner-boot-server)，因为可以省去脚本的麻烦，也可以使用最新的版本。但是repositoryRoot的配置不支持相对路径，所以还是选择了使用Stub Runner Server Fat Jar方式。

`spring-cloud-contract-stub-runner-boot`最高可使用的版本是2.2.8.RELEASE，而其Groovy版本是2.5.10，支持Java 1.8和11，但不支持17或更高版本。

选择的默认端口是16580，是第一个超过10000的莱兰数。

### adapter:web-openapi

Web适配器的另一种实现。 使用OpenAPI Generator，根据OpenAPI规范生成Web接口。只需要编写适配器代码，用以连接生成的接口和应用层的用例接口。对于错误响应模型，使用了Spring内置的RFC 9457（Problem Details for HTTP APIs）规范。

- 测试策略
  - 对于自动生成的代码，不需要测试。
  - 对于Adapter进行单元测试。

### adapter:persistence

持久化适配器，使用Spring Data JPA。集成了Flyway。

- 测试策略
  - Repository采用集成测试，测试范围包含模块本身、持久化框架和数据库。因为persistence需要使用框架的能力，需要在Spring Boot Application的环境里进行测试。其依赖的数据库，使用Docker Compose定义，随测试启动。
  - Adapter采用单元测试。

### adapter:persistence-jdbc

持久化适配器的另一种实现。使用Spring Data JDBC的持久化适配器，支持直接使用领域模型作为持久化实体。不需要转换，所以只有一层。集成了Flyway。

- 测试策略
  - 采用集成测试，测试范围包含模块本身、持久化框架和数据库。因为persistence需要使用框架的能力，需要在Spring Boot Application的环境里进行测试。其依赖的数据库，使用Docker Compose定义，随测试启动。

### adapter:client

客户端适配器，使用Spring推荐的RestClient。

- 测试策略
  - Client采用集成测试，测试范围包含模块本身和三方系统接口契约。因为使用Stub Runner运行三方系统接口契约，所以在Spring Boot Application的环境里进行测试。其依赖的三方系统，使用契约定义三方系统接口，通过Stub Runner随测试启动。
  - Adapter采用单元测试。

## configuration

配置层，包含Application和Bean配置。

- 测试策略
  - 属于测试金字塔最上层测试，测试范围包含所有模块和框架。在Spring Boot Application的环境里进行测试。对于其依赖的数据库，使用Docker Compose定义，随测试启动。其依赖的三方系统，使用契约定义三方系统接口，通过Stub Runner随测试启动。

## 自动化测试的数据库

使用[Docker Compose Support](https://docs.spring.io/spring-boot/reference/features/dev-services.html)，在测试时自动启动MySQL容器。需要安装Docker和Docker Compose。

adapter:persistence和configuration的自动化测试都使用adapter/persistence目录下的compose.yaml文件。

对于`gradle bootRun`，使用configuration的compose.yaml文件。

## 参考

- [架构整洁之道](https://book.douban.com/subject/30333919/)
- [Get Your Hands Dirty on Clean Architecture](https://reflectoring.io/book/)
- [六边形架构](https://alistair.cockburn.us/hexagonal-architecture/)
- [整洁架构落地指南](https://www.zhihu.com/column/c_1839245367729864704)