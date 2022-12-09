package com.timesplit.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.timesplit.R;
import com.timesplit.controlador.Auth_Controller;
import com.timesplit.controlador.BD_Controller;
import com.timesplit.controlador.Usuario_Controller;
import com.timesplit.modelo.Usuario;

public class LoginActivity extends AppCompatActivity {

    private Button iconButton_Home, iconButton_Back, button_Login, textButton_Registrarme;
    private TextInputEditText EditText_email, EditText_pass;
    private String email, pass;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BD_Controller db = new BD_Controller(LoginActivity.this);

        EditText_email = findViewById(R.id.EditText_email);
        EditText_pass = findViewById(R.id.EditText_pass);


        //Login
        button_Login = findViewById(R.id.filledButton_Login);
        button_Login.setOnClickListener(l -> {
            email = EditText_email.getText().toString();
            pass = EditText_pass.getText().toString();
            Usuario userLog = Usuario_Controller.selectUsuarioByMail(email, db.getReadableDatabase());
            if(userLog!=null && userLog.getPassword().equals(pass)){
                //Si el login es correcto, lo guarda en SharedPreferences
                sp = getSharedPreferences(Auth_Controller.Login, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(Auth_Controller.Nombre, userLog.getNombre());
                editor.putString(Auth_Controller.Apellidos, userLog.getApellidos());
                editor.putString(Auth_Controller.Email, userLog.getEmail());
                editor.putInt(Auth_Controller.ID, userLog.getId_usuario());
                editor.putBoolean(Auth_Controller.userLoged, true);
                editor.commit();

                Intent intent = new Intent(LoginActivity.this, com.timesplit.vista.MainActivity.class);
                startActivity(intent);
            }else{
                // TODO: MENSAJE DE ERROR AL INICIAR SESION
            }

        });

        //Registrarme
        textButton_Registrarme = findViewById(R.id.textButton_LoginRegistrarme);
        textButton_Registrarme.setOnClickListener(r -> {
                Intent intent = new Intent(LoginActivity.this, com.timesplit.vista.RegisterActivity.class);
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
            Intent intent = new Intent(LoginActivity.this, com.timesplit.vista.MainActivity.class);
            startActivity(intent);
        });


    }
}