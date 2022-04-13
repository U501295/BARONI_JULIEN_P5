package com.softwareacademy.webapp.baroni_julien_p5.service;


import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class FireStationService {

    FireStation fireStation;

    public List<FireStation> removeMapping(List<FireStation> fireStations, String address, Integer station) {
        List<FireStation> output = new ArrayList<>();
        Iterator<FireStation> itr = fireStations.iterator();
        while (itr.hasNext()) {
            FireStation iteratedFireStation = itr.next();
            if (iteratedFireStation.getAddress().equals(address)  && iteratedFireStation.getStation().equals(station)) {
                itr.remove();
            }else{
                output.add(iteratedFireStation);
            }
        }
        return output;
    }


    public List<FireStation> addMapping(List<FireStation> fireStations,String address, Integer station) {
        List<FireStation> output = fireStations;
        fireStation = new FireStation(address, station);
        output.add(fireStation);
        return output;
    }

    public List<FireStation> modifyMapping(List<FireStation> fireStations,String address, Integer station) {
        fireStations.forEach(objectToDealWith -> {
            if (objectToDealWith.getAddress().equals(address) ) {
                objectToDealWith.setStation(station);
            }

        });
        List<FireStation> output = fireStations;
        return output;
    }
}
