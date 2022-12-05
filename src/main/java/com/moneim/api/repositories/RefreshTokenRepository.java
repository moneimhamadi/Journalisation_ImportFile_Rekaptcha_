package com.moneim.api.repositories;

import com.moneim.api.entities.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends MongoRepository<RefreshToken, String> {
    public Optional <RefreshToken> findByToken(String token);

    public Long deleteByIdUser(String idUser);
}
