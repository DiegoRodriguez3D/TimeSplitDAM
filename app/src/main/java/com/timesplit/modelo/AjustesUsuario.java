package com.timesplit.modelo;

public class AjustesUsuario {
    private int id_ajustes;
    private boolean tema;
    private boolean sonido;
    private int volumen;
    private String nombre_usuario;

    //Constructores
    public AjustesUsuario(int id_ajustes, boolean tema, boolean sonido, int volumen, String nombre_usuario) {
        this.id_ajustes = id_ajustes;
        this.tema = tema;
        this.sonido = sonido;
        this.volumen = volumen;
        this.nombre_usuario = nombre_usuario;
    }

    public AjustesUsuario(){

    }

    //Metodos de acceso
    public int getId_ajustes() {
        return id_ajustes;
    }

    public void setId_ajustes(int id_ajustes) {
        this.id_ajustes = id_ajustes;
    }

    public boolean isTema() {
        return tema;
    }

    public void setTema(boolean tema) {
        this.tema = tema;
    }

    public boolean isSonido() {
        return sonido;
    }

    public void setSonido(boolean sonido) {
        this.sonido = sonido;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }
}
