package com.pradum.ebookadmin.models;

public class Admin {
    private String _id;
    private String name;
    private String email;
    private String password;
    private int __v;

    public Admin(String _id, String name, String email, String password, boolean isBlocked, int __v) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.__v = __v;
    }

    // Getters
    public String getId() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getVersion() {
        return __v;
    }
}
