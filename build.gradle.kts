plugins {
  id("java")
  id("org.springframework.boot") version "3.0.3"
  id("io.spring.dependency-management") version "1.1.0"
}

group = "br.com.cobbledplugins"
version = "0.0.1-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-data-jpa")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.kafka:spring-kafka")
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

  implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")

  runtimeOnly("com.h2database:h2")

  compileOnly("org.projectlombok:lombok")
  annotationProcessor("org.projectlombok:lombok")

  testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks {
  test {
    useJUnitPlatform()
  }

  jar {
    enabled = false
  }
}
