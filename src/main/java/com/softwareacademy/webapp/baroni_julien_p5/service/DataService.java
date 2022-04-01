package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.model.ODT.OutputDataListFormat;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class DataService {
    InputData inputData = new InputData();
    MedicalRecord medicalRecord;
    Person person;
    OutputDataListFormat outputDataList;

    public Integer[] countAdultsAndChildren(List<OutputDataListFormat> outputList){
        Integer[] adultsAndChildren = new Integer[]{0,0};
        outputList.forEach(objectToDealWith ->{
            if (objectToDealWith.getIsAnAdult().equals(1) && objectToDealWith.getIsAChildren().equals(0)){
                adultsAndChildren[0]+=1;
            }else if (objectToDealWith.getIsAnAdult().equals(0) && objectToDealWith.getIsAChildren().equals(1)){
                adultsAndChildren[1]+=1;
            }
        });
        return adultsAndChildren;
    }


    public List<OutputDataListFormat> returnPersonsCoveredByFireStation(Integer station){
        List<OutputDataListFormat> outputList = null;

        for (FireStation fireStation : inputData.getFirestations()) {
            String fireStationAddress = null;
            if (fireStation.getStation().equals(station)){
                fireStationAddress = fireStation.getAddress() ;
            }
            for (Person person : inputData.getPersons()){
                if (fireStationAddress.equals(person.getAddress())){
                    for (MedicalRecord medicalRecord : inputData.getMedicalrecords()){
                        if ((person.getFirstName().equals(medicalRecord.getFirstName())) && (person.getLastName().equals(medicalRecord.getLastName()))){
                            if (getAge(medicalRecord.getBirthDate())>18){
                                outputList.add(new OutputDataListFormat(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone(), 0,1));
                            } else if (getAge(medicalRecord.getBirthDate())<=18 && getAge(medicalRecord.getBirthDate())>=0){
                                outputList.add(new OutputDataListFormat(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone(), 1, 0));
                            }
                        }
                    }

                }
            }
        }
        return outputList;
    }

    public List<OutputDataListFormat> returnChildrenAndParentsLivingAtAnAddress(String address){
        List<OutputDataListFormat> outputList = null;

        for (MedicalRecord medicalRecord : inputData.getMedicalrecords()){
            String addressToMatch = "";
            if (getAge(medicalRecord.getBirthDate())<=18){
                for (Person person : inputData.getPersons()){
                    if ((person.getFirstName().equals(medicalRecord.getFirstName())) && (person.getLastName().equals(medicalRecord.getLastName()))){
                        addressToMatch = person.getAddress();
                        outputList.add(new OutputDataListFormat(person.getFirstName(), person.getLastName(),getAge(medicalRecord.getBirthDate()), getHouseMembers(addressToMatch)));
                    }
                }
            }
        }

        return outputList;
    }

    public List<String[]> getHouseMembers(String address){
        List<String[]> houseMembers = null;
        for(Person person : inputData.getPersons()){
            String[] houseMember = new String[]{"",""};
            if (person.getAddress().equals(address)){
                houseMember[0] = person.getFirstName();
                houseMember[1] = person.getLastName();
                houseMembers.add(houseMember);
            }
        }
        return houseMembers;
    }

    public List<OutputDataListFormat> returnPhoneListCoveredByFireStation(Integer station){
        List<OutputDataListFormat> outputList = null;
        for (FireStation fireStation : inputData.getFirestations()) {
            String fireStationAddress = null;
            if (fireStation.getStation().equals(station)) {
                fireStationAddress = fireStation.getAddress();
            }
            for (Person person : inputData.getPersons()){
                if (fireStationAddress.equals(person.getAddress())){
                    outputList.add(new OutputDataListFormat(person.getPhone()));
                }
            }
        }

        return outputList;
    }

    public List<OutputDataListFormat> returnHabitantsListLivingAtAnAddress(String address){
        List<OutputDataListFormat> outputList = null;

        return outputList;
    }



    public Integer getAge(@NotNull Calendar dateOfBirth){
        int age =0;
        Integer yearOfBirth = dateOfBirth.get(Calendar.YEAR);
        int yearOfToday = Calendar.getInstance().get(Calendar.YEAR);
        age = yearOfToday - yearOfBirth;
        return age;
    }






}
