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
    public ObjectResponse getAllUsers() {
        return new ObjectResponse("Users Founds",
                userRepository.findAll(),
                1);
    }

    @Override
    public ObjectResponse signuUp(SignUpRequest signUpRequest) {

        logger.info("User Service Impl Sign Up : creation new account:Start -input => User : " + signUpRequest.getNom());

        try {
            if ((userRepository.findByEmail(signUpRequest.getEmail()) != null)) {
                return new ObjectResponse("User déja existe!!! Changer email ",
                        userRepository.findByEmail(signUpRequest.getEmail()),
                        0);
            } else if ((userRepository.findByUsername(signUpRequest.getUsername()) != null)) {
                return new ObjectResponse("User déja existe!!! Changer username ",
                        userRepository.findByUsername(signUpRequest.getUsername()),
                        0);
            } else {
                User u = userRepository.save(new User(signUpRequest.getNom(),
                        signUpRequest.getPrenom(),
                        signUpRequest.getUsername(),
                        signUpRequest.getEmail(),
                        encoder.encode(signUpRequest.getPassword()),signUpRequest.getDateNaissance(),
                        signUpRequest.getRoles()));
                Journal journal = journalRepositoy.save(new Journal(u.getIdUser(),
                        signUpRequest.getNom() + " " + signUpRequest.getPrenom(),
                        "Create",
                        "Account Creation",
                        new Date(),
                        "USER"));
                return new ObjectResponse("User ajouté avec succés",
                        u,
                        1);
            }

        } catch (Exception e) {
            return new ObjectResponse("Erreur",
                    e,
                    2);
        }

    }

    @Override
    public ObjectResponse logOut() {
        return null;
    }

    @Override
    public ObjectResponse UpdateUser(String idUser, User user) {
        logger.info("User Service Impl update user : Updating User with id : " + idUser);
        try {
            User userToUpdate = userRepository.findByIdUser(idUser);
            if (null != userToUpdate) {
                userToUpdate.setNom(user.getNom());
                userToUpdate.setPrenom(user.getPrenom());
                userToUpdate.setUsername(user.getUsername());
                userToUpdate.setEmail(user.getEmail());
                Journal journal = journalRepositoy.save(new Journal(idUser,
                        (userRepository.findByIdUser(idUser)).getNom() + " " + (userRepository.findByIdUser(idUser)).getPrenom(),
                        "Update",
                        "Updating his profile Creation",
                        new Date(),
                        "USER"));
                return new ObjectResponse("User updated",
                        userRepository.save(userToUpdate),
                        1);
            } else return new ObjectResponse("User not found,Update Failed",
                    null,
                    0);

        } catch (Exception e) {

            return new ObjectResponse("Erreur " + e.getMessage(),
                    null,
                    2);
        }
    }

    @Override
    public ObjectResponse deleteUser(String idUser, String idTheDoear) {
        logger.info("User Service Impl Delete User Up : Deleting user with id : " + idUser);
        try {
            User user = userRepository.findByIdUser(idUser);
            if (null != user) {
                userRepository.deleteById(idUser);
                Journal journal = journalRepositoy.save(new Journal(idTheDoear,
                        (userRepository.findByIdUser(idTheDoear)).getNom() + " " + (userRepository.findByIdUser(idTheDoear)).getPrenom(),
                        "Delete",
                        "Delete Action",
                        new Date(),
                        "USER"));
                return new ObjectResponse("User Deleted ",
                        null,
                        1);
            }

            return new ObjectResponse("User Not Found ",
                    null,
                    0);
        } catch (Exception e) {
            return new ObjectResponse("Erreur",
                    e,
                    2);
        }

    }

    @Override
    public ObjectResponse getOneUserById(String idUser) {
        logger.info("User Service Impl Get User : Getting user with id : " + idUser);
        try {
            User user = userRepository.findByIdUser(idUser);
            if (null != user) {
                return new ObjectResponse("User  found",
                        user,
                        1);
            } else
                return new ObjectResponse("User Not Found",
                        null,
                        0);

        } catch (Exception e) {
            return new ObjectResponse("Erreur",
                    e,
                    2);
        }
    }
}
