package com.example.timetablesystem.model;

public class Module {
    private int moduleId,moduleCredits;
    private String moduleTitle;

    public Module(int moduleId, int moduleCredits, String moduleTitle) {
        this.moduleId = moduleId;
        this.moduleCredits = moduleCredits;
        this.moduleTitle = moduleTitle;
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
}
