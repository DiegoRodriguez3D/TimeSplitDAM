package com.timesplit.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.timesplit.R;

public class NewPerfilActivity extends AppCompatActivity {

    private Button textButton_NewPerfil_Avanzado, iconButton_Back, iconButton_Home, button_Guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_perfil);


        //Guardar
        button_Guardar = findViewById(R.id.filledButton_GuardarPerfil);
        button_Guardar.setOnClickListener(g -> {
            //TODO: RECIBIR objeto AjustesPerfil de Avanzado (si existe), guardar Pefil y, si se completa el insert, guardar ajustesPerfil
            Intent intent = new Intent(NewPerfilActivity.this, com.timesplit.vista.PerfilesActivity.class);
            startActivity(intent);
        });

        //Avanzado
        textButton_NewPerfil_Avanzado = findViewById(R.id.textButton_NewPerfil_Avanzado);
        textButton_NewPerfil_Avanzado.setOnClickListener(r -> {
            Intent intent = new Intent(NewPerfilActivity.this, com.timesplit.vista.AjustesNewPerfilActivity.class);
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
            Intent intent = new Intent(NewPerfilActivity.this, com.timesplit.vista.MainActivity.class);
            startActivity(intent);
        });

    }
}