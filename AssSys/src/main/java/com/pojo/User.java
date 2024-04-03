package com.pojo;

public class User {
    private int id;
    private String name;
    private int passWord;
    private String firstName;
    private String lastName;
    private int phone;
    private int type = -1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User() {
    }

    public User(String name, int passWord, int type) {
        this.name = name;
        this.passWord = passWord;
    }

    public User(String name, int passWord, String firstName, String lastName, int phone, int type) {
        this.name = name;
        this.passWord = passWord;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.type = type;
    }

    public User(int id, String name, int passWord, String firstName, String lastName, int phone, int type) {
        this.id = id;
        this.name = name;
        this.passWord = passWord;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.type = type;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getPassWord() {
        return passWord;
    }

    public void setPassWord(int passWord) {
        this.passWord = passWord;
    }

}
