package com.softwareacademy.webapp.baroni_julien_p5.model.ODT;

import lombok.Data;

import java.util.List;

@Data
public class OutputDataListFormat {

    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private Integer zip;

    private String phone;

    private String email;

    private Integer age;

    private List<String> medicalBackGround;

    private int isAChildren;

    private int isAnAdult;


    // pour la méthode actionsForReturnPersonsCoveredByFireStation

    public OutputDataListFormat(String firstName, String lastName, String address, String phone, int isAChildren, int isAnAdult) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.isAChildren = isAChildren;
        this.isAnAdult = isAnAdult;
    }




}
