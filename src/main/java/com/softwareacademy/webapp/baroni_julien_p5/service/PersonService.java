package com.softwareacademy.webapp.baroni_julien_p5.service;


import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PersonService {
    Person person;

    public List<Person> removePerson(List<Person> persons, String firstName, String lastName) {
        List<Person> output = new ArrayList<>();
        Iterator<Person> itr = persons.iterator();
        while (itr.hasNext()) {
            Person iteratedPerson = itr.next();
            if (iteratedPerson.getFirstName().equals(firstName)  && iteratedPerson.getLastName().equals(lastName) ) {
                itr.remove();
            }else{
                output.add(iteratedPerson);
            }
        }
        return output;
    }

    public List<Person> addPerson(List<Person> persons,String firstName,String lastName, String address, String city, Integer zip, String phone, String email){
        List<Person> output = persons;
        person = new Person(firstName,lastName,address,city,zip,phone,email);
        output.add(person);
        return output;
    }

    public List<Person> modifyPerson(List<Person> persons,String firstName,String lastName, String address, String city, Integer zip, String phone, String email) {
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
