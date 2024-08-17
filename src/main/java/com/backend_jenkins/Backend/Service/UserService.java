package com.backend_jenkins.Backend.Service;


import com.backend_jenkins.Backend.Jwt.ImageUtil;
import com.backend_jenkins.Backend.Model.ResponseLoginUser;
import com.backend_jenkins.Backend.Model.User;
import com.backend_jenkins.Backend.repository.AbsenceRepository;
import com.backend_jenkins.Backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private AbsenceRepository absenceRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public List<User> getUsers(){
        return repository.findAll();
    }

    public Optional<User> getUser(Integer id){

        return repository.findById(id);

    }
    public Optional<User> getUserByEmail(String email){
        return repository.findByEmail(email);
    }

    public void addUser(User user){
        repository.save(user);
    }


    public void updateUser(int id,User updateduser){
        Optional<User> existingUserOptional = repository.findById(id);

        User existingUser = existingUserOptional.get();
        if (updateduser.getNom() != null){
            existingUser.setNom(updateduser.getNom());
        }
        if (updateduser.getEmail() != null) {
            existingUser.setEmail(updateduser.getEmail());
        }

        if (updateduser.getNum_tel() != null) {
            existingUser.setNum_tel(updateduser.getNum_tel());
        }

        if (updateduser.getPassword() != null) {
            String hashPassword = passwordEncoder.encode(updateduser.getPassword());
            existingUser.setPassword(hashPassword);
        }
        repository.save(existingUser);
    }



    @Transactional
    public void deleteUser(Integer id){
        Optional<User> existinguser = repository.findById(id);
        if (existinguser.get() != null) {

            if (existinguser.get().getRole().equals("manager")){
                repository.setManagerNull(id);
            }else{
                absenceRepository.deleteAbsencesByUser(existinguser.get());
                existinguser.get().setManager(null);
            }
            repository.deleteById(id);

        }
        }

    public ResponseEntity<?> updateUserProfile(int id, User userupdated , MultipartFile image) throws IOException {
        Optional<User> userRepo = repository.findById(id);
        if(userRepo.isPresent()){
            User usermodified=userRepo.get();
            if (userupdated.getNom() != null){
                usermodified.setNom(userupdated.getNom());
            }
            if (userupdated.getEmail() != null){
                usermodified.setEmail(userupdated.getEmail());
            }
            if (userupdated.getNum_tel() != null){
                usermodified.setNum_tel(userupdated.getNum_tel());
            }
            if (userupdated.getPassword() != null){
                String hashPassword = passwordEncoder.encode(userupdated.getPassword());
                usermodified.setPassword(hashPassword);
            }
            if (image != null && !image.isEmpty()) {
                usermodified.setImageuser((ImageUtil.compressImage(image.getBytes())));
            }
            repository.save(usermodified);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseLoginUser("ok",true));
    }



    public ResponseEntity<?> Admin_updateUser(int id, User userupdated , MultipartFile image) throws IOException {
        Optional<User> userRepo = repository.findById(id);
        if(userRepo.isPresent()){
            User usermodified=userRepo.get();
            if (userupdated.getNom() != null){
                usermodified.setNom(userupdated.getNom());
            }
            if (userupdated.getEmail() != null){
                if ((Objects.equals(userupdated.getEmail(), usermodified.getEmail())) || !repository.existsByEmail(userupdated.getEmail())) {
                    usermodified.setEmail(userupdated.getEmail());
                }else{
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseLoginUser("Email exists ",false));

                }
            }
            if (userupdated.getNum_tel() != null){
                usermodified.setNum_tel(userupdated.getNum_tel());
            }
            if (userupdated.getPassword() != null){
                String hashPassword = passwordEncoder.encode(userupdated.getPassword());
                usermodified.setPassword(hashPassword);
            }
            if (image != null && !image.isEmpty()) {
                usermodified.setImageuser((ImageUtil.compressImage(image.getBytes())));
            }
            if (userupdated.getRole() != null){
                usermodified.setRole(userupdated.getRole());
                if (userupdated.getRole().equals("collaborateur")) {
                    repository.setManagerNull(userupdated.getId());
                    usermodified.setManager(userupdated.getManager());
                }
                else {
                    usermodified.setManager(null);
                }

            }
            repository.save(usermodified);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseLoginUser("ok",true));
    }


    public byte[] downloadImage(int id){
        Optional<User> user = repository.findById(id);
        byte[] images=ImageUtil.decompressImage(user.get().getImageuser());
        return images;
    }


    public byte[][] downloadImages() {
        List<User> users = repository.findAll();
        byte[][] images = new byte[users.size()][];
        for (int i=0;i<users.size();i++){
            images[i] = ImageUtil.decompressImage(users.get(i).getImageuser());
        }
//        byte[] images=ImageUtil.decompressImage(user.get().getImageuser());
        return images;
    }


    public List<Map<String, String>> getManagers(int id){
        List<User> listuser = getUsers();
        List<Map<String, String>> responseBodyList = new ArrayList<>();
        for (User user : listuser) {
            if (  ("manager".equals(user.getRole())) && (user.getId() != id) ) {
                Map<String, String> responseBody = new HashMap<>();
                responseBody.put("idmanager", String.valueOf(user.getId()));
                responseBody.put("nommanager", user.getNom());
                responseBodyList.add(responseBody);
            }
        }
        return responseBodyList;
    }
}
