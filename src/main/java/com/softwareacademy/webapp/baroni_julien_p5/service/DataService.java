package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.softwareacademy.webapp.baroni_julien_p5.model.Person;
import com.softwareacademy.webapp.baroni_julien_p5.repository.DataRepository;
import org.springframework.stereotype.Service;

@Service
public class DataService {

    private DataRepository dataRepository;

    public DataService(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    /*public object(Person person){
        return personRepository.save(person);
    }*/


}
