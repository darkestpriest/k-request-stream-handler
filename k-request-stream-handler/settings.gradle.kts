pluginManagement {
    plugins {
        val kotlinVersion = "1.7.20"
        kotlin("jvm") version kotlinVersion apply false
    }
}

rootProject.name = "k-request-stream-handler"

include("api")
