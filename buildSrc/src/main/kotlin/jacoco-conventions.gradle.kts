plugins {
    jacoco
}

tasks.withType<JacocoReportBase>().configureEach {
    tasks.withType<Test>().forEach { executionData(it) }

    classDirectories.setFrom(classDirectories.files.flatMap {
        fileTree(it) {
            exclude(
                // "com/example/demo/DemoApplication.class"
            )
        }
    })
}

tasks.named<JacocoReport>("jacocoTestReport") {
    reports {
        html.required = true
    }
}

tasks.named<JacocoCoverageVerification>("jacocoTestCoverageVerification") {
    violationRules {
        rule {
            limit {
                minimum = "0.8".toBigDecimal()
            }
        }
    }
}

tasks.named("check") {
    dependsOn("jacocoTestReport", "jacocoTestCoverageVerification")
}
