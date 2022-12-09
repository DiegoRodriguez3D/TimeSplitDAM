package com.timesplit.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.timesplit.modelo.Perfil;
import com.timesplit.utilidades.Utilidades;
import com.timesplit.vista.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class Perfil_Controller {

    public static long insertPerfil(Perfil perfil, SQLiteDatabase db){

        // Guarda atributos usuario
        ContentValues valores = new ContentValues();
        valores.put(Utilidades.PERFILES_NOMBRE, perfil.getNombre_perfil());
        valores.put(Utilidades.PERFILES_TIEMPO_TRABAJO, perfil.getTiempo_trabajo());
        valores.put(Utilidades.PERFILES_TIEMPO_DESCANSO, perfil.getTiempo_descanso());
        valores.put(Utilidades.PERFILES_TIEMPO_PREPARACION, perfil.getTiempo_preparacion());
        valores.put(Utilidades.PERFILES_RONDAS, perfil.getRondas());
        valores.put(Utilidades.PERFILES_USERID, perfil.getId_usuario());

        // Inserta el usuario en la BD
        return db.insert(Utilidades.BD_TABLA_PERFILES, null, valores);
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
}
