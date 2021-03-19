plugins {
    java
    `maven-publish`
}

group = "com.javatar"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit", "junit", "4.12")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.javatar"
            artifactId = "osrs-definitions"
            version = "0.1"
            from(components["java"])
        }
    }
}
