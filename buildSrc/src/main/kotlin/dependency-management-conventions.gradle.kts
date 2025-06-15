plugins {
    id("io.spring.dependency-management")
}

val versionCatalog = versionCatalogs.named("libs")

dependencyManagement {
    imports {
        mavenBom(versionCatalog.findLibrary("springBootDependencies").get().get().toString())
        mavenBom(versionCatalog.findLibrary("springCloudDependencies").get().get().toString())
    }
}
