package com.softwareacademy.webapp.baroni_julien_p5.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ObjectMapper createObjectMapper(){

        return new ObjectMapper();
    }
}
