package com.domain;

public class UserFactory {
    private UserFactory userFactory = null;
    private UserFactory() {

    }
    /**
     * @return
     */
    public UserFactory getInstanceOfUserFactory() {
        if (userFactory == null) {
            userFactory = new UserFactory();
        }
        return userFactory;
    }

    public User getInstanceOfAdmin(String name, int pwd) {
        return new Admin(name, pwd);
    }
    public User getInstanceOfStudent(String name, int pwd){
        return new Student(name,pwd);
    }
}
