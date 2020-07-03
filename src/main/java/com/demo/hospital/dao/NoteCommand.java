package com.demo.hospital.dao;

import com.demo.hospital.model.ModelBase;
import com.demo.hospital.model.NoteEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class NoteCommand extends ModelBase {

    private String description;
    private String dateOfEmition;
    private Long patient;

    public NoteCommand(){}

    public NoteCommand(NoteEntity noteEntity){
        setId(noteEntity.getId());
        setCreatedDate(noteEntity.getCreatedDate());
        setLastModifiedDate(noteEntity.getLastModifiedDate());
        setVersion(noteEntity.getVersion());

        setDescription(noteEntity.getDescription());
        setDateOfEmition(new SimpleDateFormat("MM/dd/yyyy").format(noteEntity.getDateOfEmition()));
        setPatient(noteEntity.getPatient().getId());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateOfEmition() {
        return dateOfEmition;
    }

    public void setDateOfEmition(String dateOfEmition) {
        this.dateOfEmition = dateOfEmition;
    }

    public Long getPatient() {
        return patient;
    }

    public void setPatient(Long patient) {
        this.patient = patient;
    }

    public NoteEntity convertToNoteEntity() throws ParseException {
        NoteEntity newNote = new NoteEntity();
        newNote.setId(getId());
        newNote.setCreatedDate(getCreatedDate());
        newNote.setLastModifiedDate(getLastModifiedDate());
        newNote.setVersion(getVersion());

        newNote.setDescription(getDescription());
        newNote.setDateOfEmition(new SimpleDateFormat("MM/dd/yyyy").parse(getDateOfEmition()));

        return newNote;
    }
}
