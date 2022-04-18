package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import org.assertj.core.api.Assertions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    void parseFireStationList() throws IOException {
        InputData result = InputData.getJsonData();
        Assertions.assertThat(result.getFirestationsData()).isNotEmpty();
        Assertions.assertThat(result.getFirestationsData().get(4).getAddress()).isEqualTo("748 Townings Dr");
    }

    @Test
    void parsePersonsList() throws IOException {
        InputData result = InputData.getJsonData();
        Assertions.assertThat(result.getPersonsData()).isNotEmpty();
        Assertions.assertThat(result.getPersonsData().get(4).getFirstName()).isEqualTo("Felicia");
    }

    @Test
    void parseMedicalRecordsList() throws IOException, JSONException {
        Path path = Paths.get("src\\main\\resources\\JSON\\data.json");
        List<String> lines = Files.readAllLines(path);
        String jsonContent = String.join("", lines);
        JSONObject jsonObject = new JSONObject(jsonContent);
        JSONArray jsonArray = jsonObject.getJSONArray("medicalrecords");
        //TODO : voir pourquoi on arrive pas à faire rentrer le JSONObject dans le formalisme de MedicalRecord
        //MedicalRecord mc = (MedicalRecord) jsonArray.getJSONObject(4);
        JSONObject medicalRecord =jsonArray.getJSONObject(4);
        JSONArray medications = (JSONArray) medicalRecord.get("medications");
        Assertions.assertThat(medications.get(0)).isEqualTo("tetracyclaz:650mg");
    }

    @Test
    void getJsonData() {
        InputData result = InputData.getJsonData();
        Assertions.assertThat(result.getClass()).isNotNull();
    }

    @Test
    void parseDataIntoLists() throws IOException {
        InputData result = InputData.getJsonData();
        Assertions.assertThat(result.getPersonsData().size()).isEqualTo(23);
        Assertions.assertThat(result.getFirestationsData().size()).isEqualTo(12);
        Assertions.assertThat(result.getMedicalrecordsData().size()).isEqualTo(23);

    }



}


