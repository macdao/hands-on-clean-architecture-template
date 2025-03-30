rootProject.name = "hocat"

include("domain", "application", "configuration")
include("adapter:web")
include("adapter:persistence")
include("adapter:client")
include("adapter:persistence-jdbc")
