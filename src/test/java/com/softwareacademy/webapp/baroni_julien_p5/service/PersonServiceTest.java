package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer.InputData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {
    List<Person> testJDD = new ArrayList<>();
    PersonService personService = new PersonService();
    Person person1 = new Person("John","Boyd","Mock Street","DonaldVille",567,"67899756","email@test.fr");
    Person person2 = new Person("Don","Williams","Beck Street","PicsouVille",50,"908787665","email@fake.fr");
    Person person3 = new Person("Tobby","Shill","Mock Street","DonaldVille",1000,"0987333345","email@tmock.fr");
    @BeforeEach
    void initEach(){
        testJDD.removeAll(testJDD);
        testJDD.add(person1);
        testJDD.add(person2);
        testJDD.add(person3);
    }


    @Test
    void removePerson() {
        List<Person> persons = InputData.INSTANCE.getPersonsData();
        Assertions.assertThat(persons.size()).isEqualTo(23);
        List<Person> result = personService.removePerson("John","Boyd");
        Assertions.assertThat(result.size()).isEqualTo(22);
    }

    @Test
    void addPerson() {
        List<Person> persons = InputData.INSTANCE.getPersonsData();
        Assertions.assertThat(persons.size()).isEqualTo(23);
        List<Person> result =personService.addPerson("Four","Quatre","tyzi street","MickeyVille",798,"65555555","toto@toto.fr");
        Assertions.assertThat(result.size()).isEqualTo(24);
    }

    @Test
    void modifyPerson() {
        List<Person> persons = InputData.INSTANCE.getPersonsData();
        Assertions.assertThat(persons.get(0).getFirstName()).isEqualTo("John");
        Assertions.assertThat(persons.get(0).getAddress()).isEqualTo("1509 Culver St");
        List<Person> result = personService.modifyPerson("John","Boyd","Street","Ville",000,"phone","email");
        Assertions.assertThat(persons.get(0).getAddress()).isEqualTo("Street");
    }
}