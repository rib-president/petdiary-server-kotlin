package com.petdiary

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PetdiaryServerApplication

fun main(args: Array<String>) {
    runApplication<PetdiaryServerApplication>(*args)
}