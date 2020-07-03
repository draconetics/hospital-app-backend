package com.demo.hospital.controller;

import com.demo.hospital.dao.DoctorCommand;
import com.demo.hospital.dao.PatientCommand;
import com.demo.hospital.model.PatientEntity;
import com.demo.hospital.model.nullObject.NullPatientEntity;
import com.demo.hospital.service.DoctorService;
import com.demo.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    DoctorService doctorService;

    @GetMapping("/patients")
    public ResponseEntity<List<PatientCommand>> getAllPatients() {
        List<PatientCommand> patientList = new ArrayList<>();
        patientService.getAllPatients().forEach(pat -> {
            patientList.add(new PatientCommand(pat));
        });

        return new ResponseEntity<List<PatientCommand>>(patientList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<PatientCommand> getPatientById(@PathVariable("id") long id) {
        PatientEntity foundPatient = patientService.getPatientById(id);
        if(foundPatient instanceof NullPatientEntity)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<PatientCommand>(new PatientCommand(foundPatient), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/patients")
    public ResponseEntity<PatientCommand> createPatient(@RequestBody PatientCommand patientData) throws ParseException {
        System.out.println("create patient");
        PatientEntity created = patientData.convertToPatientEntity();
        created.setDoctor(doctorService.getDoctorById(patientData.getDoctor()));
        patientService.savePatient(created);
        return new ResponseEntity<PatientCommand>(new PatientCommand(created), new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<PatientCommand> updatePatient(@PathVariable("id") long id,
                                                       @RequestBody PatientCommand patientData) throws ParseException {
        PatientEntity updated = patientData.convertToPatientEntity();
        updated.setDoctor(doctorService.getDoctorById(patientData.getDoctor()));
        updated = patientService.updatePatient(id,updated);
        if(updated instanceof NullPatientEntity)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<PatientCommand>(new PatientCommand(updated), new HttpHeaders(), HttpStatus.OK);
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
