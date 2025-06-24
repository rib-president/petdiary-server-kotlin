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

    implementation("org.springframework.security:spring-security-core:6.2.4")
    implementation("org.springframework.security:spring-security-web:6.2.4")
    implementation("org.springframework.security:spring-security-config:6.2.4")

    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
}

tasks.getByName<BootJar>("bootJar") {
    enabled = true
}