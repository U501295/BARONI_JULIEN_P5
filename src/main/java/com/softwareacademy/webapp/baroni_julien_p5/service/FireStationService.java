package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class FireStationService {

    FireStation fireStation;

    public void removeMapping(List<FireStation> fireStations, String address, Integer station) {
        Iterator<FireStation> itr = fireStations.iterator();
        while (itr.hasNext()) {
            FireStation iteratedFireStation = itr.next();
            if (iteratedFireStation.getAddress().equals(address)  && iteratedFireStation.getStation().equals(station)) {
                itr.remove();
            }
        }
    }


    public void addMapping(List<FireStation> fireStations,String address, Integer station) {
        fireStation = new FireStation(address, station);
        fireStations.add(fireStation);
    }

    public void modifyMapping(List<FireStation> fireStations,String address, Integer station) {
        fireStations.forEach(objectToDealWith -> {
            if (objectToDealWith.getAddress().equals(address) ) {
                objectToDealWith.setStation(station);
            }

        });

    }
}
