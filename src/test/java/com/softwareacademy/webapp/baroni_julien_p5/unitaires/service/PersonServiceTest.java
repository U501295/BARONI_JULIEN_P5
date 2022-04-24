package com.softwareacademy.webapp.baroni_julien_p5.unitaires.service;

import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import com.softwareacademy.webapp.baroni_julien_p5.service.PersonService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Tests unitaires permettant de s'assurer que la couche service est fonctionnelle
 * <p>
 */
class PersonServiceTest {
    List<Person> testJDD = new ArrayList<>();
    PersonService personService = new PersonService();
    Person person1 = new Person("John", "Boyd", "Mock Street", "DonaldVille", 567, "67899756", "email@test.fr");
    Person person2 = new Person("Don", "Williams", "Beck Street", "PicsouVille", 50, "908787665", "email@fake.fr");
    Person person3 = new Person("Tobby", "Shill", "Mock Street", "DonaldVille", 1000, "0987333345", "email@tmock.fr");

    @BeforeEach
    void initEach() {
        testJDD.removeAll(testJDD);
        testJDD.add(person1);
        testJDD.add(person2);
        testJDD.add(person3);
    }


    @Test
    void removePerson() {
        Assertions.assertThat(testJDD.size()).isEqualTo(3);
        personService.removePerson(testJDD, "John", "Boyd");
        Assertions.assertThat(testJDD.size()).isEqualTo(2);
    }

    @Test
    void addPerson() {
        personService.addPerson(testJDD, "Four", "Quatre", "tyzi street", "MickeyVille", 798, "65555555", "toto@toto.fr");
        Assertions.assertThat(testJDD.size()).isEqualTo(4);
    }

    @Test
    void modifyPerson() {
        personService.modifyPerson(testJDD, "John", "Boyd", "Street", "Ville", 000, "phone", "email");
        Assertions.assertThat(testJDD.size()).isEqualTo(3);
    }
}