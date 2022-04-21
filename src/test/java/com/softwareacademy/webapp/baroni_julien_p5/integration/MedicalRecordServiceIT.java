package com.softwareacademy.webapp.baroni_julien_p5.integration;

import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.service.DataService;
import com.softwareacademy.webapp.baroni_julien_p5.service.FireStationService;
import com.softwareacademy.webapp.baroni_julien_p5.service.MedicalRecordService;
import com.softwareacademy.webapp.baroni_julien_p5.unitaires.service.MedicalRecordServiceTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class MedicalRecordServiceIT {


    @Autowired
    MedicalRecordService medicalRecordService;

    DataService dataService = new DataService();

    static List<MedicalRecord> mdJsonData = InputData.getJsonData().getMedicalrecordsData();
    static List<MedicalRecord> mdRefCompare = InputData.INSTANCE.getMedicalrecordsData();

    MedicalRecordServiceTest mdt = new MedicalRecordServiceTest();


    @Test
    @Order(1)
    void parcoursUtilisateur() {
        List<MedicalRecord> result = medicalRecordService.removeMedicalRecord(mdJsonData, "Tenley", "Boyd");
        Assertions.assertThat(result.size()).isEqualTo(mdRefCompare.size() - 1);
        result = medicalRecordService.addMedicalRecord(mdJsonData,
                mdt.medicalRecordForIntegrationTest.getFirstName(),
                mdt.medicalRecordForIntegrationTest.getLastName(),
                mdt.medicalRecordForIntegrationTest.getBirthDate(),
                mdt.medicalRecordForIntegrationTest.getMedications(),
                mdt.medicalRecordForIntegrationTest.getAllergies()
        );
        Assertions.assertThat(result.size()).isEqualTo(mdRefCompare.size());

        Calendar modifiedBirthDate = dataService.returnDateWithWantedFormat("10/10/2010");

        result = medicalRecordService.modifyMedicalRecord(mdJsonData,
                mdt.medicalRecordForIntegrationTest.getFirstName(),
                mdt.medicalRecordForIntegrationTest.getLastName(),
                modifiedBirthDate,
                mdt.medicalRecordForIntegrationTest.getMedications(),
                mdt.medicalRecordForIntegrationTest.getAllergies()
        );
        Assertions.assertThat(result.get(22).getBirthDate()).isEqualTo(modifiedBirthDate);
    }

}