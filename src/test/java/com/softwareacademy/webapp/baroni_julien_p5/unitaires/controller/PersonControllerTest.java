package com.softwareacademy.webapp.baroni_julien_p5.unitaires.controller;

import com.softwareacademy.webapp.baroni_julien_p5.controller.endpoints.PersonController;
import com.softwareacademy.webapp.baroni_julien_p5.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Tests unitaires permettant de s'assurer que la couche controller communique de la manière souhaitée.
 * <p>
 */
@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @Test
    void deletePerson() throws Exception {
        when(personService.removePerson(anyList(), anyString(), anyString())).thenReturn(null);
        mockMvc.perform(delete("/person/firstName&lastName"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void postPerson() throws Exception {
        String inputJson = "{\"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\"}";
        mockMvc.perform(
                        post("/person/John&Boyd&1509 Culver St&Culver&97451&841-874-6512&jaboyd@email.com")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void modifyPerson() throws Exception {
        String inputJson = "{\"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\"}";
        mockMvc.perform(
                        put("/person/John&Boyd&1509 Culver St&Culver&97451&841-874-6512&jaboyd@email.com")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }
}