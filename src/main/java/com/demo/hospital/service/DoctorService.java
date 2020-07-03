package com.demo.hospital.service;

import com.demo.hospital.model.DoctorEntity;
import com.demo.hospital.model.HospitalEntity;
import com.demo.hospital.model.PatientEntity;
import com.demo.hospital.model.nullObject.NullDoctorEntity;
import com.demo.hospital.model.nullObject.NullHospitalEntity;
import com.demo.hospital.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class DoctorService {

    @PersistenceContext
    EntityManager entityManager;

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

    public DoctorEntity getDoctorById(long id)
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

    public DoctorEntity updateDoctor(long id, DoctorEntity doctorData)
    {
        Optional<DoctorEntity> hospitalFounded = doctorRepository.findById(id);

        if (hospitalFounded.isPresent()) {
            DoctorEntity hospitalEntity = hospitalFounded.get();
            hospitalEntity.setName(doctorData.getName());
            hospitalEntity.setLastName(doctorData.getLastName());
            hospitalEntity.setAddress(doctorData.getAddress());
            hospitalEntity.setBornDate(doctorData.getBornDate());
            hospitalEntity.setHospital(doctorData.getHospital());

            return doctorRepository.save(hospitalEntity);
        } else {
            return new NullDoctorEntity();
        }
    }

    public void replaceDoctor(long idOld, long idNew){
        try {

            Optional<DoctorEntity> oldDoctor = doctorRepository.findById(idOld);
            Optional<DoctorEntity> newDoctor = doctorRepository.findById(idNew);
            if(oldDoctor.isPresent() && newDoctor.isPresent()){

                DoctorEntity oldH = oldDoctor.get();
                DoctorEntity newH = newDoctor.get();

                oldH.getPatients().forEach(pat -> {
                    PatientEntity patRef = entityManager.getReference(PatientEntity.class, pat.getId());
                    patRef.setDoctor(newH);
                    entityManager.persist(patRef);
                });
                doctorRepository.deleteById(idOld);
            }else{
                throw new Error("Item not found");
            }
        } catch (Exception e) {
            throw new Error(e);
        }
    }

}
