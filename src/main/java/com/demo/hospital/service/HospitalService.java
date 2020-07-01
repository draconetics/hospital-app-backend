package com.demo.hospital.service;

import com.demo.hospital.model.HospitalEntity;
import com.demo.hospital.model.PatientEntity;
import com.demo.hospital.model.nullObject.NullHospitalEntity;
import com.demo.hospital.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    public List<HospitalEntity> getAllHospitals()
    {
        List<HospitalEntity> hospitalList = hospitalRepository.findAll();

        if(hospitalList.size() > 0) {
            System.out.println("mayor que cero");
            return hospitalList;
        } else {
            return new ArrayList<HospitalEntity>();
        }
    }

    public HospitalEntity saveHospital(HospitalEntity hospitalData)
    {
        HospitalEntity hospitalEntity = new HospitalEntity();
        hospitalEntity.setName(hospitalData.getName());
        hospitalEntity.setAddress(hospitalData.getAddress());
        hospitalEntity.setCreatedAt(hospitalData.getCreatedAt());

        return hospitalRepository.save(hospitalEntity);
    }

    public HospitalEntity updateHospital(long id, HospitalEntity hospitalData)
    {
        Optional<HospitalEntity> hospitalFounded = hospitalRepository.findById(id);

        if (hospitalFounded.isPresent()) {
            HospitalEntity hospitalEntity = hospitalFounded.get();
            hospitalEntity.setName(hospitalData.getName());
            hospitalEntity.setAddress(hospitalData.getAddress());
            hospitalEntity.setCreatedAt(hospitalData.getCreatedAt());

            return hospitalRepository.save(hospitalEntity);
        } else {
            return new NullHospitalEntity();
        }
    }

    public HospitalEntity getHospitalById(long id)
    {
        try{
            Optional<HospitalEntity> hospitalFounded = hospitalRepository.findById(id);

            if (hospitalFounded.isPresent()) {
                return hospitalFounded.get();
            } else {
                return new NullHospitalEntity();
            }
        }catch(Exception e){
            throw new Error(e);
        }
    }

    public void deleteHospitalById(long id){
        try {
            //patientRepository.deleteById(id);
            hospitalRepository.deleteById(id);
        } catch (Exception e) {
            throw new Error(e);
        }
    }



}
