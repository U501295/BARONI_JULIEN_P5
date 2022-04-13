package com.softwareacademy.webapp.baroni_julien_p5.integration;

import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.service.FireStationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class FireStationServiceIT {

    @Autowired
    FireStationService fireStationService;

    @Test
    void removeMapping() {
        List<FireStation> before = InputData.INSTANCE.getFirestationsData();
        List<FireStation> after = fireStationService.removeMapping(before, "1509 Culver St", 3);
    }

    @Test
    void addMapping() {
    }

    @Test
    void modifyMapping() {
    }
}