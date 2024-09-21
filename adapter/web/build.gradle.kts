plugins {
    `java-library`
    id("org.springframework.boot") version "3.3.4" apply false
	id("io.spring.dependency-management") version "1.1.6"
}

dependencies {
    implementation(project(":application"))
    implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
	imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
	}
}