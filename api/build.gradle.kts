plugins {
    kotlin("jvm")
}

dependencies {

    //kotlin
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    //aws
    implementation("com.amazonaws:aws-lambda-java-core:1.2.2")

    //tests
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.1")

}

tasks.withType<Test> {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
    withSourcesJar()

}


