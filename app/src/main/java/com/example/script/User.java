package com.example.script;

class User {
    private String id, email, firstName, lastName,  userType;
    //blank constructor

    public User()
    {}
    //constructor to initialize
    public User(String id, String email, String firstName, String lastName,
                String userType) {
        this.id=id;
        this.email=email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public String getEmail()
    {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserType() {
        return userType;
    }
}
