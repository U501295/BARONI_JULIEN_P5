package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer.InputData;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class FireStationService {

    FireStation fireStation;

    public List<FireStation> removeMapping(String address, Integer station) {
        List<FireStation> fireStations = InputData.INSTANCE.getFirestationsData();
        Iterator<FireStation> itr = fireStations.iterator();
        while (itr.hasNext()) {
            FireStation iteratedFireStation = itr.next();
            if (iteratedFireStation.getAddress().equals(address)  && iteratedFireStation.getStation().equals(station)) {
                itr.remove();
            }
        }
        return fireStations;
    }


    public List<FireStation> addMapping(String address, Integer station) {
        List<FireStation> fireStations = InputData.INSTANCE.getFirestationsData();
        fireStation = new FireStation(address, station);
        fireStations.add(fireStation);
        return fireStations;
    }

    public List<FireStation> modifyMapping(String address, Integer station) {
        List<FireStation> fireStations = InputData.INSTANCE.getFirestationsData();
        fireStations.forEach(objectToDealWith -> {
            if (objectToDealWith.getAddress().equals(address) ) {
                objectToDealWith.setStation(station);
            }

        });
        return fireStations;
    }
}
