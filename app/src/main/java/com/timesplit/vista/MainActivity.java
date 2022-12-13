package com.timesplit.vista;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.timesplit.controlador.Auth_Controller;
import com.timesplit.controlador.Perfil_Controller;
import com.timesplit.controlador.Usuario_Controller;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.timesplit.R;
import com.timesplit.controlador.BD_Controller;
import com.timesplit.controlador.Usuario_Controller;
import com.timesplit.modelo.AjustesPerfil;
import com.timesplit.modelo.AjustesUsuario;
import com.timesplit.modelo.Estadisticas;
import com.timesplit.modelo.Perfil;
import com.timesplit.modelo.Temporizador;
import com.timesplit.modelo.Usuario;
import com.timesplit.utilidades.Utilidades;
import com.vicmikhailau.maskededittext.MaskedEditText;

import java.util.List;

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
        BD_Controller db = new BD_Controller(MainActivity.this);

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
        sp = getSharedPreferences(Auth_Controller.Login, Context.MODE_PRIVATE);
        Auth_Controller.userLog.setId_usuario(sp.getInt(Auth_Controller.ID, 0));
        Auth_Controller.userLog.setEmail(sp.getString(Auth_Controller.Email, ""));
        Auth_Controller.userLog.setNombre(sp.getString(Auth_Controller.Nombre, ""));
        Auth_Controller.userLog.setApellidos(sp.getString(Auth_Controller.Apellidos, ""));

        //Muestra el nombre del usuario en la parte superior
        textView_HomeUserName.setText( Auth_Controller.userLog.getNombre());

        //Si no hay usuario logeado, oculta el boton Perfiles
        if(Auth_Controller.userLog.getId_usuario()==0){
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
                    Intent intent = new Intent(MainActivity.this, com.timesplit.vista.TimerActivity.class);
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
            Intent intent = new Intent(MainActivity.this, com.timesplit.vista.PerfilesActivity.class);
            startActivity(intent);
        });

        //Ajustes
        iconButton_HomeMenu.setOnClickListener(h -> {
            Intent intent = new Intent(MainActivity.this, com.timesplit.vista.MenuActivity.class);
            startActivity(intent);
        });


        //TEST USUARIO
        Usuario testUser = new Usuario();
        testUser.setEmail("test@mail.com");
        testUser.setPassword("Abc1234.");
        testUser.setNombre("Sara");
        testUser.setApellidos("Rial");

        Usuario testUser2 = new Usuario();
        testUser2.setEmail("test2@mail.com");
        testUser2.setPassword("Abc1234.");
        testUser2.setNombre("Diego");
        testUser2.setApellidos("Rodriguez");

        Usuario_Controller.insertUsuario(testUser, db.getWritableDatabase());
        Usuario_Controller.insertUsuario(testUser2, db.getWritableDatabase());

        Usuario testPerfilUsuario = Usuario_Controller.selectUsuarioByMail("test@mail.com", db.getReadableDatabase());
        // TEST PERFILES
        Perfil testPerfil = new Perfil();
        testPerfil.setNombre_perfil("Estudiar");
        testPerfil.setTiempo_trabajo(60000);
        testPerfil.setTiempo_descanso(30000);
        testPerfil.setTiempo_preparacion(1000);
        testPerfil.setRondas(3);
        testPerfil.setId_usuario(testPerfilUsuario.getId_usuario());

        Perfil testPerfil2 = new Perfil();
        testPerfil2.setNombre_perfil("Boxear");
        testPerfil2.setTiempo_trabajo(60000);
        testPerfil2.setTiempo_descanso(30000);
        testPerfil2.setTiempo_preparacion(1000);
        testPerfil2.setRondas(3);
        testPerfil2.setId_usuario(testPerfilUsuario.getId_usuario());

        Perfil_Controller.insertPerfil(testPerfil, db.getWritableDatabase());
        Perfil_Controller.insertPerfil(testPerfil2, db.getWritableDatabase());


        // TEST AJUSTES PERFILES
        AjustesPerfil a_testPerfil = new AjustesPerfil("#6B23DC", "#94DC23", "#E5B41A", 1, 1);
        Perfil_Controller.insertAjustesPerfil(a_testPerfil, db.getWritableDatabase());

        // TEST AJUSTES USUARIO
        AjustesUsuario a_testUsuario = new AjustesUsuario(0, 1, 100, 1);
        Usuario_Controller.insertAjustesUsuario(a_testUsuario, db.getWritableDatabase());

        //TEST ESTADISTICAS
        Estadisticas estadistica = new Estadisticas(20, 100000000, 1000001, 1000, 1);
        Usuario_Controller.insertEstadisticaUsuario(estadistica, db.getWritableDatabase());


    }

    //Vacía todos los campos
    private void vaciarCampos() {
        EditText_trabajo.setText("");
        EditText_descanso.setText("");
        EditText_preparacion.setText("");
        EditText_rondas.setText("");
    }

}