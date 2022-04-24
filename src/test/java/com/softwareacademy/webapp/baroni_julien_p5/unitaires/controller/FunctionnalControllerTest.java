package com.softwareacademy.webapp.baroni_julien_p5.unitaires.controller;

import com.softwareacademy.webapp.baroni_julien_p5.controller.endpoints.FunctionnalController;
import com.softwareacademy.webapp.baroni_julien_p5.controller.exception.NoDataFoundException;
import com.softwareacademy.webapp.baroni_julien_p5.service.DataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FunctionnalController.class)
class FunctionnalControllerTest {

    NoDataFoundException noDataFoundException = new NoDataFoundException();


    @Autowired
    public MockMvc mockMvc;


    @MockBean
    DataService dataService;

    //TODO : faire des tests avec des cas non passants et des messages d'erreurs issus des exceptions


    @Test
    void getPersonsCoveredByFireStation() throws Exception {
        when(dataService.returnPersonsCoveredByFireStation(anyInt())).thenReturn(null);
        when(dataService.countAdultsAndChildren(anyInt())).thenReturn(Arrays.asList(5, 2));

        mockMvc.perform(get("/firestation?stationNumber=3"))
                .andExpect(status().isOk())
        //.andDo(print())
        ;

    }

    //TODO : faire des tests avec des cas non passants et des messages d'erreurs issus des exceptions
    /*
    @Test
    void getPersonsCoveredByFireStationWithError() throws Exception {
        when(dataService.returnPersonsCoveredByFireStation(anyInt())).thenThrow();
        when(dataService.countAdultsAndChildren(anyInt())).thenReturn(Arrays.asList(5, 2));

        mockMvc.perform(get("/firestation?stationNumber=36"))
                .andExpect(status().isNotFound())
        //.andDo(print())
        ;

    }*/

    @Test
    void getChildrenLivingAtGivenAdress() throws Exception {
        when(dataService.returnChildrenAndHouseMembersLivingAtAnAddress(anyString())).thenReturn(null);
        mockMvc.perform(get("/childAlert?address=nonexistingaddress"))
                .andExpect(status().isOk())
        //.andDo(print())
        ;
    }

    @Test
    void getPhoneNumbersCoveredByFireStation() throws Exception {
        when(dataService.returnPhoneListCoveredByFireStation(anyInt())).thenReturn(null);
        mockMvc.perform(get("/phoneAlert?firestation=3"))
                .andExpect(status().isOk())
        //.andDo(print())
        ;

    }

    @Test
    void getPersonsLivingAtGivenAddress() throws Exception {
        when(dataService.returnHabitantsListLivingAtAnAddress(anyString())).thenReturn(null);
        when(dataService.returnFireStationNumberCoveringTheAddress(anyString())).thenReturn(null);
        mockMvc.perform(get("/fire?address=nonexistingaddress"))
                .andExpect(status().isOk())
        //.andDo(print())
        ;
    }

    @Test
    void getPersonsAndAddressesCoveredByFireStationDuringFlood() throws Exception {
        when(dataService.returnPersonsAndAdressCoveredByFireStationsDuringFlood(anyList())).thenReturn(null);
        mockMvc.perform(get("/flood/stations?stations=1,2"))
                .andExpect(status().isOk())
        //.andDo(print())
        ;
    }

    @Test
    void getPersonInfo() throws Exception {
        when(dataService.returnPersonInfos(anyString(), anyString())).thenReturn(null);
        mockMvc.perform(get("http://localhost:8080/personInfo?firstName=firstName&lastName=lastName"))
                .andExpect(status().isOk())
        //.andDo(print())
        ;
    }

    @Test
    void testGetPersonInfo() throws Exception {
        when(dataService.returnCityEmailAddresses(anyString())).thenReturn(null);
        mockMvc.perform(get("http://localhost:8080/personInfo?firstName=firstName&lastName=lastName"))
                .andExpect(status().isOk())
        //.andDo(print())
        ;
    }
}