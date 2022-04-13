package com.softwareacademy.webapp.baroni_julien_p5.controller;

import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.FireStationACCountDTO;
import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.FireStationDTO;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.service.DataService;
import com.softwareacademy.webapp.baroni_julien_p5.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")

public class FireStationController {

    @Autowired
    FireStationService fireStationService;


    @DeleteMapping(value = "firestation/{address}&{stationNumber}")
    public List<FireStation> deleteFireStation(@PathVariable String address, @PathVariable Integer stationNumber){
        List<FireStation> fireStations = InputData.INSTANCE.getFirestationsData();
        return fireStationService.removeMapping(fireStations,address, stationNumber);

    }

    @PostMapping(value = "firestation/{address}&{stationNumber}")
    public List<FireStation> postFireStation(@PathVariable String address, @PathVariable Integer stationNumber){
        List<FireStation> fireStations = InputData.INSTANCE.getFirestationsData();
        return fireStationService.addMapping(fireStations,address, stationNumber);

    }

    @PutMapping(value = "firestation/{address}&{stationNumber}")
    public List<FireStation> modifyFireStation(@PathVariable String address, @PathVariable Integer stationNumber){
        List<FireStation> fireStations = InputData.INSTANCE.getFirestationsData();
        return fireStationService.modifyMapping(fireStations,address, stationNumber);

    }



}
