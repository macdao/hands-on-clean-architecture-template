plugins {
    id("org.springframework.boot") version "3.3.4"
    id("myproject.java-conventions")
}

dependencies {
    implementation(project(":adapter:web"))
    implementation(project(":adapter:persistence"))
    implementation(project(":adapter:client"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testAndDevelopmentOnly("org.springframework.boot:spring-boot-docker-compose")
}
