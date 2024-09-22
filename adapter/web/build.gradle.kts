plugins {
    id("myproject.java-conventions")
}

dependencies {
    implementation(project(":application"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier")
}
