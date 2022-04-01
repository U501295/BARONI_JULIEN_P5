package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class JSONReadTest {


    @Test
    void parsePerson() throws JsonProcessingException {
        String jsonPerson = "{\"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\"}" ;
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = objectMapper.readValue(jsonPerson,Person.class);
        Assertions.assertThat(person.getFirstName()).isEqualTo("John");
    }

    @Test
    void parseFireStation() throws JsonProcessingException {
        String jsonFireStation = "{ \"address\":\"1509 Culver St\", \"station\":\"3\" }";
        ObjectMapper objectMapper = new ObjectMapper();
        FireStation result = objectMapper.readValue(jsonFireStation, FireStation.class);
        Assertions.assertThat(result.getAddress()).isEqualTo("1509 Culver St");
    }

    @Test
    void parseMedicalRecord() throws JsonProcessingException {
        String json = " { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] }" ;
        ObjectMapper objectMapper = new ObjectMapper();
        MedicalRecord result = objectMapper.readValue(json, MedicalRecord.class);
        Assertions.assertThat(result.getAllergies()).isNotEmpty();
        Assertions.assertThat(result.getAllergies().size()).isEqualTo(3);
    }

    @Test
    void parseDataIntoList() throws IOException {
        Path path = Paths.get("src\\main\\resources\\JSON\\persons.json");
        List<String> lines = Files.readAllLines(path);
        String jsonContent = String.join("", lines);
        ObjectMapper objectMapper = new ObjectMapper();
        InputData result = objectMapper.readValue(jsonContent, InputData.class);
        Assertions.assertThat(result.getPersons().size()).isEqualTo(23);
        //Assertions.assertThat(result.getPersons().get(4));
        //Assertions.assertThat(result.getAllergies().size()).isEqualTo(3);
    }



}


