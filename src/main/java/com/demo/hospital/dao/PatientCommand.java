package com.demo.hospital.dao;

import com.demo.hospital.model.ModelBase;
import com.demo.hospital.model.PatientEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PatientCommand extends ModelBase {

    private String name;
    private String lastName;
    private String bornDate; //MM/dd/yyyy
    private String address;
    private Long doctor;


    public PatientCommand(){}

    public PatientCommand(PatientEntity patientEntity){
        setId(patientEntity.getId());
        setCreatedDate(patientEntity.getCreatedDate());
        setLastModifiedDate(patientEntity.getLastModifiedDate());
        setVersion(patientEntity.getVersion());

        setName(patientEntity.getName());
        setLastName(patientEntity.getLastName());
        setBornDate(new SimpleDateFormat("MM/dd/yyyy").format(patientEntity.getBornDate()));
        setAddress(patientEntity.getAddress());
        setDoctor(patientEntity.getDoctor().getId());
    }

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

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getDoctor() {
        return doctor;
    }

    public void setDoctor(Long doctor) {
        this.doctor = doctor;
    }

    public PatientEntity convertToPatientEntity() throws ParseException {
        PatientEntity patient = new PatientEntity();
        patient.setId(getId());
        patient.setCreatedDate(getCreatedDate());
        patient.setLastModifiedDate(getLastModifiedDate());
        patient.setVersion(getVersion());

        patient.setName(getName());
        patient.setLastName(getLastName());
        patient.setBornDate(new SimpleDateFormat("MM/dd/yyyy").parse(getBornDate()));
        patient.setAddress(getAddress());

        return patient;
    }
}
