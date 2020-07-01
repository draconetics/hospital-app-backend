package com.demo.hospital.controller;

import com.demo.hospital.model.PatientEntity;
import com.demo.hospital.model.nullObject.NullPatientEntity;
import com.demo.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("/patients")
    public ResponseEntity<List<PatientEntity>> getAllPatients() {
        List<PatientEntity> list = patientService.getAllPatients();

        return new ResponseEntity<List<PatientEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<PatientEntity> getPatientById(@PathVariable("id") long id) {
        PatientEntity foundPatient = patientService.getPatientById(id);
        if(foundPatient instanceof NullPatientEntity)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<PatientEntity>(foundPatient, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/patients")
    public ResponseEntity<PatientEntity> createPatient(@RequestBody PatientEntity patient){
        System.out.println("this is a post-" + patient.getId());
        PatientEntity updated = patientService.savePatient(patient);
        return new ResponseEntity<PatientEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<PatientEntity> updatePatient(@PathVariable("id") long id,
                                                       @RequestBody PatientEntity patientData){
        //System.out.println("this is a post-" + patient.getId());
        PatientEntity updated = patientService.updatePatient(id,patientData);
        if(updated instanceof NullPatientEntity)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<PatientEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<HttpStatus> deletePatient(@PathVariable("id") long id) {
        try {
            patientService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }


}
