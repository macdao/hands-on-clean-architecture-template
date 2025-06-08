rootProject.name = "hocat"

include("domain", "application", "configuration")
include("adapter:web")
include("adapter:web-openapi")
include("adapter:persistence")
include("adapter:client")
include("adapter:persistence-jdbc")
