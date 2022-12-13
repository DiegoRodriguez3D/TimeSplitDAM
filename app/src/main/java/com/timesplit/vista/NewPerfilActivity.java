package com.timesplit.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.timesplit.R;
import com.timesplit.controlador.Auth_Controller;
import com.timesplit.controlador.BD_Controller;
import com.timesplit.controlador.Perfil_Controller;
import com.timesplit.controlador.Usuario_Controller;
import com.timesplit.modelo.AjustesPerfil;
import com.timesplit.modelo.Perfil;
import com.timesplit.modelo.Usuario;
import com.timesplit.utilidades.Utilidades;
import com.vicmikhailau.maskededittext.MaskedEditText;

import java.util.Locale;

public class NewPerfilActivity extends AppCompatActivity {

    private Button textButton_NewPerfil_Avanzado, iconButton_Back, iconButton_Home, filledButton_GuardarPerfil;
    private TextInputEditText EditText_NewPerfil_Nombre;
    private MaskedEditText EditText_NewPerfil_trabajo, EditText_NewPerfil_descanso, EditText_NewPerfil_preparacion, EditText_NewPerfil_rondas;
    private int tiempoTrabajo, tiempoDescanso, tiempoPreparacion, rondas;
    private String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_perfil);
        BD_Controller db = new BD_Controller(NewPerfilActivity.this);

        //DATABINDING
        EditText_NewPerfil_Nombre = findViewById(R.id.EditText_NewPerfil_Nombre);
        EditText_NewPerfil_trabajo = findViewById(R.id.EditText_NewPerfil_trabajo);
        EditText_NewPerfil_descanso = findViewById(R.id.EditText_NewPerfil_descanso);
        EditText_NewPerfil_preparacion = findViewById(R.id.EditText_NewPerfil_preparacion);
        EditText_NewPerfil_rondas = findViewById(R.id.EditText_NewPerfil_rondas);

        Intent intentAjustes = getIntent();

        //Accede a los datos del usuario
        Usuario user = Usuario_Controller.selectUsuarioByMail(Auth_Controller.userLog.getEmail(), db.getReadableDatabase());

        //Guardar
        filledButton_GuardarPerfil = findViewById(R.id.filledButton_GuardarPerfil);
        filledButton_GuardarPerfil.setOnClickListener(g -> {

            //Comprueba que los campos no están vacios
            if(!EditText_NewPerfil_Nombre.getText().toString().isEmpty() && !EditText_NewPerfil_trabajo.getText().toString().isEmpty() && !EditText_NewPerfil_descanso.getText().toString().isEmpty()
            && !EditText_NewPerfil_preparacion.getText().toString().isEmpty() && !EditText_NewPerfil_rondas.getText().toString().isEmpty()){

                //Comprueba que los input de tiempo se han cubierto correctamente
                if(EditText_NewPerfil_trabajo.getText().toString().length() == 5 && EditText_NewPerfil_descanso.getText().toString().length() == 5
                        && EditText_NewPerfil_preparacion.getText().toString().length() == 5){
                    nombre = EditText_NewPerfil_Nombre.getText().toString().toUpperCase(Locale.ROOT);
                    tiempoTrabajo = (int) Utilidades.inputMMSS(EditText_NewPerfil_trabajo.getText().toString());
                    tiempoDescanso = (int) Utilidades.inputMMSS(EditText_NewPerfil_descanso.getText().toString());
                    tiempoPreparacion = (int) Utilidades.inputMMSS(EditText_NewPerfil_preparacion.getText().toString());
                    rondas = Integer.parseInt(EditText_NewPerfil_rondas.getText().toString());


                    //Comprueba que no exista un perfil con el nombre introducido para el usuario logeado
                    if(!Perfil_Controller.existePerfilByNombre(nombre, user.getId_usuario(), db.getReadableDatabase())){
                        Perfil perfil = new Perfil(nombre, tiempoTrabajo, tiempoDescanso, tiempoPreparacion, rondas, user.getId_usuario());
                        Perfil_Controller.insertPerfil(perfil, db.getWritableDatabase());

                        //Si recupera AjustesPerfil a traves de intent, crea objeto
                        if(intentAjustes.getSerializableExtra("AjustesPerfil") != null){
                            AjustesPerfil a_perfil = (AjustesPerfil) intentAjustes.getSerializableExtra("AjustesPerfil");

                            //Recupera el id de perfil e inserta los ajustes
                            Perfil p = Perfil_Controller.selectPerfilByNombre(nombre, db.getReadableDatabase());
                            a_perfil.setId_perfil(p.getId_perfil());
                            Perfil_Controller.insertAjustesPerfil(a_perfil, db.getWritableDatabase());
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "El nombre del perfil ya existe." ,Toast.LENGTH_LONG).show();
                    }

                    Intent intent = new Intent(NewPerfilActivity.this, com.timesplit.vista.PerfilesActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "Introduzca el tiempo en formato mm:ss" ,Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "Debe rellenar todos los campos." ,Toast.LENGTH_LONG).show();
            }
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