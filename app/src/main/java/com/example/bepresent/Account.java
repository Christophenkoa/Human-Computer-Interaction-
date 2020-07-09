package com.example.bepresent;

public class Account {
    private String userName;
    private String fullName;
    private String email;
    private String location;
    private String password;

    private Account() {
    }

    private static Account ourInstance = null;

    public static Account getInstance() {
        if(ourInstance == null) {
            ourInstance = new Account();
        }

        return ourInstance;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
