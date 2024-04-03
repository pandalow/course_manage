package com.web.student;

import com.pojo.Assessment;
import com.pojo.User;
import com.service.CourseServer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/checkmarks")
public class CheckMarks extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //get the course id
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        User user = (User) request.getSession().getAttribute("userInfo");

        List<Assessment> userAssess = CourseServer.getInstanceOfCourseServer().getUserAssess(user, courseId);
        request.setAttribute("assess", userAssess);
        request.getRequestDispatcher("student/Assessment.jsp").forward(request, response);
    }
}
