package com.timesplit.Controlador;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.timesplit.R;
import com.timesplit.Modelo.Login;
import com.timesplit.Modelo.BD;
import com.timesplit.Modelo.Estadisticas;
import com.timesplit.Modelo.Usuario;

public class RegisterActivity extends AppCompatActivity {
private Button iconButton_Back, iconButton_Home, filledButton_Registrarme;
private TextInputEditText EditText_Register_Email, EditText_Register_Pass, EditText_Register_RepeatPass, EditText_Register_Nombre, EditText_Register_Apellidos;
private String email, pass, newpass, nombre, apellidos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //BD
        BD db = new BD(RegisterActivity.this);

        //DATABINDING
        EditText_Register_Email = findViewById(R.id.EditText_Register_Email);
        EditText_Register_Pass = findViewById(R.id.EditText_Register_Pass);
        EditText_Register_RepeatPass = findViewById(R.id.EditText_Register_RepeatPass);
        EditText_Register_Nombre = findViewById(R.id.EditText_Register_Nombre);
        EditText_Register_Apellidos = findViewById(R.id.EditText_Register_Apellidos);


        //Registrarme
        filledButton_Registrarme = findViewById(R.id.filledButton_Registrarme);
        filledButton_Registrarme.setOnClickListener(r -> {
            //Comprueba que los campos estén rellenados
            if(!EditText_Register_Email.getText().toString().isEmpty() && !EditText_Register_Pass.getText().toString().isEmpty() && !EditText_Register_RepeatPass.getText().toString().isEmpty() && !EditText_Register_Nombre.getText().toString().isEmpty() && !EditText_Register_Apellidos.getText().toString().isEmpty()){
                email = EditText_Register_Email.getText().toString();
                pass = EditText_Register_Pass.getText().toString();
                newpass = EditText_Register_RepeatPass.getText().toString();
                nombre = EditText_Register_Nombre.getText().toString();
                apellidos = EditText_Register_Apellidos.getText().toString();
                //Comprueba que el email es válido
                if(Login.isEmailValido(email)){
                    //Comprueba que el email no existe en BD
                    if(!Usuario.existeUsuarioMail(email, db.getReadableDatabase())){
                        //Comprueba que las contraseñas coinciden
                        if (Login.isPassValida(pass, newpass)) {
                            //Crea un usuario y lo inserta en la BD
                            Usuario user = new Usuario(pass, nombre, apellidos, email);
                            Usuario.insertUsuario(user, db.getWritableDatabase());
                            Usuario userID = Usuario.selectUsuarioByMail(email, db.getReadableDatabase());
                            Estadisticas stats = new Estadisticas(0, 0, 0, 0, userID.getId_usuario());
                            Estadisticas.insertEstadisticaUsuario(stats, db.getWritableDatabase());

                            //Vuelve a Login
                            finish();
                        } else {
                            Toast.makeText(this, "Las contraseñas no coinciden.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this, "El email ya existe.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Introduzca un email válido.", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Debe de rellenar todos los campos.", Toast.LENGTH_SHORT).show();
            }
        });

        //Back
        iconButton_Back = findViewById(R.id.iconButton_Back);
        iconButton_Back.setOnClickListener(h -> {
            finish();
        });

        // Ajustes
        iconButton_Home = findViewById(R.id.iconButton_Home);
        iconButton_Home.setOnClickListener(h -> {
            Intent intent = new Intent(RegisterActivity.this, com.timesplit.Controlador.MainActivity.class);
            startActivity(intent);
        });
    }
}