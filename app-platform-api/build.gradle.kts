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
}

tasks.getByName<BootJar>("bootJar") {
    enabled = true
}