package com.timesplit.Modelo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.timesplit.Utilidades.Utilidades;
import java.util.ArrayList;
import java.util.List;

public class Perfil {
private int id_perfil;
private String nombre_perfil;
private int tiempo_trabajo;
private int tiempo_descanso;
private int tiempo_preparacion;
private int rondas;
private int id_usuario;


//Constructores
    public Perfil(int id_perfil, String nombre_perfil, int tiempo_trabajo, int tiempo_descanso, int tiempo_preparacion, int rondas, int id_usuario) {
        this.id_perfil = id_perfil;
        this.nombre_perfil = nombre_perfil;
        this.tiempo_trabajo = tiempo_trabajo;
        this.tiempo_descanso = tiempo_descanso;
        this.tiempo_preparacion = tiempo_preparacion;
        this.rondas = rondas;
        this.id_usuario = id_usuario;
    }

    public Perfil(String nombre_perfil, int tiempo_trabajo, int tiempo_descanso, int tiempo_preparacion, int rondas, int id_usuario) {
        this.nombre_perfil = nombre_perfil;
        this.tiempo_trabajo = tiempo_trabajo;
        this.tiempo_descanso = tiempo_descanso;
        this.tiempo_preparacion = tiempo_preparacion;
        this.rondas = rondas;
        this.id_usuario = id_usuario;
    }

    public Perfil() {
    }


