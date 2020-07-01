package com.demo.hospital.controller;

import com.demo.hospital.model.HospitalEntity;
import com.demo.hospital.model.nullObject.NullHospitalEntity;
import com.demo.hospital.repository.HospitalRepository;
import com.demo.hospital.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<HospitalEntity> saveHospital(@RequestBody HospitalEntity hospitalData){
        System.out.println("this is a hospital -" + hospitalData.getName());
        HospitalEntity updated = hospitalService.saveHospital(hospitalData);
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
    public ResponseEntity<List<HospitalEntity>> searchForHospitals(@RequestParam String hospital) {
        System.out.println(hospital);
        return new ResponseEntity<>(hospitalRepository.findAll(SearchHospital), HttpStatus.OK);
        return null;
    }
}
