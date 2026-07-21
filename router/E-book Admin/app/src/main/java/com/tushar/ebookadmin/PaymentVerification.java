package com.pradum.ebookadmin;

import android.os.Bundle;
import android.widget.Toast;

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
import com.pradum.ebookadmin.models.RentalResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentVerification extends AppCompatActivity {
    RecyclerView PaymentList;
    PaymentAdpater paymentAdpater;
    ArrayList<RentalResponse> rentalResponseArrayList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_verification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        PaymentList=findViewById(R.id.paymentList);
        paymentAdpater =new PaymentAdpater(rentalResponseArrayList,PaymentVerification.this);
        AdminAPI apiService = RetrofitServices.getRetrofitBooksInstance(new TokenManager(getApplicationContext())).create(AdminAPI.class);
        Call<ArrayList<RentalResponse>> apiServiceRentals = apiService.getRentals();

        apiServiceRentals.enqueue(new Callback<ArrayList<RentalResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<RentalResponse>> call, Response<ArrayList<RentalResponse>> response) {
                if (response.isSuccessful()){
                    rentalResponseArrayList.addAll(response.body());
                    PaymentList.setAdapter(paymentAdpater);
                    paymentAdpater.notifyDataSetChanged();

                }else {

                    switch (response.code()) {
                        case 400:
                            Toast.makeText(PaymentVerification.this, response.message(), Toast.LENGTH_SHORT).show();
                            break;
                        case 401:
                            // Unauthorized
                            Toast.makeText(PaymentVerification.this, response.message(), Toast.LENGTH_SHORT).show();
                            break;
                        case 404:
                            // Not Found
                            Toast.makeText(PaymentVerification.this, response.message(), Toast.LENGTH_SHORT).show();
                            break;
                        case 500:
                            // Internal Server Error
                            Toast.makeText(PaymentVerification.this, response.message(), Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            // Handle other status codes
                            Toast.makeText(PaymentVerification.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RentalResponse>> call, Throwable throwable) {

            }
        });
    }
}