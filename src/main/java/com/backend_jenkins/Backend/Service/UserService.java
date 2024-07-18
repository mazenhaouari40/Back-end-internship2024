package com.backend_jenkins.Backend.Service;


import com.backend_jenkins.Backend.Model.User;
import com.backend_jenkins.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getUsers(){
        return repository.findAll();
    }
    public Optional<User> getUser(Integer id){
       return repository.findById(id);
    }

    public void addUser(User user){
        repository.save(user);
    }
    public void updateUser(User user){
        repository.save(user);
    }
    public void deleteUser(Integer id){
        repository.deleteById(id);
    }

}
