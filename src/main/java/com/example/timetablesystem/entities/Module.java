package com.example.timetablesystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="Module")
@JsonRootName(value = "Module")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int moduleId;
    private String moduleTitle;
    private int moduleCredits;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId")
    private Course course;

    @JsonIgnore
    @OneToMany(mappedBy = "module")
    private Set<Session> sessions;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="module_lecturer",joinColumns = @JoinColumn(name="moduleId"),inverseJoinColumns=@JoinColumn(name="lecturerId"))
    private Set<Lecturer> lecturers;


    public Module(int moduleId, int moduleCredits, String moduleTitle) {
        this.moduleId = moduleId;
        this.moduleCredits = moduleCredits;
        this.moduleTitle = moduleTitle;
    }

    public Module() {

    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(Set<Lecturer> lecturers) {
        this.lecturers = lecturers;
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

    @JsonIgnore
    public String getLecturersString() {
        StringBuilder sb = new StringBuilder();
        for (Lecturer t : lecturers) {
            sb.append(t.getUser().getName()).append(", ");
        }

        return sb.toString();
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
