package com.backend_jenkins.Backend.controller;


import com.backend_jenkins.Backend.Jwt.ImageUtil;
import com.backend_jenkins.Backend.Model.ResponseLoginUser;
import com.backend_jenkins.Backend.Model.User;
import com.backend_jenkins.Backend.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "https://angular-jenkins.onrender.com/")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/bonjour")
    public String bonjour(){
        return "Bonjour mazen";
    }

    @GetMapping("/users")
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
        service.updateUser(id,user);
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteFriend(@PathVariable Integer id){

        service.deleteUser(id);

    }


    @PutMapping("/user/editProfile/{id}")
    public ResponseEntity<?> editUser(
            @PathVariable Integer id,
            @RequestPart("user") String userJson,
            @RequestParam("file")MultipartFile file)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(userJson, User.class);

        service.updateUserProfile(id,user,file);
        return ResponseEntity.ok(new ResponseLoginUser("user Updated successfuly",true));
    }

    @GetMapping("/user/image/{id}")
    public ResponseEntity<Map<String, String>>  downloadImage(@PathVariable int id){
        byte[] imageData=service.downloadImage(id);
        String base64Image = Base64.encodeBase64String(imageData);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("image", base64Image);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseBody);

    }







}