package com.timesplit.vista;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.timesplit.R;
import com.timesplit.modelo.Contacto;
import com.timesplit.modelo.Perfil;

import java.util.List;


public class PerfilesActivity extends AppCompatActivity {

    private Button button_volver;
    private FloatingActionButton button_add;
    private RecyclerView recyclerView_perfiles;
    private com.timesplit.vista.RecyclerView_Adapter recyclerView_adapter;
    private List<Perfil> listaPerfiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_perfiles);
        com.timesplit.controlador.BD_Controller db = new com.timesplit.controlador.BD_Controller(PerfilesActivity.this);

        //Prepara recyclerView
        recyclerView_perfiles = findViewById(R.id.recyclerView_perfiles);

        //Prepara listaContactos
//        listaPerfiles = db.devuelveListaPerfiles();
        listaPerfiles = null;

        //Actualiza el layout del recyclerView cada vez que se actualiza la lista de contactos
        recyclerView_perfiles.setHasFixedSize(true);
        recyclerView_perfiles.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_perfiles.setVerticalScrollBarEnabled(true);

        // Si el usuario tiene perfiles creados los muestra
        if (listaPerfiles != null){
            //Prepara adapter
            recyclerView_adapter = new com.timesplit.vista.RecyclerView_Adapter(listaPerfiles, PerfilesActivity.this);

            //Asigna adapter al recyclerView
            recyclerView_perfiles.setAdapter(recyclerView_adapter);
        }


        // ADD
        button_add = findViewById(R.id.floatingActionButton_Add);
        button_add.setOnClickListener(a -> {
//            addPerfil();
        });

        //VOLVER
//        button_volver = findViewById(R.id.button_volver);
//        button_volver.setOnClickListener(v -> {
//            finish();
//        });
    }

}