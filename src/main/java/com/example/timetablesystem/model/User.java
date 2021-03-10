package com.example.timetablesystem.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;
@Entity
@Table(name="User",uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@JsonRootName(value = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID userId;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    private String phone;
    @ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinTable(
            name="user_roles",
            joinColumns = @JoinColumn(
                    name="userId",referencedColumnName = "useruserId"),
            inverseJoinColumns = @JoinColumn(
                    name="roleId",referencedColumnName = "roleroleId"))
    private Set<Role> roles;

    @OneToOne(mappedBy = "User")
    private Admin admin;

    @OneToOne(mappedBy = "User")
    private Student student;

    @OneToOne(mappedBy = "User")
    private Lecturer lecturer;

    public User(UUID userId, String name, String email, String password, String phone) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public User() {

    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", roles=" + roles +
                ", admin=" + admin +
                ", student=" + student +
                ", lecturer=" + lecturer +
                '}';
    }
}
