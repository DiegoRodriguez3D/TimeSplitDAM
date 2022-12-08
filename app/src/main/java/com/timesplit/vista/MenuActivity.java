package com.timesplit.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.timesplit.R;

public class MenuActivity extends AppCompatActivity {

    private Button iconButton_Back, iconButton_HomeMenu, textButton_Usuario, textButton_Estadisticas, textButton_Login, iconButton_Home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        //Databinding


        textButton_Estadisticas = findViewById(R.id.textButton_Estadisticas);



        //Usuario
        textButton_Usuario = findViewById(R.id.textButton_Usuario);
        textButton_Usuario.setOnClickListener(l -> {
            Intent intent = new Intent(MenuActivity.this, com.timesplit.vista.AjustesUsuarioActivity.class);
            startActivity(intent);
        });

        //Login
        textButton_Login = findViewById(R.id.textButton_Login);
        textButton_Login.setOnClickListener(l -> {
            Intent intent = new Intent(MenuActivity.this, com.timesplit.vista.LoginActivity.class);
            startActivity(intent);
        });


        //Back
        iconButton_Back = findViewById(R.id.iconButton_Back);
        iconButton_Back.setOnClickListener(h -> {
           finish();
        });

        //Home
        iconButton_Home = findViewById(R.id.iconButton_Home);
        iconButton_Home.setOnClickListener(h -> {
            Intent intent = new Intent(MenuActivity.this, com.timesplit.vista.MainActivity.class);
            startActivity(intent);
        });



    }



}