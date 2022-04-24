package com.softwareacademy.webapp.baroni_julien_p5.service;

import com.softwareacademy.webapp.baroni_julien_p5.controller.exception.NoDataFoundException;
import com.softwareacademy.webapp.baroni_julien_p5.model.DTO.*;
import com.softwareacademy.webapp.baroni_julien_p5.model.JsonSerializer.InputData;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.FireStation;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.MedicalRecord;
import com.softwareacademy.webapp.baroni_julien_p5.model.Entities.Person;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author : JULIEN BARONI
 *
 * <p>
 * Cette classe contient tous les services effectuant les traitement métiers renvoyés à l'utilisateur.
 * Elle utilise l'instance contenue dans @InputData pour parcourir les listes serialisées du JSON d'entrée.
 * <p>
 */


@Service
@Slf4j
public class DataService {

    //http://localhost:8080/firestation?stationNumber=%3Cstation_number%3E
    public List<Integer> countAdultsAndChildren(Integer station) {
        List<Integer> AdultsAndChildren = new ArrayList<>();
        Integer adultCount = 0;
        Integer childrenCount = 0;

        log.info("Starting the method : countAdultsAndChildren");
        for (FireStation fireStation : InputData.INSTANCE.getFirestationsData()) {
            log.debug("Iterating through FireStation list");
            String fireStationAddress = null;
            if (fireStation.getStation().equals(station)) {
                fireStationAddress = fireStation.getAddress();
            }
            for (Person person : InputData.INSTANCE.getPersonsData()) {
                log.debug("Iterating through Person list");
                if (fireStationAddress != null && fireStationAddress.equals(person.getAddress())) {
                    for (MedicalRecord medicalRecord : InputData.INSTANCE.getMedicalrecordsData()) {
                        log.debug("Iterating through MedicalRecord list");
                        if ((person.getFirstName().equals(medicalRecord.getFirstName())) && (person.getLastName().equals(medicalRecord.getLastName()))) {
                            if (getAge(medicalRecord.getBirthDate()) > 18) {
                                adultCount += 1;
                                log.debug("Adult added");
                            } else if (getAge(medicalRecord.getBirthDate()) <= 18 && getAge(medicalRecord.getBirthDate()) >= 0) {
                                childrenCount += 1;
                                log.debug("Children added");
                            }
                        }
                    }

                }
            }
        }
        if (adultCount.equals(0) && childrenCount.equals(0)) {
            log.error("No data was fetched from the database using the countAdultsAndChildren method ");
            throw new NoDataFoundException();
        } else {
            log.info("Success");
            AdultsAndChildren.add(adultCount);
            AdultsAndChildren.add(childrenCount);
        }
        return AdultsAndChildren;
    }


    //http://localhost:8080/firestation?stationNumber=%3Cstation_number%3E
    public List<FireStationDTO> returnPersonsCoveredByFireStation(Integer station) {
        List<FireStationDTO> outputList = new ArrayList<>();
        log.info("Starting the method : returnPersonsCoveredByFireStation");
        for (FireStation fireStation : InputData.INSTANCE.getFirestationsData()) {
            log.debug("Iterating through FireStation list");
            String fireStationAddress = null;
            if (fireStation.getStation().equals(station)) {
                fireStationAddress = fireStation.getAddress();
            }
            for (Person person : InputData.INSTANCE.getPersonsData()) {
                log.debug("Iterating through Person list");
                if (fireStationAddress != null && fireStationAddress.equals(person.getAddress())) {
                    for (MedicalRecord medicalRecord : InputData.INSTANCE.getMedicalrecordsData()) {
                        log.debug("Iterating through MedicalRecord list");
                        if ((person.getFirstName().equals(medicalRecord.getFirstName())) && (person.getLastName().equals(medicalRecord.getLastName()))) {
                            if (getAge(medicalRecord.getBirthDate()) > 18) {
                                FireStationDTO outputUrlStationNumber = new FireStationDTO(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone());
                                outputList.add(outputUrlStationNumber);
                                log.debug("Adult infos added");
                            } else if (getAge(medicalRecord.getBirthDate()) <= 18 && getAge(medicalRecord.getBirthDate()) >= 0) {
                                FireStationDTO outputUrlStationNumber = new FireStationDTO(person.getFirstName(), person.getLastName(), person.getAddress(), person.getPhone());
                                outputList.add(outputUrlStationNumber);
                                log.debug("Children infos added");
                            }
                        }
                    }

                }
            }
        }

        if (outputList.isEmpty()) {
            log.error("No data was fetched from the database using the returnPersonsCoveredByFireStation method ");
            throw new NoDataFoundException();
        } else {
            log.info("Success");
            return outputList;
        }


    }

