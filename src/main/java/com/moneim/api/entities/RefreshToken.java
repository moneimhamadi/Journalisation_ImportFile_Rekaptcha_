package com.moneim.api.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import java.time.Instant;

@Document(collection = "refreshTokens")
public class RefreshToken {
    @Id
    @GeneratedValue(generator = "string2")
    private String idRefreshToken;
    private String idUser;
    private String token;
    private Instant expirationDate;

    public String getIdRefreshToken() {
        return idRefreshToken;
    }

    public void setIdRefreshToken(String idRefreshToken) {
        this.idRefreshToken = idRefreshToken;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Instant expirationDate) {
        this.expirationDate = expirationDate;
    }
}
