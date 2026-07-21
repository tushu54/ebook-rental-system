package com.pradum.ebookadmin.models;

public class AdminRequest {
    private String email;
    private String password;
    private String name; // Changed from username to name

    public AdminRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
    public AdminRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
