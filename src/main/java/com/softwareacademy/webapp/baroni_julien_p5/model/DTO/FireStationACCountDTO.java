package com.softwareacademy.webapp.baroni_julien_p5.model.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FireStationACCountDTO {

    private List<FireStationDTO> coveredPersons;

    private Integer numberOfAdults;

    private Integer numberOfChildren;


}
