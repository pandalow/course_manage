package com.web.admin;

import com.pojo.User;
import com.service.UserServer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *  Encapsulate the user and pass to the service;
 */
@WebServlet("/modifyUser")
public class CreateUser extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("username");
        int password = Integer.parseInt(request.getParameter("password"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int phone = Integer.parseInt(request.getParameter("phone"));
        String paraKey = request.getParameter("type");
        int type = paraKey.equals("student") ? 1 : 2;

        // Encapsulate the variables in User Entity
        User user = new User(name, password, firstName, lastName, phone, type);

        //Invoke the service to create User
        boolean key = UserServer.getInstance().createUser(user);
        if (key) {
            request.setAttribute("status", "Success");
        } else {
            request.setAttribute("status", "Failure");
        }
        request.getRequestDispatcher("admin/UserManage.jsp").forward(request, response);
    }
}
