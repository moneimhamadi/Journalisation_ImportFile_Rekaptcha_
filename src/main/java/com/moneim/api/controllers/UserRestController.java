package com.moneim.api.controllers;

import com.moneim.api.entities.ObjectResponse;
import com.moneim.api.entities.User;
import com.moneim.api.payload.SignUpRequest;
import com.moneim.api.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    IUserService userService;

    @PostMapping(value = "/signUp")
    public ObjectResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        return userService.signuUp(signUpRequest);
    }

    @GetMapping("/getOneUserById/{idUser}")
    public ObjectResponse getOneUserById(@PathVariable String idUser) {
        return userService.getOneUserById(idUser);
    }

    @DeleteMapping("/deleteUserById/{idUser}/{idDoer}")
    public ObjectResponse deleteUserById(@PathVariable String idUser, @PathVariable String idDoer) {
        return userService.deleteUser(idUser,
                idDoer);
    }

    @PutMapping("/updateUser/{idUser}")
    public ObjectResponse updateUser(@PathVariable String idUser, @RequestBody User user) {
        return userService.UpdateUser(idUser,
                user);
    }

    @GetMapping("/getAllUsers")
    public ObjectResponse getAllUsers() {
        return userService.getAllUsers();
    }
}
