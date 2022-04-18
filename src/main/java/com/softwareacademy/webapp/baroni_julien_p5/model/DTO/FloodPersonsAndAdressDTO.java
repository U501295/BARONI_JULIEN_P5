package com.softwareacademy.webapp.baroni_julien_p5.model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class FloodPersonsAndAdressDTO {
    private String address;
    private List<FireDTO> homesCoveredByFireStation;

    public FloodPersonsAndAdressDTO() {
    }

    public FloodPersonsAndAdressDTO(String address, List<FireDTO> homesCoveredByFireStation) {
        this.address = address;
        this.homesCoveredByFireStation = homesCoveredByFireStation;
    }


}
