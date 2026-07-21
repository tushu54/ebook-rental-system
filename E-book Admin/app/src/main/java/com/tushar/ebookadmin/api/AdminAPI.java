package com.pradum.ebookadmin.api;

import com.pradum.ebookadmin.models.Admin;
import com.pradum.ebookadmin.models.AdminRequest;
import com.pradum.ebookadmin.models.AdminResponse;
import com.pradum.ebookadmin.models.Book;
import com.pradum.ebookadmin.models.RentalResponse;
import com.pradum.ebookadmin.models.Transaction;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AdminAPI {

    @POST("/api/admin/singin")
    Call<AdminResponse> signin(@Body AdminRequest adminRequest);

    @GET("/api/admin/allusers")
    Call<ArrayList<Admin>> getAllUsers();

    @DELETE("/api/admin/user")
    Call<Admin> deleteUser(@Query("user") String user);

    @DELETE("/api/admin/books")
    Call<Book> deleteBook(@Query("book") String book);

    @POST("/api/admin/book/edit")
    Call<Book> editBook(@Query("book") String book,@Body Book book1);

    @POST("/api/admin/book")
    Call<Book> CreateBook(@Body Book book1);

    @GET("/api/admin/rentals")
    Call<ArrayList<RentalResponse>> getRentals();

    @GET("/api/admin/payment/verify")
    Call<Transaction> verifyPayment(@Query("id") String id);

    @GET("/api/admin/allbooks")
    Call<ArrayList<Book>> getALlbooks();
}
