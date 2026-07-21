package com.pradum.ebookadmin.api;

import com.pradum.ebookadmin.Utils.TokenManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServices {
    private static final String BASE_URL = "http://10.0.2.2:5000/";
    private static Retrofit retrofit;
    private static Retrofit retrofitToken;


    public static Retrofit getRetrofitBooksInstance(TokenManager tokenManager) {
        if (retrofitToken == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new AuthInterceptor(tokenManager))
                    .build();

            retrofitToken = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitToken;
    }
    public static Retrofit getRetrofitUserInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
