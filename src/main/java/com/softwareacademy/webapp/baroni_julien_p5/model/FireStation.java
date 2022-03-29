package com.softwareacademy.webapp.baroni_julien_p5.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Data
public class FireStation {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @JsonProperty("address")
    private String address;
    @JsonProperty("station")
    private Integer station;



}
