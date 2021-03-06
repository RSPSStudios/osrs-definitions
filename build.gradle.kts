import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    `maven-publish`
    kotlin("jvm") version "1.5.0"
    id("org.openjfx.javafxplugin") version "0.0.9"
}

allprojects {
    group = "com.javatar"
    version = "1.0-SNAPSHOT"
}

repositories {
    mavenCentral()
}

javafx {
    version = "16"
    modules("javafx.graphics")
}

dependencies {
    testImplementation("junit", "junit", "4.12")
    implementation(kotlin("stdlib-jdk8"))
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            isAllowInsecureProtocol = true
            url = uri("http://legionkt.com:8085/repository/maven-snapshots/")
            credentials {
                username = project.properties["myNexusUsername"] as String
                password = project.properties["myNexusPassword"] as String
            }
        }
    }
}
