package com.softwareacademy.webapp.baroni_julien_p5;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwareacademy.webapp.baroni_julien_p5.model.Person;
import com.softwareacademy.webapp.baroni_julien_p5.service.PersonService;

import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class BaroniJulienP5Application {

    public static void main(String[] args) {
        SpringApplication.run(BaroniJulienP5Application.class, args);
    }


    }




