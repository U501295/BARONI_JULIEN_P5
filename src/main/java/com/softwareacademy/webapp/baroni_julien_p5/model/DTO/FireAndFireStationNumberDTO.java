package com.softwareacademy.webapp.baroni_julien_p5.model.DTO;


import lombok.Data;

import java.util.List;

@Data
public class FireAndFireStationNumberDTO {

    private List<FireDTO> personsLivingAtTheGivenAddress;
    private Integer fireStationNumber;

}
