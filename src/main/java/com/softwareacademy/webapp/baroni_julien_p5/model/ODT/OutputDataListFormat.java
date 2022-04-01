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

    private List<String> medicalBackGround;

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
    public OutputDataListFormat(String firstName,String lastName, Integer age,List<String[]> houseMembers){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.houseMembers = houseMembers;
    }

    // pour la méthode returnPhoneListCoveredByFireStation
    public OutputDataListFormat(String phone){
        this.phone = phone;
    }

    //pour la methode returnHabitantListLivingAtAnAddress
    public OutputDataListFormat(String lastName, String phone, Integer age, List<String> medicalBackGround, Integer stationNumber){
        this.lastName = lastName;
        this.phone = phone;
        this.age = age;
        this.medicalBackGround = medicalBackGround;
        this.stationNumber = stationNumber;
    }
}