    //http://localhost:8080/childAlert?address=%3Caddress%3E
    //ici il est possible d'avoir une liste vide en retour c'est pourquoi il n'y a pas de gestion d'exception
    public List<ChildDTO> returnChildrenAndHouseMembersLivingAtAnAddress(String address) {
        List<ChildDTO> outputList = new ArrayList<ChildDTO>();
        log.info("Starting the method : returnChildrenAndHouseMembersLivingAtAnAddress");
        for (MedicalRecord medicalRecord : InputData.INSTANCE.getMedicalrecordsData()) {
            log.debug("Iterating through MedicalRecord list");
            String addressToMatch = address;
            if (getAge(medicalRecord.getBirthDate()) <= 18) {
                for (Person person : InputData.INSTANCE.getPersonsData()) {
                    log.debug("Iterating through Person list");
                    if ((person.getFirstName().equals(medicalRecord.getFirstName())) && (person.getLastName().equals(medicalRecord.getLastName())) && person.getAddress().equals(addressToMatch)) {
                        outputList.add(new ChildDTO(person.getFirstName(), person.getLastName(), getAge(medicalRecord.getBirthDate()), getHouseMembers(addressToMatch, person.getFirstName(), person.getLastName())));
                        log.debug("Children and house members added");
                    }
                }
            }
        }

        log.info("Success");
        return outputList;
    }

    //http://localhost:8080/childAlert?address=%3Caddress%3E
    public List<String[]> getHouseMembers(String address, String firstName, String lastName) {
        List<String[]> houseMembers = new ArrayList<>();
        log.info("Starting the method : getHouseMembers");
        for (Person person : InputData.INSTANCE.getPersonsData()) {
            log.debug("Iterating through Person list");
            String[] houseMember = new String[]{"", ""};
            if (person.getAddress().equals(address)) {
                houseMember[0] = person.getFirstName();
                houseMember[1] = person.getLastName();
                //le vide permet ici de ne pas compter une deuxième fois l'enfant dans la maison
                if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {

                } else {
                    houseMembers.add(houseMember);
                    log.debug("HouseMembers added");
                }

            }
        }
        log.info("Success");
        return houseMembers;
    }

    //http://localhost:8080/phoneAlert?firestation=%3Cfirestation_number%3E
    public List<PhoneDTO> returnPhoneListCoveredByFireStation(Integer station) {
        List<PhoneDTO> outputList = new ArrayList<>();
        log.info("Starting the method : returnPhoneListCoveredByFireStation");
        for (FireStation fireStation : InputData.INSTANCE.getFirestationsData()) {
            log.debug("Iterationg through FireStation list");
            String fireStationAddress = null;
            if (fireStation.getStation().equals(station)) {
                fireStationAddress = fireStation.getAddress();
            }
            for (Person person : InputData.INSTANCE.getPersonsData()) {
                log.debug("Iterating through Person list");
                if (fireStationAddress != null && fireStationAddress.equals(person.getAddress())) {
                    outputList.add(new PhoneDTO(person.getPhone()));
                    log.debug("Phone added");
                }
            }
        }

        if (outputList.isEmpty()) {
            log.error("No data was fetched from the database using the returnPhoneListCoveredByFireStation method ");
            throw new NoDataFoundException();
        } else {
            log.info("Success");
            return outputList;
        }

    }

    //http://localhost:8080/fire?address=<address>
    public List<FireDTO> returnHabitantsListLivingAtAnAddress(String address) {
        List<FireDTO> outputList = new ArrayList<>();
        log.info("Starting the method : returnHabitantsListLivingAtAnAddress");
        for (Person person : InputData.INSTANCE.getPersonsData()) {
            log.debug("Iterating through Person list");
            Integer stationNumber = 0;
            for (FireStation fireStation : InputData.INSTANCE.getFirestationsData()) {
                log.debug("Iterationg through FireStation list");
                if (fireStation.getAddress().equals(address)) {
                    stationNumber = fireStation.getStation();
                }
            }
            if (person.getAddress().equals(address)) {
                for (MedicalRecord medicalRecord : InputData.INSTANCE.getMedicalrecordsData()) {
                    log.debug("Iterating through MedicalRecord list");
                    if ((person.getFirstName().equals(medicalRecord.getFirstName())) && (person.getLastName().equals(medicalRecord.getLastName()))) {
                        outputList.add(new FireDTO(medicalRecord.getFirstName(), medicalRecord.getLastName(), person.getPhone(), getAge(medicalRecord.getBirthDate()), medicalRecord.getMedications(), medicalRecord.getAllergies()));
                        log.debug("Habitants added");
                    }

                }
            }

        }
        if (outputList.isEmpty()) {
            log.error("No data was fetched from the database using the returnHabitantsListLivingAtAnAddress method ");
            throw new NoDataFoundException();
        } else {
            log.info("Success");
            return outputList;
        }

    }

