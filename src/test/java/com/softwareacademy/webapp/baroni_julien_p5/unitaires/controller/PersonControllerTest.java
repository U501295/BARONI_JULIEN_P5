package com.softwareacademy.webapp.baroni_julien_p5.unitaires.controller;

import com.softwareacademy.webapp.baroni_julien_p5.controller.endpoints.PersonController;
import com.softwareacademy.webapp.baroni_julien_p5.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    PersonService personService;

    @Test
    void deletePerson() throws Exception {
        mockMvc.perform(delete("/person/firstName&lastName"))
                //.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void postPerson() throws Exception {
        String inputJson = "{\"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\"}";
        mockMvc.perform(
                        post("/person/John&Boyd")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                //.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void modifyPerson() throws Exception {
        String inputJson = "{\"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\"}";
        mockMvc.perform(
                        put("/person/John&Boyd")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                //.andDo(print())
                .andExpect(status().isOk());
    }
}