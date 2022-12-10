package com.timesplit.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.timesplit.R;

public class AjustesNewPerfilActivity extends AppCompatActivity {

    private Button iconButton_Back, iconButton_Home, button_Guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes_new_perfil);


        //Guardar
        button_Guardar = findViewById(R.id.filledButton_Guardar2);
        button_Guardar.setOnClickListener(h -> {
            //TODO: Guardar objeto AjustesPerfil con datos almacenados, y pasar a NewPerfil para insertar en tabla
            Intent intent = new Intent(AjustesNewPerfilActivity.this, com.timesplit.vista.NewPerfilActivity.class);
            startActivity(intent);
        });

        //Back
        iconButton_Back = findViewById(R.id.iconButton_Back);
        iconButton_Back.setOnClickListener(h -> {
            finish();
        });

        // Ajustes
        iconButton_Home = findViewById(R.id.iconButton_Home);
        iconButton_Home.setOnClickListener(h -> {
            Intent intent = new Intent(AjustesNewPerfilActivity.this, com.timesplit.vista.MainActivity.class);
            startActivity(intent);
        });

    }
}