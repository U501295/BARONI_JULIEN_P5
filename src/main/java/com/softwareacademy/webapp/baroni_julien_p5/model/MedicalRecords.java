package com.softwareacademy.webapp.baroni_julien_p5.model;

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
public class MedicalRecords {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    @ElementCollection
    private List<String> medications;
    @ElementCollection
    private List<String> medicalRecord;

    public Date getBirthDate() {
        if (this.birthDate != null){
            Date inTime = (Date)this.birthDate.clone();
            return inTime;
        }else{
            return null;
        }
    }

    public void setInTime(Date birthDate) {
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
