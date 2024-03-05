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

tasks.test {
    finalizedBy("openapi3")
    finalizedBy("copyOasToSwagger")
}

openapi3 {
    setServer("http://localhost:8080")

    format = "yaml"
}

tasks.register<Copy>("copyOasToSwagger") {
    group = JavaBasePlugin.DOCUMENTATION_GROUP
    description = "openapi 파일 정적 경로에 복사"

    delete("src/main/resources/static/swagger-ui/openapi3.yaml") // 기존 OAS 파일 삭제

    from(layout.buildDirectory.file("api-spec/openapi3.yaml")) // 복제할 OAS 파일 지정

    into("src/main/resources/static/swagger-ui/.") // 타겟 디렉터리로 파일 복제

    dependsOn("openapi3") // openapi3 Task가 먼저 실행되도록 설정
}
