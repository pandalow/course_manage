package com.web.admin;

import com.service.CourseServer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
//Accept the remove courseId then pass to the service
@WebServlet("/removeCourse")
public class RemoveCourse extends HttpServlet{
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseid"));
        CourseServer.getInstanceOfCourseServer().removeCourse(courseId);
        request.getRequestDispatcher("admin/CourseManage.jsp").forward(request,response);
    }
}
