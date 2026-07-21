package com.pradum.ebookadmin.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager {
    private SharedPreferences prefs;
    public TokenManager(Context context) {
        prefs = context.getSharedPreferences("PREFS_TOKEN_FILE", Context.MODE_PRIVATE);
    }

    public void saveToken(String token) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("USER_TOKEN_KEY", token);
        editor.apply(); // Use apply() for non-blocking save
    }

    public void deleteToken() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("USER_TOKEN_KEY");
        editor.apply();
    }

    public String getToken() {
        return prefs.getString("USER_TOKEN_KEY", null);
    }
}
