package com.web.admin;

import com.pojo.Course;
import com.service.CourseServer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
@WebServlet("/createCourses")
public class CreateCourse extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String courseName = request.getParameter("courseName");
        int semester = Integer.parseInt(request.getParameter("semester"));

        // Encapsulate the variables in Course Entity
        Course course = new Course(courseId, courseName, semester);


        CourseServer courseServer = CourseServer.getInstanceOfCourseServer();

        //Invoke the service to create Course
        if (courseServer.insertCourse(course)) {
            request.setAttribute("result", "success");
        } else {
            request.setAttribute("result", "failure");
        }
        request.getRequestDispatcher("admin/CourseManage.jsp").forward(request, response);
    }
}
