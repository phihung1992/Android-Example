package com.hungnp3.demomvpm.manager;

import com.hungnp3.demomvpm.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HUNG on 15/7/2017.
 */

public class UserManager {
    private static UserManager instance;
    private List<User> listUser;

    private UserManager() {

    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public void addUser(String userName, String password) {
        if (listUser == null) {
            listUser = new ArrayList<>();
        }
        listUser.add(new User(userName, password));
    }

    public boolean validInformation(String userName, String password) {
        for (int i = 0; i < listUser.size(); ++i) {
            if (listUser.get(i).getUserName().equals(userName) && listUser.get(i).getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
