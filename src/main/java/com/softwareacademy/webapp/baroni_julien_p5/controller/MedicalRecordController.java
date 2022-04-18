package com.softwareacademy.webapp.baroni_julien_p5.controller;

import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import com.softwareacademy.webapp.baroni_julien_p5.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/")
public class MedicalRecordController {

    private MedicalRecordService medicalRecordService = new MedicalRecordService();

    @RequestMapping(value = "medicalRecord/",
            method = RequestMethod.DELETE,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<MedicalRecord> deletePerson(@RequestParam String firstName, String lastName){
        return medicalRecordService.removeMedicalRecord(firstName, lastName);

    }

    @RequestMapping(value = "medicalRecord/",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<MedicalRecord> postPerson(@RequestParam String firstName, String lastName, Calendar birthDate, List<String> medications, List<String> allergies){
        return medicalRecordService.addMedicalRecord(firstName,lastName,birthDate,medications,allergies);

    }

    @RequestMapping(value = "medicalRecord/",
            method = RequestMethod.PUT,
            produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<MedicalRecord> modifyPerson(@RequestParam String firstName, String lastName, Calendar birthDate, List<String> medications, List<String> allergies){
        return medicalRecordService.modifyMedicalRecord(firstName,lastName,birthDate,medications,allergies);

    }


}
