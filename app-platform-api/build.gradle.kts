import org.springframework.boot.gradle.tasks.bundling.BootJar

springBoot {
    mainClass.set("com.petdiary.PetdiaryServerApplication")
}

tasks.getByName<BootJar>("bootJar") {
    enabled = true
}