package com.demo.hospital.dao;

import com.demo.hospital.model.HospitalEntity;
import com.demo.hospital.model.ModelBase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HospitalCommand extends ModelBase {

    private String name;
    private String address;
    private String createdAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public HospitalEntity convertToHospitalEntity() throws ParseException {
        HospitalEntity hospital = new HospitalEntity();
        hospital.setId(getId());
        hospital.setCreatedDate(getCreatedDate());
        hospital.setLastModifiedDate(getLastModifiedDate());
        hospital.setVersion(getVersion());

        hospital.setName(getName());
        hospital.setAddress(getAddress());
        String date = getCreatedAt();
        System.out.println("this is the date : " + date);
        //MM/dd/yyyy
        hospital.setCreatedAt(new SimpleDateFormat("MM/dd/yyyy").parse(getCreatedAt()));
        return hospital;
    }
}
