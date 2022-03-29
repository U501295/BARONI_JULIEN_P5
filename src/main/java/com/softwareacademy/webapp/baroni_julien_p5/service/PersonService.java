package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.softwareacademy.webapp.baroni_julien_p5.model.Person;
import com.softwareacademy.webapp.baroni_julien_p5.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository personRepository;

    public PersonService (PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public Iterable<Person>list(){
        return personRepository.findAll();
    }

    public Person save(Person person){
        return personRepository.save(person);
    }

}
