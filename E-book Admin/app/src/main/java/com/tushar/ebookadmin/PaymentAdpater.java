package com.pradum.ebookadmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.pradum.ebookadmin.Utils.TokenManager;
import com.pradum.ebookadmin.api.AdminAPI;
import com.pradum.ebookadmin.api.RetrofitServices;
import com.pradum.ebookadmin.models.Admin;
import com.pradum.ebookadmin.models.RentalResponse;
import com.pradum.ebookadmin.models.Transaction;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentAdpater extends RecyclerView.Adapter<PaymentAdpater.viewHolder> {
    ArrayList<RentalResponse> User;
    Context context;

    public PaymentAdpater(ArrayList<RentalResponse> user, Context context) {
        User = user;
        this.context = context;
    }

    @NonNull
    @Override
    public PaymentAdpater.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_payment, parent, false);
        return new PaymentAdpater.viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentAdpater.viewHolder holder, int position) {
        RentalResponse response= User.get(position);
        holder.tvBookName.setText(response.ebook.getTitle());
        holder.tvByName.setText("Name: "+response.user.getName());
        holder.tvTxValue.setText(response.payment.getTransactionId());
        holder.tvBookPrice.setText(response.payment.getAmount());
        Glide.with(context)
                .load(response.ebook.getImageUrl())
                .into(holder.ivBook);
        if (response.payment.isVerified()){
            holder.Approve.setEnabled(true);
            holder.Approve.setText("Approved");
        }
        holder.Approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyPayment(response);
            }
        });

    }

    private void verifyPayment(RentalResponse response1) {

        AdminAPI apiService = RetrofitServices.getRetrofitBooksInstance(new TokenManager(context.getApplicationContext())).create(AdminAPI.class);
        Call<Transaction> verifyPayment = apiService.verifyPayment(response1.getPayment().get_id());
        verifyPayment.enqueue(new Callback<Transaction>() {
            @Override
            public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                Transaction transaction= response.body();
                response1.getPayment().setVerifed(true);
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Transaction> call, Throwable throwable) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return User.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        PorterShapeImageView ivBook;
        TextView tvBookName,tvByName,tvTxValue,tvBookPrice;
        AppCompatButton Approve;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            ivBook = itemView.findViewById(R.id.ivBook);
            tvBookName = itemView.findViewById(R.id.tvBookName);
            tvByName = itemView.findViewById(R.id.tvByName);
            tvTxValue = itemView.findViewById(R.id.tvTxValue);
            tvBookPrice = itemView.findViewById(R.id.tvBookPrice);
            Approve = itemView.findViewById(R.id.Approve);
        }
    }
}
