package com.softwareacademy.webapp.baroni_julien_p5.controller;

import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.FireStationACCountDTO;
import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.FireStationDTO;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.service.DataService;
import com.softwareacademy.webapp.baroni_julien_p5.service.FireStationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")

public class FireStationController {

    FireStationService fireStationService = new FireStationService();


    @RequestMapping(value = "firestation/",
            method = RequestMethod.DELETE,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<FireStation> deleteFireStation(@RequestParam String address, Integer stationNumber){
        return fireStationService.removeMapping(address, stationNumber);

    }

    @RequestMapping(value = "firestation/",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<FireStation> postFireStation(@RequestParam String address, Integer stationNumber){
        return fireStationService.addMapping(address, stationNumber);

    }

    @RequestMapping(value = "firestation/",
            method = RequestMethod.PUT,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<FireStation> modifyFireStation(@RequestParam String address, Integer stationNumber){
        return fireStationService.modifyMapping(address, stationNumber);

    }



}
