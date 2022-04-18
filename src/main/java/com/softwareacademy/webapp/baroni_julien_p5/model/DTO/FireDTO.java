package com.softwareacademy.webapp.baroni_julien_p5.model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class FireDTO {
    private String firstName;
    private String lastName;
    private String phone;
    private Integer age;
    private List<String> medications;
    private List<String> allergies;



    public FireDTO(String firstName, String lastName, String phone, Integer age, List<String> medications, List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }

}
