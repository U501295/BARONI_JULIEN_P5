package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MedicalRecordService {
    //TODO : voir si les CRUDs sont en accord avec l'ordre d'appel des beans de Spring
    InputData inputData = new InputData();
    MedicalRecord medicalRecord;

    public void removeMedicalRecord(String firstName,String lastName) {
        inputData.getMedicalrecords().forEach(objectToDealWith -> {
            if (objectToDealWith.getFirstName().equals(firstName) && objectToDealWith.getLastName().equals(lastName)) {
                inputData.getMedicalrecords().remove(objectToDealWith);
            }
        });
    }

    public void addMedicalRecord(String firstName, String lastName, Calendar birthDate, ArrayList<String> medications, ArrayList<String> allergies){
        medicalRecord = new MedicalRecord(firstName,lastName,birthDate,medications,allergies);
        inputData.getMedicalrecords().add(medicalRecord);
    }

    public void modifyMedicalRecord(String firstName, String lastName, Calendar birthDate, ArrayList<String> medications, ArrayList<String> allergies) {
        inputData.getMedicalrecords().forEach(objectToDealWith -> {
            if (objectToDealWith.getFirstName().equals(firstName) && objectToDealWith.getLastName().equals(lastName)) {
                objectToDealWith.setBirthDate(birthDate);
                objectToDealWith.setMedications(medications);
                objectToDealWith.setAllergies(allergies);
            }
        });

    }

}
