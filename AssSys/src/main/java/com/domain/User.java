package com.domain;

public class User {
    private String name;
    private int passWord;
    private String firstName;
    private String lastName;
    private int phone;
    private int type;

    public User(String name, int passWord, int type) {
        this.name = name;
        this.passWord = passWord;
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
