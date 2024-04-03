package com.service;

import com.data.DataHandler;
import com.pojo.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//Singleton model, reduce resource consumption

public class UserServer {
    private static DataHandler data;
    private static UserServer userServer;


    private UserServer() {
        data = DataHandler.getInstance();
    }

    public static UserServer getInstance() {
        if (data == null) {
            userServer = new UserServer();
        }
        return userServer;
    }

    /**
     * Using stream api to filtering the user if is in the database
     * @param userName login account name
     * @param pwd password
     * @return user or null
     */
    public User mapUser(String userName, int pwd) {
        List<User> users = data.getUsers();
        Optional<User> first = users.stream()
                .filter(user -> user.getPassWord() == pwd && user.getName().equals(userName))
                .findFirst();

        return first.orElseGet(() -> new User());
    }

    /**
     * @return all users
     */
    public List<User> getUsers() {
        return data.getUsers();
    }

    /**
     * Create user in the table
     * @param user entity
     * @return the Result of create User in table
     */
    public boolean createUser(User user) {
        if (data.insertUsers(user)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Create new user
     * @param user
     * @param courseId
     * @return true or false can represent the result
     */
    public boolean insertUserCourse(User user, int courseId) {
        return data.insertCourseUser(user.getId(), courseId);
    }

    /**
     * Searching students by courseID
     * @param courseId
     * @return students list
     */
    public List<User> getStudents(int courseId) {
        return data.getStudent(courseId).stream()
                .filter(user -> user.getType() == 1).collect(Collectors.toList());
    }
    // remove user service
    public void removeUser(int userId) {
        data.removeUser(userId);
    }
}
