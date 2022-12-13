package com.timesplit.vista;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;
import com.timesplit.R;
import com.timesplit.controlador.Auth_Controller;
import com.timesplit.controlador.BD_Controller;
import com.timesplit.controlador.Usuario_Controller;
import com.timesplit.modelo.AjustesUsuario;
import com.timesplit.modelo.Usuario;

public class AjustesUsuarioActivity extends AppCompatActivity {

    private Button textButton_Pass, iconButton_Back, iconButton_Home, filledButton_Guardar;
    private AutoCompleteTextView AutoCompleteTextView_Dropdown;
    private TextInputEditText EditText_Opciones_Nombre, EditText_Opciones_Apellidos, EditText_Opciones_Email;
    private Slider Slider_Usuario_Volumen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes_usuario);

        //DABABINDING
        AutoCompleteTextView_Dropdown = findViewById(R.id.AutoCompleteTextView_Dropdown);
        EditText_Opciones_Nombre = findViewById(R.id.EditText_Opciones_Nombre);
        EditText_Opciones_Apellidos = findViewById(R.id.EditText_Opciones_Apellidos);
        EditText_Opciones_Email = findViewById(R.id.EditText_Opciones_Email);
        Slider_Usuario_Volumen = findViewById(R.id.Slider_Usuario_Volumen);

        //Conecta a la BD
        BD_Controller db = new BD_Controller(AjustesUsuarioActivity.this);

        //DESPLEGABLE TEMAS
        //Crea un Array con los temas que se muestran
        String[] temaItems = new String[]{
                "Defecto",
                "Claro",
                "Oscuro"
        };

        //Muestra el Array en el desplegable
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                AjustesUsuarioActivity.this,
                R.layout.dropdown_item,
                temaItems
        );
        AutoCompleteTextView_Dropdown.setAdapter(adapter);

        //RECUPERA DATOS DE BD
        Usuario user = Usuario_Controller.selectUsuarioByMail(Auth_Controller.userLog.getEmail(), db.getReadableDatabase());
        AjustesUsuario a_user = Usuario_Controller.selectAjustesUsuarioByID(Auth_Controller.userLog.getId_usuario(), db.getReadableDatabase());

        //Si existen datos, los muestra en los input
        if(user.getId_usuario()!=0){
            EditText_Opciones_Nombre.setText(user.getNombre());
            EditText_Opciones_Apellidos.setText(user.getApellidos());
            EditText_Opciones_Email.setText(user.getEmail());
        }

        if(a_user.getId_ajustes() != 0){
            Slider_Usuario_Volumen.setValue((float) a_user.getVolumen());
            AutoCompleteTextView_Dropdown.setText(temaItems[a_user.getTema()], false);
            if (a_user.getVolumen() == 0){
                //TODO -> Mutea la aplicacion en el timer desde el mediaplayer
            }
        }else{
            //Si no existen ajustes, pone valores por defecto
            AutoCompleteTextView_Dropdown.setText(temaItems[0], false);
            Slider_Usuario_Volumen.setValue(100);
        }


        //Guardar
        filledButton_Guardar = findViewById(R.id.filledButton_Guardar);
        filledButton_Guardar.setOnClickListener(r -> {

            Usuario updateUser = user;

            //Si los campos no están vacios, y son distintos que los datos ya existentes, actualiza el usuario
            if (!EditText_Opciones_Nombre.getText().toString().isEmpty() && !EditText_Opciones_Apellidos.getText().toString().isEmpty() && !EditText_Opciones_Email.getText().toString().isEmpty()){
                if(!EditText_Opciones_Nombre.getText().toString().equals(user.getNombre())){
                    updateUser.setNombre(EditText_Opciones_Nombre.getText().toString());
                }

                if(!EditText_Opciones_Apellidos.getText().toString().equals(user.getApellidos()))
                    updateUser.setApellidos(EditText_Opciones_Apellidos.getText().toString());

                if(!EditText_Opciones_Email.getText().toString().equals(user.getEmail())){
                    if(Usuario_Controller.existeUsuarioMail(EditText_Opciones_Email.getText().toString(), db.getReadableDatabase())){
                        Toast.makeText(this, "El email ya existe.", Toast.LENGTH_SHORT).show();
                    }else{
                        updateUser.setEmail(EditText_Opciones_Email.getText().toString());
                    }
                }
                Usuario_Controller.updateUsuario(updateUser, db.getWritableDatabase());
            }

            //Si no recupera ajustes de la BD, los crea. De lo contrario, los actualiza
            if(a_user.getId_usuario() == 0){
               AjustesUsuario newA_User = new AjustesUsuario();

                //Cambia el tema a Defecto (Claro/Oscuro dependiendo del SO)
                if (AutoCompleteTextView_Dropdown.getText().toString().equals("Defecto")){
                    newA_User.setTema(0);
                    Usuario_Controller.cambiaTema(0);
                }

                //Cambia el tema a Claro
                if (AutoCompleteTextView_Dropdown.getText().toString().equals("Claro")) {
                    newA_User.setTema(1);
                    Usuario_Controller.cambiaTema(1);
                }

                //Cambia el tema a Oscuro
                if (AutoCompleteTextView_Dropdown.getText().toString().equals("Oscuro")) {
                    newA_User.setTema(2);
                    Usuario_Controller.cambiaTema(2);
                }

                //Ajusta el volumen
                newA_User.setVolumen((int) Slider_Usuario_Volumen.getValue());
                if(newA_User.getVolumen() != 0){
                    newA_User.setSonido(1);
                }else{
                    newA_User.setSonido(0);
                }

                //Vincula los ajustes al usuario logeado
                newA_User.setId_usuario(user.getId_usuario());

                //Inserta los ajustes en BD
                Usuario_Controller.insertAjustesUsuario(newA_User, db.getWritableDatabase());

            }else{
                Usuario_Controller.updateAjustesUsuario(a_user, db.getWritableDatabase());

                //Cambia el tema a Defecto (Claro/Oscuro dependiendo del SO)
                if (AutoCompleteTextView_Dropdown.getText().toString().equals("Defecto")){
                    a_user.setTema(0);
                    Usuario_Controller.cambiaTema(0);
                }

                //Cambia el tema a Claro
                if (AutoCompleteTextView_Dropdown.getText().toString().equals("Claro")) {
                    a_user.setTema(1);
                    Usuario_Controller.cambiaTema(1);
                }

                //Cambia el tema a Oscuro
                if (AutoCompleteTextView_Dropdown.getText().toString().equals("Oscuro")) {
                    a_user.setTema(2);
                    Usuario_Controller.cambiaTema(2);
                }

                if(!Slider_Usuario_Volumen.equals(a_user.getVolumen()))
                    a_user.setVolumen((int) Slider_Usuario_Volumen.getValue());

                if(a_user.getVolumen() != 0){
                    a_user.setSonido(1);
                }else{
                    a_user.setSonido(0);
                }

                Usuario_Controller.updateAjustesUsuario(a_user, db.getWritableDatabase());
            }

            //Vuelve a Home
            Intent intent = new Intent(AjustesUsuarioActivity.this, com.timesplit.vista.MainActivity.class);
            startActivity(intent);
        });

        //Cambiar Pass
        textButton_Pass = findViewById(R.id.textButton_OpcionesUsuario_Pass);
        textButton_Pass.setOnClickListener(r -> {
            Intent intent = new Intent(AjustesUsuarioActivity.this, com.timesplit.vista.PassActivity.class);
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
            Intent intent = new Intent(AjustesUsuarioActivity.this, com.timesplit.vista.MainActivity.class);
            startActivity(intent);
        });
    }
}