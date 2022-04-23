package com.softwareacademy.webapp.baroni_julien_p5.model.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDTO {
    private String phone;

    public PhoneDTO(String phone) {
        this.phone = phone;
    }
}
