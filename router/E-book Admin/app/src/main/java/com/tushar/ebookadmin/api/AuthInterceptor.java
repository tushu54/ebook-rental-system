package com.pradum.ebookadmin.api;

import android.util.Log;

import com.pradum.ebookadmin.Utils.TokenManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private final TokenManager tokenManager;

    public AuthInterceptor(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        Request.Builder requestBuilder = originalRequest.newBuilder();

        String token = tokenManager.getToken();
        Log.d("MILA",token);
        if (token != null) {
            requestBuilder.addHeader("Authorization", "Bearer " + token);
        }

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}