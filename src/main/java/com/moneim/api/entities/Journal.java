package com.moneim.api.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import java.util.Date;

@Document(collection = "journaux")
public class Journal {
    @Id
    @GeneratedValue(generator = "string2")
    private String idJournal;
    private String idUser;

    private String nomUser;
    private String operation;
    private String description;
    private Date dateOperation;
    private String typeEntity;

    public String getIdJournal() {
        return idJournal;
    }

    public void setIdJournal(String idJournal) {
        this.idJournal = idJournal;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }


    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getTypeEntity() {
        return typeEntity;
    }

    public void setTypeEntity(String typeEntity) {
        this.typeEntity = typeEntity;
    }

    public Journal() {
    }

    public Journal(String idUser, String nomUser, String operation, String description, Date dateOperation, String typeEntity) {
        this.idUser = idUser;
        this.nomUser = nomUser;
        this.operation = operation;
        this.description = description;
        this.dateOperation = dateOperation;
        this.typeEntity = typeEntity;
    }
}
