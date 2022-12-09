package com.timesplit.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.timesplit.R;
import com.timesplit.controlador.Auth_Controller;
import com.timesplit.modelo.Usuario;

public class MenuActivity extends AppCompatActivity {

    private LinearLayout TextButton_Layout, TextButton_Layout2, TextButton_Layout3, TextButton_Layout4;
    private Button iconButton_Back, iconButton_HomeMenu, textButton_Usuario, textButton_Estadisticas, textButton_Logout, textButton_Login, iconButton_Home;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Databinding
        textButton_Estadisticas = findViewById(R.id.textButton_Estadisticas);
        TextButton_Layout = findViewById(R.id.TextButton_Layout);
        TextButton_Layout2 = findViewById(R.id.TextButton_Layout2);
        TextButton_Layout3 = findViewById(R.id.TextButton_Layout3);
        TextButton_Layout4 = findViewById(R.id.TextButton_Layout4);

        //Usuario
        textButton_Usuario = findViewById(R.id.textButton_Usuario);
        textButton_Usuario.setOnClickListener(l -> {
            Intent intent = new Intent(MenuActivity.this, com.timesplit.vista.AjustesUsuarioActivity.class);
            startActivity(intent);
        });

        //Login
        textButton_Login = findViewById(R.id.textButton_Login);
        textButton_Login.setOnClickListener(l -> {
            Intent intent = new Intent(MenuActivity.this, com.timesplit.vista.LoginActivity.class);
            startActivity(intent);
        });

        //Logout
        textButton_Logout = findViewById(R.id.textButton_Logout);
        textButton_Logout.setOnClickListener(l -> {
            //Borra el contenido de los SharedPreferences
            sp = getSharedPreferences(Auth_Controller.Login, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.commit();
            //Establece el ID del usuario logeado como 0
            Auth_Controller.userLog.setId_usuario(0);
            //Vuelve a Home
            Intent intent = new Intent(MenuActivity.this, com.timesplit.vista.MainActivity.class);
            startActivity(intent);
        });


        //Back
        iconButton_Back = findViewById(R.id.iconButton_Back);
        iconButton_Back.setOnClickListener(h -> {
           finish();
        });

        //Home
        iconButton_Home = findViewById(R.id.iconButton_Home);
        iconButton_Home.setOnClickListener(h -> {
            Intent intent = new Intent(MenuActivity.this, com.timesplit.vista.MainActivity.class);
            startActivity(intent);
        });


        //Accede a las SharedPreferences para comprobar si hay un usuario logeado.

        if(Auth_Controller.userLog.getId_usuario()!=0){
            TextButton_Layout4.setVisibility(View.VISIBLE);
            TextButton_Layout3.setVisibility(View.INVISIBLE);
            TextButton_Layout2.setVisibility(View.VISIBLE);
            TextButton_Layout.setVisibility(View.VISIBLE);

        }else{
            TextButton_Layout4.setVisibility(View.INVISIBLE);
            TextButton_Layout3.setVisibility(View.VISIBLE);
            TextButton_Layout2.setVisibility(View.INVISIBLE);
            TextButton_Layout.setVisibility(View.INVISIBLE);
        }



    }



}