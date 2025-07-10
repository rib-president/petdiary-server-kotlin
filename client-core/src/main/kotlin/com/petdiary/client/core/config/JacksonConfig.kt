package com.petdiary.client.core.config

import com.fasterxml.jackson.databind.Module
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfig {
    @Bean
    fun jdk8Module() : Module = Jdk8Module()
}