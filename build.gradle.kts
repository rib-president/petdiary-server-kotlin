import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"

    // kotlin jvm으로 kotlin 코드 컴파일
    kotlin("jvm") version "1.9.22"
    // kotlin annotation processing로 컴파일 시간에 어노테이션 처리
    kotlin("kapt") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
}

allprojects {
    group = "com.petdiary"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    // java와 혼용하는 경우 @Nullable, @NonNull kotlin이 해석 더 빡세게 함
    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs += "-Xjsr305=strict"
                jvmTarget = "17"
            }
        }

        withType<JavaCompile> {
            sourceCompatibility = "17"
            targetCompatibility = "17"
        }
    }

}

subprojects {

    apply {
        plugin("kotlin")
        plugin("kotlin-kapt")
        plugin("org.springframework.boot")
        plugin("io.spring.dependency-management")
        plugin("kotlin-spring")
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-web")

        // serialization, deserialization
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        // reflection lib
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        runtimeOnly("com.mysql:mysql-connector-j:8.3.0")
        runtimeOnly("org.springframework.boot:spring-boot-devtools")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    tasks.test {
        useJUnitPlatform()
    }
}