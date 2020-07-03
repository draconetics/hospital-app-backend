package com.demo.hospital.service;


import com.demo.hospital.model.DoctorEntity;
import com.demo.hospital.model.SpecialityEntity;
import com.demo.hospital.model.nullObject.NullDoctorEntity;
import com.demo.hospital.model.nullObject.NullSpecialityEntity;
import com.demo.hospital.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    public List<SpecialityEntity> getAllSpecialities()
    {
        List<SpecialityEntity> doctorList = specialityRepository.findAll();

        if(doctorList.size() > 0) {
            return doctorList;
        } else {
            return new ArrayList<>();
        }
    }

    public SpecialityEntity getSpecialityById(long id)
    {
        try{
            Optional<SpecialityEntity> specialityFounded = specialityRepository.findById(id);

            if (specialityFounded.isPresent()) {
                return specialityFounded.get();
            } else {
                return new NullSpecialityEntity();
            }
        }catch(Exception e){
            throw new Error(e);
        }
    }


    public SpecialityEntity saveSpeciality(SpecialityEntity spec)
    {
        try {
            return specialityRepository.save(spec);
        }catch (Exception e){
            throw new Error(e);
        }
    }


    public void deleteSpecialityById(long id){
        try {
            //patientRepository.deleteById(id);
            specialityRepository.deleteById(id);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public SpecialityEntity updateSpeciality(long id, SpecialityEntity specialityData)
    {
        Optional<SpecialityEntity> specialityFounded = specialityRepository.findById(id);

        if (specialityFounded.isPresent()) {
            SpecialityEntity specialityEntity = specialityFounded.get();
            specialityEntity.setName(specialityData.getName());
            specialityEntity.setDescription(specialityData.getDescription());
            specialityEntity.setDoctor(specialityData.getDoctor());


            return specialityRepository.save(specialityEntity);
        } else {
            return new NullSpecialityEntity();
        }
    }
}
