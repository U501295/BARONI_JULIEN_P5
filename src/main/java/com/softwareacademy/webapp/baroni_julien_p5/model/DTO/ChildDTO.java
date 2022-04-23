package com.softwareacademy.webapp.baroni_julien_p5.model.DTO;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChildDTO {
    private String firstName;
    private String lastName;
    private Integer age;
    private List<String[]> houseMembers;


    public ChildDTO(String firstName, String lastName, Integer age, List<String[]> houseMembers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.houseMembers = houseMembers;
    }
}
