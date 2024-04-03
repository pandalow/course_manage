package com.web.teacher;

import com.pojo.User;
import com.service.UserServer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/getStudent")
public class GetStudent extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        List<User> students = UserServer.getInstance().getStudents(courseId);


        request.getSession().setAttribute("courseId", courseId);
        request.setAttribute("students", students);

        request.getRequestDispatcher("teacher/StudentList.jsp").forward(request, response);
    }
}
