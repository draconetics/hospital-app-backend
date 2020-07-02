package com.demo.hospital.service;

import com.demo.hospital.model.DoctorEntity;
import com.demo.hospital.model.HospitalEntity;
import com.demo.hospital.model.PatientEntity;
import com.demo.hospital.model.nullObject.NullHospitalEntity;
import com.demo.hospital.repository.DoctorRepository;
import com.demo.hospital.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.*;

@Transactional
@Service
public class HospitalService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private DoctorRepository doctorRepository;

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

        try {
            return hospitalRepository.save(hospitalData);
        } catch (Exception e) {
            throw new Error(e);
        }
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

    public void replaceHospital(long idOld, long idNew){
        try {

            Optional<HospitalEntity> oldHospital = hospitalRepository.findById(idOld);
            Optional<HospitalEntity> newHospital = hospitalRepository.findById(idNew);
            if(oldHospital.isPresent() && newHospital.isPresent()){

                HospitalEntity oldH = oldHospital.get();
                HospitalEntity newH = newHospital.get();

                oldH.getDoctors().forEach(doc -> {
                    DoctorEntity docRef = entityManager.getReference(DoctorEntity.class, doc.getId());
                    docRef.setHospital(newH);
                    entityManager.persist(docRef);
                });
                hospitalRepository.deleteById(idOld);
            }else{
                throw new Error("Item not found");
            }
        } catch (Exception e) {
            throw new Error(e);
        }
    }

    public List<HospitalEntity> searchHospitals(String name, Date fundation){
        System.out.println(name);
        System.out.println(fundation);
        List<HospitalEntity> searching = hospitalRepository.findAll(new Specification<HospitalEntity>() {
                @Override
                public Predicate toPredicate(Root<HospitalEntity> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                    Predicate p = cb.conjunction();
                    if(Objects.nonNull(fundation)){
                        p = cb.and(p, cb.equal(root.<Date>get("createdAt"), fundation));

                    }
                    if(!StringUtils.isEmpty(name)){
                        p = cb.or(p, cb.like(root.get("name"),"%"+ name +"%"));
                    }
                    cq.orderBy(cb.desc(root.get("name")));
                    return p;
                }
        });
        return searching;
    }

}
