plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "petdiary-server-kotlin"

include("app-platform-api")
include("client-core")
include("domain-user")
include("domain-system")
include("domain-pet")
include("domain-photo")
include("domain-diary")
include("domain-record")
include("domain-cms")
include("system-core")