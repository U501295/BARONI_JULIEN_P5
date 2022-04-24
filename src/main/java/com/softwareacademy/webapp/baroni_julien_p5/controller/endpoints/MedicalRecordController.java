package com.softwareacademy.webapp.baroni_julien_p5.controller.endpoints;

import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.service.MedicalRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Controller utilisant les services CRUD pour la gestion de la partie MedicalRecord du document d'input.
 * http://localhost:8080/medicalRecord/
 * <p>
 */
@RestController
@RequestMapping("/")
@Slf4j
public class MedicalRecordController {

    private MedicalRecordService medicalRecordService = new MedicalRecordService();

    @DeleteMapping(value = "medicalRecord/{firstName}&{lastName}")
    public List<MedicalRecord> deleteMedicalRecord(@PathVariable String firstName, @PathVariable String lastName) {
        log.info("request to delete MedicalRecord from firstName={} lastName={}", firstName, lastName);
        List<MedicalRecord> medicalRecords = InputData.INSTANCE.getMedicalrecordsData();
        return medicalRecordService.removeMedicalRecord(medicalRecords, firstName, lastName);

    }

    @PostMapping(value = "medicalRecord/{firstName}&{lastName}&{birthDate}&{medications}&{allergies}")
    public List<MedicalRecord> postMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        log.info("request to create a MedicalRecord from firstName={} lastName={}", medicalRecord.getFirstName(), medicalRecord.getLastName());
        List<MedicalRecord> medicalRecords = InputData.INSTANCE.getMedicalrecordsData();
        return medicalRecordService.addMedicalRecord(medicalRecords, medicalRecord.getFirstName(), medicalRecord.getLastName(), medicalRecord.getBirthDate(), medicalRecord.getMedications(), medicalRecord.getAllergies());

    }

    @PutMapping(value = "medicalRecord/{firstName}&{lastName}&{birthDate}&{medications}&{allergies}")
    public List<MedicalRecord> modifyMedicalRecord(@PathVariable String firstName, @PathVariable String lastName, @RequestBody MedicalRecord medicalRecord) {
        log.info("request to modify MedicalRecord from firstName={} lastName={}", firstName, lastName);
        List<MedicalRecord> medicalRecords = InputData.INSTANCE.getMedicalrecordsData();
        return medicalRecordService.modifyMedicalRecord(medicalRecords, firstName, lastName, medicalRecord.getBirthDate(), medicalRecord.getMedications(), medicalRecord.getAllergies());

    }


}
