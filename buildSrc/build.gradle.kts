plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("io.spring.gradle:dependency-management-plugin:1.1.6")
    implementation("com.diffplug.spotless:spotless-plugin-gradle:7.0.0.BETA4")
}
