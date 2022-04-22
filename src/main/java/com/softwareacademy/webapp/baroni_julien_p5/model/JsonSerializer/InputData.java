package com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.ElementCollection;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Data
@Slf4j
public class InputData {


    public static final InputData INSTANCE = getJsonData();

    @JsonProperty("persons")
    @ElementCollection
    public List<Person> personsData;
    @JsonProperty("firestations")
    @ElementCollection
    public List<FireStation> firestationsData;
    @JsonProperty("medicalrecords")
    @ElementCollection
    public List<MedicalRecord> medicalrecordsData;


    public InputData() {

    }

    public InputData(List<Person> persons, List<FireStation> firestations, List<MedicalRecord> medicalrecords) {
        this.personsData = persons;
        this.firestationsData = firestations;
        this.medicalrecordsData = medicalrecords;
    }

    public static InputData getJsonData() {
        InputData result = new InputData();

        try {
            Path path = Paths.get("src\\main\\resources\\JSON\\data.json");
            List<String> lines = Files.readAllLines(path);
            String jsonContent = String.join("", lines);
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.readValue(jsonContent, InputData.class);
        } catch (IOException e) {

        }
        return result;
    }

}
