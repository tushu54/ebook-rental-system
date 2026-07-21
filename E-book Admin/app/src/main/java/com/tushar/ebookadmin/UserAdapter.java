package com.pradum.ebookadmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pradum.ebookadmin.Utils.TokenManager;
import com.pradum.ebookadmin.api.AdminAPI;
import com.pradum.ebookadmin.api.RetrofitServices;
import com.pradum.ebookadmin.models.Admin;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewHolder> {
    ArrayList<Admin> User;
    Context context;

    public UserAdapter(ArrayList<Admin> user, Context context) {
        User = user;
        this.context = context;
    }

    @NonNull
    @Override
    public UserAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_user, parent, false);
        return new UserAdapter.viewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.viewHolder holder, int position) {
        Admin user=User.get(position);
        holder.tvUsername.setText(user.getName());
        holder.tvEmail.setText(user.getEmail());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDelete(user.getId(),user);
            }
        });
    }

    private void UserDelete(String id, Admin user) {
        AdminAPI apiService = RetrofitServices.getRetrofitBooksInstance(new TokenManager(context.getApplicationContext())).create(AdminAPI.class);
        Call<Admin> deleteUser = apiService.deleteUser(id);
        deleteUser.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {

                if (response.isSuccessful()){
                    Admin admin= response.body();
                    User.remove(user);
                    notifyDataSetChanged();
                }else {

                    switch (response.code()) {
                        case 400:
                            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                            break;
                        case 401:
                            // Unauthorized
                            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
                            // Not Found
                            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            // Internal Server Error
                            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            // Handle other status codes
                            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<Admin> call, Throwable throwable) {
                Toast.makeText(context, throwable.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return User.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
            TextView tvUsername,tvEmail;
            ImageView btnDelete;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            tvUsername=itemView.findViewById(R.id.tvUsername);
            btnDelete=itemView.findViewById(R.id.btnDelete);
            tvEmail=itemView.findViewById(R.id.tvEmail);
        }
    }
}
