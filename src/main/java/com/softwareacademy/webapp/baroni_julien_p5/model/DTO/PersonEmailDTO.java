package com.softwareacademy.webapp.baroni_julien_p5.model.DTO;

import lombok.Data;

@Data
public class PersonEmailDTO {
    private String email;

    public PersonEmailDTO(String email) {
        this.email = email;
    }
}
