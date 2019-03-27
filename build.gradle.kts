plugins {
    `build-scan`
    `maven-publish`
    kotlin("jvm") version "1.3.20"
}

group = "com.hionastudios"
version = "0.0.1"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))

    // Use the Kotlin test library.
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
}


buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"

    publishAlways()
}


publishing {
    publications {
        create<MavenPublication>("default") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("$buildDir/repository")
        }
    }
}