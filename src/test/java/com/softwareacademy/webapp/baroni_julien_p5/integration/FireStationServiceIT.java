package com.softwareacademy.webapp.baroni_julien_p5.integration;

import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.service.FireStationService;
import org.assertj.core.api.Assertions;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
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

    static List<FireStation> fsJsonData = InputData.getJsonData().getFirestationsData();
    static List<FireStation> fsRefCompare = InputData.INSTANCE.getFirestationsData();

    @Test
    @Order(1)
    void parcoursUtilisateur() {
        List<FireStation> result = fireStationService.removeMapping(fsJsonData, "1509 Culver St", 3);
        Assertions.assertThat(result.size()).isEqualTo(fsRefCompare.size() - 1);
        result = fireStationService.addMapping(fsJsonData, "1509 Culver St", 3);
        Assertions.assertThat(result.size()).isEqualTo(fsRefCompare.size());
        result = fireStationService.modifyMapping(fsJsonData, "1509 Culver St", 333);
        Assertions.assertThat(result.get(11).getStation()).isEqualTo(333);
    }

}