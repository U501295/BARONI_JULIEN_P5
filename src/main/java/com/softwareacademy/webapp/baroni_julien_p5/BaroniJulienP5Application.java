package com.softwareacademy.webapp.baroni_julien_p5;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Cette application web permet d'envoyer des informations aux systèmes de services d'urgence.
 * Elle est developpée suivant les principes MVC et SOLID.
 * <p>
 *
 */

public class BaroniJulienP5Application {


    public static void main(String[] args) {
        SpringApplication.run(BaroniJulienP5Application.class, args);
    }

}




