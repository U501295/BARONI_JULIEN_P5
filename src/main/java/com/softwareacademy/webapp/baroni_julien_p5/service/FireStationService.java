package com.softwareacademy.webapp.baroni_julien_p5.service;


import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Services CRUD pour la gestion de la partie FireStation du document d'input.
 * http://localhost:8080/firestation/
 * <p>
 */

@Slf4j
@Service
public class FireStationService {

    FireStation fireStation;


    //utilisation d'un iterator qui permet de modifier la liste pendant qu'on est entrain de la parcourir
    public List<FireStation> removeMapping(List<FireStation> fireStations, String address, Integer station) {
        List<FireStation> output = new ArrayList<>();
        Iterator<FireStation> itr = fireStations.iterator();
        while (itr.hasNext()) {
            FireStation iteratedFireStation = itr.next();
            if (iteratedFireStation.getAddress().equals(address) && iteratedFireStation.getStation().equals(station)) {
                itr.remove();
            } else {
                output.add(iteratedFireStation);
            }
        }
        log.info("Deleted firestation mapping");
        return output;
    }


    public List<FireStation> addMapping(List<FireStation> fireStations, String address, Integer station) {
        List<FireStation> output = fireStations;
        fireStation = new FireStation(address, station);
        output.add(fireStation);
        log.info("Added firestation mapping");
        return output;
    }

    public List<FireStation> modifyMapping(List<FireStation> fireStations, String address, Integer station) {
        fireStations.forEach(objectToDealWith -> {
            if (objectToDealWith.getAddress().equals(address)) {
                objectToDealWith.setStation(station);
            }

        });
        List<FireStation> output = fireStations;
        log.info("Modified firestation mapping");
        return output;
    }
}
