package com.softwareacademy.webapp.baroni_julien_p5.controller.endpoints;

import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.*;
import com.softwareacademy.webapp.baroni_julien_p5.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Services métiers pour la gestion des URLs spécifiques de l'application
 * <p>
 */

@RestController
@RequestMapping("/")
@Slf4j
public class FunctionnalController {
    @Autowired
    DataService dataService;

    //http://localhost:8080/firestation?stationNumber=%3Cstation_number%3E
    @GetMapping("firestation")
    public FireStationACCountDTO getPersonsCoveredByFireStation(@RequestParam(value = "stationNumber", required = true) Integer stationNumber) {
        log.info("request to getPersonsCoveredByFireStation number={} ", stationNumber);
        FireStationACCountDTO fireStationACCountDTO = new FireStationACCountDTO();
        fireStationACCountDTO.setCoveredPersons(dataService.returnPersonsCoveredByFireStation(stationNumber));
        fireStationACCountDTO.setNumberOfAdults(dataService.countAdultsAndChildren(stationNumber).get(0));
        fireStationACCountDTO.setNumberOfChildren(dataService.countAdultsAndChildren(stationNumber).get(1));
        return fireStationACCountDTO;
    }

    //http://localhost:8080/childAlert?address=%3Caddress%3E
    @GetMapping("childAlert")
    public ChildAlertDTO getChildrenLivingAtGivenAdress(@RequestParam(value = "address", required = true) String address) {
        log.info("request to getChildrenLivingAtGivenAdress from address={} ", address);
        ChildAlertDTO childAlertDTO = new ChildAlertDTO();
        childAlertDTO.setAlertedChildren(dataService.returnChildrenAndHouseMembersLivingAtAnAddress(address));
        return childAlertDTO;
    }

    //http://localhost:8080/phoneAlert?firestation=%3Cfirestation_number%3E
    //TODO : voir ici pourquoi ça marche quand on rentre aucun paramètre
    @GetMapping("phoneAlert")
    public PhoneAlertDTO getPhoneNumbersCoveredByFireStation(@RequestParam(value = "firestation", required = true) Integer fireStationNumber) {
        log.info("request to getPhoneNumbersCoveredByFireStation number={} ", fireStationNumber);
        PhoneAlertDTO phoneAlertDTO = new PhoneAlertDTO();
        phoneAlertDTO.setPhonesCoveredByStation(dataService.returnPhoneListCoveredByFireStation(fireStationNumber));
        return phoneAlertDTO;
    }

    //http://localhost:8080/fire?address=%3Caddress%3E
    //TODO : voir pourquoi on a quand même un affichage quand on rentre aucun paramètre
    @GetMapping("fire")
    public FireAndFireStationNumberDTO getPersonsAndAddressesCoveredByFireStation(@RequestParam(value = "address", required = true) String address) {
        log.info("request to getPersonsAndAddressesCoveredByFireStation from address={} ", address);
        FireAndFireStationNumberDTO fireAndFireStationNumberDTO = new FireAndFireStationNumberDTO();
        fireAndFireStationNumberDTO.setPersonsLivingAtTheGivenAddress(dataService.returnHabitantsListLivingAtAnAddress(address));
        fireAndFireStationNumberDTO.setFireStationNumber(dataService.returnFireStationNumberCoveringTheAddress(address));
        return fireAndFireStationNumberDTO;
    }

    //http://localhost:8080/flood/stations?stations=%3Ca
    @GetMapping("flood/stations")
    public FloodDTO getPersonsAndAddressesCoveredByFireStationsDuringFlood(@RequestParam List<Integer> stations) {
        log.info("request to getPersonsAndAddressesCoveredByFireStationsDuringFlood from stations={} ", stations);
        FloodDTO floodDTO = new FloodDTO();
        floodDTO.setFlood(dataService.returnPersonsAndAdressCoveredByFireStationsDuringFlood(stations));
        return floodDTO;
    }

    //http://localhost:8080/personInfo?firstName=%3CfirstName%3E&lastName=%3ClastName%3E
    @GetMapping("personInfo")
    public PersonInfoDTO getPersonInfo(@RequestParam String firstName, String lastName) {
        log.info("request to getPersonInfo from firstName={} lastName={} ", firstName, lastName);
        PersonInfoDTO personInfoDTO = dataService.returnPersonInfos(firstName, lastName);
        return personInfoDTO;
    }

    //http://localhost:8080/communityEmail?city=%3Ccity%3E
    @GetMapping("communityEmail")
    public EmailDTO getCityEmails(@RequestParam String city) {
        log.info("request to getCityEmails from city={} lastName={} ", city);
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setCityEmailAddresses(dataService.returnCityEmailAddresses(city));
        return emailDTO;
    }


}
