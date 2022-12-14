package com.timesplit.Utilidades;

import androidx.appcompat.app.AppCompatDelegate;

public class Utilidades {
//Constantes globales con los datos de la BD (nombres de tablas, columnas, etc)
    //Datos BD
    public static final int BD_VERSION = 1;
    public static final String BD_NOMBRE = "timeSplit_BD";

    //Tabla usuario
    public static final String BD_TABLA_USUARIO = "usuario";
    public static final String USUARIO_ID = "id_usuario";
    public static final String USUARIO_PASS = "password";
    public static final String USUARIO_NOMBRE = "nombre";
    public static final String USUARIO_APELLIDOS = "apellidos";
    public static final String USUARIO_EMAIL = "email";

    //Tabla ajustes_usuario
    public static final String BD_TABLA_A_USUARIO = "ajustes_usuario";
    public static final String USUARIO_A_ID = "id_ajustes";
    public static final String USUARIO_A_TEMA = "tema";
    public static final String USUARIO_A_SONIDO = "sonido";
    public static final String USUARIO_A_VOLUMEN = "volumen";
    public static final String USUARIO_A_USERID = "id_usuario";

    //Tabla perfiles
    public static final String BD_TABLA_PERFILES = "perfiles";
    public static final String PERFILES_ID = "id_perfil";
    public static final String PERFILES_NOMBRE = "nombre_perfil";
    public static final String PERFILES_TIEMPO_TRABAJO = "tiempo_trabajo";
    public static final String PERFILES_TIEMPO_DESCANSO = "tiempo_descanso";
    public static final String PERFILES_TIEMPO_PREPARACION = "tiempo_preparacion";
    public static final String PERFILES_RONDAS = "rondas";
    public static final String PERFILES_USERID = "id_usuario";

    //Tabla perfiles_ajustes
    public static final String BD_TABLA_PERFILES_AJUSTES = "perfiles_ajustes";
    public static final String PERFILES_A_ID = "id_ajustes_perfil";
    public static final String PERFILES_A_COLOR_TRABAJO = "color_trabajo";
    public static final String PERFILES_A_COLOR_DESCANSO = "color_descanso";
    public static final String PERFILES_A_COLOR_PREPARACION = "color_preparacion";
    public static final String PERFILES_A_SONIDO = "sonido";
    public static final String PERFILES_A_ID_PERFIL = "id_perfil";

    //Tabla estadisticas
    public static final String BD_TABLA_ESTADISTICAS = "estadisticas";
    public static final String ESTADISTICAS_ID = "id_estadisticas";
    public static final String ESTADISTICAS_NUMERO_PERFILES = "numero_perfiles";
    public static final String ESTADISTICAS_TOTAL_TRABAJO = "total_trabajo";
    public static final String ESTADISTICAS_TOTAL_DESCANSO = "total_descanso";
    public static final String ESTADISTICAS_TOTAL_RONDAS = "total_rondas";
    public static final String ESTADISTICAS_USERID = "id_usuario";


    //METODOS

    //Convierte milisegundos al formato MMSS
    public static int toMMSS(long ms) {
        //Convierte de milisegundos a minutos
        int minutos = (int) (ms / 1000) / 60;

        //Convierte los milisegundos restantes a segundos
        int segundos = (int) (ms / 1000) % 60;

        int tiempoMMSS = Integer.parseInt(minutos+""+segundos) ;

        return tiempoMMSS;
    }

    //Convierte de ms a formato HH:MM:SS y lo devuelve en string
    public static String toHHMMSS(long ms) {
        int horas = (int) (ms / (3600*1000));
        //Convierte de milisegundos a minutos
        int minutos = (int) (ms / (60*1000) % 60);
        //Convierte los milisegundos restantes a segundos
        int segundos = (int) (ms / 1000 % 60);

        String tiempoMMSS = horas+"h "+minutos+"m "+segundos + "s " ;

        return tiempoMMSS;
    }

    //Convierte de MMSS a milisegundos
    public static long toMs(int minutos, int segundos){
        long ms = (minutos*60000) + (segundos*1000);
        return ms;
    }

    //Extrae datos usuario en inputs con formato MM:SS y los devuelve en milisegundos
    public static long inputMMSS(String mmss){
        String[] MMSS = mmss.split(":");
        int minutos = Integer.parseInt(MMSS[0]);
        int segundos = Integer.parseInt(MMSS[1]);

        return toMs(minutos, segundos);
    }

    //Devuelve la posicion en un Array de Strings, del string pasado por parametro
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

    //Cambia el tema de la app dependiendo de los ajustes del usuario
    public static void cambiaTema (int tema){
        //Cambia a tema por defecto (Depende del DarkMode del SO)
        if (tema == 0){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }

        //Cambia el tema a Claro
        if (tema == 1)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //Cambia el tema a Oscuro
        if (tema == 2)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }
}