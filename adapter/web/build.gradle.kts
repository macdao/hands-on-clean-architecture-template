plugins {
    id("myproject.java-conventions")
    alias(libs.plugins.springCloudContract)
}

dependencies {
    implementation(project(":application"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

contracts {
    packageWithBaseClasses = "com.example.demo.adapter.web"
}
