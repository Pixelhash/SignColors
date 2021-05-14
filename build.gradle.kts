import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import io.gitlab.arturbosch.detekt.Detekt

plugins {
    idea
    kotlin("jvm") version PluginVersions.KOTLIN
    id("com.github.johnrengelman.shadow") version PluginVersions.SHADOW
    id("com.diffplug.spotless") version PluginVersions.SPOTLESS
    id("io.gitlab.arturbosch.detekt") version PluginVersions.DETEKT
}

group = "de.codehat"
version = "2.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/central")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:${Versions.spigotApi}")
    compileOnly("org.ktorm:ktorm-core:${Versions.ktorm}")

    implementation("io.insert-koin:koin-core:${Versions.koin}")
    testImplementation("io.insert-koin:koin-test-junit5:${Versions.koin}")

    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

spotless {
    kotlin {
        //ktlint("0.41.0") currently broken cause Kotlin 1.5.0
        licenseHeaderFile("HEADER.txt")
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Detekt> {
    jvmTarget = "1.8"
}