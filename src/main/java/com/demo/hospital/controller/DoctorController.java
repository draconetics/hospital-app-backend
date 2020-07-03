package com.demo.hospital.controller;


import com.demo.hospital.dao.DoctorCommand;

import com.demo.hospital.model.DoctorEntity;
import com.demo.hospital.model.HospitalEntity;
import com.demo.hospital.model.nullObject.NullDoctorEntity;
import com.demo.hospital.service.DoctorService;
import com.demo.hospital.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorCommand>> getAllDoctors() {

        List<DoctorCommand> doctorList = new ArrayList<>();
        doctorService.getAllDoctors().forEach(doc -> {
            doctorList.add(new DoctorCommand(doc));
        });

        return new ResponseEntity<List<DoctorCommand>>(doctorList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/doctors/{id}")
    public ResponseEntity<DoctorCommand> getDoctorById(@PathVariable("id") long id) {
        DoctorEntity foundHospital = doctorService.getDoctorById(id);
        if(foundHospital instanceof NullDoctorEntity)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<DoctorCommand>(new DoctorCommand(foundHospital), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/doctors")
    public ResponseEntity<DoctorCommand> saveDoctor(@RequestBody DoctorCommand doctorData) throws ParseException {
        DoctorEntity created = doctorData.convertToDoctorEntity();
        created.setHospital(hospitalService.getHospitalById(doctorData.getHospital()));
        doctorService.saveDoctor(created);
        return new ResponseEntity<DoctorCommand>(new DoctorCommand(created), new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/doctors/{id}")
    public ResponseEntity<DoctorCommand> updateDoctor(  @PathVariable("id") long id,
                                                        @RequestBody DoctorCommand doctorData) throws ParseException {
        //System.out.println("this is a post-" + patient.getId());
        DoctorEntity updated = doctorData.convertToDoctorEntity();
        updated.setHospital(hospitalService.getHospitalById(doctorData.getHospital()));
        updated = doctorService.updateDoctor(id,updated);
        if(updated instanceof NullDoctorEntity)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<DoctorCommand>(new DoctorCommand(updated), new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id) {
        try {

            doctorService.deleteDoctorById(id);
            return new ResponseEntity<>("msg: deleted " + id, new HttpHeaders(), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
