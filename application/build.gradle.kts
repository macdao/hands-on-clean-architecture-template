plugins {
    `java-library`
    id("org.springframework.boot") version "3.3.4" apply false
	id("io.spring.dependency-management") version "1.1.6"
}

repositories {
	mavenCentral()
}

dependencies {
    api(project(":domain"))
	implementation("org.springframework:spring-context")
	implementation("org.springframework:spring-tx")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
	imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
	}
}