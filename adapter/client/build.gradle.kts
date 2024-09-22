plugins {
    id("myproject.java-conventions")
}

dependencies {
    implementation(project(":application"))
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner")
}
