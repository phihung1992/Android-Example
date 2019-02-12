package com.hungnp3.demomvpm.presenter;

import com.hungnp3.demomvpm.manager.UserManager;

/**
 * Created by HUNG on 15/7/2017.
 */

public class MyPresenter {
    private OnCallBack listener;

    public void setListener(OnCallBack listener) {
        this.listener = listener;
    }

    public MyPresenter() {
        UserManager.getInstance().addUser("hungnp3", "123456");
    }

//    public boolean login(String userName, String password) {
//        return UserManager.getInstance().validInformation(userName, password);
//    }

    public void login(String userName, String password) {
        if (UserManager.getInstance().validInformation(userName, password)) {
            listener.disableEditText();
        }
    }

    public interface OnCallBack {
        void disableEditText();
    }
}
