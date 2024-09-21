plugins {
    `java-library`
    id("org.springframework.boot") version "3.3.4" apply false
	id("io.spring.dependency-management") version "1.1.6"
}

dependencies {
    api(project(":domain"))
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
	imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
	}
}