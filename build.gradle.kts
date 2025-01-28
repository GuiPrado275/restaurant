plugins {
	java
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"
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
	// Dependências principais
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security:3.1.5")
	implementation("org.springframework.boot:spring-boot-starter-web")

	// Lombok
	compileOnly("org.projectlombok:lombok:1.18.30")
	annotationProcessor("org.projectlombok:lombok:1.18.30")
	testCompileOnly("org.projectlombok:lombok:1.18.30")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.30")

	// Validação e Hibernate Validator
	implementation("jakarta.validation:jakarta.validation-api:3.0.2")
	implementation("org.hibernate.validator:hibernate-validator:8.0.0.Final")

	// Banco de dados
	runtimeOnly("com.h2database:h2") // Para testes
	runtimeOnly("mysql:mysql-connector-java:8.0.33") // Em produção

	// DevTools para hot reload (opcional)
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// Testes
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	implementation("com.zaxxer:HikariCP")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
