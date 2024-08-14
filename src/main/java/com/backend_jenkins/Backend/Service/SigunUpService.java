package com.backend_jenkins.Backend.Service;


import com.backend_jenkins.Backend.Jwt.ImageUtil;
import com.backend_jenkins.Backend.Model.User;
import com.backend_jenkins.Backend.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class SigunUpService implements AuthService {

    @Autowired
    public UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean createUser(User user) throws IOException {
        if (userRepository.existsByEmail(user.getEmail())) {
            return false;
        }
        User u = new User();
        BeanUtils.copyProperties(user, u);
        String hashPassword = passwordEncoder.encode(user.getPassword());
        u.setPassword(hashPassword);
        ClassPathResource imgFile = new ClassPathResource("avatar-2.jpg");
        byte[] image = Files.readAllBytes(Paths.get( imgFile.getURI()  )   );
        u.setImageuser(ImageUtil.compressImage(image));
        userRepository.save(u);
        return true;
    }

}
