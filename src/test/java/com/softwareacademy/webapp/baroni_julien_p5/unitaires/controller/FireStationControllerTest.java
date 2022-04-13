package com.softwareacademy.webapp.baroni_julien_p5.unitaires.controller;

import com.softwareacademy.webapp.baroni_julien_p5.controller.FireStationController;
import com.softwareacademy.webapp.baroni_julien_p5.service.FireStationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FireStationController.class)
class FireStationControllerTest {

    @Autowired
    public MockMvc mockMvc;


    @MockBean
    FireStationService fireStationService;



    @Test
    void deleteFireStation() throws Exception {
        mockMvc.perform(delete("/firestation/address&3"))
                //.andDo(print())
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