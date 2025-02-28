plugins {
    kotlin("jvm") version "2.1.0"
    application
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1"
}

application {
    mainClass.set("game.MainKt")
}

group = "junie"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    testImplementation("io.kotest:kotest-property:5.8.0")
}

tasks {
    gradle.startParameter.consoleOutput = ConsoleOutput.Plain

    test {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    named<JavaExec>("run") {
        standardInput = System.`in`
    }

    ktlint {
        verbose.set(true)
        outputToConsole.set(true)
        filter {
            exclude("**/generated/**")
            include("**/kotlin/**")
        }
        enableExperimentalRules.set(true)
    }
}

kotlin {
    jvmToolchain(21)

    sourceSets.all {
        languageSettings {
            languageVersion = "2.1"
            apiVersion = "2.1"
            progressiveMode = true
        }
    }
}
