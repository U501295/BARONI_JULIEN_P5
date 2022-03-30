package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.model.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Person;
import org.springframework.stereotype.Service;

@Service
public class FireStationService {

    InputData inputData = new InputData();
    FireStation fireStation;

    public void removeMapping(String address, Integer station) {
        inputData.getFirestations().forEach(objectToDealWith -> {
            if (objectToDealWith.getAddress().equals(address)  && objectToDealWith.getStation().equals(station)) {
                inputData.getFirestations().remove(objectToDealWith);
            }
        });
    }

    public void addMapping(String address, Integer station) {
        fireStation = new FireStation(address, station);
        inputData.getFirestations().add(fireStation);
    }

    public void modifyMapping(String address, Integer station) {
        inputData.getFirestations().forEach(objectToDealWith -> {
            if (objectToDealWith.getAddress().equals(address) ) {
                objectToDealWith.setStation(station);
            }

        });

    }
}
