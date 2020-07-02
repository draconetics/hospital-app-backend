package com.demo.hospital.dao;

public class ReplaceCommand {

    private int idOld;
    private int idNew;

    public ReplaceCommand(){}

    public int getIdOld() {
        return idOld;
    }

    public void setIdOld(int idOld) {
        this.idOld = idOld;
    }

    public int getIdNew() {
        return idNew;
    }

    public void setIdNew(int idNew) {
        this.idNew = idNew;
    }
}
