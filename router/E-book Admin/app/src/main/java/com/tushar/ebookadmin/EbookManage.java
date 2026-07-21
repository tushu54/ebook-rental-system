package com.pradum.ebookadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.pradum.ebookadmin.Utils.TokenManager;
import com.pradum.ebookadmin.api.AdminAPI;
import com.pradum.ebookadmin.api.RetrofitServices;
import com.pradum.ebookadmin.models.Book;
import com.pradum.ebookadmin.models.RentalResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EbookManage extends AppCompatActivity {
    RecyclerView booksList;
    EbookAdapter ebookAdapter;
    AppCompatButton btnAdd;
    ArrayList<Book> books= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ebook_manage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        booksList=findViewById(R.id.booksList);
        btnAdd=findViewById(R.id.btnAdd);
        ebookAdapter =new EbookAdapter(books,EbookManage.this);
        booksList.setAdapter(ebookAdapter);
        AdminAPI apiService = RetrofitServices.getRetrofitBooksInstance(new TokenManager(getApplicationContext())).create(AdminAPI.class);
        Call<ArrayList<Book>> apiServiceBooks = apiService.getALlbooks();
        apiServiceBooks.enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                books.addAll(response.body());
                ebookAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable throwable) {

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EbookManage.this,AddBooks.class));
            }
        });
    }
}