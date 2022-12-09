package com.timesplit.vista;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.timesplit.R;
import com.timesplit.controlador.Auth_Controller;
import com.timesplit.controlador.BD_Controller;
import com.timesplit.controlador.Perfil_Controller;
import com.timesplit.modelo.Contacto;
import com.timesplit.modelo.Perfil;

import java.util.List;


public class PerfilesActivity extends AppCompatActivity {

    private Button iconButton_Back, iconButton_HomeMenu;
    private FloatingActionButton button_add;
    private RecyclerView recyclerView_perfiles;
    private com.timesplit.vista.RecyclerView_Adapter recyclerView_adapter;
    private List<Perfil> listaPerfiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_perfiles);
        BD_Controller db = new BD_Controller(PerfilesActivity.this);

        //Prepara recyclerView
        recyclerView_perfiles = findViewById(R.id.recyclerView_perfiles);

        //Prepara listaContactos
        listaPerfiles = Perfil_Controller.listaPerfiles(Auth_Controller.userLog.getId_usuario(), db.getReadableDatabase());

        //Actualiza el layout del recyclerView cada vez que se actualiza la lista de contactos
        recyclerView_perfiles.setHasFixedSize(true);

        //Establece el layout de la lista
        recyclerView_perfiles.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView_perfiles.setVerticalScrollBarEnabled(true);

        // Si el usuario tiene perfiles creados los muestra
        if (listaPerfiles != null){
            //Prepara adapter
            recyclerView_adapter = new com.timesplit.vista.RecyclerView_Adapter(listaPerfiles, PerfilesActivity.this);

            //Asigna adapter al recyclerView
            recyclerView_perfiles.setAdapter(recyclerView_adapter);
        }

        //Nuevo Perfil
        button_add = findViewById(R.id.floatingActionButton_Add);
        button_add.setOnClickListener(a -> {
            // TODO -> INTENT NuevoPerfil
        });

        //Back
        iconButton_Back = findViewById(R.id.iconButton_Back);
        iconButton_Back.setOnClickListener(h -> {
            finish();
        });

        // Ajustes
        iconButton_HomeMenu = findViewById(R.id.iconButton_HomeMenu);
        iconButton_HomeMenu.setOnClickListener(h -> {
            Intent intent = new Intent(PerfilesActivity.this, com.timesplit.vista.MenuActivity.class);
            startActivity(intent);
        });
    }
}