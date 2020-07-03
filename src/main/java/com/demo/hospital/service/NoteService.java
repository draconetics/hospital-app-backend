package com.demo.hospital.service;

import com.demo.hospital.dao.NoteCommand;
import com.demo.hospital.model.NoteEntity;
import com.demo.hospital.model.PatientEntity;
import com.demo.hospital.model.nullObject.NullNoteEntity;
import com.demo.hospital.model.nullObject.NullPatientEntity;
import com.demo.hospital.repository.NoteRepository;
import com.demo.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository;
    @Autowired
    PatientRepository patientRepository;


    public List<NoteEntity> getAllNotes()
    {
        List<NoteEntity> noteList = noteRepository.findAll();

        if(noteList.size() > 0) {
            System.out.println("mayor que cero");
            return noteList;
        } else {
            return new ArrayList<NoteEntity>();
        }
    }

    public NoteEntity getNoteById(Long id){
        try{
            Optional<NoteEntity> noteFounded = noteRepository.findById(id);

            if (noteFounded.isPresent()) {
                return noteFounded.get();
            } else {
                return new NullNoteEntity();
            }
        }catch(Exception e){
            throw new Error(e);
        }
    }

    public NoteEntity saveNote(NoteEntity noteData){

        try {
            return noteRepository.save(noteData);
        }catch (Exception e){
            throw new Error(e);
        }
    }

    public NoteEntity updateNote(long id, NoteEntity noteData)
    {
        Optional<NoteEntity> foundNote = noteRepository.findById(id);

        if (foundNote.isPresent()) {
            NoteEntity note = foundNote.get();
            note.setDescription(noteData.getDescription());
            note.setDateOfEmition(noteData.getDateOfEmition());
            note.setPatient(noteData.getPatient());
            return noteRepository.save(note);
        } else {
            return new NullNoteEntity();
        }
    }


    public void deleteById(long id){
        try {
            noteRepository.deleteById(id);
        } catch (Exception e) {
            throw new Error(e);
        }
    }

}
