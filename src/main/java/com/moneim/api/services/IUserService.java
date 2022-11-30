package com.moneim.api.services;

import com.moneim.api.entities.ObjectResponse;
import com.moneim.api.entities.User;
import com.moneim.api.payload.SignUpRequest;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    ObjectResponse getAllUsers();

    ObjectResponse signuUp(SignUpRequest signUpRequest);

    ObjectResponse logOut();

    ObjectResponse UpdateUser(String idUser, User user);

    ObjectResponse deleteUser(String idUser, String idTheDoear);

    ObjectResponse getOneUserById(String idUser);
}
