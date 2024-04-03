package com.web.admin;

import com.service.UserServer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/removeUser")
public class RemoveUser extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userid"));
        UserServer.getInstance().removeUser(userId);

        request.getRequestDispatcher("admin/UserManage.jsp").forward(request, response);
    }
}
