package com.petdiary

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan("com.petdiary.domain.entity.**")
class PetdiaryServerApplication

fun main(args: Array<String>) {
    runApplication<PetdiaryServerApplication>(*args)
}