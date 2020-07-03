package com.demo.hospital.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "patients")
public class PatientEntity extends ModelBase {

    private String name;
    private String lastName;
    private Date bornDate;
    private String address;

    @ManyToOne
    private DoctorEntity doctor;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE)
    private List<NoteEntity> noteList;


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    public List<NoteEntity> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<NoteEntity> noteList) {
        this.noteList = noteList;
    }
}
