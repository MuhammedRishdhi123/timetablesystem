package com.example.timetablesystem.RestEntities;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@JsonRootName("restUser")
public class RestUser {
    private int userId;
    private String name;
    private String password;
    private String email;
    private String phone;

    public RestUser(int userId,String name, String password, String email, String phone) {
        this.userId=userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public RestUser() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
