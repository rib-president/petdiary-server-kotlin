import org.springframework.boot.gradle.tasks.bundling.BootJar

springBoot {
    mainClass.set("com.petdiary.PetdiaryServerApplication")
}

dependencies {
    implementation(project(":domain-user"))
    implementation(project(":domain-system"))
}

tasks.getByName<BootJar>("bootJar") {
    enabled = true
}