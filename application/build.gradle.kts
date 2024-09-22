plugins {
    id("myproject.java-conventions")
}

dependencies {
    api(project(":domain"))
    implementation("org.springframework:spring-context")
    implementation("org.springframework:spring-tx")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
