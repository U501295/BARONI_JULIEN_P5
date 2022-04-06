package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    InputData inputData = new InputData();
    Person person;

    public void removePerson(String firstName,String lastName) {
        inputData.getPersonsData().forEach(objectToDealWith -> {
            if (objectToDealWith.getFirstName().equals(firstName)  && objectToDealWith.getLastName().equals(lastName) ) {
                inputData.getPersonsData().remove(objectToDealWith);
            }
        });
    }

    public void addPerson(String firstName,String lastName, String address, String city, Integer zip, String phone, String email){
        person = new Person(firstName,lastName,address,city,zip,phone,email);
        inputData.getPersonsData().add(person);
    }

    public void modifyPerson(String firstName,String lastName, String address, String city, Integer zip, String phone, String email) {
        inputData.getPersonsData().forEach(objectToDealWith -> {
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
