plugins {
    java
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.epages.restdocs-api-spec") version "0.17.1"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    /**
     * Spring REST Docs Swagger를 위한 의존성 추가
     *
     * https://mvnrepository.com/artifact/com.epages/restdocs-api-spec-mockmvc
     */
    implementation("com.epages:restdocs-api-spec:0.17.1")
    implementation("com.epages:restdocs-api-spec-mockmvc:0.17.1")
    implementation("com.epages:restdocs-api-spec-openapi3-generator:0.17.1")

    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val snippetsDir by extra { file("build/generated-snippets") }

tasks.test {
    outputs.dir(snippetsDir)

    finalizedBy("openapi3")
}

tasks.asciidoctor {
    inputs.dir(snippetsDir)
    dependsOn(tasks.test)
}

tasks.bootJar {
    archiveFileName.set("app.jar")

    dependsOn(tasks.test)

    from("${tasks.asciidoctor.get().outputDir}") {
        into("BOOT-INF/classes/static/docs")
    }
    from("swagger-ui") {
        into("BOOT-INF/classes/static/swagger")
    }
    from("build/api-spec") {
        into("BOOT-INF/classes/static/swagger")
    }
}

openapi3{
    setServer("http://localhost:8080")

    format = "yaml"
}
