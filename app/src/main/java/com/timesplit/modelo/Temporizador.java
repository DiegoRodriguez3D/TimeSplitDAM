package com.timesplit.modelo;

public class Temporizador {
    private String nombre_timer;
    private int tiempo_trabajo;
    private int tiempo_descanso;
    private int tiempo_preparacion;
    private int numero_rondas;
    private int id_perfil;


    //Constructores

    public Temporizador() {
    }

    public Temporizador(int tiempo_trabajo, int tiempo_descanso, int tiempo_preparacion, int numero_rondas, int id_perfil) {
        this.tiempo_trabajo = tiempo_trabajo;
        this.tiempo_descanso = tiempo_descanso;
        this.tiempo_preparacion = tiempo_preparacion;
        this.numero_rondas = numero_rondas;
        this.id_perfil = id_perfil;
    }




    //Metodos de acceso
    public String getNombre_timer() {
        return nombre_timer;
    }

    public void setNombre_timer(String nombre_timer) {
        this.nombre_timer = nombre_timer;
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

    public int getNumero_rondas() {
        return numero_rondas;
    }

    public void setNumero_rondas(int numero_rondas) {
        this.numero_rondas = numero_rondas;
    }

    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }
}
