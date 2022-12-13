package com.timesplit.Modelo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.timesplit.Utilidades.Utilidades;

public class AjustesUsuario {
    private int id_ajustes;
    private int tema;
    private int sonido;
    private int volumen;
    private int id_usuario;

    //Constructores
    public AjustesUsuario(int id_ajustes, int tema, int sonido, int volumen, int id_usuario) {
        this.id_ajustes = id_ajustes;
        this.tema = tema;
        this.sonido = sonido;
        this.volumen = volumen;
        this.id_usuario = id_usuario;
    }

    public AjustesUsuario(int tema, int sonido, int volumen, int id_usuario) {
        this.tema = tema;
        this.sonido = sonido;
        this.volumen = volumen;
        this.id_usuario = id_usuario;
    }

    public AjustesUsuario(){

    }

    //Metodos de acceso
    public int getId_ajustes() {
        return id_ajustes;
    }

    public void setId_ajustes(int id_ajustes) {
        this.id_ajustes = id_ajustes;
    }

    public int getTema() {
        return tema;
    }

    public void setTema(int tema) {
        this.tema = tema;
    }

    public int getSonido() {
        return sonido;
    }

    public void setSonido(int sonido) {
        this.sonido = sonido;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    //Metodos CRUD
    //Inserta un ajuste de usuario
    public static long insertAjustesUsuario(AjustesUsuario a_usuario, SQLiteDatabase db){
        ContentValues valores = new ContentValues();
        valores.put(Utilidades.USUARIO_A_TEMA, a_usuario.getTema());
        valores.put(Utilidades.USUARIO_A_SONIDO, a_usuario.getSonido());
        valores.put(Utilidades.USUARIO_A_VOLUMEN, a_usuario.getVolumen());
        valores.put(Utilidades.USUARIO_A_USERID, a_usuario.getId_usuario());

        return db.insert(Utilidades.BD_TABLA_A_USUARIO, null, valores);
    }

    //Recupera los ajustes de usuario a traves de un id de usuario
    public static AjustesUsuario selectAjustesUsuarioByID (int id_usuario, SQLiteDatabase db) {
        Cursor cursor = db.query(Utilidades.BD_TABLA_A_USUARIO,
                new String[]{Utilidades.USUARIO_A_ID, Utilidades.USUARIO_A_TEMA, Utilidades.USUARIO_A_SONIDO, Utilidades.USUARIO_A_VOLUMEN, Utilidades.USUARIO_A_USERID},
                Utilidades.USUARIO_A_USERID+"=?",new String[]{id_usuario+""},
                null,null,null);

        AjustesUsuario a_usuario = new AjustesUsuario();

        if(cursor.moveToFirst()){
            a_usuario.setId_ajustes(cursor.getInt(0));
            a_usuario.setTema(cursor.getInt(1));
            a_usuario.setSonido(cursor.getInt(2));
            a_usuario.setVolumen(cursor.getInt(3));
            a_usuario.setId_usuario(cursor.getInt(4));
        }
        return a_usuario;
    }

    //Actualiza un ajuste de usuario
    public static int updateAjustesUsuario(AjustesUsuario a_usuario, SQLiteDatabase db){
        ContentValues valores = new ContentValues();
        valores.put(Utilidades.USUARIO_A_ID, a_usuario.getId_ajustes());
        valores.put(Utilidades.USUARIO_A_TEMA, a_usuario.getTema());
        valores.put(Utilidades.USUARIO_A_SONIDO, a_usuario.getSonido());
        valores.put(Utilidades.USUARIO_A_VOLUMEN, a_usuario.getVolumen());
        valores.put(Utilidades.USUARIO_A_USERID, a_usuario.getId_usuario());

        return db.update(Utilidades.BD_TABLA_A_USUARIO, valores, Utilidades.USUARIO_A_USERID + "=?",
                new String[]{a_usuario.getId_usuario()+""});

    }
}
