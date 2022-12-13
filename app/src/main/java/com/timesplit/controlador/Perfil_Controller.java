package com.timesplit.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.timesplit.modelo.AjustesPerfil;
import com.timesplit.modelo.AjustesUsuario;
import com.timesplit.modelo.Perfil;
import com.timesplit.modelo.Usuario;
import com.timesplit.utilidades.Utilidades;
import com.timesplit.vista.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class Perfil_Controller {

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

    public static long insertAjustesPerfil(AjustesPerfil a_perfil, SQLiteDatabase db){
        ContentValues valores = new ContentValues();
        valores.put(Utilidades.PERFILES_A_COLOR_TRABAJO, a_perfil.getColor_trabajo());
        valores.put(Utilidades.PERFILES_A_COLOR_DESCANSO, a_perfil.getColor_descanso());
        valores.put(Utilidades.PERFILES_A_COLOR_PREPARACION, a_perfil.getColor_preparacion());
        valores.put(Utilidades.PERFILES_A_SONIDO, a_perfil.getSonido());
        valores.put(Utilidades.PERFILES_A_ID_PERFIL, a_perfil.getId_perfil());

        return db.insert(Utilidades.BD_TABLA_PERFILES_AJUSTES, null, valores);
    }

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

    //Devuelve todos los perfiles del usuario
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

    public static int valorPosicionArray(String[] items, String nombre){
        int posicion = -1;

        for (int i = 0; i < items.length; i++) {
            if(items[i].equals(nombre)){
                posicion = i;
                break;
            }
        }
        return posicion;
    }

    //Devuelve el nombre del sonido a partir de su id
    public static String nombreSonido(int sonido) {
        String nombre = "";
        if (sonido == 0)
            nombre = "relax";
        if (sonido == 1)
            nombre = "tono";
        if (sonido == 2)
            nombre = "boxeo";

        return nombre;
    }
}
