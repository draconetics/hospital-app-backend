package com.demo.hospital.controller;


import com.demo.hospital.dao.HospitalCommand;
import com.demo.hospital.model.HospitalEntity;
import com.demo.hospital.model.nullObject.NullHospitalEntity;
import com.demo.hospital.repository.HospitalRepository;
import com.demo.hospital.service.HospitalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private HospitalRepository hospitalRepository;

    @GetMapping("/hospitals")
    public ResponseEntity<List<HospitalEntity>> getAllHospitals() {
        List<HospitalEntity> list = hospitalService.getAllHospitals();
        return new ResponseEntity<List<HospitalEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/hospitals/{id}")
    public ResponseEntity<HospitalEntity> getHospitalById(@PathVariable("id") long id) {
        HospitalEntity foundHospital = hospitalService.getHospitalById(id);
        if(foundHospital instanceof NullHospitalEntity)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<HospitalEntity>(foundHospital, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/hospitals")
    public ResponseEntity<HospitalEntity> saveHospital(@RequestBody HospitalCommand hospitalData) throws ParseException {
        System.out.println("this is a hospital -" + hospitalData.getName());
        HospitalEntity updated = hospitalService.saveHospital(hospitalData.convertToHospitalEntity());
        return new ResponseEntity<HospitalEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/hospitals/{id}")
    public ResponseEntity<HospitalEntity> updateHospital(@PathVariable("id") long id,
                                                 @RequestBody HospitalEntity hospitalData){
        //System.out.println("this is a post-" + patient.getId());
        HospitalEntity updated = hospitalService.updateHospital(id,hospitalData);
        if(updated instanceof NullHospitalEntity)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<HospitalEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }



    @DeleteMapping("/hospitals/{id}")
    public ResponseEntity<HttpStatus> deleteHospital(@PathVariable("id") long id) {
        try {
            System.out.println("delte this id " + id);
            hospitalService.deleteHospitalById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/hospitals/search")
    public ResponseEntity<List<HospitalEntity>> searchForHospitals(
            @RequestParam(required = false) String name,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "MM/dd/yyyy") Date fundation) {
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
                    p = cb.and(p, cb.like(root.get("name"),"%"+ name +"%"));
                }
                cq.orderBy(cb.desc(root.get("name")));
                return p;
            }
        });
        return new ResponseEntity<>(searching, HttpStatus.OK);
        //return null;
    }
}
