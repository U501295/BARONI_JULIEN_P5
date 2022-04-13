package com.softwareacademy.webapp.baroni_julien_p5.model.ODT;

import lombok.Data;

import java.util.List;

@Data
public class OutputDataListFormat {

    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private Integer zip;

    private String phone;

    private String email;

    private Integer age;

    private List<String> medications;

    private List<String> allergies;

    private Integer isAChildren;

    private Integer isAnAdult;

    private List<String[]> houseMembers;

    private Integer stationNumber;


    // pour la méthode actionsForReturnPersonsCoveredByFireStation
    public OutputDataListFormat(String firstName, String lastName, String address, String phone, Integer isAChildren, Integer isAnAdult) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.isAChildren = isAChildren;
        this.isAnAdult = isAnAdult;
    }

    // pour la méthode returnChildrenAndParentsLivingAtAnAddress
    public OutputDataListFormat(String firstName, String lastName, Integer age, List<String[]> houseMembers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.houseMembers = houseMembers;
    }

    // pour la méthode returnPhoneListCoveredByFireStation et returnUnhabitantsEmails
    public OutputDataListFormat(String phone,String email) {
        this.phone = phone;
        this.email = email;
    }


    //pour la methode returnHabitantListLivingAtAnAddress
    public OutputDataListFormat(String firstName, String lastName, String phone, Integer age, List<String> medications, List<String> allergies, Integer stationNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
        this.stationNumber = stationNumber;
    }

    //pour la methode returnHomesCoveredByFireStationsDuringFlood
    public OutputDataListFormat(String firstName, String lastName, String phone, Integer age, List<String> medications, List<String> allergies, String address, Integer stationNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
        this.address = address;
        this.stationNumber = stationNumber;
    }

    //pour la methode returnPersonInfos
    public OutputDataListFormat(String firstName, String lastName, String address, Integer age, String email, List<String> medications, List<String> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;

    }
}