package com.demo.hospital.controller;

import com.demo.hospital.dao.NoteCommand;
import com.demo.hospital.model.NoteEntity;
import com.demo.hospital.model.PatientEntity;
import com.demo.hospital.model.nullObject.NullNoteEntity;
import com.demo.hospital.model.nullObject.NullPatientEntity;
import com.demo.hospital.service.NoteService;
import com.demo.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/notes")
    public ResponseEntity<List<NoteEntity>> getAllNotes() {
        List<NoteEntity> list = noteService.getAllNotes();
        return new ResponseEntity<List<NoteEntity>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/notes")
    public ResponseEntity<NoteEntity> saveNote(@RequestBody NoteCommand noteCommand){
        NoteEntity updated = noteService.saveNote(noteCommand);
        if(updated instanceof NullNoteEntity)
            return new ResponseEntity<NoteEntity>(updated, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<NoteEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<NoteEntity> updateNote(@PathVariable("id") long id,
                                                 @RequestBody NoteEntity noteData){
        //System.out.println("this is a post-" + patient.getId());
        NoteEntity updated = noteService.updateNote(id,noteData);
        if(updated instanceof NullNoteEntity)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<NoteEntity>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
        try {
            noteService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
