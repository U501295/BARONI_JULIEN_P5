package com.softwareacademy.webapp.baroni_julien_p5.controller;

import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import com.softwareacademy.webapp.baroni_julien_p5.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/")
public class PersonController {

    PersonService personService = new PersonService();


    @RequestMapping(value = "person/",
            method = RequestMethod.DELETE,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Person> deletePerson(@RequestParam String firstName, String lastName){
        return personService.removePerson(firstName, lastName);

    }

    @RequestMapping(value = "person/",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Person> postPerson(@RequestParam String firstName,String lastName, String address, String city, Integer zip, String phone, String email){
        return personService.addPerson(firstName,lastName,address,city,zip,phone,email);

    }

    @RequestMapping(value = "person/",
            method = RequestMethod.PUT,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Person> modifyPerson(@RequestParam String firstName,String lastName, String address, String city, Integer zip, String phone, String email){
        return personService.modifyPerson(firstName,lastName,address,city,zip,phone,email);

    }
}