    //Metodos de acceso
    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }

    public String getNombre_perfil() {
        return nombre_perfil;
    }

    public void setNombre_perfil(String nombre_perfil) {
        this.nombre_perfil = nombre_perfil;
    }

    public int getTiempo_trabajo() {
        return tiempo_trabajo;
    }

    public void setTiempo_trabajo(int tiempo_trabajo) {
        this.tiempo_trabajo = tiempo_trabajo;
    }

    public int getTiempo_descanso() {
        return tiempo_descanso;
    }

    public void setTiempo_descanso(int tiempo_descanso) {
        this.tiempo_descanso = tiempo_descanso;
    }

    public int getTiempo_preparacion() {
        return tiempo_preparacion;
    }

    public void setTiempo_preparacion(int tiempo_preparacion) {
        this.tiempo_preparacion = tiempo_preparacion;
    }

    public int getRondas() {
        return rondas;
    }

    public void setRondas(int rondas) {
        this.rondas = rondas;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }


    //Metodos CRUD
    //Inserta perfil
    public static long insertPerfil(Perfil perfil, SQLiteDatabase db){
        // Guarda atributos perfil
        ContentValues valores = new ContentValues();
        valores.put(Utilidades.PERFILES_NOMBRE, perfil.getNombre_perfil());
        valores.put(Utilidades.PERFILES_TIEMPO_TRABAJO, perfil.getTiempo_trabajo());
        valores.put(Utilidades.PERFILES_TIEMPO_DESCANSO, perfil.getTiempo_descanso());
        valores.put(Utilidades.PERFILES_TIEMPO_PREPARACION, perfil.getTiempo_preparacion());
        valores.put(Utilidades.PERFILES_RONDAS, perfil.getRondas());
        valores.put(Utilidades.PERFILES_USERID, perfil.getId_usuario());

        // Inserta el perfil en la BD
        return db.insert(Utilidades.BD_TABLA_PERFILES, null, valores);
    }

    //Recupera un perfil a traves de su id
    public static Perfil selectPerfilByID (int id_perfil, SQLiteDatabase db) {
        Cursor cursor = db.query(Utilidades.BD_TABLA_PERFILES,
                new String[]{Utilidades.PERFILES_ID, Utilidades.PERFILES_NOMBRE, Utilidades.PERFILES_TIEMPO_TRABAJO, Utilidades.PERFILES_TIEMPO_DESCANSO, Utilidades.PERFILES_TIEMPO_PREPARACION,
                        Utilidades.PERFILES_RONDAS, Utilidades.PERFILES_USERID},
                Utilidades.PERFILES_ID+"=?",new String[]{id_perfil+""},
                null,null,null);

        Perfil perfil = new Perfil();

        //Si recupera datos, va a la primera posicion
        if(cursor.moveToFirst()){
            // Crea usuario con los atributos que recupera el cursor
            perfil.setId_perfil(cursor.getInt(0));
            perfil.setNombre_perfil(cursor.getString(1));
            perfil.setTiempo_trabajo(cursor.getInt(2));
            perfil.setTiempo_descanso(cursor.getInt(3));
            perfil.setTiempo_preparacion(cursor.getInt(4));
            perfil.setRondas(cursor.getInt(5));
            perfil.setId_usuario(cursor.getInt(6));
        }

        return perfil;
    }

    //Recupera un perfil a traves de su nombre
    public static Perfil selectPerfilByNombre (String nombre, SQLiteDatabase db) {
        Cursor cursor = db.query(Utilidades.BD_TABLA_PERFILES,
                new String[]{Utilidades.PERFILES_ID, Utilidades.PERFILES_NOMBRE, Utilidades.PERFILES_TIEMPO_TRABAJO, Utilidades.PERFILES_TIEMPO_DESCANSO, Utilidades.PERFILES_TIEMPO_PREPARACION,
                        Utilidades.PERFILES_RONDAS, Utilidades.PERFILES_USERID},
                Utilidades.PERFILES_NOMBRE+"=?",new String[]{nombre},
                null,null,null);

        Perfil perfil = new Perfil();

        //Si recupera datos, va a la primera posicion
        if(cursor.moveToFirst()){
            // Crea usuario con los atributos que recupera el cursor
            perfil.setId_perfil(cursor.getInt(0));
            perfil.setNombre_perfil(cursor.getString(1));
            perfil.setTiempo_trabajo(cursor.getInt(2));
            perfil.setTiempo_descanso(cursor.getInt(3));
            perfil.setTiempo_preparacion(cursor.getInt(4));
            perfil.setRondas(cursor.getInt(5));
            perfil.setId_usuario(cursor.getInt(6));
        }

        return perfil;
    }

    //Comprueba si existe un perfil en la BD con el nombre pasado por parametro para el id de usuario correspondiente
    public static boolean existePerfilByNombre (String nombre, int id_usuario, SQLiteDatabase db) {
        String selectAll = "SELECT * FROM " + Utilidades.BD_TABLA_PERFILES + " WHERE " + Utilidades.PERFILES_NOMBRE + " = '" + nombre + "' AND " + Utilidades.PERFILES_USERID + " = " + id_usuario;
        Cursor cursor = db.rawQuery(selectAll, null);
        //Si recupera datos, va a la primera posicion
        Log.d("TAG", "existePerfilByNombre: " + cursor.getCount());
        if(cursor.getCount()!=0){
            return true;
        }else{
            return false;
        }

    }

    //Devuelve todos los perfiles del usuario pasado por parametro
    public static List<Perfil> listaPerfiles(int id_usuario, SQLiteDatabase db){
        List<Perfil> listaPerfiles = new ArrayList<>();
        String selectAll = "SELECT * FROM " + Utilidades.BD_TABLA_PERFILES + " WHERE " + Utilidades.PERFILES_USERID + " = " + id_usuario;
        //Con rawQuery() se usan sentencias SQL directamente
        Cursor cursor = db.rawQuery(selectAll, null);

        //Si el cursor tiene algún dato, recorrerá el bucle añadiendo contactos a la lista hasta que no tenga más posiciones que recorrer
        if(cursor.moveToFirst()) {
            do{
                //Crea perfil y le asigna atributos devueltos por la consulta
                Perfil perfil = new Perfil();
                perfil.setId_perfil(cursor.getInt(0));
                perfil.setNombre_perfil(cursor.getString(1));
                perfil.setTiempo_trabajo(cursor.getInt(2));
                perfil.setTiempo_descanso(cursor.getInt(3));
                perfil.setTiempo_preparacion(cursor.getInt(4));
                perfil.setRondas(cursor.getInt(5));
                perfil.setId_usuario(cursor.getInt(6));

                //Añade perfil a la lista
                listaPerfiles.add(perfil);
            }while (cursor.moveToNext());
        }

        // Devuelve una lista de perfiles
        return listaPerfiles;
    }
}