package com.example.timetablesystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Lecturer")
@JsonRootName(value = "Lecturer")
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lecturerId;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="module_lecturer",joinColumns = @JoinColumn(name = "lecturerId"),inverseJoinColumns = @JoinColumn(name = "moduleId"))
    private Set<Module> modules;

    @JsonIgnore
    @OneToMany(mappedBy = "lecturer")
    private Set<Session> sessions;

    public Lecturer(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public Lecturer() {
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public Set<Session> getSessions() {
        return sessions;
    }

    public void setSessions(Set<Session> sessions) {
        this.sessions = sessions;
    }

    public String getModulesString() {
        StringBuilder sb = new StringBuilder();
        for (Module t : modules) {
            sb.append(t.getModuleTitle()).append(", \n");
        }

        return sb.toString();
    }


}
