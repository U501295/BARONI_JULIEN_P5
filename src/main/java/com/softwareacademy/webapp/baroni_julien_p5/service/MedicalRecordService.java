package com.softwareacademy.webapp.baroni_julien_p5.service;


import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MedicalRecordService {

    MedicalRecord medicalRecord;

    public List<MedicalRecord> removeMedicalRecord(List<MedicalRecord> medicalRecords,String firstName,String lastName) {
        List<MedicalRecord> output = new ArrayList<>();
        Iterator<MedicalRecord> itr = medicalRecords.iterator();
        while (itr.hasNext()) {
            MedicalRecord iteratedMedicalRecord = itr.next();
            if (iteratedMedicalRecord.getFirstName().equals(firstName)  && iteratedMedicalRecord.getLastName().equals(lastName) ) {
                itr.remove();
            }else{
                output.add(iteratedMedicalRecord);
            }

        }
        return output;
    }

    public List<MedicalRecord> addMedicalRecord(List<MedicalRecord> medicalRecords,String firstName, String lastName, Calendar birthDate, List<String> medications, List<String> allergies){
        List<MedicalRecord> output = medicalRecords;
        medicalRecord = new MedicalRecord(firstName,lastName,birthDate,medications,allergies);
       output.add(medicalRecord);
       return output;
    }

    public List<MedicalRecord> modifyMedicalRecord(List<MedicalRecord> medicalRecords,String firstName, String lastName, Calendar birthDate, List<String> medications, List<String> allergies) {

        medicalRecords.forEach(objectToDealWith -> {
            if (objectToDealWith.getFirstName().equals(firstName) && objectToDealWith.getLastName().equals(lastName)) {
                objectToDealWith.setBirthDate(birthDate);
                objectToDealWith.setMedications(medications);
                objectToDealWith.setAllergies(allergies);
            }
        });
        List<MedicalRecord> output = medicalRecords;
        return output;

    }

}
