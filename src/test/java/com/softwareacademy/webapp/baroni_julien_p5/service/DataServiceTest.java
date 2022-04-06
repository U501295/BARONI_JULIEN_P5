package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import com.softwareacademy.webapp.baroni_julien_p5.model.ODT.OutputDataListFormat;
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

import javax.persistence.criteria.CriteriaBuilder;
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

    DataService dataService = new DataService();
    @Mock
    @Autowired
    InputData inputData = new InputData();

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
    void returnPersonsCoveredByFireStationWhenAStationIsGiven() throws IOException {
        List<OutputDataListFormat> outputList = dataService.returnPersonsCoveredByFireStation(3);
        Assertions.assertThat(outputList).isNotEmpty();

    }

    @Test
    void countAdultsAndChildrenWhenIsGivenPersons(){
        List<OutputDataListFormat> mockOutputList = new ArrayList<OutputDataListFormat>();
        OutputDataListFormat mockedAdultOne = new OutputDataListFormat("JASON", "Statam", "Himalaya Street", "07654367", 0,1);
        OutputDataListFormat mockedChildrenOne = new OutputDataListFormat("Baby", "Bell", "Himalaya Street", "07654367", 1,0);
        OutputDataListFormat mockedChildrenTwo = new OutputDataListFormat("Bell", "Baby", "Himalaya Street", "07654367", 1,0);
        mockOutputList.add(mockedAdultOne);
        mockOutputList.add(mockedChildrenOne);
        mockOutputList.add(mockedChildrenTwo);
        Integer[] result = dataService.countAdultsAndChildren(mockOutputList);
        Integer numberOfAdults = result[0];
        Integer numberOfChildren = result[1];
        Assertions.assertThat(numberOfChildren).isEqualTo(2);
        Assertions.assertThat(numberOfAdults).isEqualTo(1);
    }

    @Test
    void getMembersLivingAtAnAdress(){
        List<String[]> houseMembers = dataService.getHouseMembers("1509 Culver St");
        Assertions.assertThat(houseMembers.size()).isEqualTo(5);
    }

    @Test
    void getPhoneNumberOfMembersFromAnAdress(){
        List<OutputDataListFormat> outputList = dataService.returnPhoneListCoveredByFireStation(11);
        Assertions.assertThat(outputList.size()).isEqualTo(5);
    }


}


