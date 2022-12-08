package com.timesplit.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.timesplit.R;

public class RegisterActivity extends AppCompatActivity {
private Button iconButton_Back, iconButton_HomeMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        //Back
        iconButton_Back = findViewById(R.id.iconButton_Back);
        iconButton_Back.setOnClickListener(h -> {
            finish();
        });

        // Ajustes
        iconButton_HomeMenu = findViewById(R.id.iconButton_HomeMenu);
        iconButton_HomeMenu.setOnClickListener(h -> {
            Intent intent = new Intent(RegisterActivity.this, com.timesplit.vista.MenuActivity.class);
            startActivity(intent);
        });


    }
}