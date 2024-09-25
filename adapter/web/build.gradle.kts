plugins {
    id("myproject.java-conventions")
    id("org.springframework.cloud.contract") version "4.1.4"
}

extra["springCloudVersion"] = "2023.0.3"

dependencies {
    implementation(project(":application"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

contracts {
    packageWithBaseClasses = "com.example.demo.adapter.web"
}
