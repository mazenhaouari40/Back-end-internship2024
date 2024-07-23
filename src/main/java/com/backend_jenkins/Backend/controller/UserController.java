package com.backend_jenkins.Backend.controller;


import com.backend_jenkins.Backend.Model.User;
import com.backend_jenkins.Backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/bonjour")
    public String bonjour(){
        return "Bonjour mazen";
    }

    @GetMapping("/user")
    public List<User> getUsers(){
        return service.getUsers();
    }

    @GetMapping("/user/{id}")
    public Optional<User> getUser(@PathVariable Integer id){
        return service.getUser(id);
    }

    @PostMapping("/user/add")
    public void addUser(@RequestBody User user){
        service.addUser(user);
    }

    @PutMapping("/user/edit/{id}")
    public void editUser(@PathVariable Integer id,@RequestBody User user){
        service.updateUser(user);
    }
    @DeleteMapping("/user/delete/{id}")
    public void deleteFriend(@PathVariable Integer id){
        service.deleteUser(id);
    }
}
