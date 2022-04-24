package com.softwareacademy.webapp.baroni_julien_p5.service;


import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Services CRUD pour la gestion de la partie MedicalRecord du document d'input.
 * http://localhost:8080/firestation/
 * <p>
 */
@Service
@Slf4j
public class MedicalRecordService {

    MedicalRecord medicalRecord;

    //utilisation d'un iterator qui permet de modifier la liste pendant qu'on est entrain de la parcourir
    public List<MedicalRecord> removeMedicalRecord(List<MedicalRecord> medicalRecords, String firstName, String lastName) {
        List<MedicalRecord> output = new ArrayList<>();
        Iterator<MedicalRecord> itr = medicalRecords.iterator();
        while (itr.hasNext()) {
            MedicalRecord iteratedMedicalRecord = itr.next();
            if (iteratedMedicalRecord.getFirstName().equals(firstName) && iteratedMedicalRecord.getLastName().equals(lastName)) {
                itr.remove();
            } else {
                output.add(iteratedMedicalRecord);
            }

        }
        log.info("Deleted MedicalRecord mapping");
        return output;
    }

    public List<MedicalRecord> addMedicalRecord(List<MedicalRecord> medicalRecords, String firstName, String lastName, Calendar birthDate, List<String> medications, List<String> allergies) {
        List<MedicalRecord> output = medicalRecords;
        medicalRecord = new MedicalRecord(firstName, lastName, birthDate, medications, allergies);
        output.add(medicalRecord);
        log.info("Added MedicalRecord mapping");
        return output;
    }

    public List<MedicalRecord> modifyMedicalRecord(List<MedicalRecord> medicalRecords, String firstName, String lastName, Calendar birthDate, List<String> medications, List<String> allergies) {

        medicalRecords.forEach(objectToDealWith -> {
            if (objectToDealWith.getFirstName().equals(firstName) && objectToDealWith.getLastName().equals(lastName)) {
                objectToDealWith.setBirthDate(birthDate);
                objectToDealWith.setMedications(medications);
                objectToDealWith.setAllergies(allergies);
            }
        });
        List<MedicalRecord> output = medicalRecords;
        log.info("Modified MedicalRecord mapping");
        return output;

    }

}
