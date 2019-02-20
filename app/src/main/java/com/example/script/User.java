package com.example.script;

class User {
    private String firstName, lastName, regID, userType;
    //blank constructor

    public User()
    {}
    //constructor to initialize
    public User(String firstName, String lastName, String regID, String userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.regID = regID;
        this.userType = userType;
    }
}
