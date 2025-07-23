import org.springframework.boot.gradle.tasks.bundling.BootJar

springBoot {
    mainClass.set("com.petdiary.PetdiaryServerApplication")
}

dependencies {
    implementation(project(":client-core"))
    implementation(project(":domain-user"))
    implementation(project(":domain-pet"))
    implementation(project(":domain-photo"))
    implementation(project(":domain-diary"))
    implementation(project(":domain-record"))
    implementation(project(":domain-cms"))
    implementation(project(":domain-system"))
    implementation(project(":system-core"))

    implementation("org.springframework.boot:spring-boot-starter-validation")
    kapt("org.hibernate.validator:hibernate-validator")

    implementation("org.springframework.security:spring-security-core:6.2.4")
    implementation("org.springframework.security:spring-security-web:6.2.4")
    implementation("org.springframework.security:spring-security-config:6.2.4")

    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")

    // === AWS SDK 관련 의존성 추가 ===
    implementation(platform("software.amazon.awssdk:bom:2.25.14"))
    implementation("software.amazon.awssdk:secretsmanager")
    implementation("software.amazon.awssdk:auth")
    implementation("software.amazon.awssdk:regions")
}

tasks.getByName<BootJar>("bootJar") {
    enabled = true
}