package com.timesplit.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.timesplit.modelo.Usuario;
import com.timesplit.utilidades.Utilidades;

public class Usuario_Controller extends SQLiteOpenHelper {

    public Usuario_Controller(Context context) {
        super(context, Utilidades.BD_NOMBRE, null, Utilidades.BD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertUsuario(Usuario usuario){
        SQLiteDatabase db = this.getWritableDatabase();

        // Guarda atributos usuario
        ContentValues valores = new ContentValues();
        valores.put(Utilidades.USUARIO_USERNAME, usuario.getNombre_usuario());
        valores.put(Utilidades.USUARIO_PASS, usuario.getPassword());
        valores.put(Utilidades.USUARIO_NOMBRE, usuario.getNombre_completo());
        valores.put(Utilidades.USUARIO_EMAIL, usuario.getEmail());

        // Inserta el usuario en la BD
        return db.insert(Utilidades.BD_TABLA_USUARIO, null, valores);
    }

    public Usuario selectUsuario (String nombre_usuario) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Crea cursor
        //Utilizando query() utilizaremos los parametros para crear una sentencia SQL
        Cursor cursor = db.query(Utilidades.BD_TABLA_USUARIO,
                new String[]{Utilidades.USUARIO_USERNAME, Utilidades.USUARIO_PASS, Utilidades.USUARIO_NOMBRE, Utilidades.USUARIO_EMAIL},
                Utilidades.USUARIO_USERNAME+"=?",new String[]{nombre_usuario},
                null,null,null);

        //Si tiene datos, va a la primera posicion
        if(cursor != null)
            cursor.moveToFirst();

        // Crea usuario con los atributos que recupera el cursor
        Usuario usuario = new Usuario();
        usuario.setNombre_usuario((cursor.getString(0)));
        usuario.setPassword((cursor.getString(1)));
        usuario.setNombre_completo((cursor.getString(2)));
        usuario.setEmail((cursor.getString(3)));

        return usuario;
    }

    // Actualiza usuario
    public int updateUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Almacena los atributos del usuario pasado por parametro en una coleccion de valores
        ContentValues valores = new ContentValues();
        valores.put(Utilidades.USUARIO_USERNAME, usuario.getNombre_usuario());
        valores.put(Utilidades.USUARIO_PASS, usuario.getPassword());
        valores.put(Utilidades.USUARIO_NOMBRE, usuario.getNombre_completo());
        valores.put(Utilidades.USUARIO_EMAIL, usuario.getEmail());

        // retorna un int (-1 si no se pudo actualizar, o la posición que se actualizó)
        return db.update(Utilidades.BD_TABLA_USUARIO, valores, Utilidades.USUARIO_USERNAME+"=?",
                new String[]{usuario.getNombre_usuario()});
    }

    //Borra usuario
    public long deleteUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Retorna un int (-1 si no se pudo borrar, o la posición borrada
        return db.delete(Utilidades.BD_TABLA_USUARIO, Utilidades.USUARIO_USERNAME+"=?",
                new String[]{usuario.getNombre_usuario()});
    }

}
