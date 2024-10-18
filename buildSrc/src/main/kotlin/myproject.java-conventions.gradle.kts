plugins {
    `java-library`
    jacoco
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
    finalizedBy(tasks.named("jacocoTestReport"))
}

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("-parameters")
}

tasks.named<JacocoReport>("jacocoTestReport") {
    reports {
        html.required = true
    }
    tasks.withType<Test>().forEach { executionData(it) }
}

tasks.named<JacocoCoverageVerification>("jacocoTestCoverageVerification") {
    violationRules {
        rule {
            limit {
                minimum = "0.7".toBigDecimal()
            }
        }
    }
}

tasks.named("check") {
    dependsOn(tasks.named("jacocoTestCoverageVerification"))
}