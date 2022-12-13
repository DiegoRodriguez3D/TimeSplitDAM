package com.timesplit.Controlador;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.timesplit.R;
import com.timesplit.Modelo.Login;
import com.timesplit.Modelo.BD;
import com.timesplit.Modelo.Estadisticas;
import com.timesplit.Utilidades.Utilidades;
import com.vicmikhailau.maskededittext.MaskedEditText;

public class StatsActivity extends AppCompatActivity {
private Button iconButton_Back, iconButton_Home;
private MaskedEditText EditText_Stats_Perfiles, EditText_Stats_trabajo, EditText_Stats_Descanso, EditText_Stats_Rondas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        BD db = new BD(StatsActivity.this);

        //DATABINDING
        EditText_Stats_Perfiles = findViewById(R.id.EditText_Stats_Perfiles);
        EditText_Stats_trabajo = findViewById(R.id.EditText_Stats_trabajo);
        EditText_Stats_Descanso = findViewById(R.id.EditText_Stats_Descanso);
        EditText_Stats_Rondas = findViewById(R.id.EditText_Stats_Rondas);

        //Recupera datos de BD y los muestra
        Estadisticas stats = Estadisticas.selectEstadisticasUsuario(Login.userLog.getId_usuario() ,db.getReadableDatabase());
        if (stats != null){
            EditText_Stats_Perfiles.setText(stats.getNumero_perfiles()+"");
            EditText_Stats_trabajo.setText(Utilidades.toHHMMSS(stats.getTotal_trabajo())+"");
            EditText_Stats_Descanso.setText(Utilidades.toHHMMSS(stats.getTotal_descanso())+"");
            EditText_Stats_Rondas.setText(stats.getTotal_rondas()+"");
        }

        //Back
        iconButton_Back = findViewById(R.id.iconButton_Back);
        iconButton_Back.setOnClickListener(h -> {
            finish();
        });

        // Home
        iconButton_Home = findViewById(R.id.iconButton_Home);
        iconButton_Home.setOnClickListener(h -> {
            Intent intent = new Intent(StatsActivity.this, com.timesplit.Controlador.MainActivity.class);
            startActivity(intent);
        });
    }
}