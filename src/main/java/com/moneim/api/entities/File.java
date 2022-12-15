package com.moneim.api.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import java.util.Date;

@Document(collection = "fichiers")
public class File {
    @Id
    @GeneratedValue(generator = "string2")
    private String idFile;
    private String idUser;
    private String nomFile;
    private String url;

    private Date dateCreation;

    public String getIdFile() {
        return idFile;
    }

    public void setIdFile(String idFile) {
        this.idFile = idFile;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNomFile() {
        return nomFile;
    }

    public void setNomFile(String nomFile) {
        this.nomFile = nomFile;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public File() {
    }

    public File(String idUser, String nomFile, String url,Date dateCreation) {
        this.idUser = idUser;
        this.nomFile = nomFile;
        this.url = url;
        this.dateCreation=dateCreation;
    }
}
