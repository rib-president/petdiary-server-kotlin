import org.springframework.boot.gradle.tasks.bundling.BootJar

dependencies {
    implementation(project(":domain-pet"))
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}