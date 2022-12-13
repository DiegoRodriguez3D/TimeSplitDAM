package com.timesplit.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.mbms.StreamingServiceInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.timesplit.R;
import com.timesplit.controlador.Perfil_Controller;
import com.timesplit.modelo.AjustesPerfil;

public class AjustesNewPerfilActivity extends AppCompatActivity {

    private Button iconButton_Back, iconButton_Home, filledButton_Guardar2;
    private AutoCompleteTextView AutoCompleteTextView_Dropdown_ColorTrabajo,AutoCompleteTextView_Dropdown_ColorDescanso, AutoCompleteTextView_Dropdown_ColorPreparacion, AutoCompleteTextView_Dropdown_Sonido;

    private String colorTrabajo, colorDescanso, colorPreparacion, sonido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes_new_perfil);

        //DATABINDING
        AutoCompleteTextView_Dropdown_ColorTrabajo = findViewById(R.id.AutoCompleteTextView_Dropdown_ColorTrabajo);
        AutoCompleteTextView_Dropdown_ColorDescanso = findViewById(R.id.AutoCompleteTextView_Dropdown_ColorDescanso);
        AutoCompleteTextView_Dropdown_ColorPreparacion = findViewById(R.id.AutoCompleteTextView_Dropdown_ColorPreparacion);
        AutoCompleteTextView_Dropdown_Sonido = findViewById(R.id.AutoCompleteTextView_Dropdown_Sonido);

        //Array de colores
        String[] colorItems = new String[]{
                "Rojo", //0
                "Azul", //1
                "Verde", //2
                "Amarillo", //3
                "Naranja",  //4
                "Morado" //5
        };

        String[] colorValores = new String[]{
                "#E04E40", //Rojo
                "#4DE9FF", //Azul
                "#62E62E", //Verde
                "#FCD160", //Amarillo
                "#FF9343",  //Naranja
                "#9351F5" //Morado
        };

        String[] sonidoItems = new String[]{
                "Relax", //0
                "Tono", //1
                "Boxeo", //2
        };

        ArrayAdapter<String> adapterColores = new ArrayAdapter<>(
                AjustesNewPerfilActivity.this,
                R.layout.dropdown_item,
                colorItems
        );

        ArrayAdapter<String> adapterSonidos = new ArrayAdapter<>(
                AjustesNewPerfilActivity.this,
                R.layout.dropdown_item,
                sonidoItems
        );

        AutoCompleteTextView_Dropdown_ColorTrabajo.setAdapter(adapterColores);
        AutoCompleteTextView_Dropdown_ColorDescanso.setAdapter(adapterColores);
        AutoCompleteTextView_Dropdown_ColorPreparacion.setAdapter(adapterColores);
        AutoCompleteTextView_Dropdown_Sonido.setAdapter(adapterSonidos);


        //Guardar
        filledButton_Guardar2 = findViewById(R.id.filledButton_Guardar2);
        filledButton_Guardar2.setOnClickListener(h -> {

            //Comprueba que todos los campos han sido rellenados
            if(!AutoCompleteTextView_Dropdown_ColorTrabajo.getText().toString().isEmpty() && !AutoCompleteTextView_Dropdown_ColorDescanso.getText().toString().isEmpty() && !AutoCompleteTextView_Dropdown_ColorPreparacion.getText().toString().isEmpty() && !AutoCompleteTextView_Dropdown_Sonido.getText().toString().isEmpty()){
                colorTrabajo = AutoCompleteTextView_Dropdown_ColorTrabajo.getText().toString();
                colorDescanso = AutoCompleteTextView_Dropdown_ColorDescanso.getText().toString();
                colorPreparacion = AutoCompleteTextView_Dropdown_ColorPreparacion.getText().toString();
                sonido = AutoCompleteTextView_Dropdown_Sonido.getText().toString();

                AjustesPerfil a_perfil = new AjustesPerfil();
                a_perfil.setColor_trabajo(colorValores[Perfil_Controller.valorPosicionArray(colorItems, colorTrabajo)]);
                a_perfil.setColor_descanso(colorValores[Perfil_Controller.valorPosicionArray(colorItems, colorDescanso)]);
                a_perfil.setColor_preparacion(colorValores[Perfil_Controller.valorPosicionArray(colorItems, colorPreparacion)]);
                a_perfil.setSonido(Perfil_Controller.valorPosicionArray(sonidoItems, sonido));
                Intent intent = new Intent(AjustesNewPerfilActivity.this, com.timesplit.vista.NewPerfilActivity.class);
                intent.putExtra("AjustesPerfil", a_perfil);
                startActivity(intent);

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
            Intent intent = new Intent(AjustesNewPerfilActivity.this, com.timesplit.vista.MainActivity.class);
            startActivity(intent);
        });

    }
}