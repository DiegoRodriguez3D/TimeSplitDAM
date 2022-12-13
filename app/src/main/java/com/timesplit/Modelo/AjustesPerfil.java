package com.timesplit.Modelo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.timesplit.Utilidades.Utilidades;
import java.io.Serializable;

//Implementa Seruializable para poder pasar objetos por Intent
public class AjustesPerfil implements Serializable {
    private int id_ajustes_perfil;
    private String color_trabajo;
    private String color_descanso;
    private String color_preparacion;
    private int sonido;
    private int id_perfil;

    // Constructores
    public AjustesPerfil(int id_ajustes_perfil, String color_trabajo, String color_descanso, String color_preparacion, int sonido, int id_perfil) {
        this.id_ajustes_perfil = id_ajustes_perfil;
        this.color_trabajo = color_trabajo;
        this.color_descanso = color_descanso;
        this.color_preparacion = color_preparacion;
        this.sonido = sonido;
        this.id_perfil = id_perfil;
    }

    public AjustesPerfil(String color_trabajo, String color_descanso, String color_preparacion, int sonido, int id_perfil) {
        this.color_trabajo = color_trabajo;
        this.color_descanso = color_descanso;
        this.color_preparacion = color_preparacion;
        this.sonido = sonido;
        this.id_perfil = id_perfil;
    }

    public AjustesPerfil(String color_trabajo, String color_descanso, String color_preparacion, int sonido) {
        this.color_trabajo = color_trabajo;
        this.color_descanso = color_descanso;
        this.color_preparacion = color_preparacion;
        this.sonido = sonido;
    }

    public AjustesPerfil() {

    }

    // Metodos de acceso
    public int getId_ajustes_perfil() {
        return id_ajustes_perfil;
    }

    public void setId_ajustes_perfil(int id_ajustes_perfil) {
        this.id_ajustes_perfil = id_ajustes_perfil;
    }

    public String getColor_trabajo() {
        return color_trabajo;
    }

    public void setColor_trabajo(String color_trabajo) {
        this.color_trabajo = color_trabajo;
    }

    public String getColor_descanso() {
        return color_descanso;
    }

    public void setColor_descanso(String color_descanso) {
        this.color_descanso = color_descanso;
    }

    public String getColor_preparacion() {
        return color_preparacion;
    }

    public void setColor_preparacion(String color_preparacion) {
        this.color_preparacion = color_preparacion;
    }

    public int getSonido() {
        return sonido;
    }

    public void setSonido(int sonido) {
        this.sonido = sonido;
    }

    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }


    //Metodos CRUD
    //Inserta un ajuste de perfil para el id de perfil pasado por parametro
    public static long insertAjustesPerfil(AjustesPerfil a_perfil, SQLiteDatabase db){
        ContentValues valores = new ContentValues();
        valores.put(Utilidades.PERFILES_A_COLOR_TRABAJO, a_perfil.getColor_trabajo());
        valores.put(Utilidades.PERFILES_A_COLOR_DESCANSO, a_perfil.getColor_descanso());
        valores.put(Utilidades.PERFILES_A_COLOR_PREPARACION, a_perfil.getColor_preparacion());
        valores.put(Utilidades.PERFILES_A_SONIDO, a_perfil.getSonido());
        valores.put(Utilidades.PERFILES_A_ID_PERFIL, a_perfil.getId_perfil());

        return db.insert(Utilidades.BD_TABLA_PERFILES_AJUSTES, null, valores);
    }

    //Recupera un ajuste de perfil para el perfil correspondiente
    public static AjustesPerfil selectAjustesPerfilById (int id_perfil, SQLiteDatabase db) {
        Cursor cursor = db.query(Utilidades.BD_TABLA_PERFILES_AJUSTES,
                new String[]{Utilidades.PERFILES_A_ID, Utilidades.PERFILES_A_COLOR_TRABAJO, Utilidades.PERFILES_A_COLOR_DESCANSO, Utilidades.PERFILES_A_COLOR_PREPARACION, Utilidades.PERFILES_A_SONIDO,
                        Utilidades.PERFILES_A_ID_PERFIL},
                Utilidades.PERFILES_A_ID_PERFIL+"=?",new String[]{id_perfil+""},
                null,null,null);

        AjustesPerfil a_perfil = new AjustesPerfil();

        //Si recupera datos, va a la primera posicion
        if(cursor.moveToFirst()){
            // Crea perfil con los atributos que recupera el cursor
            a_perfil.setId_ajustes_perfil(cursor.getInt(0));
            a_perfil.setColor_trabajo(cursor.getString(1));
            a_perfil.setColor_descanso(cursor.getString(2));
            a_perfil.setColor_preparacion(cursor.getString(3));
            a_perfil.setSonido(cursor.getInt(4));
            a_perfil.setId_perfil(cursor.getInt(5));
        }

        return a_perfil;
    }
}
