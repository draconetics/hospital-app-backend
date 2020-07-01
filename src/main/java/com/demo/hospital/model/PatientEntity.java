package com.demo.hospital.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.ui.Model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "patients")
public class PatientEntity extends ModelBase {

    private String name;
    private String lastName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date bornDate;
    private String direction;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
