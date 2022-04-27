package com.softwareacademy.webapp.baroni_julien_p5.integration;

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Tests d'intégrations permettant de dérouler les services CRUDS les uns à la suites des autres
 * dans une logique de test fonctionnel. Afin de valider la non permanence des modifications en base.
 * On compare nos modifications avec une version vierge de la base de donnée afin de s'assurer de leur validité.
 * <p>
 */

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
    static List<FireStation> fsRefCompare = InputData.getJsonData().getFirestationsData();

    FireStation fireStationObjectforIntegrationTest = new FireStation("Integration Street", 333);


    @Test
    void parcoursUtilisateurPassant() {
        List<FireStation> result = fireStationService.removeMapping(fsJsonData, "1509 Culver St", 3);
        Assertions.assertThat(result.size()).isEqualTo(fsRefCompare.size() - 1);
        result = fireStationService.addMapping(fsJsonData, "1509 Culver St", 3);
        Assertions.assertThat(result.size()).isEqualTo(fsRefCompare.size());
        result = fireStationService.modifyMapping(fsJsonData, "1509 Culver St", 3, fireStationObjectforIntegrationTest);
        Assertions.assertThat(result.get(11).getStation()).isEqualTo(333);
    }

}