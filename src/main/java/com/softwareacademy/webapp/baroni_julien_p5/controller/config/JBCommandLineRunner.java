package com.softwareacademy.webapp.baroni_julien_p5.controller.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.service.PersonService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Cette partie d'initialisation permet de s'assurer que les données utilisées par l'application sont bien présentes.
 * <p>
 */

@Component
@Slf4j
public class JBCommandLineRunner implements InitializingBean {
    Resource resource;

    public JBCommandLineRunner(@Value("classpath:JSON/data.json") Resource resource) {
        this.resource = resource;
    }

    @Override
    public void afterPropertiesSet() {
        ObjectMapper mapper = new ObjectMapper();
        log.debug("Fetching data from input :");
        try (InputStream inputStream = resource.getInputStream()) {
            //la partie chargée d'aller récupérer et sérialiser les données est située dans la classe InputData
            InputData inputData = mapper
                    .readValue(inputStream, new TypeReference<InputData>() {
                    });
            log.info("SUCCESS in fetching data");
        } catch (IOException e) {
            //si les données ne parviennent pas à être chargées on stoppe l'application en lançant une RuntimeError
            log.error("FAIL in fetching data : " + e.getMessage());
            throw new RuntimeException("Unable to save data : " + e.getMessage(), e);
        }
    }
}

