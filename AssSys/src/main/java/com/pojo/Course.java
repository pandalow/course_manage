package com.pojo;

public class Course {
    private int courseID;
    private String name;
    private int semester;

    public Course(int courseID, String name, int semester) {
        this.courseID = courseID;
        this.name = name;
        this.semester = semester;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
