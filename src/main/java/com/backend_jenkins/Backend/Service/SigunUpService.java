package com.backend_jenkins.Backend.Service;


import com.backend_jenkins.Backend.Model.User;
import com.backend_jenkins.Backend.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SigunUpService implements AuthService {

    @Autowired
    public UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return false;
        }
        User u = new User();
        BeanUtils.copyProperties(user,u);
        //Hash the password before saving
        String hashPassword = passwordEncoder.encode(user.getPassword());
        u.setPassword(hashPassword);
        userRepository.save(u);
        return true;
    }

}
