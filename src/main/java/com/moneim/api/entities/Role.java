package com.moneim.api.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;

@Document(collection = "roles")
public class Role {
    @Id
    @GeneratedValue (generator = "String2")
    private String idRole;
    private String nom;

    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public Role() {
    }

    public Role(String nom) {
        this.nom = nom;
    }
}
