package com.timesplit.modelo;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int id_usuario;
    private String password;
    private String nombre;
    private String apellidos;
    private String email;

    // Constructores
    public Usuario(int id_usuario, String password, String nombre, String apellidos, String email) {
        this.id_usuario = id_usuario;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Usuario(){
    }


    // Metodos de acceso
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
