package com.pradum.ebookadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.pradum.ebookadmin.Utils.TokenManager;
import com.pradum.ebookadmin.api.AdminAPI;
import com.pradum.ebookadmin.api.RetrofitServices;
import com.pradum.ebookadmin.models.AdminRequest;
import com.pradum.ebookadmin.models.AdminResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText edtEmail, edtPass;
    AppCompatButton btnLogIn;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        btnLogIn = findViewById(R.id.btnLogIn);
        getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        });
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edtEmail.getText().toString().trim();
                password = edtPass.getText().toString().trim();
                LogIn(email, password);
            }
        });
    }

    private void LogIn(String email, String password) {
            if (!isValidMail(email) || email.isEmpty()) {
                edtEmail.requestFocus();
                edtEmail.setError(getResources().getString(R.string.please_enter_email));
            } else if (password.isEmpty()) {
                edtPass.requestFocus();
                edtPass.setError(getResources().getString(R.string.please_enter_password));
            } else {
                AdminRequest userRequest = new AdminRequest(email, password);
                AdminAPI apiService = RetrofitServices.getRetrofitUserInstance().create(AdminAPI.class);
                Call<AdminResponse> userResponseCall = apiService.signin(userRequest);
                userResponseCall.enqueue(new Callback<AdminResponse>() {
                    @Override
                    public void onResponse(Call<AdminResponse> call, Response<AdminResponse> response) {
                        AdminResponse userResponse = response.body();
                        new TokenManager(getApplicationContext()).saveToken(userResponse.getToken());
                        startActivity(new Intent(MainActivity.this, Home.class));
                        finish();
                    }

                    @Override
                    public void onFailure(Call<AdminResponse> call, Throwable throwable) {
                        Toast.makeText(MainActivity.this, throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }


        }


        private boolean isValidMail(String email){
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
}