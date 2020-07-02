package com.demo.hospital.service;

import com.demo.hospital.model.DoctorEntity;
import com.demo.hospital.model.nullObject.NullDoctorEntity;
import com.demo.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<DoctorEntity> getAllDoctors()
    {
        List<DoctorEntity> doctorList = doctorRepository.findAll();

        if(doctorList.size() > 0) {
            System.out.println("mayor que cero");
            return doctorList;
        } else {
            return new ArrayList<>();
        }
    }

    public DoctorEntity getHospitalById(long id)
    {
        try{
            Optional<DoctorEntity> doctorFounded = doctorRepository.findById(id);

            if (doctorFounded.isPresent()) {
                return doctorFounded.get();
            } else {
                return new NullDoctorEntity();
            }
        }catch(Exception e){
            throw new Error(e);
        }
    }


    public DoctorEntity saveDoctor(DoctorEntity doctor)
    {
        try {
            return doctorRepository.save(doctor);
        }catch (Exception e){
            throw new Error(e);
        }
    }

    public void deleteDoctorById(long id){
        try {
            //patientRepository.deleteById(id);
            doctorRepository.deleteById(id);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

}
