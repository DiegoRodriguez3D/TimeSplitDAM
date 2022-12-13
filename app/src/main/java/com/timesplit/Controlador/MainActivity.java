package com.timesplit.Controlador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.timesplit.Modelo.Login;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.timesplit.R;
import com.timesplit.Modelo.BD;
import com.timesplit.Modelo.AjustesPerfil;
import com.timesplit.Modelo.AjustesUsuario;
import com.timesplit.Modelo.Estadisticas;
import com.timesplit.Modelo.Perfil;
import com.timesplit.Modelo.Temporizador;
import com.timesplit.Modelo.Usuario;
import com.timesplit.Utilidades.Utilidades;
import com.vicmikhailau.maskededittext.MaskedEditText;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button iconButton_PlayQuickStart, button_perfiles, iconButton_HomeMenu;
    private MaskedEditText EditText_rondas, EditText_trabajo, EditText_descanso, EditText_preparacion;
    private TextView textView_HomeUserName;
    private SharedPreferences sp;
    private int tiempoTrabajo, tiempoDescanso, tiempoPreparacion, rondas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BD db = new BD(MainActivity.this);

        //Databinding
        button_perfiles = findViewById(R.id.filledButton_Perfiles);
        iconButton_HomeMenu = findViewById(R.id.iconButton_HomeMenu);
        iconButton_PlayQuickStart = findViewById(R.id.iconButton_PlayQuickStart);
        EditText_trabajo = findViewById(R.id.EditText_trabajo);
        EditText_descanso = findViewById(R.id.EditText_descanso);
        EditText_preparacion = findViewById(R.id.EditText_preparacion);
        EditText_rondas = findViewById(R.id.EditText_rondas);
        textView_HomeUserName = findViewById(R.id.textView_HomeUserName);

        //Accede a las SharedPreferences para recuperar la informacion del usuario logeado, si no existe se reinicia
        sp = getSharedPreferences(Login.Login, Context.MODE_PRIVATE);
        Login.userLog.setId_usuario(sp.getInt(Login.ID, 0));
        Login.userLog.setEmail(sp.getString(Login.Email, ""));
        Login.userLog.setNombre(sp.getString(Login.Nombre, ""));
        Login.userLog.setApellidos(sp.getString(Login.Apellidos, ""));

        //Muestra el nombre del usuario en la parte superior
        textView_HomeUserName.setText( Login.userLog.getNombre());

        //Si no hay usuario logeado, oculta el boton Perfiles
        if(Login.userLog.getId_usuario()==0){
            button_perfiles.setVisibility(View.INVISIBLE);
        }else{
            button_perfiles.setVisibility(View.VISIBLE);
        }

        //QuickStart Play
        iconButton_PlayQuickStart = findViewById(R.id.iconButton_PlayQuickStart);
        iconButton_PlayQuickStart.setOnClickListener(v -> {

            //Comprueba que los campos no están vacios
            if(!EditText_trabajo.getText().toString().isEmpty() && !EditText_descanso.getText().toString().isEmpty()
                    && !EditText_preparacion.getText().toString().isEmpty() && !EditText_rondas.getText().toString().isEmpty()){

                //Comprueba que los input de tiempo se han cubierto correctamente
                if(EditText_trabajo.getText().toString().length() == 5 && EditText_descanso.getText().toString().length() == 5
                        && EditText_preparacion.getText().toString().length() == 5){

                    tiempoTrabajo = (int) Utilidades.inputMMSS(EditText_trabajo.getText().toString());
                    tiempoDescanso = (int) Utilidades.inputMMSS(EditText_descanso.getText().toString());
                    tiempoPreparacion = (int) Utilidades.inputMMSS(EditText_preparacion.getText().toString());
                    rondas = Integer.parseInt(EditText_rondas.getText().toString());

                    Temporizador temporizador = new Temporizador();
                    temporizador.setId_perfil(0);
                    temporizador.setTiempo_trabajo(tiempoTrabajo);
                    temporizador.setTiempo_descanso(tiempoDescanso);
                    temporizador.setTiempo_preparacion(tiempoPreparacion);
                    temporizador.setNumero_rondas(rondas);

                    //Crea intent para lanzar la activity con el objeto Temporizador
                    Intent intent = new Intent(MainActivity.this, com.timesplit.Controlador.TimerActivity.class);
                    //Envia un objeto Temporizador a la nueva activity
                    intent.putExtra("Temporizador", temporizador);
                    //Abre activity
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "Introduzca el tiempo en formato mm:ss" ,Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "Debe rellenar todos los campos." ,Toast.LENGTH_LONG).show();
            }
        });

        //PERFILES
        button_perfiles.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, com.timesplit.Controlador.PerfilesActivity.class);
            startActivity(intent);
        });

        //Ajustes
        iconButton_HomeMenu.setOnClickListener(h -> {
            Intent intent = new Intent(MainActivity.this, com.timesplit.Controlador.MenuActivity.class);
            startActivity(intent);
        });


        // ------ Crea usuario y perfiles de prueba -------
        String mail = "test@mail.com";
        boolean existe = Usuario.existeUsuarioMail(mail, db.getReadableDatabase());
        if(!existe) {
            Usuario testUser = new Usuario();
            testUser.setEmail("test@mail.com");
            testUser.setPassword("12345.");
            testUser.setNombre("Diego");
            testUser.setApellidos("Rodriguez Barcala");

            Usuario.insertUsuario(testUser, db.getWritableDatabase());

            Usuario testPerfilUsuario = Usuario.selectUsuarioByMail("test@mail.com", db.getReadableDatabase());

            Perfil testPerfil = new Perfil("Estudiar".toUpperCase(Locale.ROOT), 1800000, 300000, 60000, 5, testPerfilUsuario.getId_usuario());
            Perfil testPerfil2 = new Perfil("Boxear".toUpperCase(Locale.ROOT), 180000, 60000, 20000, 10, testPerfilUsuario.getId_usuario());
            Perfil testPerfil3 = new Perfil("Correr".toUpperCase(Locale.ROOT), 180000, 30000, 10000, 3, testPerfilUsuario.getId_usuario());
            Perfil testPerfil4 = new Perfil("Trabajar".toUpperCase(Locale.ROOT), 3600000, 300000, 60000, 8, testPerfilUsuario.getId_usuario());
            Perfil testPerfil5 = new Perfil("Test Rápido".toUpperCase(Locale.ROOT), 20000, 10000, 5000, 5, testPerfilUsuario.getId_usuario());


            Perfil.insertPerfil(testPerfil, db.getWritableDatabase());
            Perfil.insertPerfil(testPerfil2, db.getWritableDatabase());
            Perfil.insertPerfil(testPerfil3, db.getWritableDatabase());
            Perfil.insertPerfil(testPerfil4, db.getWritableDatabase());
            Perfil.insertPerfil(testPerfil5, db.getWritableDatabase());

            AjustesPerfil a_testPerfil = new AjustesPerfil("#f82b2b", "#4fff5d", "#ffde4c", 1, 1);
            AjustesPerfil a_testPerfil2 = new AjustesPerfil("#f82b2b", "#4fff5d", "#a232f1", 0, 2);
            AjustesPerfil a_testPerfil3 = new AjustesPerfil("#f82b2b", "#4fff5d", "#323bff", 1, 3);
            AjustesPerfil a_testPerfil4 = new AjustesPerfil("#f82b2b", "#4fff5d", "#a232f1", 0, 4);
            AjustesPerfil a_testPerfil5 = new AjustesPerfil("#f82b2b", "#4fff5d", "#ffde4c", 1, 5);

            AjustesPerfil.insertAjustesPerfil(a_testPerfil, db.getWritableDatabase());
            AjustesPerfil.insertAjustesPerfil(a_testPerfil2, db.getWritableDatabase());
            AjustesPerfil.insertAjustesPerfil(a_testPerfil3, db.getWritableDatabase());
            AjustesPerfil.insertAjustesPerfil(a_testPerfil4, db.getWritableDatabase());
            AjustesPerfil.insertAjustesPerfil(a_testPerfil5, db.getWritableDatabase());

            AjustesUsuario a_testUsuario = new AjustesUsuario(0, 1, 100, 1);
            AjustesUsuario.insertAjustesUsuario(a_testUsuario, db.getWritableDatabase());

            Estadisticas estadistica = new Estadisticas(5, 0, 0, 0, 1);
            Estadisticas.insertEstadisticaUsuario(estadistica, db.getWritableDatabase());
        }
    }
}