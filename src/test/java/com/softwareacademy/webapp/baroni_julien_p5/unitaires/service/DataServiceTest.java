package com.softwareacademy.webapp.baroni_julien_p5.unitaires.service;

import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.*;
import com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.service.DataService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.*;

class DataServiceTest {

    DataService dataService = new DataService();

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
        List<FireStationDTO> outputList = dataService.returnPersonsCoveredByFireStation(3);
        Assertions.assertThat(outputList).isNotEmpty();

    }

    @Test
    void countAdultsAndChildrenWhenIsGivenPersons(){
        List<Integer> output = dataService.countAdultsAndChildren(3);
        Assertions.assertThat(output).isNotEmpty();
    }

    @Test
    void getMembersLivingAtAnAdress(){
        List<String[]> houseMembers = dataService.getHouseMembers("1509 Culver St");
        Assertions.assertThat(houseMembers.size()).isEqualTo(5);
    }

    @Test
    void getPhoneNumberOfMembersFromAnAdress(){
        List<PhoneDTO> outputList = dataService.returnPhoneListCoveredByFireStation(3);
        Assertions.assertThat(outputList.size()).isEqualTo(11);
    }

    @Test
    void returnHabitantsListLivingAtAnAddressWithOneUnhabitant(){
        List<FireDTO> outputList = dataService.returnHabitantsListLivingAtAnAddress("834 Binoc Ave");
        Assertions.assertThat(outputList.size()).isEqualTo(1);
    }

    @Test
    void returnHabitantsListLivingAtAnAddressWithMultipleUnhabitants(){
        List<FireDTO> outputList = dataService.returnHabitantsListLivingAtAnAddress("1509 Culver St");
        Assertions.assertThat(outputList.size()).isEqualTo(5);
    }

    //TODO : voir pourquoi il y a des doublons
    @Test
    void returnPersonsAndAdressCoveredByFireStationsDuringFlood(){
        List<Integer> stationsNumbers = new ArrayList<>();
        stationsNumbers.add(1);
        stationsNumbers.add(2);
        List <FloodPersonsAndAdressDTO> outputList = dataService.returnPersonsAndAdressCoveredByFireStationsDuringFlood(stationsNumbers);
        Assertions.assertThat(outputList.size()).isEqualTo(6);
    }



    @Test
    void returnPersonInfos(){
        PersonInfoDTO personInfos = dataService.returnPersonInfos("Kendrik", "Stelzer");
        Assertions.assertThat(personInfos).isNotNull();
        Assertions.assertThat(personInfos.getAge()).isEqualTo(8);
    }

    @Test
    void returnCityUnhabitantsEmailWithNonExistingCity(){
        List<PersonEmailDTO> outputList = dataService.returnCityEmailAddresses("City");
        Assertions.assertThat(outputList).isEmpty();
    }

    @Test
    void returnCityUnhabitantsEmailWithExistingCity(){
        List<PersonEmailDTO> outputList = dataService.returnCityEmailAddresses("Culver");
        Assertions.assertThat(outputList).isNotEmpty();
    }







}


