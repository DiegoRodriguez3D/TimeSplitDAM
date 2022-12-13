package com.timesplit.controlador;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.Patterns;

import com.google.android.material.textfield.TextInputEditText;
import com.timesplit.modelo.Usuario;

public class Auth_Controller {
    public static final String Login = "LogPrefs" ;
    public static final String Nombre = "nombre";
    public static final String Apellidos = "apellidos";
    public static final String Email = "email";
    public static final String ID = "id";
    public static final String userLoged = "userLoged";
    public static final Usuario userLog = new Usuario();


    public static boolean isEmailValido(String email) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        } else {
           return false;
        }
    }

    public static boolean isPassValida(String pass, String repeatPass) {
        if (pass.equals(repeatPass)) {
            return true;
        } else {
            return false;
        }
    }

}
