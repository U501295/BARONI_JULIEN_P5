package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer.InputData;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class PersonService {
    Person person;

    public List<Person> removePerson(String firstName, String lastName) {
        List<Person> persons = InputData.INSTANCE.getPersonsData();
        Iterator<Person> itr = persons.iterator();
        while (itr.hasNext()) {
            Person iteratedPerson = itr.next();
            if (iteratedPerson.getFirstName().equals(firstName)  && iteratedPerson.getLastName().equals(lastName) ) {
                itr.remove();
            }
        }
        return persons;
    }

    public List<Person> addPerson(String firstName,String lastName, String address, String city, Integer zip, String phone, String email){
        List<Person> persons = InputData.INSTANCE.getPersonsData();
        person = new Person(firstName,lastName,address,city,zip,phone,email);
       persons.add(person);
       return persons;
    }

    public List<Person> modifyPerson(String firstName,String lastName, String address, String city, Integer zip, String phone, String email) {
        List<Person> persons = InputData.INSTANCE.getPersonsData();
       persons.forEach(objectToDealWith -> {
            if (objectToDealWith.getFirstName().equals(firstName) && objectToDealWith.getLastName().equals(lastName)) {
                objectToDealWith.setAddress(address);
                objectToDealWith.setCity(city);
                objectToDealWith.setZip(zip);
                objectToDealWith.setPhone(phone);
                objectToDealWith.setEmail(email);
            }
        });
        return persons;
    }


}
