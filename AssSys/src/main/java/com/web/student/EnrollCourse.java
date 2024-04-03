package com.web.student;

import com.pojo.User;
import com.service.UserServer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/enroll")
public class EnrollCourse extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User userInfo = (User) session.getAttribute("userInfo");
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        boolean key = UserServer.getInstance().insertUserCourse(userInfo, courseId);

        if (key) {
            request.setAttribute("result", "success");
        } else {
            request.setAttribute("result", "failure");
        }

        request.getRequestDispatcher("student/AllCourses.jsp").forward(request, response);

    }
}
