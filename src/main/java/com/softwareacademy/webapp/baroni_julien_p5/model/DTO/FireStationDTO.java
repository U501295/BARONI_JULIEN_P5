package com.softwareacademy.webapp.baroni_julien_p5.model.DTO;

import lombok.Data;

@Data
public class FireStationDTO {

    private String firstName;

    private String lastName;

    private String address;

    private String phone;

    public FireStationDTO(String firstName, String lastName, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }
}
