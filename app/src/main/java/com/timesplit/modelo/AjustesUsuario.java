package com.timesplit.modelo;

public class AjustesUsuario {
    private int id_ajustes;
    private int tema;
    private int sonido;
    private int volumen;
    private int id_usuario;

    //Constructores
    public AjustesUsuario(int id_ajustes, int tema, int sonido, int volumen, int id_usuario) {
        this.id_ajustes = id_ajustes;
        this.tema = tema;
        this.sonido = sonido;
        this.volumen = volumen;
        this.id_usuario = id_usuario;
    }

    public AjustesUsuario(int tema, int sonido, int volumen, int id_usuario) {
        this.tema = tema;
        this.sonido = sonido;
        this.volumen = volumen;
        this.id_usuario = id_usuario;
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

    public int getTema() {
        return tema;
    }

    public void setTema(int tema) {
        this.tema = tema;
    }

    public int getSonido() {
        return sonido;
    }

    public void setSonido(int sonido) {
        this.sonido = sonido;
    }

    public int getVolumen() {
        return volumen;
    }

    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}
