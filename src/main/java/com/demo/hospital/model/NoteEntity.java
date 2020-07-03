package com.demo.hospital.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notes")
public class NoteEntity extends ModelBase{


    private String description;
    private Date dateOfEmition;
    @ManyToOne
    private PatientEntity patient;

    public NoteEntity(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateOfEmition() {
        return dateOfEmition;
    }

    public void setDateOfEmition(Date dateOfEmition) {
        this.dateOfEmition = dateOfEmition;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }
}
