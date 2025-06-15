plugins {
    id("common-conventions")
    id("org.openapi.generator") version "7.13.0"
}

sourceSets {
    main {
        java {
            srcDir(layout.buildDirectory.dir("generate-resources/main/src/main/java"))
        }
    }
}

dependencies {
    implementation(project(":application"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
}

openApiGenerate {
    generatorName = "spring"
    inputSpecRootDirectory = "${layout.projectDirectory}/specs"
    globalProperties =
        mapOf(
            "models" to "",
            "apis" to "",
        )
    configOptions =
        mapOf(
            "apiPackage" to "com.example.demo.adapter.webopenapi.api",
            "modelPackage" to "com.example.demo.adapter.webopenapi.model",
            "interfaceOnly" to "true",
            "skipDefaultInterface" to "true",
            "requestMappingMode" to "api_interface",
            "documentationProvider" to "none",
            "useResponseEntity" to "false",
            "useSpringBoot3" to "true",
            "useSpringController" to "true",
            "openApiNullable" to "false",
            "additionalEnumTypeAnnotations" to "@lombok.Generated",
            "additionalModelTypeAnnotations" to "@lombok.Generated",
        )
}

tasks.named("compileJava") {
    dependsOn("openApiGenerate")
}
