package com.demo.hospital.service;

import com.demo.hospital.model.PatientEntity;
import com.demo.hospital.model.nullObject.NullPatientEntity;
import com.demo.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    public List<PatientEntity> getAllPatients()
    {
        List<PatientEntity> patientList = patientRepository.findAll();

        if(patientList.size() > 0) {
            return patientList;
        } else {
            return new ArrayList<PatientEntity>();
        }
    }


    public PatientEntity savePatient(PatientEntity patient)
    {
        try {
            return patientRepository.save(patient);
        }catch (Exception e){
            throw new Error(e);
        }
    }

    public PatientEntity updatePatient(long id, PatientEntity patientData)
    {
        Optional<PatientEntity> patientFounded = patientRepository.findById(id);

        if (patientFounded.isPresent()) {
            PatientEntity patient = patientFounded.get();
            patient.setName(patientData.getName());
            patient.setLastName(patientData.getLastName());
            patient.setAddress(patientData.getAddress());
            patient.setBornDate(patientData.getBornDate());

            return patientRepository.save(patient);
        } else {
            return new NullPatientEntity();
        }
    }

    public PatientEntity getPatientById(long id)
    {
        try{
            Optional<PatientEntity> patientFounded = patientRepository.findById(id);

            if (patientFounded.isPresent()) {
                return patientFounded.get();
            } else {
                return new NullPatientEntity();
            }
        }catch(Exception e){
            throw new Error(e);
        }
    }

    public void deleteById(long id){
        try {
            //patientRepository.deleteById(id);
            patientRepository.deleteById(id);
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
