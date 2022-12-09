package com.timesplit.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.core.database.sqlite.SQLiteDatabaseKt;

import com.timesplit.modelo.AjustesUsuario;
import com.timesplit.modelo.Perfil;
import com.timesplit.modelo.Usuario;
import com.timesplit.utilidades.Utilidades;

import java.util.ArrayList;
import java.util.List;

public class Usuario_Controller {


    public static long insertUsuario(Usuario usuario, SQLiteDatabase db){

        // Guarda atributos usuario
        ContentValues valores = new ContentValues();
        valores.put(Utilidades.USUARIO_EMAIL, usuario.getEmail());
        valores.put(Utilidades.USUARIO_PASS, usuario.getPassword());
        valores.put(Utilidades.USUARIO_NOMBRE, usuario.getNombre());
        valores.put(Utilidades.USUARIO_APELLIDOS, usuario.getApellidos());

        // Inserta el usuario en la BD
        return db.insert(Utilidades.BD_TABLA_USUARIO, null, valores);
    }

    public long insertAjustesUsuario(AjustesUsuario a_usuario, SQLiteDatabase db){
        ContentValues valores = new ContentValues();
        valores.put(Utilidades.USUARIO_A_TEMA, a_usuario.getTema());
        valores.put(Utilidades.USUARIO_A_SONIDO, a_usuario.getSonido());
        valores.put(Utilidades.USUARIO_A_VOLUMEN, a_usuario.getVolumen());
        valores.put(Utilidades.USUARIO_A_USERID, a_usuario.getId_usuario());

        return db.insert(Utilidades.BD_TABLA_A_USUARIO, null, valores);
    }

    public static Usuario selectUsuarioByID (int id_usuario, SQLiteDatabase db) {
        // Crea cursor
        //Utilizando query() utilizaremos los parametros para crear una sentencia SQL
        Cursor cursor = db.query(Utilidades.BD_TABLA_USUARIO,
                new String[]{Utilidades.USUARIO_ID, Utilidades.USUARIO_EMAIL, Utilidades.USUARIO_PASS, Utilidades.USUARIO_NOMBRE, Utilidades.USUARIO_APELLIDOS},
                Utilidades.USUARIO_ID+"=?",new String[]{id_usuario+""},
                null,null,null);

        //Si tiene datos, va a la primera posicion
        if(cursor != null)
            cursor.moveToFirst();

        // Crea usuario con los atributos que recupera el cursor
        Usuario usuario = new Usuario();
        usuario.setId_usuario(cursor.getInt(0));
        usuario.setEmail(cursor.getString(1));
        usuario.setPassword(cursor.getString(2));
        usuario.setNombre(cursor.getString(3));
        usuario.setApellidos(cursor.getString(4));

        return usuario;
    }

    public static Usuario selectUsuarioByMail (String email, SQLiteDatabase db) {
        // Crea cursor
        //Utilizando query() utilizaremos los parametros para crear una sentencia SQL
        Cursor cursor = db.query(Utilidades.BD_TABLA_USUARIO,
                new String[]{Utilidades.USUARIO_ID, Utilidades.USUARIO_EMAIL, Utilidades.USUARIO_PASS, Utilidades.USUARIO_NOMBRE, Utilidades.USUARIO_APELLIDOS},
                Utilidades.USUARIO_EMAIL+"=?",new String[]{email},
                null,null,null);

        //Si tiene datos, va a la primera posicion
        if(cursor != null)
            cursor.moveToFirst();

        // Crea usuario con los atributos que recupera el cursor
        Usuario usuario = new Usuario();
        usuario.setId_usuario(cursor.getInt(0));
        usuario.setEmail(cursor.getString(1));
        usuario.setPassword(cursor.getString(2));
        usuario.setNombre(cursor.getString(3));
        usuario.setApellidos(cursor.getString(4));

        return usuario;
    }

