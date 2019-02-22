package com.hungnp3.signinwithfacebook;

public class FacebookInfo {
    private String id;
    private String name;
    private String dateOfBirth;
    private String email;

    public FacebookInfo(String id, String name, String dateOfBirth, String email) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }
}
