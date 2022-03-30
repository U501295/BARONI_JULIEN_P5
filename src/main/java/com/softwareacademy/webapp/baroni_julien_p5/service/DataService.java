package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.model.ODT.OutputDataCountFormat;
import com.softwareacademy.webapp.baroni_julien_p5.model.ODT.OutputDataListFormat;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DataService {
    InputData inputData = new InputData();
    MedicalRecord medicalRecord;
    Person person;
    OutputDataListFormat outputDataList;
    OutputDataCountFormat outputDataCount;

    public List<OutputDataListFormat> returnPersonsCoveredByFireStation(Integer station){
        List<OutputDataListFormat> output = null;
        String fireStationAdress ;
        inputData.getFirestations().forEach(objectTodealWith ->{
            if (objectTodealWith.getStation().equals(station)){
                objectTodealWith.getAddress();
            }
        });

        return output;
    }

    //TODO : compter les enfants et les adultes dans la fonction
    private List<OutputDataListFormat> actionsForReturnPersonsCoveredByFireStation(Integer station){
        List<OutputDataListFormat> outputList = null;

        for (FireStation fireStation : inputData.getFirestations()) {
            String fireStationAdress = null;
            if (fireStation.getStation().equals(station)){
                fireStationAdress = fireStation.getAddress() ;
            }
            for (Person person : inputData.getPersons()){
                if (fireStationAdress.equals(person.getAddress())){
                    for (MedicalRecord medicalRecord : inputData.getMedicalrecords()){
                        if ((person.getFirstName().equals(medicalRecord.getFirstName())) && (person.getLastName().equals(medicalRecord.getLastName()))){
                            if (getAge(medicalRecord.getBirthDate())>18){
                                outputList.add(new OutputDataListFormat(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone(), 0,1));
                            } else if (getAge(medicalRecord.getBirthDate())<18 && getAge(medicalRecord.getBirthDate())>=0){
                                outputList.add(new OutputDataListFormat(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone(), 1, 0));
                            }
                        }
                    }

                }
            }
        }
        return outputList;
    }



    private int getAge(@NotNull Date dateOfBirth){
        int age =0;
        String dateToparse = dateOfBirth.toString();
        String[] parsedDateTable = dateToparse.split("/");
        String parsedDate = parsedDateTable[2];
        Integer yearOfBirth = Integer.parseInt(parsedDate);
        int yearOfToday = Calendar.getInstance().get(Calendar.YEAR);
        age = yearOfToday - yearOfBirth;
        return age;
    }






}
