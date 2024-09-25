plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation(libs.dependencyManagementPlugin)
    implementation(libs.spotlessPlugin)
}
