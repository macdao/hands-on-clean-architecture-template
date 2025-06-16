plugins {
    id("common-conventions")
}

dependencies {
    implementation("org.springframework:spring-context")
    //  Only if you want to use Spring Data JDBC to map your domain objects
    implementation("org.springframework.data:spring-data-relational")
}
