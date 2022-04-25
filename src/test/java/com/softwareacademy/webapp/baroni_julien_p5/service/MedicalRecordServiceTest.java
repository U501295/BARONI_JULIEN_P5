package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.service.DataService;
import com.softwareacademy.webapp.baroni_julien_p5.service.MedicalRecordService;
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

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Tests unitaires permettant de s'assurer que la couche service est fonctionnelle
 * <p>
 */
public class MedicalRecordServiceTest {
    List<MedicalRecord> testJDD = new ArrayList<>();
    DataService dataService = new DataService();
    MedicalRecordService medicalRecordService = new MedicalRecordService();

    Calendar calendar1 = dataService.returnDateWithWantedFormat("03/06/1988");
    Calendar calendar2 = dataService.returnDateWithWantedFormat("09/06/2017");
    public Calendar calendar3 = dataService.returnDateWithWantedFormat("03/15/1965");

    List<String> medications1 = new ArrayList() {{
        add("aznol:60mg");
    }};
    List<String> medications2 = new ArrayList() {{
        add("hydrapermazol:900mg");
    }};
    List<String> medications3 = new ArrayList() {{
        add("pharmacol:5000mg");
        add("terazine:500mg");

    }};
    List<String> allergies1 = new ArrayList() {{
        add("peanut");
    }};
    List<String> allergies2 = new ArrayList() {{
        add("shellfish");
        add("aznol");
    }};
    List<String> allergies3 = new ArrayList();


    public MedicalRecord medicalRecordForIntegrationTest = new MedicalRecord("Sophia", "Zemicks", calendar1, medications1, allergies1);

    @BeforeEach
    public void initAll() {
        MedicalRecord medicalRecord1 = new MedicalRecord("Sophia", "Zemicks", calendar1, medications1, allergies1);
        MedicalRecord medicalRecord2 = new MedicalRecord("Paul", "Poulpe", calendar2, medications2, allergies2);
        MedicalRecord medicalRecord3 = new MedicalRecord("Titi", "Toz", calendar3, medications3, allergies3);
        testJDD.removeAll(testJDD);
        testJDD.add(medicalRecord1);
        testJDD.add(medicalRecord2);
        testJDD.add(medicalRecord3);
    }

    @Test
    void removeMedicalRecord() {
        Assertions.assertThat(testJDD.size()).isEqualTo(3);
        medicalRecordService.removeMedicalRecord(testJDD, "Sophia", "Zemicks");
        Assertions.assertThat(testJDD.size()).isEqualTo(2);
    }

    @Test
    void addMedicalRecord() throws ParseException {
        Calendar calendar4 = dataService.returnDateWithWantedFormat("07/18/1900");
        List<String> medications4 = new ArrayList();
        List<String> allergies4 = new ArrayList() {{
            add("banana");
            add("oreo");
        }};
        medicalRecordService.addMedicalRecord(testJDD, "Four", "Quatre", calendar4, medications4, allergies4);
        Assertions.assertThat(testJDD.size()).isEqualTo(4);

    }

    @Test
    void modifyMedicalRecord() throws ParseException {
        List<String> medications5 = new ArrayList();
        List<String> allergies5 = new ArrayList() {{
            add("banana");
            add("oreo");
        }};
        medicalRecordService.modifyMedicalRecord(testJDD, "Titi", "Toz", dataService.returnDateWithWantedFormat("07/18/1900"), medications5, allergies5);
        Assertions.assertThat(testJDD.size()).isEqualTo(3);
    }
}