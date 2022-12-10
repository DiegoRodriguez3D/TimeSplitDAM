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
import com.timesplit.modelo.Perfil;
import com.timesplit.modelo.Usuario;
import com.timesplit.utilidades.Utilidades;
import com.vicmikhailau.maskededittext.MaskedEditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button iconButton_PlayQuickStart, button_perfiles, iconButton_HomeMenu;
    private MaskedEditText EditText_rondas, EditText_trabajo, EditText_descanso, EditText_preparacion;
    private TextView textView_HomeUserName;
    public static Usuario userLog;
    private SharedPreferences sp;

    public static int estado = 0;

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

        iconButton_HomeMenu.setOnClickListener(h -> {
            Intent intent = new Intent(MainActivity.this, com.timesplit.vista.MenuActivity.class);
            startActivity(intent);
        });


        // TODO: --------------- TEST BD --------------------
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

        //Lista por consola los usuarios almacenados al iniciar la aplicacion
        List<Usuario> listaUsuarios = Usuario_Controller.listaUsuarios(db.getReadableDatabase());

        for(Usuario usuario: listaUsuarios){
            Log.d("testBD", "USUARIO ID: " + usuario.getId_usuario() + " - Email: " + usuario.getEmail() + " - Pass: " + usuario.getPassword()
            + " - Nombre: " + usuario.getNombre() + " - Apellidos: " + usuario.getApellidos());
        }


        Usuario testPerfilUsuario = Usuario_Controller.selectUsuarioByMail("test@mail.com", db.getReadableDatabase());
        Log.d("testBD", "USUARIOEMAIL: " + testPerfilUsuario.getEmail() + " - ID: " + testPerfilUsuario.getId_usuario());

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

        //Lista por consola los usuarios almacenados al iniciar la aplicacion
        List<Perfil> listaPerfiles = Perfil_Controller.listaPerfiles(testPerfilUsuario.getId_usuario(), db.getReadableDatabase());

        for(Perfil perfil: listaPerfiles){
            Log.d("testBD", "PERFIL ID: " + perfil.getId_perfil() + " - Nombre: " + perfil.getNombre_perfil() + " - Trabajo: " + perfil.getTiempo_trabajo()
                    + " - Descanso: " + perfil.getTiempo_descanso() + " - Preparacion: " + perfil.getTiempo_preparacion() + " - Rondas: " + perfil.getRondas() + " - Usuario: " + perfil.getId_usuario());
        }

    }

    //Vacía todos los campos
    private void vaciarCampos() {
        EditText_trabajo.setText("");
        EditText_descanso.setText("");
        EditText_preparacion.setText("");
        EditText_rondas.setText("");
    }

}