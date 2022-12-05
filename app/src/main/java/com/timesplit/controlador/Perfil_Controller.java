package com.timesplit.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.timesplit.modelo.Perfil;
import com.timesplit.utilidades.Utilidades;

public class Perfil_Controller extends SQLiteOpenHelper {
    public Perfil_Controller(Context context) {
        super(context, Utilidades.BD_NOMBRE, null, Utilidades.BD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertPerfil(Perfil perfil){
        SQLiteDatabase db = this.getWritableDatabase();

        // Guarda atributos usuario
        ContentValues valores = new ContentValues();
        valores.put(Utilidades.PERFILES_NOMBRE, perfil.getNombre_perfil());
        valores.put(Utilidades.PERFILES_TIEMPO_TRABAJO, perfil.getTiempo_trabajo());
        valores.put(Utilidades.PERFILES_TIEMPO_DESCANSO, perfil.getTiempo_descanso());
        valores.put(Utilidades.PERFILES_TIEMPO_PREPARACION, perfil.getTiempo_preparacion());
        valores.put(Utilidades.PERFILES_RONDAS, perfil.getRondas());
        valores.put(Utilidades.PERFILES_USERNAME, perfil.getNombre_usuario());

        // Inserta el usuario en la BD
        return db.insert(Utilidades.BD_TABLA_PERFILES, null, valores);
    }


}
