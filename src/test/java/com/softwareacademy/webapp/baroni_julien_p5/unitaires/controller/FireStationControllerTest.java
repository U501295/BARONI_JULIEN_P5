package com.softwareacademy.webapp.baroni_julien_p5.unitaires.controller;

import com.softwareacademy.webapp.baroni_julien_p5.controller.endpoints.FireStationController;
import com.softwareacademy.webapp.baroni_julien_p5.service.FireStationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Tests unitaires permettant de s'assurer que la couche controller communique de la manière souhaitée.
 * <p>
 */


@WebMvcTest(controllers = FireStationController.class)
class FireStationControllerTest {

    @Autowired
    public MockMvc mockMvc;


    @MockBean
    FireStationService fireStationService;


    @Test
    void deleteFireStation() throws Exception {
        mockMvc.perform(delete("/firestation/address&3"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void postFireStation() throws Exception {
        mockMvc.perform(post("/firestation/address&3"))
                //.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void modifyFireStation() throws Exception {
        mockMvc.perform(put("/firestation/address&3"))
                //.andDo(print())
                .andExpect(status().isOk());
    }
}