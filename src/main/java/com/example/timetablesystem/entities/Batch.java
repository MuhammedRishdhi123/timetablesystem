package com.example.timetablesystem.entities;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Batch")
@JsonRootName("Batch")
public class Batch {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int batchId;

    private String batchTitle;

    @OneToMany(mappedBy = "batch")
    private Set<Student> students;

    public Batch(int batchId, String batchTitle) {
        this.batchId = batchId;
        this.batchTitle = batchTitle;
    }

    public Batch() {
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public String getBatchTitle() {
        return batchTitle;
    }

    public void setBatchTitle(String batchTitle) {
        this.batchTitle = batchTitle;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }



    @Override
    public String toString() {
        return "Batch{" +
                "batchId=" + batchId +
                ", batchTitle='" + batchTitle + '\'' +
                ", students=" + students +
                '}';
    }
}
