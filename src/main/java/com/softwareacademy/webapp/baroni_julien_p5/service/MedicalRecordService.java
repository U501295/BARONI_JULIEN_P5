package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MedicalRecordService {
    InputData inputData = new InputData();
    MedicalRecord medicalRecord;

    public void removeMedicalRecord(String firstName,String lastName) {
        inputData.getMedicalrecords().forEach(objectToDealWith -> {
            if (objectToDealWith.getFirstName().equals(firstName) && objectToDealWith.getLastName().equals(lastName)) {
                inputData.getMedicalrecords().remove(objectToDealWith);
            }
        });
    }

    public void addMedicalRecord(String firstName, String lastName, Date birthDate, List<String> medications, List<String> allergies){
        medicalRecord = new MedicalRecord(firstName,lastName,birthDate,medications,allergies);
        inputData.getMedicalrecords().add(medicalRecord);
    }

    public void modifyMedicalRecord(String firstName, String lastName, Date birthDate, List<String> medications, List<String> allergies) {
        inputData.getMedicalrecords().forEach(objectToDealWith -> {
            if (objectToDealWith.getFirstName().equals(firstName) && objectToDealWith.getLastName().equals(lastName)) {
                objectToDealWith.setBirthDate(birthDate);
                objectToDealWith.setMedications(medications);
                objectToDealWith.setAllergies(allergies);
            }
        });

    }

}
