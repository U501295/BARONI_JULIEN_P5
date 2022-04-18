package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer.InputData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class FireStationServiceTest {

    FireStationService fireStationService = new FireStationService();

    @Test
    void removeMapping() {
        List<FireStation> fireStations = InputData.INSTANCE.getFirestationsData();
        Assertions.assertThat(fireStations.size()).isEqualTo(12);
        List<FireStation> result = fireStationService.removeMapping("1509 Culver St",3);
        Assertions.assertThat(result.size()).isEqualTo(11);
    }

    @Test
    void addMapping() {
        List<FireStation> fireStations = InputData.INSTANCE.getFirestationsData();
        Assertions.assertThat(fireStations.size()).isEqualTo(12);
        List<FireStation> result =fireStationService.addMapping("test adress street",4);
        Assertions.assertThat(result.size()).isEqualTo(13);

    }

    @Test
    void modifyMapping() {
        List<FireStation> fireStations = InputData.INSTANCE.getFirestationsData();
        Assertions.assertThat(fireStations.get(4).getStation()).isEqualTo(3);
        Assertions.assertThat(fireStations.get(4).getAddress()).isEqualTo("748 Townings Dr");
        List<FireStation> result =fireStationService.modifyMapping("748 Townings Dr",0);
        Assertions.assertThat(result.get(4).getStation()).isEqualTo(0);
    }

    @Test
    void modifyMappingWhenAdressIsNotKnown(){
        /*fireStationService.modifyMapping(testJDD,"Unknown adress", 0);
        for (FireStation itr : testJDD){
            Assertions.assertThat(itr.getStation()).isNotEqualTo(0);
        }
*/
    }
}