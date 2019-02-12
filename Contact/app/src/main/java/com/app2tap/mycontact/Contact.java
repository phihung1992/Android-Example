package com.app2tap.mycontact;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

/**
 * Project: MyContact.
 * Author: HUNG CODER.
 * Created on: 26/9/2017
 */

public class Contact implements Comparable<Contact> {
    private String mName;
    private String mPhoneNumber;
    private Bitmap mAvatar;

    public Contact(String name, String phoneNumber, Bitmap avatar) {
        mName = name;
        mPhoneNumber = phoneNumber;
        mAvatar = avatar;
    }

    public String getName() {
        return mName;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public Bitmap getAvatar() {
        return mAvatar;
    }

    @Override
    public int compareTo(@NonNull Contact o) {
        return this.mName.compareTo(o.mName);
    }
}
