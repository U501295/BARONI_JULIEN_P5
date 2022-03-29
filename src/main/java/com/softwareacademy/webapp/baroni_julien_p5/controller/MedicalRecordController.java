package com.softwareacademy.webapp.baroni_julien_p5.controller;

import com.softwareacademy.webapp.baroni_julien_p5.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalRecordController {

    private MedicalRecordService medicalRecordService;

    public MedicalRecordController (MedicalRecordService medicalRecordService){
        this.medicalRecordService = medicalRecordService;
    }
}
