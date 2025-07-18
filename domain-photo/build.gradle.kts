import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    implementation(project(":domain-pet"))
    implementation(project(":domain-user"))
    implementation(project(":domain-system"))
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}
