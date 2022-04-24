package com.softwareacademy.webapp.baroni_julien_p5.service;


import com.softwareacademy.webapp.baroni_julien_p5.controller.exception.NoDataFoundException;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Services CRUD pour la gestion de la partie Person du document d'input.
 * http://localhost:8080/firestation/
 * <p>
 */


@Service
@Slf4j
public class PersonService {
    Person person;

    //utilisation d'un iterator qui permet de modifier la liste pendant qu'on est entrain de la parcourir
    //on se sert d'un compteur appelé flagOfRemoval pour déterminer si la donné est matchée ou non et lancer une exception le cas échant
    public List<Person> removePerson(List<Person> persons, String firstName, String lastName) {
        List<Person> output = new ArrayList<>();
        Iterator<Person> itr = persons.iterator();
        Integer flagOfRemoval = 0;
        while (itr.hasNext()) {
            Person iteratedPerson = itr.next();
            if (iteratedPerson.getFirstName().equals(firstName) && iteratedPerson.getLastName().equals(lastName)) {
                itr.remove();
                flagOfRemoval = 1;
            } else {
                output.add(iteratedPerson);
            }
        }
        if (flagOfRemoval.equals(1)) {
            log.info("Deleted firestation mapping");
        } else {
            throw new NoDataFoundException();
        }
        return output;
    }

    public List<Person> addPerson(List<Person> persons, String firstName, String lastName, String address, String city, Integer zip, String phone, String email) {
        List<Person> output = persons;
        person = new Person(firstName, lastName, address, city, zip, phone, email);
        output.add(person);
        return output;
    }

    public List<Person> modifyPerson(List<Person> persons, String firstName, String lastName, String address, String city, Integer zip, String phone, String email) {
        persons.forEach(objectToDealWith -> {
            if (objectToDealWith.getFirstName().equals(firstName) && objectToDealWith.getLastName().equals(lastName)) {
                objectToDealWith.setAddress(address);
                objectToDealWith.setCity(city);
                objectToDealWith.setZip(zip);
                objectToDealWith.setPhone(phone);
                objectToDealWith.setEmail(email);
            }
        });
        List<Person> output = persons;
        return output;
    }


}
