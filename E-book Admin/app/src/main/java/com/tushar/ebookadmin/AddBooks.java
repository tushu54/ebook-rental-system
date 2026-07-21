package com.pradum.ebookadmin;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.pradum.ebookadmin.Utils.TokenManager;
import com.pradum.ebookadmin.api.AdminAPI;
import com.pradum.ebookadmin.api.RetrofitServices;
import com.pradum.ebookadmin.models.Book;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBooks extends AppCompatActivity {
    Book book;
    Boolean isUpate=false;
    AppCompatButton btnAdd;
    EditText  edtTitle,edtAuthor,edtDescription,edtLanguage,edtdate_of_publication,edtprice,edtImage_url,edtPdf_url,edtCat;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_books);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnAdd = findViewById(R.id.btnAdd);
        edtTitle = findViewById(R.id.edtTitle);
        edtAuthor = findViewById(R.id.edtAuthor);
        edtDescription = findViewById(R.id.edtDescription);
        edtLanguage = findViewById(R.id.edtLanguage);
        edtdate_of_publication = findViewById(R.id.edtdate_of_publication);
        edtprice = findViewById(R.id.edtprice);
        edtImage_url = findViewById(R.id.edtImage_url);
        edtPdf_url = findViewById(R.id.edtPdf_url);
        edtCat = findViewById(R.id.edtCat);
        if (getIntent().getExtras() != null) {
            book= (Book) getIntent().getSerializableExtra("BOOK");
            btnAdd.setText("Update Book");
            edtTitle.setText(book.getTitle());
            edtAuthor.setText(book.getAuthor());
            edtDescription.setText(book.getDescription());
            edtLanguage.setText(book.getLanguage());
            edtdate_of_publication.setText(book.getDateOfPublication());
            edtprice.setText(book.getPrice());
            edtImage_url.setText(book.getImageUrl());
            edtPdf_url.setText(book.getPdfUrl());
            edtCat.setText(book.getCategoryId());
            isUpate=true;
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isUpate){
                    UpdateBook(book);
                }else {
                    createBook();
                }
            }
        });

    }

    private void createBook() {
        Book create= new Book(
                edtTitle.getText().toString(),
                edtAuthor.getText().toString(),
                edtCat.getText().toString(),
                edtDescription.getText().toString(),
                edtLanguage.getText().toString(),
                edtdate_of_publication.getText().toString(),
                edtprice.getText().toString(),
                edtImage_url.getText().toString(),
                edtPdf_url.getText().toString());
        AdminAPI apiService = RetrofitServices.getRetrofitBooksInstance(new TokenManager(getApplicationContext())).create(AdminAPI.class);
        Call<Book> createBook = apiService.CreateBook(create);
        createBook.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful()){

                    Toast.makeText(AddBooks.this, "Upload Complete", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable throwable) {

            }
        });

    }

    private void UpdateBook(Book book) {
        Book update= new Book(book.getId(),
                edtTitle.getText().toString(),
                edtAuthor.getText().toString(),
                edtCat.getText().toString(),
                edtDescription.getText().toString(),
                edtLanguage.getText().toString(),
                edtdate_of_publication.getText().toString(),
                edtprice.getText().toString(),
                edtImage_url.getText().toString(),
                edtPdf_url.getText().toString());

        AdminAPI apiService = RetrofitServices.getRetrofitBooksInstance(new TokenManager(getApplicationContext())).create(AdminAPI.class);
        Call<Book> editBook = apiService.editBook(book.getId(),update);
        editBook.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful()){
                    Toast.makeText(AddBooks.this, "Upload Complete", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable throwable) {

            }
        });
    }



}