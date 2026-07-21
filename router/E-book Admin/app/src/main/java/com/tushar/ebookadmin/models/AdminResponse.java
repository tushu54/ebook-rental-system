package com.pradum.ebookadmin.models;

public class AdminResponse {
    private final String token;
    private final Admin user;

    public AdminResponse(String token, Admin user) {

        this.token = token;
        this.user = user;
    }


    public String getToken() {
        return token;
    }

    public Admin getUser () {
        return user;
    }
}
