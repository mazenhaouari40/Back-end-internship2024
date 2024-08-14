package com.backend_jenkins.Backend.controller;


import com.backend_jenkins.Backend.Jwt.JwtUtil;
import com.backend_jenkins.Backend.Model.JwtResponse;
import com.backend_jenkins.Backend.Model.LoginUser;
import com.backend_jenkins.Backend.Model.ResponseLoginUser;
import com.backend_jenkins.Backend.Model.User;
import com.backend_jenkins.Backend.Service.LoginUserService;
import com.backend_jenkins.Backend.Service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
//@CrossOrigin(origins = "http://localhost:4200")

public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private LoginUserService loginUserService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginUser login)  {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDetails userDetails;
        try{
            userDetails = loginUserService.loadUserByUsername(login.getEmail());
        } catch (UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        User user = userService.getUserByEmail(login.getEmail()).get();


        String jwt = jwtUtil.generateToken(userDetails.getUsername());

//        ResponseLoginUser loginresponse = loginUserService.loginUser(login);


//        return ResponseEntity.ok(loginresponse);
        return ResponseEntity.ok(new JwtResponse(jwt,user));

    }

}
