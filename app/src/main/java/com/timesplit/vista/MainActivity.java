package com.timesplit.vista;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.timesplit.R;
import com.timesplit.controlador.BD_Controller;

public class MainActivity extends AppCompatActivity {

    private TextView textView_nombre, textView_telefono, textView_email;
    private EditText editText_nombre, editText_telefono, editText_email;
    private Button button_comenzar, button_perfiles;


    public static int estado = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BD_Controller db = new BD_Controller(MainActivity.this);

        //Databinding
        button_perfiles = findViewById(R.id.filledButton_Perfiles);
//        textView_nombre = findViewById(R.id.outlinedTextField_tiempoDescanso);
//        editText_nombre = findViewById(R.id.outlinedTextField_tiempoDescanso);


        //INSERTAR
//        button_insertar.setOnClickListener(v -> {
            //Comprueba que todos los campos estén llenos
//            if(!TextUtils.isEmpty(editText_nombre.getText()) && !TextUtils.isEmpty(editText_telefono.getText()) && !TextUtils.isEmpty(  editText_email.getText())){
//
//                //Crea contacto con los datos introducidos por el usuario
//                com.timesplit.modelo.Contacto contacto = new com.timesplit.modelo.Contacto(
//                        editText_nombre.getText().toString(),
//                        editText_telefono.getText().toString(),
//                        editText_email.getText().toString());
//
//                //Inserta el contacto
//                long resultado = db.insertarContacto(contacto);
//
//                // Si el método insertar devuelve -1, significa que no ha podido realizar la inserción
//                if(resultado== -1){
//                    Toast.makeText(this, R.string.errorNombre, Toast.LENGTH_SHORT).show();
//                }else{
//                    vaciarCampos();
//                    Toast.makeText(getApplicationContext(), R.string.success ,Toast.LENGTH_LONG).show();
//                }
//
//                //Test resultado obtenido
//               // Toast.makeText(getApplicationContext(), resultado+"" ,Toast.LENGTH_LONG).show();
//
//            }else{
//                Toast.makeText(this, R.string.empty, Toast.LENGTH_SHORT).show();
//            }

//        });

        //ELIMINAR
//        button_eliminar.setOnClickListener(v -> {
            //Comprueba que el campo Nombre esté lleno
//            if(!TextUtils.isEmpty(editText_nombre.getText())){
//
//                try{
//                    //Busca el contacto a eliminar
//                    com.timesplit.modelo.Contacto c = db.devuelveContacto(editText_nombre.getText().toString());
//
//                    //Borra el contacto
//                    long resultado = db.borraContacto(c);
//
//                    //Si devuelve -1 significa que no se ha podido eliminar
//                    if(resultado==-1){
//                        Toast.makeText(this, R.string.errorNombre2, Toast.LENGTH_SHORT).show();
//                    }else{
//                        Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show();
//                        vaciarCampos();
//                    }
//
//                    //Toast.makeText(getApplicationContext(), resultado+"" ,Toast.LENGTH_LONG).show();
//
//                    //Controla excepción si se intenta consultar un contacto que no existe
//                }catch(Exception e){
//                    Toast.makeText(MainActivity.this, R.string.errorNombre2, Toast.LENGTH_SHORT).show();
//                }
//
//              }else{
//                Toast.makeText(this, R.string.emptyNombre, Toast.LENGTH_SHORT).show();
//            }
//        });

        //PERFILES
        button_perfiles.setOnClickListener(v -> {
            // Crea intent para lanzar la activity con el listado de perfiles
            Intent intent = new Intent(MainActivity.this, com.timesplit.vista.PerfilesActivity.class);
            //Abre activity
            startActivity(intent);
        });


        //TEST CONTACTOS
        //Lista por consola los contactos almacenados al iniciar la aplicacion
//        List<Contacto> listaContactos = db.devuelveListaContactos();
//
//        for(Contacto contacto: listaContactos){
//            Log.d("MAIN", "Datos devueltos: " + contacto.getNombre() + " - " + contacto.getTelefono() + " - " + contacto.getEmail());
//        }
    }

    //Vacía todos los campos
    private void vaciarCampos() {
        editText_nombre.setText("");
        editText_telefono.setText("");
        editText_email.setText("");
    }

}