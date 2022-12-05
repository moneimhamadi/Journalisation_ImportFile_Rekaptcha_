package com.moneim.api.security.services;

import com.moneim.api.entities.RefreshToken;
import com.moneim.api.exception.TokenRefreshException;
import com.moneim.api.repositories.RefreshTokenRepository;
import com.moneim.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;


@Service
public class RefreshTokenService {

    @Value("${journal.api.jwtRefreshExpirationMs}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;


    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(String idUser) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setIdUser(idUser);
        refreshToken.setExpirationDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());


        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpirationDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(),
                    "Refresh tokenExpiree ,veuillez connecter de nouveau !");
        }

        return token;
    }

    @Transactional
    public Long deleteByUserId(String idUser) {
        return refreshTokenRepository.deleteByIdUser(idUser);
    }

}
