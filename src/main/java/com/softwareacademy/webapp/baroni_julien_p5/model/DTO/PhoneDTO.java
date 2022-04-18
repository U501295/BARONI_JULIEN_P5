package com.softwareacademy.webapp.baroni_julien_p5.model.DTO;

import lombok.Data;

@Data
public class PhoneDTO {
    private String phone;

    public PhoneDTO(String phone) {
        this.phone = phone;
    }
}
