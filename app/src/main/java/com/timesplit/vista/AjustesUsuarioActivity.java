package com.timesplit.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.timesplit.R;

import java.lang.reflect.Array;

public class AjustesUsuarioActivity extends AppCompatActivity {

    private Button textButton_Pass, iconButton_Back, iconButton_Home;
//    private TextInputEditText EditText_Opciones_Tema;
    private MaterialAutoCompleteTextView EditText_Opciones_Tema;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes_usuario);

        //Dropdown Tema
        // TODO -> ASIGNAR ARRAY AL DROPDOWN
//        val items = arrayOf("Item 1", "Item 2", "Item 3", "Item 4")
//        (textField.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)
        EditText_Opciones_Tema = findViewById(R.id.EditText_Opciones_Tema);
        String[][] tema = {{"1", "2"},{"Claro", "Oscuro"}};


        // Volumen
        //TODO -> ASIGNAR volumen a BD, si es 0 sonido = false -- Como establecer volumen app?

        //Cambiar Pass
        textButton_Pass = findViewById(R.id.textButton_OpcionesUsuario_Pass);
        textButton_Pass.setOnClickListener(r -> {
            Intent intent = new Intent(AjustesUsuarioActivity.this, com.timesplit.vista.PassActivity.class);
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
            Intent intent = new Intent(AjustesUsuarioActivity.this, com.timesplit.vista.MainActivity.class);
            startActivity(intent);
        });

    }
}