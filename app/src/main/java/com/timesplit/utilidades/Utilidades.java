package com.timesplit.utilidades;

//Clase que contiene los datos de la BD (nombres de tablas, columnas, etc)
public class Utilidades {
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
    public static final String USUARIO_A_USERNAME = "nombre_usuario";

    //Tabla perfiles
    public static final String BD_TABLA_PERFILES = "perfiles";
    public static final String PERFILES_ID = "id_perfil";
    public static final String PERFILES_NOMBRE = "nombre_perfil";
    public static final String PERFILES_TIEMPO_TRABAJO = "tiempo_trabajo";
    public static final String PERFILES_TIEMPO_DESCANSO = "tiempo_descanso";
    public static final String PERFILES_TIEMPO_PREPARACION = "tiempo_preparacion";
    public static final String PERFILES_RONDAS = "rondas";
    public static final String PERFILES_USERNAME = "nombre_usuario";

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
    public static final String ESTADISTICAS_USERNAME = "nombre_usuario";

}
