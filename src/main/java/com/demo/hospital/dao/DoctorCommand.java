package com.demo.hospital.dao;

import com.demo.hospital.model.DoctorEntity;
import com.demo.hospital.model.ModelBase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DoctorCommand extends ModelBase {

    private String name;
    private String lastName;
    private String bornDate;
    private String address;
    private Long hospital;

    public DoctorCommand(){}

    public DoctorCommand(DoctorEntity doctor){
        setId(doctor.getId());
        setCreatedDate(doctor.getCreatedDate());
        setLastName(doctor.getLastName());
        setVersion(doctor.getVersion());

        setName(doctor.getName());
        setLastName(doctor.getLastName());
        setBornDate(new SimpleDateFormat("MM/dd/yyyy").format(doctor.getBornDate()));
        setAddress(doctor.getAddress());
        setHospital(doctor.getHospital().getId());
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

    public Long getHospital() {
        return hospital;
    }

    public void setHospital(Long hospital) {
        this.hospital = hospital;
    }

    public DoctorEntity convertToDoctorEntity() throws ParseException {
        DoctorEntity doctor = new DoctorEntity();
        doctor.setId(getId());
        doctor.setCreatedDate(getCreatedDate());
        doctor.setLastModifiedDate(getLastModifiedDate());
        doctor.setVersion(getVersion());

        doctor.setName(getName());
        doctor.setLastName(getLastName());
        doctor.setBornDate(new SimpleDateFormat("MM/dd/yyyy").parse(getBornDate()));
        doctor.setAddress(getAddress());

        return doctor;
    }
}
