plugins {
	java
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"
	id("java")
	id("io.freefair.lombok") version "8.1.0"
}

group = "com.restaurant"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security:3.1.5")
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("com.h2database:h2")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	compileOnly("org.projectlombok:lombok:1.18.30") // Use a versão mais recente do Lombok
	annotationProcessor("org.projectlombok:lombok:1.18.30")
	testCompileOnly("org.projectlombok:lombok:1.18.30")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.30")
	implementation("jakarta.validation:jakarta.validation-api:3.0.2") // Versão mais recente
	implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final") // Implementação de referência
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.zaxxer:HikariCP")
	implementation("mysql:mysql-connector-java:8.0.29")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
