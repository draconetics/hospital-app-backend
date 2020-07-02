package com.demo.hospital.controller;


import com.demo.hospital.dao.HospitalCommand;
import com.demo.hospital.dao.ReplaceCommand;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/hospitals")
    public ResponseEntity<List<HospitalCommand>> getAllHospitals() {

        List<HospitalCommand> hospitalList = new ArrayList<>();
        hospitalService.getAllHospitals().forEach(hosp -> {
            hospitalList.add(new HospitalCommand(hosp));
        });

        return new ResponseEntity<List<HospitalCommand>>(hospitalList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/hospitals/{id}")
    public ResponseEntity<HospitalCommand> getHospitalById(@PathVariable("id") long id) {
        HospitalEntity foundHospital = hospitalService.getHospitalById(id);
        if(foundHospital instanceof NullHospitalEntity)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<HospitalCommand>(new HospitalCommand(foundHospital), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/hospitals")
    public ResponseEntity<HospitalCommand> saveHospital(@RequestBody HospitalCommand hospitalData) throws ParseException {
        System.out.println("this is a hospital -" + hospitalData.getName());

        HospitalEntity created = hospitalService.saveHospital(hospitalData.convertToHospitalEntity());
        return new ResponseEntity<HospitalCommand>(new HospitalCommand(created), new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/hospitals/{id}")
    public ResponseEntity<HospitalCommand> updateHospital(@PathVariable("id") long id,
                                                 @RequestBody HospitalCommand hospitalData) throws ParseException {
        //System.out.println("this is a post-" + patient.getId());
        HospitalEntity updated = hospitalService.updateHospital(id,hospitalData.convertToHospitalEntity());
        if(updated instanceof NullHospitalEntity)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<HospitalCommand>(new HospitalCommand(updated), new HttpHeaders(), HttpStatus.OK);
    }



    @PostMapping("/hospitals/replace")
    public ResponseEntity<String> replaceHospital(@RequestBody ReplaceCommand replaceId) {
        try {
            System.out.println("delte this id " + replaceId.getIdOld() + " to " + replaceId.getIdNew());
            hospitalService.replaceHospital(replaceId.getIdOld(),replaceId.getIdNew());
            return new ResponseEntity<>("msg: deleted " + replaceId.getIdOld(), new HttpHeaders(), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/hospitals/search")
    public ResponseEntity<List<HospitalCommand>> searchHospitals(
            @RequestParam(required = false) String name,
            @RequestParam(required = false)
            @DateTimeFormat(pattern = "MM/dd/yyyy") Date fundation) {

        List<HospitalCommand> hospitalList = new ArrayList<>();
        hospitalService.searchHospitals(name,fundation).forEach(hosp -> {
            hospitalList.add(new HospitalCommand(hosp));
        });

        return new ResponseEntity<List<HospitalCommand>>(hospitalList, new HttpHeaders(), HttpStatus.OK);
        //return null;
    }
}
