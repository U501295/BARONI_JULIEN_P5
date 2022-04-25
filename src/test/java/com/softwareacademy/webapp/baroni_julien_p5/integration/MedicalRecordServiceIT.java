package com.softwareacademy.webapp.baroni_julien_p5.integration;

import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.service.DataService;
import com.softwareacademy.webapp.baroni_julien_p5.service.MedicalRecordService;
import com.softwareacademy.webapp.baroni_julien_p5.service.MedicalRecordServiceTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.List;

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Tests d'intégrations permettant de dérouler les services CRUDS les uns à la suites des autres
 * dans une logique de test fonctionnel. Afin de valider la non permanence des modifications en base.
 * On compare nos modifications avec une version vierge de la base de donnée afin de s'assurer de leur validité.
 * <p>
 */


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class MedicalRecordServiceIT {


    @Autowired
    MedicalRecordService medicalRecordService;

    DataService dataService = new DataService();

    static List<MedicalRecord> mdJsonData = InputData.getJsonData().getMedicalrecordsData();
    static List<MedicalRecord> mdRefCompare = InputData.getJsonData().getMedicalrecordsData();

    MedicalRecordServiceTest mdt = new MedicalRecordServiceTest();


    @Test
    void parcoursUtilisateurPassant() {
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