    //http://localhost:8080/fire?address=<address>
    public Integer returnFireStationNumberCoveringTheAddress(String address) {
        log.info("Starting the method : returnFireStationNumberCoveringTheAddress");
        Integer stationNumber = 0;

        for (FireStation fireStation : InputData.INSTANCE.getFirestationsData()) {
            log.debug("Iterating through FireStation list");
            if (fireStation.getAddress().equals(address)) {
                log.debug("Station found");
                stationNumber = fireStation.getStation();
            }
        }

        log.info("Success");
        return stationNumber;

    }

    public List<FloodPersonsAndAdressDTO> returnPersonsAndAdressCoveredByFireStationsDuringFlood(List<Integer> fireStationsNumbers) {
        List<FloodPersonsAndAdressDTO> outputList = new ArrayList<FloodPersonsAndAdressDTO>();
        log.info("Starting the method : returnPersonsAndAdressCoveredByFireStationsDuringFlood");
        for (int fireStationNumber : fireStationsNumbers) {
            for (FireStation fireStation : InputData.INSTANCE.getFirestationsData()) {
                log.debug("Iterating through FireStation list");
                String fireStationAddress = null;
                if (fireStation.getStation().equals(fireStationNumber)) {
                    fireStationAddress = fireStation.getAddress();
                }
                if (fireStationAddress != null) {

                    outputList.add(new FloodPersonsAndAdressDTO(fireStation.getAddress(), returnHabitantsListLivingAtAnAddress(fireStation.getAddress())));
                    log.debug("Persons infos during flood added");
                }
            }
        }
        if (outputList.isEmpty()) {
            log.error("No data was fetched from the database using the returnPersonsAndAdressCoveredByFireStationsDuringFlood method ");
            throw new NoDataFoundException();
        } else {
            log.info("Success");
            return outputList;
        }
    }


    //http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
    public PersonInfoDTO returnPersonInfos(String firstName, String lastName) {
        PersonInfoDTO personInfos = null;
        log.info("Starting the method : returnPersonInfos");
        for (Person person : InputData.INSTANCE.getPersonsData()) {
            log.debug("Iterating through Person list");
            if ((person.getFirstName().equals(firstName) && (person.getLastName().equals(lastName)))) {
                for (MedicalRecord medicalRecord : InputData.INSTANCE.getMedicalrecordsData()) {
                    log.debug("Iterating through MedicalRecord list");
                    if ((person.getFirstName().equals(medicalRecord.getFirstName())) && (person.getLastName().equals(medicalRecord.getLastName()))) {
                        log.debug("Infos added");
                        personInfos = new PersonInfoDTO(firstName, lastName, person.getAddress(), getAge(medicalRecord.getBirthDate()), person.getEmail(), medicalRecord.getMedications(), medicalRecord.getAllergies());
                    }
                }
            }
        }
        try {
            personInfos.getFirstName();
            log.info("Success");
            return personInfos;
        } catch (NullPointerException e) {
            log.error("No data was fetched from the database using the returnPersonInfos method ");
            throw new NoDataFoundException();
        }
    }


    public List<PersonEmailDTO> returnCityEmailAddresses(String city) {
        List<PersonEmailDTO> outputList = new ArrayList<>();
        for (Person person : InputData.INSTANCE.getPersonsData()) {
            log.debug("Iterating through Person list");
            if (person.getCity().equals(city)) {
                log.debug("Email address added");
                outputList.add(new PersonEmailDTO(person.getEmail()));
            }
        }
        if (outputList.isEmpty()) {
            log.error("No data was fetched from the database using the returnCityEmailAddresses method ");
            throw new NoDataFoundException();
        } else {
            log.info("Success");
            return outputList;
        }
    }


    //cette fonction renvoit un age quand on lui donne une date de naissance au format DD/MM/YYYY
    public Integer getAge(@NotNull Calendar dateOfBirth) {
        int age = 0;
        Integer yearOfBirth = dateOfBirth.get(Calendar.YEAR);
        int yearOfToday = Calendar.getInstance().get(Calendar.YEAR);
        age = yearOfToday - yearOfBirth;
        return age;
    }

    //cette fonction permet d'appliquer le format MM/dd/yyyy à une string rentrée en Input
    public Calendar returnDateWithWantedFormat(String date) {
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
