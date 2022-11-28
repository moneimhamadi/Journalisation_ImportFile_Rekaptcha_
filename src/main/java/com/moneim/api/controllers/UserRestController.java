package com.moneim.api.controllers;

import com.moneim.api.entities.ObjectResponse;
import com.moneim.api.payload.SignUpRequest;
import com.moneim.api.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping ("/user")
public class UserRestController {

    @Autowired
    IUserService userService;

    @PostMapping("/signUp")
    public ObjectResponse signUp(@RequestBody SignUpRequest signUpRequest){
        return  userService.signuUp(signUpRequest);
    }
}
