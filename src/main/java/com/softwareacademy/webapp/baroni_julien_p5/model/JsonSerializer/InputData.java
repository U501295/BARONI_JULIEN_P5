package com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.ElementCollection;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Cette classe permet de serialiser à l'aide d'un mapper dans getJsonData() le contenu du
 * document JSON donné en input du projet. La serialisation est ensuite stockée dans une instance statique pouvant être
 * ainsi plus facilement atteinte en lecture dans les services métiers. cela permet d'éviter d'avoir à faire appel à un nouvel objet
 * à chaque fois qu'on veut accéder aux données sérialisées
 * <p>
 */


@Getter
@Setter
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
        log.info("Request data from JSON");
        try {
            Path path = Paths.get("src\\main\\resources\\JSON\\data.json");
            List<String> lines = Files.readAllLines(path);
            String jsonContent = String.join("", lines);
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.readValue(jsonContent, InputData.class);
        } catch (IOException e) {
            //si les données ne parviennent pas à être chargées on stoppe l'application en lançant une RuntimeError
            log.error("FAIL in fetching data : " + e.getMessage());
            throw new RuntimeException("Unable to save data : " + e.getMessage(), e);
        }
        log.info("SUCCESS in fetching data");
        return result;
    }

}
