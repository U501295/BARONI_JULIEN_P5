package com.softwareacademy.webapp.baroni_julien_p5.controller;

import com.softwareacademy.webapp.baroni_julien_p5.controller.endpoints.MedicalRecordController;
import com.softwareacademy.webapp.baroni_julien_p5.controller.exception.NoDataFoundException;
import com.softwareacademy.webapp.baroni_julien_p5.service.MedicalRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Calendar;

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
@WebMvcTest(controllers = MedicalRecordController.class)
class MedicalRecordControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private MedicalRecordService medicalRecordService;

    @Test
    void deleteMedicalRecordBadRequest() throws Exception {
        when(medicalRecordService.removeMedicalRecord(anyList(), anyString(), anyString())).thenReturn(null);
        mockMvc.perform(delete("/medicalRecord/firstName&lastName"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void deleteMedicalRecordInternalError() throws Exception {
        when(medicalRecordService.removeMedicalRecord(anyList(), anyString(), anyString())).thenThrow(NoDataFoundException.class);
        mockMvc.perform(delete("/medicalRecord/firstName&4"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void deleteMedicalRecordNotFound() throws Exception {
        when(medicalRecordService.removeMedicalRecord(anyList(), anyString(), anyString())).thenReturn(null);
        mockMvc.perform(delete("/medicalRecord/firstName"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteMedicalRecordSuccessMockedInput() throws Exception {
        String inputJson = " { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] }";
        mockMvc.perform(
                        delete("/medicalRecord/Sophia&Zemicks")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void postMedicalRecordSuccessMockedInput() throws Exception {
        String inputJson = " { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] }";
        mockMvc.perform(
                        post("/medicalRecord/Sophia&Zemicks&1988-06-03T22:00:00&aznol&peanut")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void postMedicalRecordNotFound() throws Exception {
        String inputJson = " { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] }";
        mockMvc.perform(
                        post("/medicalRecord/Sophia")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void postMedicalRecordBadRequest() throws Exception {
        String inputJson = " { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] }";
        mockMvc.perform(
                        post("/medicalRecord/1&2")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void postMedicalRecordInternalError() throws Exception {
        String inputJson = " { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] }";
        mockMvc.perform(
                        post("/medicalRecord/Sophia")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void modifyMedicalRecordSuccessMockedInput() throws Exception {
        String inputJson = " { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] }";
        mockMvc.perform(
                        put("/medicalRecord/Sophia&Zemicks&1988-06-03T22:00:00&aznol&peanut")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void modifyMedicalRecordNotFound() throws Exception {
        String inputJson = " { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] }";
        mockMvc.perform(
                        put("/medicalRecord/")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void modifyMedicalRecordBadRequest() throws Exception {
        String inputJson = " { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] }";
        mockMvc.perform(
                        put("/medicalRecord/12")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }
}