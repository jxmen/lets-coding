plugins {
    kotlin("jvm") version "2.0.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    //assertj
    testImplementation("org.assertj:assertj-core:3.21.0")

    // Fixture Monkey
    testImplementation("com.navercorp.fixturemonkey:fixture-monkey-starter-kotlin:1.0.23")
}



tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
