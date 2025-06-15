plugins {
    id("common-conventions")
}

dependencies {
    implementation("org.springframework:spring-context")
    //  Only if you want to use Spring Data JDBC to map your domain objects
    implementation("org.springframework.data:spring-data-relational")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core")
    testImplementation("org.mockito:mockito-junit-jupiter")
}
