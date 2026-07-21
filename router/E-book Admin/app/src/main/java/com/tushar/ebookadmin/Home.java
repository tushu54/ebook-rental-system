package com.pradum.ebookadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.mask.PorterShapeImageView;
import com.pradum.ebookadmin.Utils.TokenManager;

public class Home extends AppCompatActivity {
    ImageView ivUser,ivBook,ivPay;
    Button btnLogout;
    FrameLayout UserCat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

         ivUser = findViewById(R.id.ivUser);
         ivBook = findViewById(R.id.ivBook);
         ivPay = findViewById(R.id.ivPay);
        UserCat = findViewById(R.id.UserCat);

        Glide.with(this)
                .load(R.drawable.img_1)
                .into(ivUser);
        Glide.with(this)
                .load(R.drawable.img)
                .into(ivBook);
        Glide.with(this)
                .load(R.drawable.img_2)
                .into(ivPay);

        UserCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this,UserManage.class));
            }
        });
        ivPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this,PaymentVerification.class));
            }
        });
        ivBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Home.this,EbookManage.class));
            }
        });

        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new TokenManager(getApplicationContext()).deleteToken();

                Intent intent = new Intent(Home.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });
    }
}