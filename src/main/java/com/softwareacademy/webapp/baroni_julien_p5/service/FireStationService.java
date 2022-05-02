package com.softwareacademy.webapp.baroni_julien_p5.service;


import com.softwareacademy.webapp.baroni_julien_p5.controller.exception.NoDataFoundException;
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
    //on se sert d'un compteur appelé flagOfRemoval pour déterminer si la donné est matchée ou non et lancer une exception le cas échant
    public List<FireStation> removeMapping(List<FireStation> fireStations, String address, Integer station) {
        List<FireStation> output = new ArrayList<>();
        Iterator<FireStation> itr = fireStations.iterator();
        Integer flagOfRemoval = 0;
        while (itr.hasNext()) {
            FireStation iteratedFireStation = itr.next();
            if (iteratedFireStation.getAddress().equals(address) && iteratedFireStation.getStation().equals(station)) {
                itr.remove();
                flagOfRemoval = 1;
            } else {
                output.add(iteratedFireStation);
            }
        }
        if (flagOfRemoval.equals(1)) {
            log.info("Deleted firestation mapping");
        } else {
            throw new NoDataFoundException();
        }

        return output;
    }


    public List<FireStation> addMapping(List<FireStation> fireStations, String address, Integer station) {
        fireStation = new FireStation(address, station);
        fireStations.add(fireStation);
        log.info("Added firestation mapping");
        return fireStations;
    }

    //utilisation d'un iterator qui permet de modifier la liste pendant qu'on est entrain de la parcourir
    //on se sert d'un compteur appelé flagOfRemoval pour déterminer si la donné est matchée ou non et lancer une exception le cas échant
    public List<FireStation> modifyMapping(List<FireStation> fireStations, String address, Integer station, FireStation fireStation) {

        List<FireStation> output = new ArrayList<>();
        Iterator<FireStation> itr = fireStations.iterator();
        Integer flagOfModification = 0;
        while (itr.hasNext()) {
            FireStation iteratedFireStation = itr.next();
            if (iteratedFireStation.getAddress().equals(address) && iteratedFireStation.getStation().equals(station)) {
                iteratedFireStation.setAddress(fireStation.getAddress());
                iteratedFireStation.setStation(fireStation.getStation());
                output.add(iteratedFireStation);
                flagOfModification = 1;
            } else {
                output.add(iteratedFireStation);
            }
        }
        if (flagOfModification.equals(1)) {
            log.info("Modified firestation mapping");
        } else {
            throw new NoDataFoundException();
        }

        return output;

        /* fireStations.forEach(objectToDealWith -> {
            if (objectToDealWith.getAddress().equals(address) && objectToDealWith.getStation().equals(station)) {
                objectToDealWith.setAddress(fireStation.getAddress());
                objectToDealWith.setStation(fireStation.getStation());
            }

        });
        log.info("Modified firestation mapping");
        return fireStations;*/
    }
}
