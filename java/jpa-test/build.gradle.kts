plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // JPA
    implementation("javax.persistence:javax.persistence-api:2.2")
    testImplementation("com.h2database:h2:2.3.232")
    implementation("org.hibernate:hibernate-core:5.6.15.Final")
}

tasks.test {
    useJUnitPlatform()
}
