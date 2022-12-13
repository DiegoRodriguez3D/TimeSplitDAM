package com.timesplit.Modelo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.timesplit.Utilidades.Utilidades;
import java.io.Serializable;

public class Usuario implements Serializable {
    private int id_usuario;
    private String password;
    private String nombre;
    private String apellidos;
    private String email;


    // Constructores
    public Usuario(int id_usuario, String password, String nombre, String apellidos, String email) {
        this.id_usuario = id_usuario;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Usuario(String password, String nombre, String apellidos, String email) {
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Usuario(){
    }


    // Metodos de acceso
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    //Metodos CRUD
    //Inserta un usuario
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

    //Recupera un usuario a traves de su email
    public static Usuario selectUsuarioByMail (String email, SQLiteDatabase db) {
        Cursor cursor = db.query(Utilidades.BD_TABLA_USUARIO,
                new String[]{Utilidades.USUARIO_ID, Utilidades.USUARIO_EMAIL, Utilidades.USUARIO_PASS, Utilidades.USUARIO_NOMBRE, Utilidades.USUARIO_APELLIDOS},
                Utilidades.USUARIO_EMAIL+"=?",new String[]{email},
                null,null,null);

        Usuario usuario = new Usuario();

        if(cursor.moveToFirst()) {
            usuario.setId_usuario(cursor.getInt(0));
            usuario.setEmail(cursor.getString(1));
            usuario.setPassword(cursor.getString(2));
            usuario.setNombre(cursor.getString(3));
            usuario.setApellidos(cursor.getString(4));
        }

        return usuario;
    }

    //Comprueba si ya existe un usuario con el email pasado por parámetro
    public static boolean existeUsuarioMail (String email, SQLiteDatabase db) {
        Cursor cursor = db.query(Utilidades.BD_TABLA_USUARIO,
                new String[]{Utilidades.USUARIO_ID, Utilidades.USUARIO_EMAIL, Utilidades.USUARIO_PASS, Utilidades.USUARIO_NOMBRE, Utilidades.USUARIO_APELLIDOS},
                Utilidades.USUARIO_EMAIL+"=?",new String[]{email},
                null,null,null);

        //Si recupera datos, el email ya existe y devuelve true
        if(cursor.moveToFirst()){
            return true;
        } else{
            return false;
        }
    }

    // Actualiza usuario
    public static int updateUsuario(Usuario usuario, SQLiteDatabase db) {
        // Almacena los atributos del usuario pasado por parametro en una coleccion de valores
        ContentValues valores = new ContentValues();
        valores.put(Utilidades.USUARIO_ID, usuario.getId_usuario());
        valores.put(Utilidades.USUARIO_EMAIL, usuario.getEmail());
        valores.put(Utilidades.USUARIO_PASS, usuario.getPassword());
        valores.put(Utilidades.USUARIO_NOMBRE, usuario.getNombre());
        valores.put(Utilidades.USUARIO_APELLIDOS, usuario.getApellidos());

        // retorna un int (-1 si no se pudo actualizar, o la posición que se actualizó)
        return db.update(Utilidades.BD_TABLA_USUARIO, valores, Utilidades.USUARIO_ID+"=?",
                new String[]{usuario.getId_usuario()+""});
    }
}
