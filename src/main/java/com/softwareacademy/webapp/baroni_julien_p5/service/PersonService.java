package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class PersonService {
    Person person;

    public void removePerson(List<Person> persons, String firstName, String lastName) {
        Iterator<Person> itr = persons.iterator();
        while (itr.hasNext()) {
            Person iteratedPerson = itr.next();
            if (iteratedPerson.getFirstName().equals(firstName)  && iteratedPerson.getLastName().equals(lastName) ) {
                itr.remove();
            }
        }
    }

    public void addPerson(List<Person> persons,String firstName,String lastName, String address, String city, Integer zip, String phone, String email){
        person = new Person(firstName,lastName,address,city,zip,phone,email);
       persons.add(person);
    }

    public void modifyPerson(List<Person> persons,String firstName,String lastName, String address, String city, Integer zip, String phone, String email) {
       persons.forEach(objectToDealWith -> {
            if (objectToDealWith.getFirstName().equals(firstName) && objectToDealWith.getLastName().equals(lastName)) {
                objectToDealWith.setAddress(address);
                objectToDealWith.setCity(city);
                objectToDealWith.setZip(zip);
                objectToDealWith.setPhone(phone);
                objectToDealWith.setEmail(email);
            }
        });

    }


}
