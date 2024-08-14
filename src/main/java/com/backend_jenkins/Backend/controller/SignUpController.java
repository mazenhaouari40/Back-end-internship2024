package com.backend_jenkins.Backend.controller;

import com.backend_jenkins.Backend.Model.ResponseLoginUser;
import com.backend_jenkins.Backend.Model.User;
import com.backend_jenkins.Backend.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/signup")
//@CrossOrigin(origins = "http://localhost:4200")

public class SignUpController {

    private final AuthService authService;
    @Autowired
    public SignUpController(AuthService authservice){
    this.authService = authservice;
    }

    @PostMapping
    public ResponseEntity<?> signupUser(@RequestBody User user) throws IOException {
        boolean  createduser = authService.createUser(user);
        if (createduser){
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseLoginUser("user created successfully",true));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseLoginUser("Failed to create user",true));

        }
    }


}
