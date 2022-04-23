package com.softwareacademy.webapp.baroni_julien_p5.model.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonInfoDTO {

    private String firstName;

    private String lastName;

    private String address;

    private Integer age;

    private String email;

    private List<String> medications;

    private List<String> allergies;

    public PersonInfoDTO() {
    }

    public PersonInfoDTO(String firstName, String lastName, String address, Integer age, String email, List<String> medications, List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.age = age;
        this.email = email;
        this.medications = medications;
        this.allergies = allergies;
    }


}
