package com.pradum.ebookadmin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.pradum.ebookadmin.Utils.TokenManager;
import com.pradum.ebookadmin.api.AdminAPI;
import com.pradum.ebookadmin.api.RetrofitServices;
import com.pradum.ebookadmin.models.Admin;
import com.pradum.ebookadmin.models.Book;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EbookAdapter extends RecyclerView.Adapter<EbookAdapter.viewHolder> {

    ArrayList<Book> books;
    Context context;

    public EbookAdapter(ArrayList<Book> books, Context context) {
        this.books = books;
        this.context = context;
    }

    @NonNull
    @Override
    public EbookAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_book_list, parent, false);
        return new EbookAdapter.viewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EbookAdapter.viewHolder holder, int position) {

        Book book= books.get(position);
        holder.tvBookName.setText(book.getTitle());
        holder.tvByNameard.setText(book.getAuthor());
        holder.tvBookPrice.setText(book.getPrice());

        Glide.with(context)
                .load(book.getImageUrl())
                .into(holder.ivBook);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    deleteBooks(book);
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,AddBooks.class).putExtra("BOOK",book));
            }
        });

    }

    private void deleteBooks(Book book) {

        AdminAPI apiService = RetrofitServices.getRetrofitBooksInstance(new TokenManager(context.getApplicationContext())).create(AdminAPI.class);
        Call<Book> deleteUser = apiService.deleteBook(book.getId());
        deleteUser.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                books.remove(book);
                notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Book> call, Throwable throwable) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        PorterShapeImageView ivBook;
        TextView tvBookName,tvByNameard,tvBookPrice;
        CardView delete,edit;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            ivBook = itemView.findViewById(R.id.ivBook);
            tvBookName = itemView.findViewById(R.id.tvBookName);
            tvBookPrice = itemView.findViewById(R.id.tvBookPrice);
            tvByNameard = itemView.findViewById(R.id.tvByAuthor);
            delete = itemView.findViewById(R.id.delete);
            edit = itemView.findViewById(R.id.edit);
        }
    }
}
