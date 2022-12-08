package com.timesplit.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.timesplit.R;

public class LoginActivity extends AppCompatActivity {

    private Button iconButton_HomeMenu, iconButton_Back, button_Login, textButton_Registrarme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Login
        button_Login = findViewById(R.id.filledButton_Login);
        button_Login.setOnClickListener(l -> {

        });

        //Registrarme
        textButton_Registrarme = findViewById(R.id.textButton_LoginRegistrarme);
        textButton_Registrarme.setOnClickListener(r -> {
            Intent intent = new Intent(LoginActivity.this, com.timesplit.vista.RegisterActivity.class);
            startActivity(intent);
        });

        //Back
        iconButton_Back = findViewById(R.id.iconButton_Back);
        iconButton_Back.setOnClickListener(h -> {
            finish();
        });

        // Ajustes
        iconButton_HomeMenu = findViewById(R.id.iconButton_HomeMenu);
        iconButton_HomeMenu.setOnClickListener(h -> {
            Intent intent = new Intent(LoginActivity.this, com.timesplit.vista.MenuActivity.class);
            startActivity(intent);
        });


    }
}