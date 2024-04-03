package com.data;

import com.pojo.Assessment;
import com.pojo.Course;
import com.pojo.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class DataHandler {
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String db_url;
    private static String user;
    private static String pass;
    private Connection con;
    private static DataHandler dataHandler = null;

    /*  Force the driver class to start once, which is required in older
        versions of mysql.
     */
    static {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("MySQL JDBC driver not found", e);
        }
    }

    private DataHandler() {
        /* Here's what to do with the url, username and my server password,
        change it to a properties configuration file.*/
        Properties property = new Properties();
        try {
            BufferedReader br = new BufferedReader(new FileReader("/Users/zhuangxiaojian/Downloads/Competition/AssSys/src/main/Mysql.properties"));
            property.load(br);
            db_url = property.getProperty("db_url");
            System.out.println(db_url);
            user = property.getProperty("user");
            System.out.println(user);
            pass = property.getProperty("pass");
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Building connection with database
        try {
            con = DriverManager.getConnection(db_url, user, pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //The singleton pattern gets the same instance of the data handler
    public static DataHandler getInstance() {
        if (dataHandler == null) {
            dataHandler = new DataHandler();
        }
        return dataHandler;
    }

    /**
     * Access to all users
     *
     * @return List of User
     */
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from User");


            while (rs.next()) {
                int id = rs.getInt("USER_ID");
                String name = rs.getString("name");
                int password = rs.getInt("password");
                int type = rs.getInt("type");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int phone = rs.getInt("phone");

                users.add(new User(id, name, password, firstName, lastName, phone, type));
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /*
       Creat new user by using user entity
     */
    public boolean insertUsers(User user) {
        String sql = "INSERT INTO User (name, password, type, firstName, lastName, phone) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setInt(2, user.getPassWord()); // Assuming getPassWord returns an int. Consider security implications.
            pstmt.setInt(3, user.getType());
            pstmt.setString(4, user.getFirstName());
            pstmt.setString(5, user.getLastName());
            pstmt.setInt(6, user.getPhone());
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("failure inject user");
            return false;
        }
    }

    /**
     * Access to all Courses
     *
     * @return List of course
     */
    public List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Course");

            while (rs.next()) {
                int courseid = rs.getInt("COURSEID");
                String courseName = rs.getString("courseName");
                int semester = rs.getInt("semester");
                courses.add(new Course(courseid, courseName, semester));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    /*
       Creat new course by using user entity
     */
    public boolean insertCourse(Course course) {
        String sql = "INSERT INTO Course (COURSEID ,courseName, semester) VALUES (?,?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, course.getCourseID());
            pstmt.setString(2, course.getName());
            pstmt.setInt(3, course.getSemester()); // Assuming getPassWord returns an int. Consider security implications.
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * get all students relate to class;
     *
     * @param courseId
     * @return all the students enrolled the course
     */
    public List<User> getStudent(int courseId) {
        List<User> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM USER WHERE USER_ID IN (SELECT USERID FROM USERCOURSE WHERE COURSEID =?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, courseId);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("USER_ID");
                String name = rs.getString("name");
                int password = rs.getInt("password");
                int type = rs.getInt("type");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                int phone = rs.getInt("phone");

                users.add(new User(id, name, password, firstName, lastName, phone, type));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /** get all student or teacher relate courses
     *
     * @param user user info
     * @return all the courses relate the login user
     */
    public List<Course> getUserCourse(User user) {
        List<Course> courses = new ArrayList<>();
        try {
            int id = user.getId();
            String query = "SELECT * FROM COURSE WHERE COURSEID IN (SELECT COURSEID FROM USERCOURSE WHERE USERID = ?)";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int courseid = rs.getInt("COURSEID");
                String courseName = rs.getString("courseName");
                int semester = rs.getInt("semester");
                courses.add(new Course(courseid, courseName, semester));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
    // Get all assessments
    public List<Assessment> getAssess() {
        List<Assessment> assess = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from ASSESSMENT");
            while (rs.next()) {
                int courseid = rs.getInt("COURSEID");
                int userid = rs.getInt("USERID");
                float quiz = rs.getFloat("quiz");
                float assignment = rs.getFloat("assignment");
                float exam = rs.getFloat("exam");

                assess.add(new Assessment(userid, courseid, quiz, exam, assignment));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assess;
    }

    /**
     * Insert the record into USER-COURSE Table and Assessment Table.
     * In terms of when Teacher update the assessment, will cause the marks changing.
     *
     * @param userid
     * @param courseId
     * @return success result
     */
    public boolean insertCourseUser(int userid, int courseId) {
        try {
            String sql = "INSERT INTO USERCOURSE (USERID, COURSEID) VALUES (?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, userid);
            pstmt.setInt(2, courseId);
            pstmt.executeUpdate();

            insertAssessment(userid, courseId);

            pstmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Creat new assessment,
     *
     * @implNote it will be invoked by  insertCourseUser automatically
     */
    public void insertAssessment(int userid, int courseId) throws SQLException {
        String insertAssessSQL = "INSERT INTO ASSESSMENT (USERID,COURSEID) VALUES (?,?) ";
        PreparedStatement prepared = con.prepareStatement(insertAssessSQL);
        prepared.setInt(1, userid);
        prepared.setInt(2, courseId);
        prepared.executeUpdate();
        prepared.close();
    }

    /**
     * Update the assessment marks by type
     * type include(quiz, assignment, exam)
     *
     * @param assessment entity assessment
     * @return
     */

    public boolean updateAssess(Assessment assessment, String type) {

        String sql = "UPDATE ASSESSMENT SET " + type + " = ? WHERE COURSEID = ? AND USERID = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql);) {
            // Use placeholders (?) to prevent SQL injection
            if (type.equals("quiz")) {
                pstmt.setFloat(1, assessment.getQuiz());
            } else if (type.equals("assignment")) {
                pstmt.setFloat(1, assessment.getAssignment());
            } else if (type.equals("exam")) {
                pstmt.setFloat(1, assessment.getExam());
            }
            // Set the value according to the order of the placeholders in the SQL statement.
            pstmt.setInt(2, assessment.getCourseID());
            pstmt.setInt(3, assessment.getUserID());
            pstmt.executeUpdate();
            pstmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Remove user by userID
     *
     * @param userId to searching the user
     */
    public void removeUser(int userId) {
        String sql = "delete from user where USER_ID = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failure remove");
        }
    }

    /**
     * Remove course by ID
     *
     * @param courseId to searching the course
     */
    public void removeCourse(Integer courseId) {
        String sql = "delete from course where COURSEID = ?";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, courseId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failure remove");
        }
    }
}
