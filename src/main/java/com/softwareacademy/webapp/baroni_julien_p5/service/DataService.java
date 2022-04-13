package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.model.ODT.OutputDataListFormat;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DataService {
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
        List<OutputDataListFormat> outputList = new ArrayList<OutputDataListFormat>();
        for (FireStation fireStation : InputData.INSTANCE.getFirestationsData()) {
            String fireStationAddress = null;
            if (fireStation.getStation().equals(station)){
                fireStationAddress = fireStation.getAddress() ;
            }
            for (Person person : InputData.INSTANCE.getPersonsData()){
                if (fireStationAddress!=null && fireStationAddress.equals(person.getAddress())){
                    for (MedicalRecord medicalRecord : InputData.INSTANCE.getMedicalrecordsData()){
                        if ((person.getFirstName().equals(medicalRecord.getFirstName())) && (person.getLastName().equals(medicalRecord.getLastName()))){
                            if (getAge(medicalRecord.getBirthDate())>18){
                                OutputDataListFormat outputDataList = new OutputDataListFormat(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone(), 0,1);
                                outputList.add(outputDataList);
                            } else if (getAge(medicalRecord.getBirthDate())<=18 && getAge(medicalRecord.getBirthDate())>=0){
                                OutputDataListFormat outputDataList = new OutputDataListFormat(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone(), 1, 0);
                                outputList.add(outputDataList);
                            }
                        }
                    }

                }
            }
        }
        return outputList;
    }

    public List<OutputDataListFormat> returnChildrenAndParentsLivingAtAnAddress(String address){
        List<OutputDataListFormat> outputList = new ArrayList<OutputDataListFormat>();
        for (MedicalRecord medicalRecord : InputData.INSTANCE.getMedicalrecordsData()){
            String addressToMatch = "";
            if (getAge(medicalRecord.getBirthDate())<=18){
                for (Person person : InputData.INSTANCE.getPersonsData()){
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
        List<String[]> houseMembers = new ArrayList<String[]>();
        for(Person person : InputData.INSTANCE.getPersonsData()){
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
        List<OutputDataListFormat> outputList = new ArrayList<OutputDataListFormat>();
        for (FireStation fireStation : InputData.INSTANCE.getFirestationsData()) {
            String fireStationAddress = null;
            if (fireStation.getStation().equals(station)) {
                fireStationAddress = fireStation.getAddress();
            }
            for (Person person : InputData.INSTANCE.getPersonsData()){
                if (fireStationAddress!=null && fireStationAddress.equals(person.getAddress())){
                    outputList.add(new OutputDataListFormat(person.getPhone(),null));
                }
            }
        }

        return outputList;
    }

    //http://localhost:8080/fire?address=<address>
    public List<OutputDataListFormat> returnHabitantsListLivingAtAnAddress(String address){
        List<OutputDataListFormat> outputList = new ArrayList<>();

        for(Person person : InputData.INSTANCE.getPersonsData()){
            Integer stationNumber = 0;
            for (FireStation fireStation : InputData.INSTANCE.getFirestationsData()){
                if (fireStation.getAddress().equals(address)){
                    stationNumber = fireStation.getStation();
                }
            }
            if (person.getAddress().equals(address)){
                for (MedicalRecord medicalRecord : InputData.INSTANCE.getMedicalrecordsData()){
                    if ((person.getFirstName().equals(medicalRecord.getFirstName())) && (person.getLastName().equals(medicalRecord.getLastName()))){
                        outputList.add(new OutputDataListFormat(medicalRecord.getFirstName(), medicalRecord.getLastName(), person.getPhone(), getAge(medicalRecord.getBirthDate()), medicalRecord.getMedications(),medicalRecord.getAllergies(), stationNumber));
                    }

                }
            }

        }
        return outputList;
    }


    //http://localhost:8080/flood/stations?stations=<a list of station_numbers>
    public List<List <OutputDataListFormat>> returnHomesCoveredByFireStationsDuringFlood(List<Integer> fireStationsNumbers){
        List<List <OutputDataListFormat>> homesCoveredByFireStationsDuringFlood = new ArrayList<>();

        for (int fireStationNumber : fireStationsNumbers){

            List<OutputDataListFormat> outputList = new ArrayList<OutputDataListFormat>();
            for (FireStation fireStation : InputData.INSTANCE.getFirestationsData()) {
                String fireStationAddress = null;
                if (fireStation.getStation().equals(fireStationNumber)){
                    fireStationAddress = fireStation.getAddress() ;
                }
                for (Person person : InputData.INSTANCE.getPersonsData()){
                    if (fireStationAddress!=null && fireStationAddress.equals(person.getAddress())){
                        for (MedicalRecord medicalRecord : InputData.INSTANCE.getMedicalrecordsData()){
                            if ((person.getFirstName().equals(medicalRecord.getFirstName())) && (person.getLastName().equals(medicalRecord.getLastName()))){
                                outputList.add(new OutputDataListFormat(medicalRecord.getFirstName(), medicalRecord.getLastName(), person.getPhone(), getAge(medicalRecord.getBirthDate()), medicalRecord.getMedications(),medicalRecord.getAllergies(),fireStation.getAddress(), fireStation.getStation()));
                            }
                        }

                    }
                }
            }

            homesCoveredByFireStationsDuringFlood.add(outputList);
        }
        return homesCoveredByFireStationsDuringFlood;
    }


    //http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
    public OutputDataListFormat returnPersonInfos(String firstName, String lastName){
        OutputDataListFormat personInfos = null;
        for(Person person : InputData.INSTANCE.getPersonsData()){
            if ((person.getFirstName().equals(firstName) && (person.getLastName().equals(lastName)))){
                    for(MedicalRecord medicalRecord : InputData.INSTANCE.getMedicalrecordsData()){
                        if ((person.getFirstName().equals(medicalRecord.getFirstName())) && (person.getLastName().equals(medicalRecord.getLastName()))) {
                            personInfos = new OutputDataListFormat(firstName,lastName, person.getAddress(),getAge(medicalRecord.getBirthDate()),person.getEmail(), medicalRecord.getMedications(),medicalRecord.getAllergies());
                        }
                        }
                }

        }
        
        return personInfos;
    }


    public List<OutputDataListFormat> returnCityUnhabitantsEmail(String city){
        List<OutputDataListFormat> outputList = new ArrayList<>();
        for (Person person : InputData.INSTANCE.getPersonsData()){
            if (person.getCity().equals(city)){
                outputList.add(new OutputDataListFormat(null,person.getEmail()));
            }
        }
        return outputList;
    }




    public Integer getAge(@NotNull Calendar dateOfBirth){
        int age =0;
        Integer yearOfBirth = dateOfBirth.get(Calendar.YEAR);
        int yearOfToday = Calendar.getInstance().get(Calendar.YEAR);
        age = yearOfToday - yearOfBirth;
        return age;
    }

    public Calendar returnDateWithWantedFormat(String date){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date parsedDate = null;
        try {
            parsedDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(parsedDate);
        return calendar;

    }






}
