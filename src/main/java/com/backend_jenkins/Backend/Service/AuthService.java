package com.backend_jenkins.Backend.Service;

import com.backend_jenkins.Backend.Model.User;

import java.io.IOException;

public interface AuthService {
    boolean createUser(User user) throws IOException;
}
