package com.test;

import com.pojo.User;
import com.data.DataHandler;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestDriver {
    @Test
    public void testList() throws SQLException {
        DataHandler instance = DataHandler.getInstance();
        List<User> users = instance.getUsers();
        users.stream().map(user -> user.getId()).forEach(System.out::println);

    }
    @Test
    public void testIn()throws  SQLException{
        DataHandler instance = DataHandler.getInstance();
        System.out.println(instance.insertCourseUser(3, 322));
    }
}
