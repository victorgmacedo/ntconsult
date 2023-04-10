import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.5"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	kotlin("plugin.jpa") version "1.7.22"
	kotlin("kapt") version "1.7.22"
}

group = "br.com.ntconsult.challenge"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-amqp")
	implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
	implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")
	implementation("org.springframework.cloud:spring-cloud-starter-config")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.hypersistence:hypersistence-utils-hibernate-60:3.3.1")
	implementation("org.mapstruct:mapstruct:1.5.3.Final")
	implementation("org.flywaydb:flyway-core")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
	kapt("org.mapstruct:mapstruct-processor:1.5.3.Final")
	runtimeOnly("org.postgresql:postgresql")
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testAnnotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")
}

kapt {
	arguments {
		arg("mapstruct.defaultComponentModel", "spring")
	}
}

dependencyManagement {
	imports {
		mavenBom ("org.springframework.cloud:spring-cloud-dependencies:2022.0.1")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
