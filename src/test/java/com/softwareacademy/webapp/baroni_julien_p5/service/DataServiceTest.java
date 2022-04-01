package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DataServiceTest {

    @Value("classpath:JSON/data.json")
    Resource resource;
    //@Mock
    //private static InputData inputData;
    @Mock
    DataService dataService;






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
    void returnPersonsCoveredByFireStationWhenAStationIsGiven() {
        InputData inputData = new InputData();
        ObjectMapper objectMapper = new ObjectMapper();
        FireStation fireStation = new FireStation("",0);
        Resource resource;
        //"C:\\JBA\\methodo_dev\\SOFTWARE ACADEMY\\Projet_5\\BARONI_JULIEN_P5\\src\\main\\resources\\JSON\\
        try (InputStream input = new FileInputStream("C:\\JBA\\methodo_dev\\SOFTWARE ACADEMY\\Projet_5\\BARONI_JULIEN_P5\\src\\main\\resources\\JSON\\data.json")) {
            inputData = objectMapper.readValue(input, new TypeReference<InputData>() {
            });
        } catch (IOException e) {
            //throw new JsonProcessingException("Error when trying to fetch ressource");
        }
        String test = "endpoint";

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
}

        /*List<InputData> testFS = null;
        try(InputStream inputStream = resource.getInputStream()){
            testFS = mapper.readValue(inputStream, new TypeReference<InputData>(){});
        }catch(IOException e){
            //throw new JsonProcessingException("Error when trying to fetch ressource");
        }

        //when(inputData.getFirestations()).thenReturn(testFS);
    }*/
