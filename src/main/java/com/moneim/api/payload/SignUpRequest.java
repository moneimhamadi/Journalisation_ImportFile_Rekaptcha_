package com.moneim.api.payload;

import com.moneim.api.entities.Role;

import java.util.Date;
import java.util.List;

public class SignUpRequest {
    private String nom;
    private String prenom;
    private String username;
    private String email;
    private String password;

    private Date dateNaissance;
    private List<String> roles;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
