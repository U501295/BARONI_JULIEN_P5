package com.softwareacademy.webapp.baroni_julien_p5.controller;

import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.*;
import com.softwareacademy.webapp.baroni_julien_p5.service.DataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/")
public class FunctionnalController {
    DataService dataService = new DataService();

    @GetMapping("firestation")
    public FireStationACCountDTO getPersonsCoveredByFireStation(@RequestParam(value="stationNumber", required=true) Integer stationNumber) {
        FireStationACCountDTO fireStationACCountDTO = new FireStationACCountDTO();

        fireStationACCountDTO.setCoveredPersons(dataService.returnPersonsCoveredByFireStation(stationNumber));
        fireStationACCountDTO.setNumberOfAdults(dataService.countAdultsAndChildren(stationNumber).get(0));
        fireStationACCountDTO.setNumberOfChildren(dataService.countAdultsAndChildren(stationNumber).get(1));


        return fireStationACCountDTO;
    }

    //TODO : voir pourquoi les adresses ne matchent pas celles qui sont rentrées en input
    //TODO : virer des houses members l'enfant en question
    //TODO : renvoyer chaine vide si pas d'enfants
    @GetMapping("childAlert")
    public ChildAlertDTO getChildrenLivingAtGivenAdress(@RequestParam(value="address", required=true) String address) {
        ChildAlertDTO childAlertDTO = new ChildAlertDTO();
        childAlertDTO.setAlertedChildren(dataService.returnChildrenAndParentsLivingAtAnAddress(address));
        return childAlertDTO;
    }

    //TODO : voir ici pourquoi ça marche quand on rentre aucun paramètre
    @GetMapping("phoneAlert")
    public PhoneAlertDTO getPhoneNumbersCoveredByFireStation(@RequestParam(value="firestation", required=true) Integer fireStationNumber) {
        PhoneAlertDTO phoneAlertDTO = new PhoneAlertDTO();
        phoneAlertDTO.setPhonesCoveredByStation(dataService.returnPhoneListCoveredByFireStation(fireStationNumber));
        return phoneAlertDTO;
    }

    //TODO : voir pourquoi on a quand même un affichage quand on rentre aucun paramètre
    @GetMapping("fire")
    public FireAndFireStationNumberDTO getPersonsLivingAtGivenAddress(@RequestParam(value="address", required=true) String address) {
        FireAndFireStationNumberDTO fireAndFireStationNumberDTO = new FireAndFireStationNumberDTO();
        fireAndFireStationNumberDTO.setPersonsLivingAtTheGivenAddress(dataService.returnHabitantsListLivingAtAnAddress(address));
        fireAndFireStationNumberDTO.setFireStationNumber(dataService.returnFireStationNumberCoveringTheAddress(address));
        return fireAndFireStationNumberDTO;
    }

    @GetMapping("flood/stations")
    public FloodDTO getPersonsLivingAtGivenAddress(@RequestParam List<Integer> stations) {
        FloodDTO floodDTO = new FloodDTO();
        floodDTO.setFlood(dataService.returnPersonsAndAdressCoveredByFireStationsDuringFlood(stations));
        return floodDTO;
    }

    @GetMapping("personInfo")
    public PersonInfoDTO getPersonInfo(@RequestParam String firstName,String lastName) {
        PersonInfoDTO personInfoDTO = dataService.returnPersonInfos(firstName, lastName);
        return personInfoDTO;
    }

    @GetMapping("communityEmail")
    public EmailDTO getPersonInfo(@RequestParam String city) {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setCityEmailAddresses(dataService.returnCityEmailAddresses(city));
        return emailDTO;
    }



}
