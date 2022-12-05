package com.timesplit.modelo;

public class Perfil {
private int id_perfil;
private String nombre_perfil;
private int tiempo_trabajo;
private int tiempo_descanso;
private int tiempo_preparacion;
private int rondas;
private String nombre_usuario;

//Constructores
    public Perfil(int id_perfil, String nombre_perfil, int tiempo_trabajo, int tiempo_descanso, int tiempo_preparacion, int rondas, String nombre_usuario) {
        this.id_perfil = id_perfil;
        this.nombre_perfil = nombre_perfil;
        this.tiempo_trabajo = tiempo_trabajo;
        this.tiempo_descanso = tiempo_descanso;
        this.tiempo_preparacion = tiempo_preparacion;
        this.rondas = rondas;
        this.nombre_usuario = nombre_usuario;
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

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }
}
