package com.timesplit.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.timesplit.modelo.AjustesUsuario;
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

    public long insertAjustesUsuario(AjustesUsuario a_usuario){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Utilidades.USUARIO_A_TEMA, a_usuario.getTema());
        valores.put(Utilidades.USUARIO_A_SONIDO, a_usuario.getSonido());
        valores.put(Utilidades.USUARIO_A_VOLUMEN, a_usuario.getVolumen());
        valores.put(Utilidades.USUARIO_A_USERNAME, a_usuario.getNombre_usuario());

        return db.insert(Utilidades.BD_TABLA_A_USUARIO, null, valores);
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

    public AjustesUsuario selectAjustesUsuario (String nombre_usuario) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Utilidades.BD_TABLA_A_USUARIO,
                new String[]{Utilidades.USUARIO_A_ID, Utilidades.USUARIO_A_TEMA, Utilidades.USUARIO_A_SONIDO, Utilidades.USUARIO_A_VOLUMEN, Utilidades.USUARIO_A_USERNAME},
                Utilidades.USUARIO_A_USERNAME+"=?",new String[]{nombre_usuario},
                null,null,null);

        if(cursor != null)
            cursor.moveToFirst();

        AjustesUsuario a_usuario = new AjustesUsuario();
        a_usuario.setId_ajustes((cursor.getInt(0)));
        a_usuario.setTema((cursor.getInt(1)));
        a_usuario.setSonido((cursor.getInt(2)));
        a_usuario.setVolumen((cursor.getInt(3)));
        a_usuario.setNombre_usuario((cursor.getString(4)));

        return a_usuario;
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

    public int updateAjustesUsuario(AjustesUsuario a_usuario){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Utilidades.USUARIO_A_ID, a_usuario.getId_ajustes());
        valores.put(Utilidades.USUARIO_A_TEMA, a_usuario.getTema());
        valores.put(Utilidades.USUARIO_A_SONIDO, a_usuario.getSonido());
        valores.put(Utilidades.USUARIO_A_VOLUMEN, a_usuario.getVolumen());
        valores.put(Utilidades.USUARIO_A_USERNAME, a_usuario.getNombre_usuario());

        return db.update(Utilidades.BD_TABLA_A_USUARIO, valores, Utilidades.USUARIO_A_USERNAME + "=?",
                new String[]{a_usuario.getNombre_usuario()});

    }

    //Borra usuario
    public long deleteUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Retorna un int (-1 si no se pudo borrar, o la posición borrada
        return db.delete(Utilidades.BD_TABLA_USUARIO, Utilidades.USUARIO_USERNAME+"=?",
                new String[]{usuario.getNombre_usuario()});
    }

}
