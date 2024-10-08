plugins {
    `java-library`
    id("io.spring.dependency-management")
    id("com.diffplug.spotless")
}

repositories {
    mavenCentral()
}

group = "com.example.demo"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

val versionCatalog = versionCatalogs.named("libs")

dependencyManagement {
    imports {
        mavenBom(versionCatalog.findLibrary("springBootDependencies").get().get().toString())
        mavenBom(versionCatalog.findLibrary("springCloudDependencies").get().get().toString())
    }
}

spotless {
    java {
        target("src/*/java/**/*.java")
        palantirJavaFormat()
    }
    kotlinGradle {
        ktlint()
    }
    groovy {
        target("src/contractTest/resources/contracts/**/*.groovy")
        greclipse()
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-parameters")
}