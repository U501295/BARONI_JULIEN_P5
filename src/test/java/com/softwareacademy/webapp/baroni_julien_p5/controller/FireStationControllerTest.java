package com.softwareacademy.webapp.baroni_julien_p5.controller;

import com.softwareacademy.webapp.baroni_julien_p5.controller.endpoints.FireStationController;
import com.softwareacademy.webapp.baroni_julien_p5.controller.exception.NoDataFoundException;
import com.softwareacademy.webapp.baroni_julien_p5.service.FireStationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
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
    private FireStationService fireStationService;


    @Test
    void deleteFireStationSuccessMockedInput() throws Exception {
        String jsonFireStation = "{ \"address\":\"1509 Culver St\", \"station\":\"3\" }";
        mockMvc.perform(
                        delete("/firestation/1509 Culver St&3")
                                .content(jsonFireStation)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteFireStationInternalError() throws Exception {
        when(fireStationService.removeMapping(anyList(), anyString(), anyInt())).thenThrow(NoDataFoundException.class);
        mockMvc.perform(delete("/firestation/address&3"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void deleteFireStationNotFound() throws Exception {
        when(fireStationService.removeMapping(anyList(), anyString(), anyInt())).thenReturn(null);
        mockMvc.perform(delete("/firestation/address"))
                .andExpect(status().isNotFound());
    }

    @Test
    void postFireStationBadRequest() throws Exception {
        when(fireStationService.addMapping(anyList(), anyString(), anyInt())).thenReturn(null);
        mockMvc.perform(post("/firestation/address&3"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void postFireStationSuccessMockedInput() throws Exception {
        String jsonFireStation = "{ \"address\":\"1509 Culver St\", \"station\":\"3\" }";
        mockMvc.perform(
                        post("/firestation/1509 Culver St&3")
                                .content(jsonFireStation)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void postFireStationNotFound() throws Exception {
        when(fireStationService.addMapping(anyList(), anyString(), anyInt())).thenReturn(null);
        mockMvc.perform(post("/firestation/address"))
                .andExpect(status().isNotFound());
    }

    @Test
    void modifyFireStationSuccess() throws Exception {
        when(fireStationService.modifyMapping(anyList(), anyString(), anyInt())).thenReturn(null);
        mockMvc.perform(put("/firestation/address&3"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void modifyFireStationSuccessMockedInput() throws Exception {
        String jsonFireStation = "{ \"address\":\"1509 Culver St\", \"station\":\"3\" }";
        mockMvc.perform(
                        put("/firestation/1509 Culver St&4")
                                .content(jsonFireStation)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    void modifyFireStationBadRequest() throws Exception {
        when(fireStationService.modifyMapping(anyList(), anyString(), anyInt())).thenReturn(null);
        mockMvc.perform(put("/firestation/address&4"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void modifyFireStationNotFound() throws Exception {
        when(fireStationService.modifyMapping(anyList(), anyString(), anyInt())).thenReturn(null);
        mockMvc.perform(put("/firestation/address"))
                .andExpect(status().isNotFound());
    }
}