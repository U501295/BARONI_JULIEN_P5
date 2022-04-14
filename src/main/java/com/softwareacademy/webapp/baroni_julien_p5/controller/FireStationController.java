package com.softwareacademy.webapp.baroni_julien_p5.controller;

import com.softwareacademy.webapp.baroni_julien_p5.model.ODT.OutputDataListFormat;
import com.softwareacademy.webapp.baroni_julien_p5.service.DataService;
import com.softwareacademy.webapp.baroni_julien_p5.service.FireStationService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;


@RestController
@RequestMapping("/")

public class FireStationController {
FireStationService fireStationService = new FireStationService();
DataService dataService = new DataService();

    @GetMapping("firestation")
    public List<OutputDataListFormat> getPersonsCoveredByFireStation(@RequestParam(value="stationNumber", required=true) Integer stationNumber) {
        return dataService.returnPersonsCoveredByFireStation(stationNumber);
    }





}
