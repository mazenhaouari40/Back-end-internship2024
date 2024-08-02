package com.backend_jenkins.Backend.Service;

import com.backend_jenkins.Backend.Model.User;

public interface AuthService {
    boolean createUser(User user);
}
