package com.softwareacademy.webapp.baroni_julien_p5.controller.endpoints;

import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.FireStationACCountDTO;
import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.FireStationDTO;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.service.DataService;
import com.softwareacademy.webapp.baroni_julien_p5.service.FireStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Controller utilisant les services CRUD pour la gestion de la partie FireStation du document d'input.
 * http://localhost:8080/firestation/
 * <p>
 */
@RestController
@RequestMapping("/")
@Slf4j
public class FireStationController {


    @Autowired
    FireStationService fireStationService;

    @DeleteMapping(value = "firestation/{address}/{station}")
    public List<FireStation> deleteFireStation(@PathVariable String address, @PathVariable Integer station) {
        log.info("request to delete firestation number ={} from address{}", address, station);
        List<FireStation> fireStations = InputData.INSTANCE.getFirestationsData();
        return fireStationService.removeMapping(fireStations, address, station);

    }

    @PostMapping(value = "firestation")
    public List<FireStation> postFireStation(@RequestBody FireStation firestation) {
        log.info("request to create firestation number ={} from address{}", firestation.getStation(), firestation.getAddress());
        List<FireStation> fireStations = InputData.INSTANCE.getFirestationsData();
        return fireStationService.addMapping(fireStations, firestation.getAddress(), firestation.getStation());

    }

    @PutMapping(value = "firestation/{address}/{station}")
    public List<FireStation> modifyFireStation(@PathVariable String address, @PathVariable Integer station, @RequestBody FireStation firestation) {
        log.info("request to modify firestation number ={} from address{}", firestation.getStation(), address);
        List<FireStation> fireStations = InputData.INSTANCE.getFirestationsData();
        return fireStationService.modifyMapping(fireStations, address, station, firestation);

    }


}
