package com.softwareacademy.webapp.baroni_julien_p5.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.service.PersonService;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;


@Component
public class JBCommandLineRunner implements InitializingBean{
    PersonService personService;
    Resource resource;
    @Autowired
    ObjectMapper mapper;
    public JBCommandLineRunner (PersonService personService, @Value("classpath:JSON/data.json")  Resource resource){
        this.personService = personService;
        this.resource = resource;
    }

    @Override
    public void afterPropertiesSet() {
        //creer le bean et l'injecter en passant par la class configuration

        //TypeReference<List<Person>> typeReference = new TypeReference<List<Person>>(){};


        try(InputStream inputStream = resource.getInputStream()) {
            InputData inputData = mapper.readValue(inputStream, new TypeReference<InputData>(){});
            System.out.println("JSON found with"+inputData.getPersons().size()+"persons");
            inputData.getPersons().forEach(e ->personService.save(e));
            //listPerson.forEach(personService::save);
            System.out.println("JSON saved into db");
        }catch(IOException e){
            System.out.println("Unable to save data : " + e.getMessage());
            throw new RuntimeException("Unable to save data : " + e.getMessage(),e);
        }
    }
}

