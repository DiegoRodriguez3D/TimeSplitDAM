package com.timesplit.modelo;

public class AjustesPerfil {
    private int id_ajustes_perfil;
    private String color_trabajo;
    private String color_descanso;
    private String color_preparacion;
    private boolean sonido;
    private int id_perfil;

    // Constructores
    public AjustesPerfil(int id_ajustes_perfil, String color_trabajo, String color_descanso, String color_preparacion, boolean sonido, int id_perfil) {
        this.id_ajustes_perfil = id_ajustes_perfil;
        this.color_trabajo = color_trabajo;
        this.color_descanso = color_descanso;
        this.color_preparacion = color_preparacion;
        this.sonido = sonido;
        this.id_perfil = id_perfil;
    }

    public AjustesPerfil() {

    }

    // Metodos de acceso
    public int getId_ajustes_perfil() {
        return id_ajustes_perfil;
    }

    public void setId_ajustes_perfil(int id_ajustes_perfil) {
        this.id_ajustes_perfil = id_ajustes_perfil;
    }

    public String getColor_trabajo() {
        return color_trabajo;
    }

    public void setColor_trabajo(String color_trabajo) {
        this.color_trabajo = color_trabajo;
    }

    public String getColor_descanso() {
        return color_descanso;
    }

    public void setColor_descanso(String color_descanso) {
        this.color_descanso = color_descanso;
    }

    public String getColor_preparacion() {
        return color_preparacion;
    }

    public void setColor_preparacion(String color_preparacion) {
        this.color_preparacion = color_preparacion;
    }

    public boolean isSonido() {
        return sonido;
    }

    public void setSonido(boolean sonido) {
        this.sonido = sonido;
    }

    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }
}
