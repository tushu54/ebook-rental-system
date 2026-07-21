package com.pradum.ebookadmin;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.pradum.ebookadmin.Utils.TokenManager;
import com.pradum.ebookadmin.api.AdminAPI;
import com.pradum.ebookadmin.api.RetrofitServices;
import com.pradum.ebookadmin.models.Admin;
import com.pradum.ebookadmin.models.AdminResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserManage extends AppCompatActivity {
RecyclerView userlist;
    UserAdapter userAdapter;
    ArrayList<Admin> adminArrayList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_manage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userlist=findViewById(R.id.UserList);
        userAdapter =new UserAdapter(adminArrayList,UserManage.this);
        AdminAPI apiService = RetrofitServices.getRetrofitBooksInstance(new TokenManager(getApplicationContext())).create(AdminAPI.class);
        Call<ArrayList<Admin>> adminResponseCall = apiService.getAllUsers();
        adminResponseCall.enqueue(new Callback<ArrayList<Admin>>() {
            @Override
            public void onResponse(Call<ArrayList<Admin>> call, Response<ArrayList<Admin>> response) {
                 adminArrayList.addAll(response.body());
                userlist.setAdapter(userAdapter);
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Admin>> call, Throwable throwable) {

            }
        });

    }
}