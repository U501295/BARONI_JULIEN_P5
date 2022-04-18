package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;

import com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer.InputData;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MedicalRecordService {

    MedicalRecord medicalRecord;

    public List<MedicalRecord> removeMedicalRecord(String firstName,String lastName) {
        List<MedicalRecord> medicalRecords = InputData.INSTANCE.getMedicalrecordsData();
        Iterator<MedicalRecord> itr = medicalRecords.iterator();
        while (itr.hasNext()) {
            MedicalRecord iteratedMedicalRecord = itr.next();
            if (iteratedMedicalRecord.getFirstName().equals(firstName)  && iteratedMedicalRecord.getLastName().equals(lastName) ) {
                itr.remove();
            }
        }
        return medicalRecords;
    }

    public List<MedicalRecord> addMedicalRecord(String firstName, String lastName, Calendar birthDate, List<String> medications, List<String> allergies){
        List<MedicalRecord> medicalRecords = InputData.INSTANCE.getMedicalrecordsData();
        medicalRecord = new MedicalRecord(firstName,lastName,birthDate,medications,allergies);
        medicalRecords.add(medicalRecord);
        return medicalRecords;
    }

    public List<MedicalRecord> modifyMedicalRecord(String firstName, String lastName, Calendar birthDate, List<String> medications, List<String> allergies) {
        List<MedicalRecord> medicalRecords = InputData.INSTANCE.getMedicalrecordsData();
        medicalRecords.forEach(objectToDealWith -> {
            if (objectToDealWith.getFirstName().equals(firstName) && objectToDealWith.getLastName().equals(lastName)) {
                objectToDealWith.setBirthDate(birthDate);
                objectToDealWith.setMedications(medications);
                objectToDealWith.setAllergies(allergies);
            }
        });
        return medicalRecords;
    }

}
