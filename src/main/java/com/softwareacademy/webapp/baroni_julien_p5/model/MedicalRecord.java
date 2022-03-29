package com.softwareacademy.webapp.baroni_julien_p5.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class MedicalRecord {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("birthdate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
    private Date birthDate ;
    @JsonProperty("medications")
    @ElementCollection
    private List<String> medications;
    @JsonProperty("allergies")
    @ElementCollection
    private List<String> allergies ;


    @JsonAnyGetter
    public Date getBirthDate() {
        if (this.birthDate != null){
            Date birthDate = (Date)this.birthDate.clone();
            return birthDate;
        }else{
            return null;
        }
    }

    @JsonAnySetter
    public void setBirthDate(Date birthDate) {
        if (birthDate != null){
            if (this.birthDate == null){
                this.birthDate = (Date)birthDate.clone();
            }else{
                this.birthDate.setTime(birthDate.getTime());
            }
        }else{
            this.birthDate = null;
        }
    }


}
