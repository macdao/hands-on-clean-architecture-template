plugins {
    id("myproject.java-conventions")
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
}

openApiGenerate {
    generatorName = "spring"
    inputSpec = "${layout.projectDirectory}/src/main/resources/specs/petstore.yaml"
    // inputSpecRootDirectory
    // mergedFileName
    globalProperties =
        mapOf(
            "models" to "",
            "apis" to "",
        )
    configOptions =
        mapOf(
            "apiPackage" to "com.example.demo.adapter.webopenapi.api",
            "modelPackage" to "com.example.demo.adapter.webopenapi.model",
            "invokerPackage" to "com.example.demo.adapter.webopenapi2",
            "interfaceOnly" to "true",
            "skipDefaultInterface" to "true",
            "requestMappingMode" to "api_interface",
            "documentationProvider" to "none",
            "useResponseEntity" to "false",
            "useSpringBoot3" to "true",
            "useSpringController" to "true",
            "openApiNullable" to "false",
        )
}

tasks.named("compileJava") {
    dependsOn(tasks.named("openApiGenerate"))
}
