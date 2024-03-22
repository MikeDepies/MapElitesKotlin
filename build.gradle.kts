plugins {
    kotlin("jvm") version "1.9.22"
}

group = "ai.sunnystratgies"
version = "1.0-SNAPSHOT"
val kotest = "5.8.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("io.kotest:kotest-runner-junit5:$kotest")
    testImplementation ("io.kotest:kotest-assertions-core:$kotest")
    testImplementation ("io.kotest:kotest-property:$kotest")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(18)
}