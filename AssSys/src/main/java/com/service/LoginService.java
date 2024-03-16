package com.service;

import com.domain.User;
import com.handler.DataHandler;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

import static java.lang.Integer.parseInt;

@WebServlet("/login")
public class LoginService extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private User user = null;


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String userName = request.getParameter("username").trim();
        int password = parseInt(request.getParameter("password").trim());
        System.out.println("成功登入" + userName + password);

        //Get instance of data handler
        DataHandler dataHandler = DataHandler.getInstance();
        System.out.println(dataHandler.getClass());

        try {
            user = dataHandler.verifyUser(userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(user.getName() + user.getPassWord());

        if (user == null) {
            response.sendRedirect("index.jsp");
        }

        int key = user.getType();
        switch (key) {
            case 0:
                request.getSession().setAttribute("userInfo",user);
                response.sendRedirect("AdminPage.jsp");
                break;
            case 1:
                request.getSession().setAttribute("userInfo",user);
                response.sendRedirect("StudentPage.jsp");
                break;
            case 2:
                response.sendRedirect("Teacher.jsp");
                break;
        }
    }
}
