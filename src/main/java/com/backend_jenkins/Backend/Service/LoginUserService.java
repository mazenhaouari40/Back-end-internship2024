package com.backend_jenkins.Backend.Service;

import ch.qos.logback.core.joran.spi.ElementSelector;
import com.backend_jenkins.Backend.Model.LoginUser;
import com.backend_jenkins.Backend.Model.ResponseLoginUser;
import com.backend_jenkins.Backend.Model.User;
import com.backend_jenkins.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;


@Service
public class LoginUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("user not found with email"));
        return  new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), Collections.emptyList());
    }


//    public ResponseLoginUser loginUser(LoginUser loginUser){
//        String msg = "";
//        User user1 = userRepository.findByEmail(loginUser.getEmail());
//        if (user1 != null) {
//            String password = loginUser.getPassword();
//            String encodedPassword = user1.getPassword();
//            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
//
//            if (isPwdRight){
//                Optional<User> user = userRepository.findOneByEmailAndPassword(loginUser.getEmail(),encodedPassword);
//                if (user.isPresent()) {
//                    return new ResponseLoginUser("login Success",true);
//                }else {
//                    return new ResponseLoginUser("login failed",false);
//                }
//            }
//            else {
//                return new ResponseLoginUser("Password Not Match",false);
//
//            }
//
//        }else{
//            return new ResponseLoginUser("Email not exits",false);
//        }
//
//
//    }


}
