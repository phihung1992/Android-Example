package com.hung.mycustomlistview;

/**
 * Created by HUNG on 2/5/2017.
 */

public class Person {
    private int avatarId;
    private String name;

    public Person(int avatarId, String name) {
        this.avatarId = avatarId; // ResId
        this.name = name; // Content of TextView
    }

    public int getAvatarId() {
        return avatarId;
    }

    public String getName() {
        return name;
    }
}
