plugins {
    id("myproject.java-conventions")
    alias(libs.plugins.springCloudContract)
}

dependencies {
    implementation(project(":application"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

contracts {
    packageWithBaseClasses = "com.example.demo.adapter.web"
}
