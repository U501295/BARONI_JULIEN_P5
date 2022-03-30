package com.softwareacademy.webapp.baroni_julien_p5.model.ODT;

import lombok.Data;

@Data
public class OutputDataCountFormat {

    private Integer numberOfChildren;

    private Integer numberOfAdults;

    public OutputDataCountFormat(Integer numberOfChildren, Integer numberOfAdults) {
        this.numberOfChildren = numberOfChildren;
        this.numberOfAdults = numberOfAdults;
    }
}
