package com.hungnp3.demomvpm.model;

/**
 * Created by HUNG on 15/7/2017.
 */

public class User {
    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
