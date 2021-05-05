package com.example.timetablesystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Course")
@JsonRootName(value = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    private String courseTitle;
    private int courseDuration;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "course")
    private Set<Module> modules;

    public Course(int courseId, int courseDuration, String courseTitle) {
        this.courseId = courseId;
        this.courseDuration = courseDuration;
        this.courseTitle = courseTitle;
    }

    public Course() {

    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(int courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public String getModulesString() {
        StringBuilder sb = new StringBuilder();
        for (Module t : modules) {
            sb.append(t.toString()).append(", ");
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseTitle='" + courseTitle + '\'' +
                ", courseDuration=" + courseDuration +
                '}';
    }
}
