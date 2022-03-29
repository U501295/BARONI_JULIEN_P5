package com.softwareacademy.webapp.baroni_julien_p5.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwareacademy.webapp.baroni_julien_p5.model.Person;
import com.softwareacademy.webapp.baroni_julien_p5.service.PersonService;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
public class ApplicationConfig {
    @Bean
    public ObjectMapper createObjectMapper(){

        return new ObjectMapper();
    }
}
