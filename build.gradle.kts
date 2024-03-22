plugins {
    kotlin("jvm") version "1.9.22"
    `maven-publish`
}

group = "com.github.MikeDepies"
version = "0.1.5"
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
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "com.github.MikeDepies"
            artifactId = "MapElitesKotlin"
            version = "0.1.5"
        }
    }
}
