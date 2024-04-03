package com.service;

import com.data.DataHandler;
import com.pojo.Assessment;
import com.pojo.Course;
import com.pojo.User;

import java.util.List;
import java.util.stream.Collectors;

//Singleton model, reduce resource consumption
public class CourseServer {
    private static CourseServer courseServer;

    DataHandler data;

    private CourseServer() {
        data = DataHandler.getInstance();
    }

    public static CourseServer getInstanceOfCourseServer() {
        if (courseServer == null) {
            courseServer = new CourseServer();
        }
        return courseServer;
    }
    // Get all courses
    public List<Course> getCourses() {
        return data.getCourses();
    }
    // Get user relate courses
    public List<Course> getUserCourses(User user) {
        return data.getUserCourse(user);
    }
    // Creating course by admin
    public boolean insertCourse(Course course) {
        boolean key = data.getCourses()
                .stream().map(Course::getCourseID)
                .anyMatch(id -> id == course.getCourseID());
        if (key) {
            return false;
        } else {
            data.insertCourse(course);
            return true;
        }
    }
    // Get students relate assessment
    public List<Assessment> getUserAssess(User user, int courseId) {
        return data.getAssess().stream()
                .filter(assessment -> assessment.getUserID() == user.getId() && assessment.getCourseID() == courseId)
                .collect(Collectors.toList());
    }
    // Access the student marks in typical course, which can be changed or updated
    public List<Assessment> getMarksAssess(int userid, int courseId) {
        return data.getAssess().stream()
                .filter(assessment -> assessment.getUserID() == userid && assessment.getCourseID() == courseId)
                .collect(Collectors.toList());
    }
    // Update assessment marks
    public boolean updateAssess(Assessment assessment, String type) {
        return data.updateAssess(assessment, type);
    }
    // Remove courses by admin
    public void removeCourse(Integer courseId) {
        data.removeCourse(courseId);
    }
}
