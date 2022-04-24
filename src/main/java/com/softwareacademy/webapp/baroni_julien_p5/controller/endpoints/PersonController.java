package com.softwareacademy.webapp.baroni_julien_p5.controller.endpoints;

import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.service.PersonService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Controller utilisant les services CRUD pour la gestion de la partie Person du document d'input.
 * http://localhost:8080/person/
 * <p>
 */
@RestController
@RequestMapping("/")
public class PersonController {

    PersonService personService = new PersonService();


    @DeleteMapping(value = "person/{firstName}&{lastName}")
    public List<Person> deletePerson(@PathVariable String firstName, @PathVariable String lastName) {
        List<Person> persons = InputData.INSTANCE.getPersonsData();
        return personService.removePerson(persons, firstName, lastName);

    }

    @PostMapping(value = "person/{firstName}&{lastName}")
    public List<Person> postPerson(@PathVariable String firstName, @PathVariable String lastName, @RequestBody Person person) {
        List<Person> persons = InputData.INSTANCE.getPersonsData();
        return personService.addPerson(persons, firstName, lastName, person.getAddress(), person.getCity(), person.getZip(), person.getPhone(), person.getEmail());

    }


    @PutMapping(value = "person/{firstName}&{lastName}")
    public List<Person> modifyPerson(@PathVariable String firstName, @PathVariable String lastName, @RequestBody Person person) {
        List<Person> persons = InputData.INSTANCE.getPersonsData();
        return personService.modifyPerson(persons, firstName, lastName, person.getAddress(), person.getCity(), person.getZip(), person.getPhone(), person.getEmail());

    }
}
