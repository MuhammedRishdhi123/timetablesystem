package com.example.timetablesystem.entities;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.*;

@Entity
@Table(name="Student")
@JsonRootName(value = "Student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "batchId")
    private Batch batch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="courseId")
    private Course course;

    public Student(int studentId, User user, Batch batch, Course course) {
        this.studentId = studentId;
        this.user = user;
        this.batch = batch;
        this.course = course;
    }

    public Student() {
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }






}
