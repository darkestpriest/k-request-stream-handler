plugins {
    kotlin("jvm")
}

dependencies {

    implementation(kotlin("stdlib-jdk8"))

    //aws
    implementation("com.amazonaws:aws-lambda-java-core:1.2.2")

    //tests
    testImplementation(kotlin("test"))

}

tasks.withType<Test> {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    sourceCompatibility = JavaVersion.VERSION_17
    withSourcesJar()
}


