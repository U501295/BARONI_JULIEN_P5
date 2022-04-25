package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.softwareacademy.webapp.baroni_julien_p5.controller.exception.NoDataFoundException;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.service.FireStationService;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Tests unitaires permettant de s'assurer que la couche service est fonctionnelle
 * <p>
 */
class FireStationServiceTest {
    List<FireStation> testJDD = new ArrayList<>();
    FireStation fireStation1 = new FireStation("1509 Culver St", 3);
    FireStation fireStation2 = new FireStation("834 Binoc Ave", 3);
    FireStation fireStation3 = new FireStation("29 15th St", 2);
    FireStation fireStation4 = new FireStation("908 73rd St", 1);
    FireStation fireStation5 = new FireStation("PUT TEST", 10);
    FireStationService fireStationService = new FireStationService();


    @BeforeEach
    public void initEach() {
        testJDD.removeAll(testJDD);
        testJDD.add(fireStation1);
        testJDD.add(fireStation2);
        testJDD.add(fireStation3);
        testJDD.add(fireStation4);
    }

    @Test
    void removeMapping() {
        fireStationService.removeMapping(testJDD, "1509 Culver St", 3);
        Assertions.assertThat(testJDD.size()).isEqualTo(3);

    }

    @Test
    void addMapping() {
        fireStationService.addMapping(testJDD, "test adress street", 4);
        Assertions.assertThat(testJDD).isNotEmpty();

    }


    @Test
    void modifyMapping() {
        fireStationService.modifyMapping(testJDD, "834 Binoc Ave", 3, fireStation5);
        Assertions.assertThat(testJDD.get(1).getStation()).isEqualTo(10);
    }


    @Test
    void modifyMappingWhenAdressIsNotKnown() {
        try {
            Assertions.assertThat(fireStationService.modifyMapping(testJDD, "Unknown adress", 0, fireStation5));
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(NoDataFoundException.class);
        }


    }
}