package com.timesplit.modelo;

public class Estadisticas {
    private int id_estadisticas;
    private int numero_perfiles;
    private int total_trabajo;
    private int total_descanso;
    private int total_rondas;
    private int id_usuario;

    //Constructores
    public Estadisticas(int id_estadisticas, int numero_perfiles, int total_trabajo, int total_descanso, int total_rondas, int id_usuario) {
        this.id_estadisticas = id_estadisticas;
        this.numero_perfiles = numero_perfiles;
        this.total_trabajo = total_trabajo;
        this.total_descanso = total_descanso;
        this.total_rondas = total_rondas;
        this.id_usuario = id_usuario;
    }

    public Estadisticas(int numero_perfiles, int total_trabajo, int total_descanso, int total_rondas, int id_usuario) {
        this.numero_perfiles = numero_perfiles;
        this.total_trabajo = total_trabajo;
        this.total_descanso = total_descanso;
        this.total_rondas = total_rondas;
        this.id_usuario = id_usuario;
    }

    public Estadisticas() {
    }

    //Metodos de acceso
    public int getId_estadisticas() {
        return id_estadisticas;
    }

    public void setId_estadisticas(int id_estadisticas) {
        this.id_estadisticas = id_estadisticas;
    }

    public int getNumero_perfiles() {
        return numero_perfiles;
    }

    public void setNumero_perfiles(int numero_perfiles) {
        this.numero_perfiles = numero_perfiles;
    }

    public int getTotal_trabajo() {
        return total_trabajo;
    }

    public void setTotal_trabajo(int total_trabajo) {
        this.total_trabajo = total_trabajo;
    }

    public int getTotal_descanso() {
        return total_descanso;
    }

    public void setTotal_descanso(int total_descanso) {
        this.total_descanso = total_descanso;
    }

    public int getTotal_rondas() {
        return total_rondas;
    }

    public void setTotal_rondas(int total_rondas) {
        this.total_rondas = total_rondas;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
}
