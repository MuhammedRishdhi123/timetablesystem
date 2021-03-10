package com.example.timetablesystem.model;

import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.*;

@Entity
@Table(name="Module")
@JsonRootName(value = "Module")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int moduleId;
    private String moduleTitle;
    private int moduleCredits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId")
    private Course course;

    public Module(int moduleId, int moduleCredits, String moduleTitle) {
        this.moduleId = moduleId;
        this.moduleCredits = moduleCredits;
        this.moduleTitle = moduleTitle;
    }

    public Module() {

    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public int getModuleCredits() {
        return moduleCredits;
    }

    public void setModuleCredits(int moduleCredits) {
        this.moduleCredits = moduleCredits;
    }

    public String getModuleTitle() {
        return moduleTitle;
    }

    public void setModuleTitle(String moduleTitle) {
        this.moduleTitle = moduleTitle;
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleId=" + moduleId +
                ", moduleTitle='" + moduleTitle + '\'' +
                ", moduleCredits=" + moduleCredits +
                ", course=" + course +
                '}';
    }
}
