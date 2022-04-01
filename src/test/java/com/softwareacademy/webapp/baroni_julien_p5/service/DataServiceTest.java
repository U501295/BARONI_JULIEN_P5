package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import jdk.internal.util.xml.impl.Input;
import jdk.nashorn.internal.parser.JSONParser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.core.io.Resource;
import org.json.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DataServiceTest {


    @Test
    void whenGetAgeIsGivenADateFromThisYearReturnZero(){
        DataService dS = new DataService();
        Calendar dateOfBirth = Calendar.getInstance();
        Integer resultAge = dS.getAge(dateOfBirth);
        Integer expectedAge = 0;
        Assertions.assertThat(resultAge).isEqualTo(expectedAge);
    }

    @Test
    void whenGetAgeIsGivenADateFrom22YearsBackReturn22(){
        DataService dS = new DataService();
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(1,2000);
        Integer resultAge = dS.getAge(dateOfBirth);
        Integer expectedAge = 22;
        Assertions.assertThat(resultAge).isEqualTo(expectedAge);
    }


    @Test
    void returnPersonsCoveredByFireStationWhenAStationIsGiven(){

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("JSON\\data.json");

        try{
            InputData inputData = objectMapper.readValue(file, InputData.class);
            String test = "endpoint1";
        }catch(IOException e) {

        }
        String test = "endpoint2";

        /*FireStation fireStation = new FireStation("",0);
        Resource resource;
        //"C:\\JBA\\methodo_dev\\SOFTWARE ACADEMY\\Projet_5\\BARONI_JULIEN_P5\\src\\main\\resources\\JSON\\
        try (InputStream input = new FileInputStream("C:\\JBA\\methodo_dev\\SOFTWARE ACADEMY\\Projet_5\\BARONI_JULIEN_P5\\src\\main\\resources\\JSON\\data.json")) {
            inputData = objectMapper.readValue(input, new TypeReference<InputData>() {
            });
        } catch (IOException e) {
            //throw Exception("Error when trying to fetch ressource");
        }
        String test = "endpoint";*/

    }
        /*ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream input = new FileInputStream("JSON/data.json")) {
            InputData inputData = mapper.readValue(input, new TypeReference<InputData>() {
            });
        } catch (IOException e) {
            //throw new JsonProcessingException("Error when trying to fetch ressource");
        }
        List<FireStation> fs = inputData.getFirestations();
        String test = "endpoint";
    }*/
     /*List<InputData> testFS = null;
        try(InputStream inputStream = resource.getInputStream()){
            testFS = mapper.readValue(inputStream, new TypeReference<InputData>(){});
        }catch(IOException e){
            //throw new JsonProcessingException("Error when trying to fetch ressource");
        }

        //when(inputData.getFirestations()).thenReturn(testFS);
    }*/

    @Test
    void getPersons() throws JsonProcessingException {
        List<Person> listOfPersons = new ArrayList<Person>(){};
        String jsonPerson = "{\"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\"}" ;



                //String path = "C:\\JBA\\methodo_dev\\SOFTWARE ACADEMY\\Projet_5\\BARONI_JULIEN_P5\\src\\main\\resources\\JSON\\persons.json";
                //byte[] jsonData = Files.readAllLines(Paths.get(path));
                ObjectMapper objectMapper = new ObjectMapper();
                Person person = objectMapper.readValue(jsonPerson,Person.class);
                Assertions.assertThat(person.getFirstName()).isEqualTo("John");

                //File file = new File("C:\\JBA\\methodo_dev\\SOFTWARE ACADEMY\\Projet_5\\BARONI_JULIEN_P5\\src\\main\\resources\\JSON\\persons.json");
                String test = "endpoint";


    }

    @Test
    void parseMedicalRecord() throws JsonProcessingException {
        List<Person> listOfPersons = new ArrayList<Person>(){};
        String json = " { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] }" ;



        //String path = "C:\\JBA\\methodo_dev\\SOFTWARE ACADEMY\\Projet_5\\BARONI_JULIEN_P5\\src\\main\\resources\\JSON\\persons.json";
        //byte[] jsonData = Files.readAllLines(Paths.get(path));
        ObjectMapper objectMapper = new ObjectMapper();
        MedicalRecord result = objectMapper.readValue(json, MedicalRecord.class);
        Assertions.assertThat(result.getAllergies()).isNotEmpty();
        Assertions.assertThat(result.getAllergies().size()).isEqualTo(3);

        //File file = new File("C:\\JBA\\methodo_dev\\SOFTWARE ACADEMY\\Projet_5\\BARONI_JULIEN_P5\\src\\main\\resources\\JSON\\persons.json");
        String test = "endpoint";


    }

    @Test
    void parseData() throws IOException {
        List<Person> listOfPersons = new ArrayList<Person>(){};
        Path path = Paths.get("src\\main\\resources\\JSON\\persons.json");
        //String json = " { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] }" ;
        List<String> lines = Files.readAllLines(path);
        String jsonContent = String.join("", lines);


        //String path = "C:\\JBA\\methodo_dev\\SOFTWARE ACADEMY\\Projet_5\\BARONI_JULIEN_P5\\src\\main\\resources\\JSON\\persons.json";
        //byte[] jsonData = Files.readAllLines(Paths.get(path));
        ObjectMapper objectMapper = new ObjectMapper();
        InputData result = objectMapper.readValue(jsonContent, InputData.class);
        Assertions.assertThat(result.getPersons().size()).isEqualTo(23);
        //Assertions.assertThat(result.getPersons().get(4));
        //Assertions.assertThat(result.getAllergies().size()).isEqualTo(3);

        //File file = new File("C:\\JBA\\methodo_dev\\SOFTWARE ACADEMY\\Projet_5\\BARONI_JULIEN_P5\\src\\main\\resources\\JSON\\persons.json");
        String test = "endpoint";


    }



}


