package com.pojo;

public class Assessment {
    private int userID;
    private int courseID;
    private float quiz;
    private float exam;
    private float assignment;

    public Assessment(int userID, int courseID) {
        this.userID = userID;
        this.courseID = courseID;
    }

    public int getUserID() {
        return userID;
    }

    public int getCourseID() {
        return courseID;
    }

    public Assessment(int userID, int courseID, float quiz, float exam, float assignment) {
        this.userID = userID;
        this.courseID = courseID;
        this.quiz = quiz;
        this.exam = exam;
        this.assignment = assignment;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public float getQuiz() {
        return quiz;
    }

    public void setQuiz(float quiz) {
        this.quiz = quiz;
    }

    public float getExam() {
        return exam;
    }

    public void setExam(float exam) {
        this.exam = exam;
    }

    public float getAssignment() {
        return assignment;
    }

    public void setAssignment(float assignment) {
        this.assignment = assignment;
    }
}
