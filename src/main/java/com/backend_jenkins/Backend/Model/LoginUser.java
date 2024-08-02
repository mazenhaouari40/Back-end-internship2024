package com.backend_jenkins.Backend.Model;

public class LoginUser {
    private String email;
    private String password;


    public LoginUser(String email, String password){
        this.email = email;
        this.password = password;
    }

    public LoginUser() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
