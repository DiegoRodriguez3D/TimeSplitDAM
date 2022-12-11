package com.timesplit.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.timesplit.R;
import com.timesplit.modelo.Temporizador;

import java.util.Timer;

public class TimerActivity extends AppCompatActivity {
    private Button iconButton_Back, iconButton_Previous_Ronda, iconButton_Play, iconButton_Pause, iconButton_Next_Ronda;
    private CircularProgressIndicator progressBar;
    private TextView textView_Timer, textView_numeroRondas;

    private CountDownTimer timer;
    private long tiempo_Inicial;
    private long tiempo_Restante;
    private long tiempo_Trabajo;
    private long tiempo_Descanso;
    private long tiempo_Preparacion;
    private String nombreTrabajo;
    private String nombreDescanso;
    private String nombrePreparacion;
    private int progress = 100;
    private int rondas_Iniciales;
    private int rondas_Restantes;
    private String colorTrabajo;
    private String colorDescanso;
    private String colorPreparacion;
    private boolean isTimerRunning = false;
    private boolean isTimerStop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        //Databinding
        textView_Timer = findViewById(R.id.textView_Timer);
        textView_numeroRondas = findViewById(R.id.textView_numeroRondas);
        progressBar = findViewById(R.id.Progress_bar);
        progressBar.setIndeterminate(false);

        //RECIBE EL OBJETO TIMER
        tiempo_Inicial = 5000;
        rondas_Iniciales = 5;
        tiempo_Restante= tiempo_Inicial;
        rondas_Restantes = rondas_Iniciales;
        progressBar.setProgress(100);

        Temporizador temporizador = new Temporizador();
        temporizador.getId_perfil();

        if(temporizador.getId_perfil() != 0){
            //SIGNIFICA QUE ES UN TEMPORIZADOR DE USUARIO
            //Comprueba tablas datos

            // Si tiempoPreparacion = 0, tiempo_Inicial = tiempoTrabajo else tiempo_Inciial = tiempoPreparacion

            // Si no tiene ajustes utiliza colores por defecto
        }else{
            // SI es 0, significa que es un QuickStart
            //Utiliza colores por defecto
        }


        //Previous
        iconButton_Previous_Ronda = findViewById(R.id.iconButton_Previous_Ronda);
        iconButton_Previous_Ronda.setOnClickListener(a -> {
            previousRonda();
        });

        //Next
        iconButton_Next_Ronda = findViewById(R.id.iconButton_Next_Ronda);
        iconButton_Next_Ronda.setOnClickListener(n -> {
            nextRonda();
        });

        //Play
        iconButton_Play = findViewById(R.id.iconButton_Play);
        iconButton_Play.setOnClickListener(p -> {
            startTimer();
        });

        //Pause
        iconButton_Pause = findViewById(R.id.iconButton_Pause);
        iconButton_Pause.setOnClickListener(s -> {
            pauseTimer();
        });

        iconButton_Pause.setOnLongClickListener(r -> {
            stopTimer();
            return isTimerStop = true;
        });

        //Back
        iconButton_Back = findViewById(R.id.iconButton_Back);
        iconButton_Back.setOnClickListener(h -> {
            Intent intent = new Intent(TimerActivity.this, com.timesplit.vista.MainActivity.class);
            startActivity(intent);
        });

        actualizaTemporizadorTexto();
        actualizarRondasTexto();
    }

    private void nextRonda() {
        if (rondas_Restantes < rondas_Iniciales) {
            rondas_Restantes++;
            actualizarRondasTexto();
            if (isTimerRunning) {
                stopTimerRonda();
            }
        }
    }

    private void previousRonda() {
        if (rondas_Restantes > 1) {
            rondas_Restantes--;
            actualizarRondasTexto();
            if (isTimerRunning) {
                stopTimerRonda();
            }
        }
    }

    private void stopTimer() {
        timer.cancel();
        tiempo_Restante = tiempo_Inicial;
        rondas_Restantes = rondas_Iniciales;
        progressBar.setProgress(100);
        actualizaTemporizadorTexto();
        actualizarRondasTexto();
        iconButton_Play.setVisibility(View.VISIBLE);
        iconButton_Pause.setVisibility(View.INVISIBLE);
    }

    private void stopTimerRonda() {
        timer.cancel();
        tiempo_Restante = tiempo_Inicial;
        progressBar.setProgress(100);
        actualizaTemporizadorTexto();
        actualizarRondasTexto();
        startTimer();
    }

    private void pauseTimer() {
        timer.cancel();
        isTimerRunning = false;
        iconButton_Play.setVisibility(View.VISIBLE);
        iconButton_Pause.setVisibility(View.INVISIBLE);
    }

    private void startTimer() {
        //Crea un temporizador con el tiempo restante que se actualice cada segundo (1000 ms)
        //Se calcula el progreso de la barra con una regla de tres (tiempo restante x 100 / tiempo inicial)
        timer = new CountDownTimer(tiempo_Restante, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                tiempo_Restante = millisUntilFinished;
                progress= (int) ((tiempo_Restante*100)/tiempo_Inicial);
                Log.d("TIMER", "onTick: progress -> " + progress);
                if(tiempo_Restante<1000){
                    progressBar.setProgressCompat(progress-10, true);
                }else{

                progressBar.setProgressCompat(progress-10, true);
                }
                actualizaTemporizadorTexto();
            }

            @Override
            public void onFinish() {
                if (rondas_Restantes==1){
                    isTimerRunning = false;
                    textView_numeroRondas.setText("0");
                    iconButton_Play.setVisibility(View.VISIBLE);
                    iconButton_Pause.setVisibility(View.INVISIBLE);
                }else{
                    progressBar.setProgressCompat(0, false);
                    try {
                        timer.wait(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    rondas_Restantes--;
                    tiempo_Restante=tiempo_Inicial;
                    progressBar.setProgressCompat(100, false);
                    actualizarRondasTexto();
                    startTimer();
                }

            }
        }.start();
        isTimerRunning = true;
        iconButton_Play.setVisibility(View.INVISIBLE);
        iconButton_Pause.setVisibility(View.VISIBLE);
    }

    private void actualizaTemporizadorTexto() {
        //Convierte de milisegundos a minutos
        int minutos = (int) (tiempo_Restante / 1000) / 60;

        //Convierte de milisegundos a segundos
        int segundos = (int) (tiempo_Restante / 1000) % 60;

        String tiempoRestanteTexto = String.format("%02d:%02d", minutos, segundos);

        textView_Timer.setText(tiempoRestanteTexto);
    }

    private void actualizarRondasTexto(){
        textView_numeroRondas.setText(rondas_Restantes+"");
    }

    private long toMs(int minutos, int segundos){
        //Convierte de minutos:segundos a milisegundos
        long ms = (minutos*60000) + (segundos*1000);
        return ms;
    }

}