package com.softwareacademy.webapp.baroni_julien_p5.model.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


@Data
public class FireStation {

    @JsonProperty("address")
    private String address;
    @JsonProperty("station")
    private Integer station;


    public FireStation(String address, Integer station) {
        this.address = address;
        this.station = station;
    }

    public FireStation() {

    }
}
