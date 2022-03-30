package com.softwareacademy.webapp.baroni_julien_p5.model.DTO;

import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import lombok.Data;

import java.util.List;

@Data
public class InputData {

    //TODO : est ce que les types génériques List doivent être gettés settés comme des Dates ?
    List<Person> persons;
    List<FireStation> firestations;
    List<MedicalRecord> medicalrecords;
}
