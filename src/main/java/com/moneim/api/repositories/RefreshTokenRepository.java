package com.moneim.api.repositories;

import com.moneim.api.entities.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
    public RefreshToken findByToken(String token);

    public Long deleteByIdUser(String idUser);
}
