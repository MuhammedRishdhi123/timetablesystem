package com.example.timetablesystem.RestEntities;

import com.fasterxml.jackson.annotation.JsonRootName;



@JsonRootName("loginUser")
public class RestLogin {
    private String email;
    private String password;

    public RestLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public RestLogin() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RestLogin{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
