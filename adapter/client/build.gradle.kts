plugins {
    id("myproject.java-conventions")
}

dependencies {
    implementation(project(":application"))
    implementation("org.springframework:spring-web")
    runtimeOnly("org.springframework.boot:spring-boot-starter-json")
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner")
}
