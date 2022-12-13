package com.timesplit.Controlador;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.timesplit.Modelo.OnRecyclerViewItemClickListener;
import com.timesplit.R;
import com.timesplit.Modelo.Login;
import com.timesplit.Modelo.BD;
import com.timesplit.Modelo.Perfil;
import com.timesplit.Modelo.Temporizador;

import java.util.List;


public class PerfilesActivity extends AppCompatActivity implements OnRecyclerViewItemClickListener {

    private Button iconButton_Back, iconButton_HomeMenu;
    private FloatingActionButton button_add;
    private RecyclerView recyclerView_perfiles;
    private com.timesplit.Controlador.RecyclerView_Adapter recyclerView_adapter;
    private List<Perfil> listaPerfiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_perfiles);
        BD db = new BD(PerfilesActivity.this);

        //Prepara recyclerView
        recyclerView_perfiles = findViewById(R.id.recyclerView_perfiles);

        //Prepara listaContactos
        listaPerfiles = Perfil.listaPerfiles(Login.userLog.getId_usuario(), db.getReadableDatabase());

        //Actualiza el layout del recyclerView cada vez que se actualiza la lista de contactos
        recyclerView_perfiles.setHasFixedSize(true);

        //Establece el layout de la lista
        recyclerView_perfiles.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView_perfiles.setVerticalScrollBarEnabled(true);

        // Si el usuario tiene perfiles creados los muestra
        if (listaPerfiles != null){
            //Prepara adapter
            recyclerView_adapter = new com.timesplit.Controlador.RecyclerView_Adapter(listaPerfiles, PerfilesActivity.this);

            //Asigna adapter al recyclerView
            recyclerView_perfiles.setAdapter(recyclerView_adapter);

            //Crea listener para detectar elemento pulsado
            recyclerView_adapter.setOnItemClickListener((OnRecyclerViewItemClickListener) this);

        }

        //Nuevo Perfil
        button_add = findViewById(R.id.floatingActionButton_Add);
        button_add.setOnClickListener(a -> {
            Intent intent = new Intent(PerfilesActivity.this, com.timesplit.Controlador.NewPerfilActivity.class);
            startActivity(intent);
        });

        //Back
        iconButton_Back = findViewById(R.id.iconButton_Back);
        iconButton_Back.setOnClickListener(h -> {
            Intent intent = new Intent(PerfilesActivity.this, com.timesplit.Controlador.MainActivity.class);
            startActivity(intent);
        });

        // Ajustes
        iconButton_HomeMenu = findViewById(R.id.iconButton_HomeMenu);
        iconButton_HomeMenu.setOnClickListener(h -> {
            Intent intent = new Intent(PerfilesActivity.this, com.timesplit.Controlador.MenuActivity.class);
            startActivity(intent);
        });
    }

    //Lanza un temporizador con los parametros del perfil seleccionado
    @Override
    public void onRecyclerViewItemClicked(int position, int id) {
        if(id==-1) {
            Perfil perfil = listaPerfiles.get(position);
            Temporizador temporizador = new Temporizador(perfil.getTiempo_trabajo(), perfil.getTiempo_descanso(), perfil.getTiempo_preparacion(), perfil.getRondas(), perfil.getId_perfil());
            //Crea intent para lanzar la activity con el objeto Temporizador
            Intent intent = new Intent(PerfilesActivity.this, com.timesplit.Controlador.TimerActivity.class);
            //Envia un objeto Temporizador a la nueva activity
            intent.putExtra("Temporizador", temporizador);
            //Abre activity
            startActivity(intent);
        }
    }
}