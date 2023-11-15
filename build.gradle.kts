import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.asciidoctor.jvm.convert") version "3.3.2"

    val kotlinVersion = "1.9.0"

    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.jpa") version kotlinVersion
    kotlin("kapt") version kotlinVersion

    kotlin("plugin.allopen") version kotlinVersion
    kotlin("plugin.noarg") version kotlinVersion
}

group = "studio.kidari"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    all {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    }
}

repositories {
    mavenCentral()
}

allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

val asciidoctorExt by configurations.creating

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("mysql:mysql-connector-java:8.0.32")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("org.flywaydb:flyway-core")
    implementation("org.flywaydb:flyway-mysql")

    implementation("com.vladmihalcea:hibernate-types-60:2.20.0")
    implementation("com.infobip:infobip-spring-data-jpa-querydsl-boot-starter:8.1.2")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
    implementation("org.mapstruct:mapstruct:1.5.5.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.5.Final")
    kaptTest("org.mapstruct:mapstruct-processor:1.5.5.Final")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.testcontainers:testcontainers:1.19.1")
    testImplementation("org.testcontainers:junit-jupiter:1.19.1")
    testImplementation("org.testcontainers:mysql:1.19.1")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
}

val snippetsDir by extra {
    file("build/generated-snippets")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = "17"
        }
    }
    withType<Test> {
        testLogging {
            showStandardStreams = true
            showCauses = true
            showExceptions = true
            showStackTraces = true
        }
        useJUnitPlatform()
    }

    asciidoctor {
        dependsOn(test)
        configurations("asciidoctorExt")
        baseDirFollowsSourceFile()
        inputs.dir(snippetsDir)
    }
    bootJar {
        if (System.getProperty("env") == null || System.getProperty("env") == "dev") {
            dependsOn("asciidoctor")

            from("$buildDir/docs/asciidoc/") {
                into("static/docs")
            }
        }
    }
}
