package com.demo.hospital.model;

import com.demo.hospital.repository.DoctorRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "doctors")
public class DoctorEntity extends ModelBase {

    private String name;
    private String lastName;
    private Date bornDate;
    private String address;
    @ManyToOne
    private HospitalEntity hospital;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.REMOVE)
    private List<SpecialityEntity> specialityEntities;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    private List<PatientEntity> patients = new ArrayList<>();

    public DoctorEntity(){}

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

    public HospitalEntity getHospital() {
        return hospital;
    }

    public void setHospital(HospitalEntity hospital) {
        this.hospital = hospital;
    }

    public List<SpecialityEntity> getSpecialityEntities() {
        return specialityEntities;
    }

    public void setSpecialityEntities(List<SpecialityEntity> specialityEntities) {
        this.specialityEntities = specialityEntities;
    }

    public List<PatientEntity> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientEntity> patients) {
        this.patients = patients;
    }
}
