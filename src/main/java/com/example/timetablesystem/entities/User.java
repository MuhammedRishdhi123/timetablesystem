package com.example.timetablesystem.entities;
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
    private int userId;
    private String name;
    private String email;
    private String password;
    private String phone;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinTable(
            name="user_roles",
            joinColumns = @JoinColumn(
                    name="userId",referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(
                    name="roleId",referencedColumnName = "roleId"))
    private Set<Role> roles;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Admin admin;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Student student;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Lecturer lecturer;

    public User(int userId, String name, String email, String password, String phone) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public User() {

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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
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
