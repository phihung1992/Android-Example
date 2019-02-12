package com.hung.demosqlite;

/**
 * Created by HUNG on 25/5/2017.
 */

public class Student {
    private String name;
    private int age;
    private String gender;

    public Student(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "\nName: " + name + "\nAge: " + age + "\nGender: " + gender;
    }
}
