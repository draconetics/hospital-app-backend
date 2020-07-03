package com.demo.hospital.dao;

import com.demo.hospital.model.ModelBase;
import com.demo.hospital.model.SpecialityEntity;

public class SpecialityCommand extends ModelBase {

    private String name;
    private String description;
    private Long doctor;

    public SpecialityCommand(){}

    public SpecialityCommand(SpecialityEntity specialityEntity) {
        setId(specialityEntity.getId());
        setCreatedDate(specialityEntity.getCreatedDate());
        setLastModifiedDate(specialityEntity.getLastModifiedDate());
        setVersion(specialityEntity.getVersion());

        setName(specialityEntity.getName());
        setDescription(specialityEntity.getDescription());
        setDoctor(specialityEntity.getDoctor().getId());
    }

        public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDoctor() {
        return doctor;
    }

    public void setDoctor(Long doctor) {
        this.doctor = doctor;
    }

    public SpecialityEntity convertToSpecialityEntity(){
        SpecialityEntity spec = new SpecialityEntity();
        spec.setId(getId());
        spec.setCreatedDate(getCreatedDate());
        spec.setLastModifiedDate(getLastModifiedDate());
        spec.setVersion(getVersion());

        spec.setName(getName());
        spec.setDescription(getDescription());

        return spec;
    }
}
