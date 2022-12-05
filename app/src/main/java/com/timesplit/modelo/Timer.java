package com.timesplit.modelo;

public class Timer {
    private String nombre_timer;
    private int tiempo_trabajo;
    private int tiempo_descanso;
    private int tiempo_preparacion;
    private int numero_rondas;
    private String color_trabajo;
    private String color_descanso;
    private String color_preparacion;
    private boolean sonido;
    private boolean volumen;


    //Constructores
    public Timer(int tiempo_trabajo, int tiempo_descanso, int tiempo_preparacion, int numero_rondas) {
        this.tiempo_trabajo = tiempo_trabajo;
        this.tiempo_descanso = tiempo_descanso;
        this.tiempo_preparacion = tiempo_preparacion;
        this.numero_rondas = numero_rondas;
    }


    //Metodos de acceso


}
