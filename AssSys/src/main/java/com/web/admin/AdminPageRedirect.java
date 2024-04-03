package com.web.admin;

import com.pojo.Course;
import com.pojo.User;
import com.service.CourseServer;
import com.service.UserServer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/selectPage")
public class AdminPageRedirect extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //Page navigation is used, according to the different buttons to direct to different services
        String action = request.getParameter("action");

        if ("useCreate".equals(action)) {
            request.getRequestDispatcher("admin/UserManage.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("admin/CourseManage.jsp").forward(request, response);
        }
    }
}

