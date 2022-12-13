package com.timesplit.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.timesplit.R;
import com.timesplit.controlador.Auth_Controller;
import com.timesplit.controlador.BD_Controller;
import com.timesplit.controlador.Usuario_Controller;
import com.timesplit.modelo.Usuario;

public class PassActivity extends AppCompatActivity {
private Button iconButton_Back, iconButton_Home, filledButton_Pass_Guardar;
private TextInputEditText EditText_Pass_oldPass, EditText_Pass_newPass, EditText_Pass_repeatNewPass;
private String passActual, passNueva, repeatPassNueva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass);

        //BD
        BD_Controller db = new BD_Controller(PassActivity.this);

        //DATABINDING
        EditText_Pass_oldPass = findViewById(R.id.EditText_Pass_oldPass);
        EditText_Pass_newPass = findViewById(R.id.EditText_Pass_newPass);
        EditText_Pass_repeatNewPass = findViewById(R.id.EditText_Pass_repeatNewPass);

        // Accede a la BD para recuperar los datos del usuario logeado.
        Usuario user = Usuario_Controller.selectUsuarioByMail(Auth_Controller.userLog.getEmail(), db.getReadableDatabase());


        filledButton_Pass_Guardar = findViewById(R.id.filledButton_Pass_Guardar);
        filledButton_Pass_Guardar.setOnClickListener(g->{
            // Si el usuario existe, procede a actualizarlo
            if(user != null){
                //Comprueba que todos los input hayan sido rellenados por el usuario y recupera los datos
                if(!EditText_Pass_oldPass.getText().toString().isEmpty() && !EditText_Pass_newPass.getText().toString().isEmpty() && !EditText_Pass_repeatNewPass.getText().toString().isEmpty()){
                    passActual = EditText_Pass_oldPass.getText().toString();
                    passNueva = EditText_Pass_newPass.getText().toString();
                    repeatPassNueva = EditText_Pass_repeatNewPass.getText().toString();
                    //Comprueba que la contraseña actual coincida con la del usuario
                    if(passActual.equals(user.getPassword())){
                        //Comprueba que la contraseña nueva sea distinta que la actual
                        if (!passActual.equals(passNueva)) {
                            //Comprueba que las contraseñas nuevas coincidan
                            if (passNueva.equals(repeatPassNueva)) {
                                //Actualiza la contraseña y vuelve a la pantalla anterior
                                user.setPassword(passNueva);
                                Usuario_Controller.updateUsuario(user, db.getWritableDatabase());
                                finish();
                            } else {
                                Toast.makeText(this, "Las contrañas nuevas no coinciden.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "La nueva contraseña no puede ser la misma que la actual.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this, "La contraseña actual no es correcta.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Debe rellenar todos los campos.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //Back
        iconButton_Back = findViewById(R.id.iconButton_Back);
        iconButton_Back.setOnClickListener(h -> {
            finish();
        });

        // Home
        iconButton_Home = findViewById(R.id.iconButton_Home);
        iconButton_Home.setOnClickListener(h -> {
            Intent intent = new Intent(PassActivity.this, com.timesplit.vista.MainActivity.class);
            startActivity(intent);
        });
    }
}