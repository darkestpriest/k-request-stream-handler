plugins {
    val kotlinVersion = "1.8.0"
    kotlin("jvm") version kotlinVersion apply false
}

group = "org.example"
version = "1.0-SNAPSHOT"

subprojects {
    repositories {
        mavenCentral()
    }
}
