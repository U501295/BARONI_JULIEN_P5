package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer.InputData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MedicalRecordServiceTest {

    MedicalRecordService medicalRecordService = new MedicalRecordService();
    DataService dataService = new DataService();


    @Test
    void removeMedicalRecord() {
        List<MedicalRecord> medicalRecords = InputData.INSTANCE.getMedicalrecordsData();
        Assertions.assertThat(medicalRecords.size()).isEqualTo(23);
        List<MedicalRecord> results = medicalRecordService.removeMedicalRecord("Sophia","Zemicks");
        Assertions.assertThat(results.size()).isEqualTo(22);
    }

    @Test
    void addMedicalRecord() {
        Calendar calendar4 = dataService.returnDateWithWantedFormat("07/18/1900");
        List<String> medications4 = new ArrayList();
        List<String> allergies4 = new ArrayList() {{
            add("banana");
            add("oreo");
        }};
        List<MedicalRecord> results = medicalRecordService.addMedicalRecord("Four","Quatre",calendar4,medications4,allergies4);
        Assertions.assertThat(results.size()).isEqualTo(24);

    }

    @Test
    void modifyMedicalRecord(){
        List<MedicalRecord> medicalRecords = InputData.INSTANCE.getMedicalrecordsData();
        Assertions.assertThat(medicalRecords.get(0).getMedications().get(0)).isEqualTo("aznol:350mg");
        Assertions.assertThat(medicalRecords.get(0).getAllergies().get(0)).isEqualTo("nillacilan");
        List<String> medications5 = new ArrayList();
        List<String> allergies5 = new ArrayList() {{
            add("banana");
            add("oreo");
        }};
        List<MedicalRecord> results = medicalRecordService.modifyMedicalRecord("John","Boyd",dataService.returnDateWithWantedFormat("07/18/1900"),medications5,allergies5);
        Assertions.assertThat(results.size()).isEqualTo(23);
        Assertions.assertThat(results.get(0).getMedications()).isEmpty();
        Assertions.assertThat(results.get(0).getAllergies().get(0)).isEqualTo("banana");
    }
}