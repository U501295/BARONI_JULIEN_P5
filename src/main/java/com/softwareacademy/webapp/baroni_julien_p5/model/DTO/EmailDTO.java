package com.softwareacademy.webapp.baroni_julien_p5.model.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmailDTO {
    private List<PersonEmailDTO> cityEmailAddresses;


}
