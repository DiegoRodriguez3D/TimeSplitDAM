package com.timesplit.Modelo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.timesplit.Utilidades.Utilidades;

public class Estadisticas {
    private int id_estadisticas;
    private int numero_perfiles;
    private int total_trabajo;
    private int total_descanso;
    private int total_rondas;
    private int id_usuario;

    //Constructores
    public Estadisticas(int id_estadisticas, int numero_perfiles, int total_trabajo, int total_descanso, int total_rondas, int id_usuario) {
        this.id_estadisticas = id_estadisticas;
        this.numero_perfiles = numero_perfiles;
        this.total_trabajo = total_trabajo;
        this.total_descanso = total_descanso;
        this.total_rondas = total_rondas;
        this.id_usuario = id_usuario;
    }

    public Estadisticas(int numero_perfiles, int total_trabajo, int total_descanso, int total_rondas, int id_usuario) {
        this.numero_perfiles = numero_perfiles;
        this.total_trabajo = total_trabajo;
        this.total_descanso = total_descanso;
        this.total_rondas = total_rondas;
        this.id_usuario = id_usuario;
    }

    public Estadisticas() {
    }

    //Metodos de acceso
    public int getId_estadisticas() {
        return id_estadisticas;
    }

    public void setId_estadisticas(int id_estadisticas) {
        this.id_estadisticas = id_estadisticas;
    }

    public int getNumero_perfiles() {
        return numero_perfiles;
    }

    public void setNumero_perfiles(int numero_perfiles) {
        this.numero_perfiles = numero_perfiles;
    }

    public int getTotal_trabajo() {
        return total_trabajo;
    }

    public void setTotal_trabajo(int total_trabajo) {
        this.total_trabajo = total_trabajo;
    }

    public int getTotal_descanso() {
        return total_descanso;
    }

    public void setTotal_descanso(int total_descanso) {
        this.total_descanso = total_descanso;
    }

    public int getTotal_rondas() {
        return total_rondas;
    }

    public void setTotal_rondas(int total_rondas) {
        this.total_rondas = total_rondas;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    //Metodos CRUD
    //Inserta estadisticas
    public static long insertEstadisticaUsuario(Estadisticas estadisticas, SQLiteDatabase db){
        ContentValues valores = new ContentValues();
        valores.put(Utilidades.ESTADISTICAS_NUMERO_PERFILES, estadisticas.getNumero_perfiles());
        valores.put(Utilidades.ESTADISTICAS_TOTAL_TRABAJO, estadisticas.getTotal_trabajo());
        valores.put(Utilidades.ESTADISTICAS_TOTAL_DESCANSO, estadisticas.getTotal_descanso());
        valores.put(Utilidades.ESTADISTICAS_TOTAL_RONDAS, estadisticas.getTotal_rondas());
        valores.put(Utilidades.ESTADISTICAS_USERID, estadisticas.getId_usuario());

        return db.insert(Utilidades.BD_TABLA_ESTADISTICAS, null, valores);
    }

    //Recupera las estadisticas de usuario a traves de un id de usuario
    public static Estadisticas selectEstadisticasUsuario (int id_usuario, SQLiteDatabase db) {
        Cursor cursor = db.query(Utilidades.BD_TABLA_ESTADISTICAS,
                new String[]{Utilidades.ESTADISTICAS_ID, Utilidades.ESTADISTICAS_NUMERO_PERFILES, Utilidades.ESTADISTICAS_TOTAL_TRABAJO, Utilidades.ESTADISTICAS_TOTAL_DESCANSO, Utilidades.ESTADISTICAS_TOTAL_RONDAS, Utilidades.ESTADISTICAS_USERID},
                Utilidades.ESTADISTICAS_USERID+"=?",new String[]{id_usuario+""},
                null,null,null);

        Estadisticas estadisticas = new Estadisticas();
        if(cursor.moveToFirst()){
            estadisticas.setId_estadisticas(cursor.getInt(0));
            estadisticas.setNumero_perfiles(cursor.getInt(1));
            estadisticas.setTotal_trabajo(cursor.getInt(2));
            estadisticas.setTotal_descanso(cursor.getInt(3));
            estadisticas.setTotal_rondas(cursor.getInt(4));
            estadisticas.setId_usuario(cursor.getInt(5));
        }

        return estadisticas;
    }

    //Actualiza las estadisticas
    public static int updateEstadisticas(Estadisticas stats, SQLiteDatabase db){
        ContentValues valores = new ContentValues();
        valores.put(Utilidades.ESTADISTICAS_NUMERO_PERFILES, stats.getNumero_perfiles());
        valores.put(Utilidades.ESTADISTICAS_TOTAL_TRABAJO, stats.getTotal_trabajo());
        valores.put(Utilidades.ESTADISTICAS_TOTAL_DESCANSO, stats.getTotal_descanso());
        valores.put(Utilidades.ESTADISTICAS_TOTAL_RONDAS, stats.getTotal_rondas());
        valores.put(Utilidades.ESTADISTICAS_USERID, stats.getId_usuario());

        return db.update(Utilidades.BD_TABLA_ESTADISTICAS, valores, Utilidades.ESTADISTICAS_USERID + "=?",
                new String[]{stats.getId_usuario()+""});

    }
}
