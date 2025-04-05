plugins {
    alias(libs.plugins.springBoot)
    id("myproject.java-conventions")
}

dependencies {
    implementation(project(":adapter:web"))
    implementation(project(":adapter:persistence"))
    // alternative to persistence (jpa)
    // implementation(project(":adapter:persistence-jdbc"))
    implementation(project(":adapter:client"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner")
    testAndDevelopmentOnly("org.springframework.boot:spring-boot-docker-compose")
}
