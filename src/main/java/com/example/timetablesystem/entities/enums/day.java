package com.example.timetablesystem.entities.enums;

public enum day {
    MONDAY(1, "Monday"),
    TUESDAY(2, "Tuesday"),
    WEDNESDAY(3, "Wednesday"),
    THURSDAY(4, "Thursday"),
    FRIDAY(5, "Friday");

    private int day;
    private String dayName;

    day(int day, String dayName) {
        this.day = day;
        this.dayName = dayName;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }
}
