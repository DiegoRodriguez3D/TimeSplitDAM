package com.timesplit.modelo;

public class Usuario {
    private String nombre_usuario;
    private String password;
    private String nombre_completo;
    private String email;

    // Constructores
    public Usuario(String username, String password, String nombre, String email){
        this.nombre_usuario =username;
        this.password=password;
        this.nombre_completo =nombre;
        this.email=email;
    }

    public Usuario(){
    }


    // Metodos de acceso
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
