package com.timesplit.vista;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.timesplit.R;
import com.timesplit.modelo.Contacto;

import java.util.List;


public class PerfilesActivity extends AppCompatActivity {

    private Button button_volver;
    private RecyclerView recyclerView_contactos;
    private com.timesplit.vista.RecyclerView_Adapter recyclerView_adapter;
    private List<Contacto> listaContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_perfiles);
        com.timesplit.controlador.BD_Controller db = new com.timesplit.controlador.BD_Controller(PerfilesActivity.this);

        //Prepara recyclerView
        recyclerView_contactos = findViewById(R.id.recyclerView_contactos);

        //Prepara listaContactos
        listaContactos = db.devuelveListaContactos();

        //Actualiza el layout del recyclerView cada vez que se actualiza la lista de contactos
        recyclerView_contactos.setHasFixedSize(true);
        recyclerView_contactos.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_contactos.setVerticalScrollBarEnabled(true);

        //Prepara adapter
        recyclerView_adapter = new com.timesplit.vista.RecyclerView_Adapter(listaContactos, PerfilesActivity.this);

        //Asigna adapter al recyclerView
        recyclerView_contactos.setAdapter(recyclerView_adapter);

        //VOLVER
        button_volver = findViewById(R.id.button_volver);
        button_volver.setOnClickListener(v -> {
            finish();
        });
    }

}