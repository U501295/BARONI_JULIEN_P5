package com.softwareacademy.webapp.baroni_julien_p5.model;

import lombok.Data;

import java.util.List;

@Data
public class InputData {

    List<Person> persons;
    List<FireStation> firestations;
    List<MedicalRecord> medicalrecords;
}
