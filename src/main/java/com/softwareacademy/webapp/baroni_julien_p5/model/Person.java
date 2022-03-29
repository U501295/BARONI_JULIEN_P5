package com.softwareacademy.webapp.baroni_julien_p5.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Data
public class Person {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("address")
    private String address;
    @JsonProperty("city")
    private String city;
    @JsonProperty("zip")
    private Integer zip;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("email")
    private String email;


}
