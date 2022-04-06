package com.softwareacademy.webapp.baroni_julien_p5.model.DTO;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Data
@Component
public class InputData {

    //TODO : est ce que les types génériques List doivent être gettés settés comme des Dates ?
    public  List<Person> persons;
    public  List<FireStation> firestations;
    public  List<MedicalRecord> medicalrecords;

    public InputData() {

    }

    public InputData(List<Person> persons, List<FireStation> firestations, List<MedicalRecord> medicalrecords) {
        this.persons = persons;
        this.firestations = firestations;
        this.medicalrecords = medicalrecords;
    }

    public static InputData getJsonData(){
        InputData result = new InputData();
        try {
            Path path = Paths.get("src\\main\\resources\\JSON\\data.json");
            List<String> lines = Files.readAllLines(path);
            String jsonContent = String.join("", lines);
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.readValue(jsonContent, InputData.class);
        } catch (Exception e){

        }
        return result;
    }
}