    public static List<Usuario> listaUsuarios(SQLiteDatabase db){
        List<Usuario> listaUsuarios = new ArrayList<>();

        String selectAll = "SELECT * FROM " + Utilidades.BD_TABLA_USUARIO;
        //Con rawQuery() se usan sentencias SQL directamente
        Cursor cursor = db.rawQuery(selectAll, null);

        //Si el cursor tiene algún dato, recorrerá el bucle añadiendo contactos a la lista hasta que no tenga más posiciones que recorrer
        if(cursor.moveToFirst()) {
            do{
                //Crea perfil y le asigna atributos devueltos por la consulta
                Usuario usuario = new Usuario();
                usuario.setId_usuario(cursor.getInt(0));
                usuario.setEmail(cursor.getString(1));
                usuario.setPassword(cursor.getString(2));
                usuario.setNombre(cursor.getString(3));
                usuario.setApellidos(cursor.getString(4));

                //Añade perfil a la lista
                listaUsuarios.add(usuario);
            }while (cursor.moveToNext());
        }

        // Devuelve una lista de perfiles
        return listaUsuarios;
    }


    public AjustesUsuario selectAjustesUsuario (int id_usuario, SQLiteDatabase db) {
        Cursor cursor = db.query(Utilidades.BD_TABLA_A_USUARIO,
                new String[]{Utilidades.USUARIO_A_ID, Utilidades.USUARIO_A_TEMA, Utilidades.USUARIO_A_SONIDO, Utilidades.USUARIO_A_VOLUMEN, Utilidades.USUARIO_A_USERID},
                Utilidades.USUARIO_A_USERID+"=?",new String[]{id_usuario+""},
                null,null,null);

        if(cursor != null)
            cursor.moveToFirst();

        AjustesUsuario a_usuario = new AjustesUsuario();
        a_usuario.setId_ajustes((cursor.getInt(0)));
        a_usuario.setTema((cursor.getInt(1)));
        a_usuario.setSonido((cursor.getInt(2)));
        a_usuario.setVolumen((cursor.getInt(3)));
        a_usuario.setId_usuario((cursor.getInt(4)));

        return a_usuario;
    }

    // Actualiza usuario
    public int updateUsuario(Usuario usuario, SQLiteDatabase db) {
        // Almacena los atributos del usuario pasado por parametro en una coleccion de valores
        ContentValues valores = new ContentValues();
        valores.put(Utilidades.USUARIO_ID, usuario.getId_usuario());
        valores.put(Utilidades.USUARIO_PASS, usuario.getPassword());
        valores.put(Utilidades.USUARIO_NOMBRE, usuario.getNombre());
        valores.put(Utilidades.USUARIO_APELLIDOS, usuario.getApellidos());
        valores.put(Utilidades.USUARIO_EMAIL, usuario.getEmail());

        // retorna un int (-1 si no se pudo actualizar, o la posición que se actualizó)
        return db.update(Utilidades.BD_TABLA_USUARIO, valores, Utilidades.USUARIO_ID+"=?",
                new String[]{usuario.getId_usuario()+""});
    }

    public int updateAjustesUsuario(AjustesUsuario a_usuario, SQLiteDatabase db){
        ContentValues valores = new ContentValues();
        valores.put(Utilidades.USUARIO_A_ID, a_usuario.getId_ajustes());
        valores.put(Utilidades.USUARIO_A_TEMA, a_usuario.getTema());
        valores.put(Utilidades.USUARIO_A_SONIDO, a_usuario.getSonido());
        valores.put(Utilidades.USUARIO_A_VOLUMEN, a_usuario.getVolumen());
        valores.put(Utilidades.USUARIO_A_USERID, a_usuario.getId_usuario());

        return db.update(Utilidades.BD_TABLA_A_USUARIO, valores, Utilidades.USUARIO_A_USERID + "=?",
                new String[]{a_usuario.getId_usuario()+""});

    }

    //Borra usuario
    public long deleteUsuario(Usuario usuario, SQLiteDatabase db) {
        // Retorna un int (-1 si no se pudo borrar, o la posición borrada
        return db.delete(Utilidades.BD_TABLA_USUARIO, Utilidades.USUARIO_ID+"=?",
                new String[]{usuario.getId_usuario()+""});
    }

}
