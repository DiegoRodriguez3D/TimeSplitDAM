package com.timesplit.Controlador;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.timesplit.R;
import com.timesplit.Modelo.Login;
import com.timesplit.Modelo.BD;
import com.timesplit.Modelo.Usuario;

public class LoginActivity extends AppCompatActivity {

    private Button iconButton_Home, iconButton_Back, button_Login, textButton_Registrarme;
    private TextInputEditText EditText_email, EditText_pass;
    private String email, pass;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //BD
        BD db = new BD(LoginActivity.this);

        //DATABINDING
        EditText_email = findViewById(R.id.EditText_email);
        EditText_pass = findViewById(R.id.EditText_pass);

        //Login
        button_Login = findViewById(R.id.filledButton_Login);
        button_Login.setOnClickListener(l -> {
            //Comprueba que los campos no esten vacios
            if(!EditText_email.getText().toString().isEmpty() && !EditText_pass.getText().toString().isEmpty()){
                email = EditText_email.getText().toString();
                pass = EditText_pass.getText().toString();
                //Comprueba que el email tenga formato valido
                if( Login.isEmailValido(email)){
                    //Comprueba que el email exista en la BD y que las contraseñas coincidan
                    Usuario userLog = Usuario.selectUsuarioByMail(email, db.getReadableDatabase());
                    if (userLog.getId_usuario()!=0 && Login.isPassValida(userLog.getPassword(), pass)) {
                        //Si el login es correcto, lo guarda en SharedPreferences
                        sp = getSharedPreferences(Login.Login, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString(Login.Nombre, userLog.getNombre());
                        editor.putString(Login.Apellidos, userLog.getApellidos());
                        editor.putString(Login.Email, userLog.getEmail());
                        editor.putInt(Login.ID, userLog.getId_usuario());
                        editor.putBoolean(Login.userLoged, true);
                        editor.commit();

                        //Redirige al usuario a Home
                        Intent intent = new Intent(LoginActivity.this, com.timesplit.Controlador.MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "El email o la contraseña introducidos no son correctos.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Introduzca un email válido.", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Debe de rellenar todos los campos.", Toast.LENGTH_SHORT).show();
            }
        });

        //Registrarme
        textButton_Registrarme = findViewById(R.id.textButton_LoginRegistrarme);
        textButton_Registrarme.setOnClickListener(r -> {
                Intent intent = new Intent(LoginActivity.this, com.timesplit.Controlador.RegisterActivity.class);
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
            Intent intent = new Intent(LoginActivity.this, com.timesplit.Controlador.MainActivity.class);
            startActivity(intent);
        });
    }
}