package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.*;
import com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DataService {
    MedicalRecord medicalRecord;
    Person person;
    DataListFormatDTO outputDataList;

    public List<Integer> countAdultsAndChildren(Integer station){
        List<Integer> AdultsAndChildren= new ArrayList<>();
        Integer adultCount = 0;
        Integer childrenCount = 0;

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
                                adultCount+=1;
                            } else if (getAge(medicalRecord.getBirthDate())<=18 && getAge(medicalRecord.getBirthDate())>=0){
                               childrenCount+=1;
                            }
                        }
                    }

                }
            }
        }

        AdultsAndChildren.add(adultCount);
        AdultsAndChildren.add(childrenCount);
        return AdultsAndChildren;
    }

    //TODO : supprimer si code modifie bon 2
    /*public List<OutputDataListFormat> returnPersonsCoveredByFireStation(Integer station){
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
    }*/

    public List<FireStationDTO> returnPersonsCoveredByFireStation(Integer station){
        List<FireStationDTO> outputList = new ArrayList<FireStationDTO>();
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
                                FireStationDTO outputUrlStationNumber = new FireStationDTO(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone());
                                outputList.add(outputUrlStationNumber);
                            } else if (getAge(medicalRecord.getBirthDate())<=18 && getAge(medicalRecord.getBirthDate())>=0){
                                FireStationDTO outputUrlStationNumber = new FireStationDTO(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone());
                                outputList.add(outputUrlStationNumber);
                            }
                        }
                    }

                }
            }
        }
        return outputList;
    }

    public List<ChildDTO> returnChildrenAndParentsLivingAtAnAddress(String address){
        List<ChildDTO> outputList = new ArrayList<ChildDTO>();
        for (MedicalRecord medicalRecord : InputData.INSTANCE.getMedicalrecordsData()){
            String addressToMatch = address;
            if (getAge(medicalRecord.getBirthDate())<=18){
                for (Person person : InputData.INSTANCE.getPersonsData()){
                    if ((person.getFirstName().equals(medicalRecord.getFirstName())) && (person.getLastName().equals(medicalRecord.getLastName()))){
                        //addressToMatch = person.getAddress();
                        outputList.add(new ChildDTO(person.getFirstName(), person.getLastName(),getAge(medicalRecord.getBirthDate()), getHouseMembers(addressToMatch)));
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

    public List<PhoneDTO> returnPhoneListCoveredByFireStation(Integer station){
        List<PhoneDTO> outputList = new ArrayList<PhoneDTO>();
        for (FireStation fireStation : InputData.INSTANCE.getFirestationsData()) {
            String fireStationAddress = null;
            if (fireStation.getStation().equals(station)) {
                fireStationAddress = fireStation.getAddress();
            }
            for (Person person : InputData.INSTANCE.getPersonsData()){
                if (fireStationAddress!=null && fireStationAddress.equals(person.getAddress())){
                    outputList.add(new PhoneDTO(person.getPhone()));
                }
            }
        }

        return outputList;
    }

    //http://localhost:8080/fire?address=<address>
    public List<FireDTO> returnHabitantsListLivingAtAnAddress (String address){
        List<FireDTO> outputList = new ArrayList<>();

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
                        outputList.add(new FireDTO(medicalRecord.getFirstName(), medicalRecord.getLastName(), person.getPhone(), getAge(medicalRecord.getBirthDate()), medicalRecord.getMedications(),medicalRecord.getAllergies()));
                    }

                }
            }

        }
        return outputList;
    }

    //http://localhost:8080/fire?address=<address>
    public Integer returnFireStationNumberCoveringTheAddress(String address){

            Integer stationNumber = 0;

        for (FireStation fireStation : InputData.INSTANCE.getFirestationsData()){
            if (fireStation.getAddress().equals(address)){
                stationNumber = fireStation.getStation();
            }
        }

        return stationNumber;
    }

    public List <FloodPersonsAndAdressDTO> returnPersonsAndAdressCoveredByFireStationsDuringFlood(List<Integer> fireStationsNumbers){
        List<FloodPersonsAndAdressDTO> outputList = new ArrayList<FloodPersonsAndAdressDTO>();

        for (int fireStationNumber : fireStationsNumbers){
            for (FireStation fireStation : InputData.INSTANCE.getFirestationsData()) {
                String fireStationAddress = null;
                if (fireStation.getStation().equals(fireStationNumber)){
                    fireStationAddress = fireStation.getAddress() ;
                }

                    if (fireStationAddress!=null){

                                outputList.add(new FloodPersonsAndAdressDTO(fireStation.getAddress(),returnHabitantsListLivingAtAnAddress(fireStation.getAddress())));


                    }

            }
        }
        return outputList;
    }


    //http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
    public PersonInfoDTO returnPersonInfos(String firstName, String lastName){
        PersonInfoDTO personInfos = null;
        for(Person person : InputData.INSTANCE.getPersonsData()){
            if ((person.getFirstName().equals(firstName) && (person.getLastName().equals(lastName)))){
                    for(MedicalRecord medicalRecord : InputData.INSTANCE.getMedicalrecordsData()){
                        if ((person.getFirstName().equals(medicalRecord.getFirstName())) && (person.getLastName().equals(medicalRecord.getLastName()))) {
                            personInfos = new PersonInfoDTO(firstName,lastName, person.getAddress(),getAge(medicalRecord.getBirthDate()),person.getEmail(), medicalRecord.getMedications(),medicalRecord.getAllergies());
                        }
                        }
                }

        }
        
        return personInfos;
    }


    public List<PersonEmailDTO> returnCityEmailAddresses(String city){
        List<PersonEmailDTO> outputList = new ArrayList<>();
        for (Person person : InputData.INSTANCE.getPersonsData()){
            if (person.getCity().equals(city)){
                outputList.add(new PersonEmailDTO(person.getEmail()));
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
