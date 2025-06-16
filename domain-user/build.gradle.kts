import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    implementation(project(":domain-system"))
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}