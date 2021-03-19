package com.example.timetablesystem.RestEntities;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("restUser")
public class RestUser {
    private String name;
    private String password;
    private String email;
    private String phone;

    public RestUser(String name, String password, String email, String phone) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public RestUser() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
