package com.softwareacademy.webapp.baroni_julien_p5.controller.endpoints;

import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.service.PersonService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class PersonController {

    PersonService personService = new PersonService();


    @DeleteMapping(value = "person/{firstName}&{lastName}")
    public List<Person> deletePerson(@PathVariable String firstName, @PathVariable String lastName) {
        log.info("request to delete firstName={} lastName={}", firstName, lastName);
        List<Person> persons = InputData.INSTANCE.getPersonsData();
        return personService.removePerson(persons, firstName, lastName);

    }

    @PostMapping(value = "person/{firstName}&{lastName}&{address}&{city}&{zip}&{phone}&{email}")
    public List<Person> postPerson(@RequestBody Person person) {
        log.info("request to create firstName={} lastName={}", person.getFirstName(), person.getLastName());
        List<Person> persons = InputData.INSTANCE.getPersonsData();
        return personService.addPerson(persons, person.getFirstName(), person.getLastName(), person.getAddress(), person.getCity(), person.getZip(), person.getPhone(), person.getEmail());

    }


    @PutMapping(value = "person/{firstName}&{lastName}&{address}&{city}&{zip}&{phone}&{email}")
    public List<Person> modifyPerson(@PathVariable String firstName, @PathVariable String lastName, @RequestBody Person person) {
        log.info("request to modify firstName={} lastName={}", person.getFirstName(), person.getLastName());
        List<Person> persons = InputData.INSTANCE.getPersonsData();
        return personService.modifyPerson(persons, firstName, lastName, person.getAddress(), person.getCity(), person.getZip(), person.getPhone(), person.getEmail());

    }
}
