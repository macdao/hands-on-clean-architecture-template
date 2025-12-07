# Quick Start: Maven and MyBatis Plus Support

## Prerequisites
- Java 21
- Maven 3.8+
- MySQL 8

## Using Maven Build System

1. **Build the project**:
   ```bash
   mvn clean compile
   ```

2. **Run tests**:
   ```bash
   mvn test
   ```

3. **Package application**:
   ```bash
   mvn package
   ```

4. **Run application**:
   ```bash
   mvn spring-boot:run
   ```

## Using MyBatis Plus Adapter

1. **Configure database** in `application.yaml`:
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/hocat
       username: root
       password: password
   ```

2. **Switch to MyBatis Plus** in build configuration:
   - Include `adapter/persistence-mybatisplus` module
   - Exclude other persistence adapters

3. **Code generation** (optional):
   ```java
   // Run AutoGenerator to create entities and mappers
   AutoGenerator generator = new AutoGenerator();
   // Configure and execute
   ```

4. **Custom queries** in `resources/mapper/OrderMapper.xml`:
   ```xml
   <select id="findByStatus" resultType="OrderEntity">
       SELECT * FROM order WHERE status = #{status}
   </select>
   ```

## Switching Between Adapters

Edit your build configuration to include desired persistence adapter:

- JPA: `adapter/persistence`
- JDBC: `adapter/persistence-jdbc`
- MyBatis Plus: `adapter/persistence-mybatisplus`