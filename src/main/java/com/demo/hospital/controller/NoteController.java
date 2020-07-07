package com.demo.hospital.controller;

import com.demo.hospital.dao.NoteCommand;
import com.demo.hospital.model.NoteEntity;
import com.demo.hospital.model.nullObject.NullNoteEntity;
import com.demo.hospital.service.NoteService;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/notes")
    public ResponseEntity<List<NoteCommand>> getAllNotes() {
        List<NoteCommand> noteList = new ArrayList<>();
        noteService.getAllNotes().forEach(note -> {
            noteList.add(new NoteCommand(note));
        });
        return new ResponseEntity<List<NoteCommand>>(noteList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<NoteCommand> getNoteById(@PathVariable("id") long id) {
        NoteEntity foundNote = noteService.getNoteById(id);
        if(foundNote instanceof NullNoteEntity)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<NoteCommand>(new NoteCommand(foundNote), new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping("/notes")
    public ResponseEntity<NoteCommand> saveNote(@RequestBody NoteCommand noteCommand) throws ParseException,Exception {
        NoteEntity created = noteCommand.convertToNoteEntity();
        created.setPatient(patientService.getPatientById(noteCommand.getPatient()));
        created = noteService.saveNote(created);
        return new ResponseEntity<NoteCommand>(new NoteCommand(created), new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<NoteCommand> updateNote(@PathVariable("id") long id,
                                                 @RequestBody NoteCommand noteData) throws ParseException,Exception {
        //System.out.println("this is a post-" + patient.getId());
        NoteEntity updated = noteData.convertToNoteEntity();
        updated.setPatient(patientService.getPatientById(noteData.getPatient()));
        updated = noteService.updateNote(id,updated);
        if(updated instanceof NullNoteEntity)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<NoteCommand>(new NoteCommand(updated), new HttpHeaders(), HttpStatus.OK);
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
