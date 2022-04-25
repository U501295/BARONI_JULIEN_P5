package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.softwareacademy.webapp.baroni_julien_p5.controller.exception.NoDataFoundException;
import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.*;
import com.softwareacademy.webapp.baroni_julien_p5.service.DataService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Tests unitaires permettant de s'assurer que la couche service est fonctionnelle
 * <p>
 */


class DataServiceTest {

    DataService dataService = new DataService();

    @Test
    void whenGetAgeIsGivenADateFromThisYearReturnZero() {
        DataService dS = new DataService();
        Calendar dateOfBirth = Calendar.getInstance();
        Integer resultAge = dS.getAge(dateOfBirth);
        Integer expectedAge = 0;
        Assertions.assertThat(resultAge).isEqualTo(expectedAge);
    }

    @Test
    void whenGetAgeIsGivenADateFrom22YearsBackReturn22() {
        DataService dS = new DataService();
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.set(1, 2000);
        Integer resultAge = dS.getAge(dateOfBirth);
        Integer expectedAge = 22;
        Assertions.assertThat(resultAge).isEqualTo(expectedAge);
    }

    @Test
    void returnPersonsCoveredByFireStationWhenAStationIsGiven() {
        List<FireStationDTO> outputList = dataService.returnPersonsCoveredByFireStation(3);
        Assertions.assertThat(outputList).isNotEmpty();

    }

    @Test
    void returnChildrenAndHouseMembersLivingAtAnAddress() {
        List<ChildDTO> outputList = dataService.returnChildrenAndHouseMembersLivingAtAnAddress("892 Downing Ct");
        Assertions.assertThat(outputList.size()).isEqualTo(1);

    }

    @Test
    void countAdultsAndChildrenWhenIsGivenPersons() {
        List<Integer> output = dataService.countAdultsAndChildren(3);
        Assertions.assertThat(output).isNotEmpty();
    }

    //TODO : voir comment c'est possible que deux stations aient la même adresse
    @Test
    void returnFireStationNumberCoveringTheAddress() {
        Integer output = dataService.returnFireStationNumberCoveringTheAddress("112 Steppes Pl");
        Assertions.assertThat(output).isEqualTo(4);
    }

    @Test
    void getMembersLivingAtAnAdress() {
        List<String[]> houseMembers = dataService.getHouseMembers("112 Steppes Pl", "Tony", "Cooper");
        Assertions.assertThat(houseMembers.size()).isEqualTo(2);
    }

    @Test
    void getPhoneNumberOfMembersFromAnAdress() {
        List<PhoneDTO> outputList = dataService.returnPhoneListCoveredByFireStation(4);
        Assertions.assertThat(outputList.size()).isEqualTo(4);
    }

    @Test
    void returnHabitantsListLivingAtAnAddressWithOneUnhabitant() {
        List<FireDTO> outputList = dataService.returnHabitantsListLivingAtAnAddress("834 Binoc Ave");
        Assertions.assertThat(outputList.size()).isEqualTo(1);
    }

    @Test
    void returnHabitantsListLivingAtAnAddressWithMultipleUnhabitants() {
        List<FireDTO> outputList = dataService.returnHabitantsListLivingAtAnAddress("112 Steppes Pl");
        Assertions.assertThat(outputList.size()).isEqualTo(3);
    }

    @Test
    void returnPersonsAndAdressCoveredByFireStationsDuringFlood() {
        List<Integer> stationsNumbers = new ArrayList<>();
        stationsNumbers.add(1);
        stationsNumbers.add(2);
        List<FloodPersonsAndAdressDTO> outputList = dataService.returnPersonsAndAdressCoveredByFireStationsDuringFlood(stationsNumbers);
        Assertions.assertThat(outputList.size()).isEqualTo(6);
    }

    @Test
    void returnPersonInfos() {
        PersonInfoDTO personInfos = dataService.returnPersonInfos("Kendrik", "Stelzer");
        Assertions.assertThat(personInfos).isNotNull();
        Assertions.assertThat(personInfos.getAge()).isEqualTo(8);
    }

    @Test
    void returnCityUnhabitantsEmailWithNonExistingCity() {
        try {
            List<PersonEmailDTO> outputList = dataService.returnCityEmailAddresses("City");
        } catch (Exception e) {
            Assertions.assertThat(e)
                    .isInstanceOf(NoDataFoundException.class);
        }
    }

    @Test
    void returnCityUnhabitantsEmailWithExistingCity() {
        List<PersonEmailDTO> outputList = dataService.returnCityEmailAddresses("Culver");
        Assertions.assertThat(outputList).isNotEmpty();
    }


}


