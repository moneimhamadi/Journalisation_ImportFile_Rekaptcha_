package com.moneim.api.services;

import com.moneim.api.entities.Journal;
import com.moneim.api.entities.ObjectResponse;
import com.moneim.api.entities.User;
import com.moneim.api.payload.SignUpRequest;
import com.moneim.api.repositories.JournalRepository;
import com.moneim.api.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class UserServiceImplementation implements IUserService {
    @Autowired
    UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JournalRepository journalRepositoy;
    @Override
    public ObjectResponse signuUp(SignUpRequest signUpRequest) {

        logger.info("User Service Impl Sign Up : creation new account:Start -input => User : " + signUpRequest.getNom());

        try {
            if ((userRepository.findByEmail(signUpRequest.getEmail()) != null)) {
                return new ObjectResponse("User déja existe!!! Changer email )", userRepository.findByEmail(signUpRequest.getEmail()), 0);
            } else if ((userRepository.findByUsername(signUpRequest.getUsername()) != null)) {
                return new ObjectResponse("User déja existe!!! Changer username  )", userRepository.findByUsername(signUpRequest.getUsername()), 0);
            } else {
                User u = userRepository.save(new User(signUpRequest.getNom(), signUpRequest.getPrenom(), signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword())  , signUpRequest.getRoles()));
                Journal journal=journalRepositoy.save( new Journal(u.getIdUser(),signUpRequest.getNom()+" "+signUpRequest.getPrenom(),"Create","Account Creation",new Date(),"USER"));
                return new ObjectResponse("User ajouté avec succés", u, 1);
            }

        } catch (Exception e) {
            return new ObjectResponse("Erreur", e, 2);
        }

    }

    @Override
    public ObjectResponse logOut() {
        return null;
    }

    @Override
    public ObjectResponse UpdateUser(String idUser, User user) {
        return null;
    }

    @Override
    public ObjectResponse deleteUser(String idUser) {
        return null;
    }

    @Override
    public ObjectResponse getOneUserById(String idUser) {
        return null;
    }
}
