import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    implementation("org.springframework.security:spring-security-core:6.2.4")
    implementation("org.springframework.security:spring-security-web:6.2.4")
    implementation("org.springframework.security:spring-security-config:6.2.4")

    implementation("io.jsonwebtoken:jjwt-api:0.12.3")

    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.3")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.3")
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}