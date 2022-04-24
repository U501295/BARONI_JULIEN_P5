package com.softwareacademy.webapp.baroni_julien_p5.unitaires.controller;

import com.softwareacademy.webapp.baroni_julien_p5.controller.endpoints.MedicalRecordController;
import com.softwareacademy.webapp.baroni_julien_p5.service.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MedicalRecordController.class)
class MedicalRecordControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    MedicalRecordService medicalRecordService;

    @Test
    void deleteMedicalRecord() throws Exception {
        mockMvc.perform(delete("/medicalRecord/firstName&lastName"))
                //.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void postMedicalRecord() throws Exception {
        String inputJson = " { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] }";
        mockMvc.perform(
                        post("/medicalRecord/Sophia&Zemicks")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                //.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void modifyMedicalRecord() throws Exception {
        String inputJson = " { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] }";
        mockMvc.perform(
                        put("/medicalRecord/Sophia&Zemicks")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                //.andDo(print())
                .andExpect(status().isOk());
    }
}