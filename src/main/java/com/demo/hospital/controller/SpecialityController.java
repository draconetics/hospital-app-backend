package com.demo.hospital.controller;

import com.demo.hospital.dao.DoctorCommand;
import com.demo.hospital.dao.SpecialityCommand;
import com.demo.hospital.model.DoctorEntity;
import com.demo.hospital.model.SpecialityEntity;
import com.demo.hospital.model.nullObject.NullDoctorEntity;
import com.demo.hospital.model.nullObject.NullSpecialityEntity;
import com.demo.hospital.service.DoctorService;
import com.demo.hospital.service.HospitalService;
import com.demo.hospital.service.SpecialityService;
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
public class SpecialityController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private SpecialityService specialityService;

    @GetMapping("/specs")
    public ResponseEntity<List<SpecialityCommand>> getAllSpelicialities() {

        List<SpecialityCommand> specList = new ArrayList<>();
        specialityService.getAllSpecialities().forEach(spec -> {
            specList.add(new SpecialityCommand(spec));
        });

        return new ResponseEntity<List<SpecialityCommand>>(specList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/specs/{id}")
    public ResponseEntity<SpecialityCommand> getSpecialityrById(@PathVariable("id") long id) {
        SpecialityEntity foundSpec = specialityService.getSpecialityById(id);
        if(foundSpec instanceof NullSpecialityEntity)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<SpecialityCommand>(new SpecialityCommand(foundSpec), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/specs")
    public ResponseEntity<SpecialityCommand> saveSpeciality(@RequestBody SpecialityCommand specData) throws ParseException {
        SpecialityEntity created = specData.convertToSpecialityEntity();
        created.setDoctor(doctorService.getDoctorById(specData.getDoctor()));
        specialityService.saveSpeciality(created);
        return new ResponseEntity<SpecialityCommand>(new SpecialityCommand(created), new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/specs/{id}")
    public ResponseEntity<SpecialityCommand> updateSpeciality(  @PathVariable("id") long id,
                                                            @RequestBody SpecialityCommand specialityData) throws ParseException {
        //System.out.println("this is a post-" + patient.getId());
        SpecialityEntity updated = specialityData.convertToSpecialityEntity();
        updated.setDoctor(doctorService.getDoctorById(specialityData.getDoctor()));
        updated = specialityService.updateSpeciality(id,updated);
        if(updated instanceof NullSpecialityEntity)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<SpecialityCommand>(new SpecialityCommand(updated), new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/specs/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id) {
        try {

            specialityService.deleteSpecialityById(id);
            return new ResponseEntity<>("msg: deleted " + id, new HttpHeaders(), HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
