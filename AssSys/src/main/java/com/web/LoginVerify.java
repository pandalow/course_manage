package com.web;

import com.pojo.User;
import com.service.UserServer;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet("/login")
public class LoginVerify extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private User user = null;


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String userName = request.getParameter("username").trim();
        int password = parseInt(request.getParameter("password").trim());


        user = UserServer.getInstance().mapUser(userName, password);
        int key = user.getType();

        // Set the user tag to the session authorized on behalf of the user
        request.getSession().setAttribute("userInfo", user);

        //Fowarding the different module by type
        switch (key) {
            case 0:
                response.sendRedirect("admin/UserManage.jsp");
                break;
            case 1:
                response.sendRedirect("student/StudentPage.jsp");
                break;
            case 2:
                response.sendRedirect("teacher/TeacherPage.jsp");
                break;
            default:
                response.sendRedirect("index.jsp");
        }
    }
}
