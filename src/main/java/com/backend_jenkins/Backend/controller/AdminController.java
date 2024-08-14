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
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService service;

    @GetMapping("/managers/{id}")
    public ResponseEntity<List<Map<String, String>>>  getManagers(@PathVariable int id){
        List<Map<String, String>> managers = service.getManagers(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(managers);
    }


    @PutMapping("/edituser/{id}")
    public ResponseEntity<?> edituser(
    @PathVariable Integer id,
    @RequestPart("user") String userJson,
    @RequestParam("file")
    MultipartFile file) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(userJson, User.class);

        return service.Admin_updateUser(id,user,file);
    }


    @GetMapping("/users")
    public ResponseEntity<List<Map<String, String>>> downloadImagesUsers() {
        List<User> listuser = service.getUsers();
        List<Map<String, String>> responseBodyList = new ArrayList<>();
        for (User user : listuser) {
            Map<String, String> responseBody = new HashMap<>();
            byte[] image = ImageUtil.decompressImage(user.getImageuser());
            String base64Image = Base64.encodeBase64String(image);
            responseBody.put("id", String.valueOf(user.getId()) );
            responseBody.put("nom", user.getNom() );
            responseBody.put("email", user.getEmail() );
            responseBody.put("num_tel", String.valueOf(user.getNum_tel()) );
            if(user.getRole() != null) {
                responseBody.put("role", String.valueOf(user.getRole()));
            }else{
                responseBody.put("role", "no role assigned");
            }
            if (user.getManager() != null){

                Optional<User> manager = service.getUser(user.getManager().getId());
                responseBody.put("manager", manager.get().getNom() );
            }else {
                responseBody.put("manager", "No manager assigned");
            }
            responseBody.put("image", base64Image);
            responseBodyList.add(responseBody);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseBodyList);
    }
}
