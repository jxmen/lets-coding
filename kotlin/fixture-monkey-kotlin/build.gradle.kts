plugins {
    kotlin("jvm") version "2.0.10"

    id("org.jetbrains.kotlin.plugin.jpa") version "2.0.0" // auto generate no args constructor
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.hibernate:hibernate-core:5.4.32.Final")

    testImplementation(kotlin("test"))

    //assertj
    testImplementation("org.assertj:assertj-core:3.21.0")

    // Fixture Monkey
    testImplementation("com.navercorp.fixturemonkey:fixture-monkey-starter-kotlin:1.0.23")
    testImplementation("com.navercorp.fixturemonkey:fixture-monkey-jakarta-validation:1.0.23")
}



tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
