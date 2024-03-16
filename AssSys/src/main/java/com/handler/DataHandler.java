package com.handler;

import com.domain.User;

import java.sql.*;

public class DataHandler {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String db_url = "jdbc:mysql://localhost:3306/Assignment";
    private static final String user = "root";

    private Statement st;
    private static DataHandler dataHandler = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC driver not found", e);
        }
    }
    private DataHandler() {
        //这里处理一下url,用户名和我的服务器密码,把它变更为properties配置文件,用反射获取;

        try {
            Connection con = DriverManager.getConnection(db_url, user, pass);
            st = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataHandler getInstance() {
        if (dataHandler == null) {
            dataHandler = new DataHandler();
        }
        return dataHandler;
    }

    /**
     *
     * @param userName fetch the user input username from the request.
     * @param pwd fetch the password from the request.
     * @return Object User which store the relate user information.
     * @throws SQLException catch the exception when interacting with database.
     */
    public User verifyUser(String userName, int pwd) throws SQLException {
        // Link to the MySql Service;

        ResultSet rs = st.executeQuery("select name, password, type from User");

        User user = null;
        //Searching User
        while (rs.next()) {
            String name = rs.getString("name");
            int password = rs.getInt("password");
            int type = rs.getInt("type");

            if (name.equals(userName) && password == pwd) {
                user = new User(name, password, type);
            }
        }
        if (user != null) return user;
        else return null;

    }
}
