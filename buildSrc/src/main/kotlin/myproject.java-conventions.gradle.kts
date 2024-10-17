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
        target("src/*/resources/contracts/**/*.groovy")
        greclipse()
    }
    sql {
        target("src/*/resources/**/*.sql")
        dbeaver()
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("-parameters")
}