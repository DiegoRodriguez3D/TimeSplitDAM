package com.timesplit.Modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.timesplit.R;
import com.timesplit.Utilidades.Utilidades;

public class BD extends SQLiteOpenHelper {

    //Constructor
    public BD(Context context) {
        super(context, Utilidades.BD_NOMBRE, null, Utilidades.BD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crea las tablas de la BD
        String CREAR_TABLA_USUARIOS = "CREATE TABLE " + Utilidades.BD_TABLA_USUARIO + "("
                + Utilidades.USUARIO_ID + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT,"
                + Utilidades.USUARIO_EMAIL + " VARCHAR NOT NULL UNIQUE,"
                + Utilidades.USUARIO_PASS + " VARCHAR NOT NULL,"
                + Utilidades.USUARIO_NOMBRE + " VARCHAR,"
                + Utilidades.USUARIO_APELLIDOS + " VARCHAR"
                + ")";

        db.execSQL(CREAR_TABLA_USUARIOS);

        String CREA_TABLA_A_USUARIOS = "CREATE TABLE " + Utilidades.BD_TABLA_A_USUARIO + "("
                + Utilidades.USUARIO_A_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Utilidades.USUARIO_A_TEMA + " INTEGER,"
                + Utilidades.USUARIO_A_SONIDO + " INTEGER,"
                + Utilidades.USUARIO_A_VOLUMEN + " INTEGER,"
                + Utilidades.USUARIO_A_USERID + " INTEGER NOT NULL UNIQUE,"
                + " CONSTRAINT fk_"+Utilidades.USUARIO_A_USERID
                + " FOREIGN KEY("+Utilidades.USUARIO_A_USERID+") REFERENCES "+ Utilidades.BD_TABLA_USUARIO +"("+Utilidades.USUARIO_ID +") ON DELETE CASCADE"
                + ")";


        db.execSQL(CREA_TABLA_A_USUARIOS);

        String CREA_TABLA_PERFILES = "CREATE TABLE " + Utilidades.BD_TABLA_PERFILES + "("
                + Utilidades.PERFILES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Utilidades.PERFILES_NOMBRE + " VARCHAR NOT NULL,"
                + Utilidades.PERFILES_TIEMPO_TRABAJO + " INTEGER NOT NULL,"
                + Utilidades.PERFILES_TIEMPO_DESCANSO + " INTEGER NOT NULL,"
                + Utilidades.PERFILES_TIEMPO_PREPARACION + " INTEGER,"
                + Utilidades.PERFILES_RONDAS + " INTEGER,"
                + Utilidades.PERFILES_USERID + " INTEGER NOT NULL,"
                + " CONSTRAINT fk_"+Utilidades.PERFILES_USERID
                + " FOREIGN KEY("+ Utilidades.PERFILES_USERID+") REFERENCES " + Utilidades.BD_TABLA_USUARIO + "(" + Utilidades.USUARIO_ID + ") ON DELETE CASCADE"
                + ")";

        db.execSQL(CREA_TABLA_PERFILES);


        String CREA_TABLA_A_PERFILES = "CREATE TABLE " + Utilidades.BD_TABLA_PERFILES_AJUSTES + "("
                + Utilidades.PERFILES_A_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Utilidades.PERFILES_A_COLOR_TRABAJO + " VARCHAR,"
                + Utilidades.PERFILES_A_COLOR_DESCANSO + " VARCHAR,"
                + Utilidades.PERFILES_A_COLOR_PREPARACION + " VARCHAR,"
                + Utilidades.PERFILES_A_SONIDO + " INTEGER,"
                + Utilidades.PERFILES_A_ID_PERFIL + " INTEGER NOT NULL UNIQUE,"
                + " CONSTRAINT fk_" + Utilidades.PERFILES_A_ID_PERFIL
                + " FOREIGN KEY("+ Utilidades.PERFILES_A_ID_PERFIL+") REFERENCES " + Utilidades.BD_TABLA_PERFILES + "(" + Utilidades.PERFILES_ID + ") ON DELETE CASCADE"
                + ")";

        db.execSQL(CREA_TABLA_A_PERFILES);

        String CREA_TABLA_ESTADISTICAS = "CREATE TABLE " + Utilidades.BD_TABLA_ESTADISTICAS + "("
                + Utilidades.ESTADISTICAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Utilidades.ESTADISTICAS_NUMERO_PERFILES + " INTEGER,"
                + Utilidades.ESTADISTICAS_TOTAL_TRABAJO + " INTEGER,"
                + Utilidades.ESTADISTICAS_TOTAL_DESCANSO + " INTEGER,"
                + Utilidades.ESTADISTICAS_TOTAL_RONDAS + " INTEGER,"
                + Utilidades.ESTADISTICAS_USERID + " INTEGER NOT NULL UNIQUE,"
                + " CONSTRAINT fk_" + Utilidades.ESTADISTICAS_USERID
                + " FOREIGN KEY(" + Utilidades.ESTADISTICAS_USERID + ") REFERENCES " + Utilidades.BD_TABLA_USUARIO + "(" + Utilidades.USUARIO_ID + " ) ON DELETE CASCADE"
                + ")";

        db.execSQL(CREA_TABLA_ESTADISTICAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Si se necesita actualizar versión, borra tablas antiguas y crea nuevas
        String BORRAR_TABLA = String.valueOf(R.string.db_borrar);

        db.execSQL(BORRAR_TABLA, new String[]{Utilidades.BD_TABLA_USUARIO});
        db.execSQL(BORRAR_TABLA, new String[]{Utilidades.BD_TABLA_A_USUARIO});
        db.execSQL(BORRAR_TABLA, new String[]{Utilidades.BD_TABLA_PERFILES});
        db.execSQL(BORRAR_TABLA, new String[]{Utilidades.BD_TABLA_PERFILES_AJUSTES});
        db.execSQL(BORRAR_TABLA, new String[]{Utilidades.BD_TABLA_ESTADISTICAS});

        onCreate(db);
    }
}