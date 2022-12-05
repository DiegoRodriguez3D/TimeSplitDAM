package com.timesplit.controlador;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.timesplit.utilidades.Utilidades;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BD_Controller extends SQLiteOpenHelper {

    //Constructor
    public BD_Controller(Context context) {
        super(context, Utilidades.BD_NOMBRE, null, Utilidades.BD_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Crea las tablas de la BD

        String CREAR_TABLA_USUARIOS = "CREATE TABLE " + Utilidades.BD_TABLA_USUARIO + "("
                + Utilidades.USUARIO_USERNAME + " VARCHAR NOT NULL PRIMARY KEY,"
                + Utilidades.USUARIO_PASS + " VARCHAR NOT NULL,"
                + Utilidades.USUARIO_NOMBRE + " VARCHAR,"
                + Utilidades.USUARIO_EMAIL + " VARCHAR NOT NULL"
                + ")";

        db.execSQL(CREAR_TABLA_USUARIOS);

        String CREA_TABLA_A_USUARIOS = "CREATE TABLE " + Utilidades.BD_TABLA_A_USUARIO + "("
                + Utilidades.USUARIO_A_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Utilidades.USUARIO_A_TEMA + " VARCHAR,"
                + Utilidades.USUARIO_A_SONIDO + " BOOLEAN,"
                + Utilidades.USUARIO_A_VOLUMEN + " INTEGER,"
                + Utilidades.USUARIO_A_USERNAME + " VARCHAR,"
                + " CONSTRAINT fk_"+Utilidades.USUARIO_A_USERNAME
                + " FOREIGN KEY("+Utilidades.USUARIO_A_USERNAME+") REFERENCES "+ Utilidades.BD_TABLA_USUARIO +"("+Utilidades.USUARIO_USERNAME+") ON DELETE CASCADE"
                + ")";


        db.execSQL(CREA_TABLA_A_USUARIOS);

        String CREA_TABLA_PERFILES = "CREATE TABLE " + Utilidades.BD_TABLA_PERFILES + "("
                + Utilidades.PERFILES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Utilidades.PERFILES_NOMBRE + " VARCHAR,"
                + Utilidades.PERFILES_TIEMPO_TRABAJO + " INTEGER,"
                + Utilidades.PERFILES_TIEMPO_DESCANSO + " INTEGER,"
                + Utilidades.PERFILES_TIEMPO_PREPARACION + " INTEGER,"
                + Utilidades.PERFILES_RONDAS + " INTEGER,"
                + Utilidades.PERFILES_USERNAME + " VARCHAR,"
                + " CONSTRAINT fk_"+Utilidades.PERFILES_USERNAME
                + " FOREIGN KEY("+ Utilidades.PERFILES_USERNAME+") REFERENCES " + Utilidades.BD_TABLA_USUARIO + "(" + Utilidades.USUARIO_USERNAME + ") ON DELETE CASCADE"
                + ")";

        db.execSQL(CREA_TABLA_PERFILES);


        String CREA_TABLA_A_PERFILES = "CREATE TABLE " + Utilidades.BD_TABLA_PERFILES_AJUSTES + "("
                + Utilidades.PERFILES_A_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Utilidades.PERFILES_A_COLOR_TRABAJO + " VARCHAR,"
                + Utilidades.PERFILES_A_COLOR_DESCANSO + " VARCHAR,"
                + Utilidades.PERFILES_A_COLOR_PREPARACION + " VARCHAR,"
                + Utilidades.PERFILES_A_SONIDO + " BOOLEAN,"
                + Utilidades.PERFILES_A_ID_PERFIL + " INTEGER,"
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
                + Utilidades.ESTADISTICAS_USERNAME + " VARCHAR,"
                + " CONSTRAINT fk_" + Utilidades.ESTADISTICAS_USERNAME
                + " FOREIGN KEY(" + Utilidades.ESTADISTICAS_USERNAME + ") REFERENCES " + Utilidades.BD_TABLA_USUARIO + "(" + Utilidades.USUARIO_USERNAME + " ) ON DELETE CASCADE"
                + ")";

        db.execSQL(CREA_TABLA_ESTADISTICAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Borra tablas antiguas, y crea nuevas
        String BORRAR_TABLA = String.valueOf(R.string.db_borrar);

        db.execSQL(BORRAR_TABLA, new String[]{Utilidades.BD_TABLA_USUARIO});
        db.execSQL(BORRAR_TABLA, new String[]{Utilidades.BD_TABLA_A_USUARIO});
        db.execSQL(BORRAR_TABLA, new String[]{Utilidades.BD_TABLA_PERFILES});
        db.execSQL(BORRAR_TABLA, new String[]{Utilidades.BD_TABLA_PERFILES_AJUSTES});
        db.execSQL(BORRAR_TABLA, new String[]{Utilidades.BD_TABLA_ESTADISTICAS});

        onCreate(db);
    }

    /*
       CRUD
     */

    // Añade contacto
    public long insertarContacto(Contacto contacto){
        SQLiteDatabase db = this.getWritableDatabase();

        // Guarda atributos contacto
        ContentValues valores = new ContentValues();
        valores.put(Utilidades.CONTACTOS_NOMBRE, contacto.getNombre());
        valores.put(Utilidades.CONTACTOS_TELEFONO, contacto.getTelefono());
        valores.put(Utilidades.CONTACTOS_EMAIL, contacto.getEmail());

        // Inserta valores almacenados
        return db.insert(Utilidades.BD_TABLA_NOMBRE, null, valores);

    }

    // Accede a contacto
    public Contacto devuelveContacto (String nombre) {
        SQLiteDatabase db = this.getReadableDatabase();

        // Crea cursor
        //Utilizando query() utilizaremos los parametros para crear una sentencia SQL
        Cursor cursor = db.query(Utilidades.BD_TABLA_NOMBRE,
                new String[]{Utilidades.CONTACTOS_NOMBRE, Utilidades.CONTACTOS_TELEFONO, Utilidades.CONTACTOS_EMAIL},
                Utilidades.CONTACTOS_NOMBRE+"=?",new String[]{nombre},
                null,null,null);

        //Si tiene datos, va a la primera posicion
        if(cursor != null)
            cursor.moveToFirst();

        // Crea contacto con los atributos que recupera el cursor
        // columna 0 -> nombre | columna 1 -> teléfono | columna 2 -> email
        Contacto contacto = new Contacto();
        contacto.setNombre(cursor.getString(0));
        contacto.setTelefono(cursor.getString(1));
        contacto.setEmail(cursor.getString(2));

        return contacto;
    }

    // Accede a todos los contactos
    public List<Contacto> devuelveListaContactos(){
        List<Contacto> listaContactos = new ArrayList<Contacto>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll = "SELECT * FROM " + Utilidades.BD_TABLA_NOMBRE;
        //Con rawQuery() se usan sentencias SQL directamente
        Cursor cursor = db.rawQuery(selectAll, null);

        //Si el cursor tiene algún dato, recorrerá el bucle añadiendo contactos a la lista hasta que no tenga más posiciones que recorrer
        if(cursor.moveToFirst()) {
            do{
                //Crea contacto y le asigna atributos devueltos por la consulta
                Contacto contacto = new Contacto();
                contacto.setNombre(cursor.getString(0));
                contacto.setTelefono(cursor.getString(1));
                contacto.setEmail(cursor.getString(2));

                //Añade contacto a la lista
                listaContactos.add(contacto);
            }while (cursor.moveToNext());
        }

        // Devuelve una lista de contactos
        return listaContactos;
    }

    // Actualiza contacto
    public int actualizaContacto(Contacto contacto) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Almacena los atributos del contacto pasado por parametro en una coleccion de valores
        ContentValues valores = new ContentValues();
        valores.put(Utilidades.CONTACTOS_NOMBRE, contacto.getNombre());
        valores.put(Utilidades.CONTACTOS_TELEFONO, contacto.getTelefono());
        valores.put(Utilidades.CONTACTOS_EMAIL, contacto.getEmail());

        // retorna un int (-1 si no se pudo actualizar, o la posición que se actualizó)
        return db.update(Utilidades.BD_TABLA_NOMBRE, valores, Utilidades.CONTACTOS_NOMBRE+"=?",
                new String[]{contacto.getNombre()});
    }

    //Borra contacto
    public long borraContacto(Contacto contacto) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Retorna un int (-1 si no se pudo borrar, o la posición borrada
        return db.delete(Utilidades.BD_TABLA_NOMBRE, Utilidades.CONTACTOS_NOMBRE+"=?",
                new String[]{contacto.getNombre()});
    }

    //Cuenta numero de contactos guardados
    public int numeroContactos() {
        String selectContactos = "SELECT * FROM " + Utilidades.BD_TABLA_NOMBRE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectContactos, null);

        return cursor.getCount();
    }
}
