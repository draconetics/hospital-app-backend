package com.demo.hospital.dao;

import com.demo.hospital.model.ModelBase;
import com.demo.hospital.model.NoteEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class NoteCommand extends ModelBase {

    private String description;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfEmition;
    private Long patientId;

    public NoteCommand(){}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateOfEmition() {
        return dateOfEmition;
    }

    public void setDateOfEmition(Date dateOfEmition) {
        this.dateOfEmition = dateOfEmition;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public NoteEntity convertToNoteEntity(){
        NoteEntity newNote = new NoteEntity();
        newNote.setId(getId());
        newNote.setCreatedDate(getCreatedDate());
        newNote.setLastModifiedDate(getLastModifiedDate());
        newNote.setVersion(getVersion());

        newNote.setDescription(getDescription());
        newNote.setDateOfEmition(getDateOfEmition());

        return newNote;
    }
}
