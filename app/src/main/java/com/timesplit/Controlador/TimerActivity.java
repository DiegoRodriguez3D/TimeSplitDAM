package com.timesplit.Controlador;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.timesplit.R;
import com.timesplit.Modelo.Login;
import com.timesplit.Modelo.BD;
import com.timesplit.Modelo.AjustesPerfil;
import com.timesplit.Modelo.AjustesUsuario;
import com.timesplit.Modelo.Estadisticas;
import com.timesplit.Modelo.Perfil;
import com.timesplit.Modelo.Temporizador;
import com.timesplit.Modelo.Usuario;


public class TimerActivity extends AppCompatActivity {
    private Button iconButton_Back, iconButton_Previous_Ronda, iconButton_Play, iconButton_Pause, iconButton_Next_Ronda;
    private CircularProgressIndicator progressBar;
    private TextView textView_Timer, textView_numeroRondas, textView_TituloPerfilTimer;
    int volumen = 100, sonidoID = 0, sonido;
    private SoundPool sp;
    private CountDownTimer timer;
    private long tiempo_Inicial, tiempo_Restante, tiempo_Trabajo, tiempo_Descanso, tiempo_Preparacion;
    private String nombreTrabajo = "TRABAJO", nombreDescanso = "DESCANSO", nombrePreparacion = "PREPARACION";
    private int progress = 100, numero_Rondas, rondas_Iniciales, rondas_Restantes;
    private String colorTrabajo_HEX = "#EC1738", colorDescanso_HEX = "#325D79", colorPreparacion_HEX = "#ECA417";
    private int colorTrabajo = Color.parseColor(colorTrabajo_HEX), colorDescanso = Color.parseColor(colorDescanso_HEX), colorPreparacion = Color.parseColor(colorPreparacion_HEX);
    private boolean isTimerRunning = false, isTrabajo = false, isDescanso = false, isPreparacion = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        //BD
        BD db = new BD(TimerActivity.this);

        //Databinding
        textView_Timer = findViewById(R.id.textView_Timer);
        textView_numeroRondas = findViewById(R.id.textView_numeroRondas);
        textView_TituloPerfilTimer = findViewById(R.id.textView_TituloPerfilTimer);
        progressBar = findViewById(R.id.Progress_bar);
        progressBar.setIndeterminate(false);

        //Crea SoundPool, dependiendo de la version de android, para reproducir sonidos
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            //optimiza uso para notificaciones
            AudioAttributes at = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_NOTIFICATION).build();
            sp = new SoundPool.Builder().setAudioAttributes(at).build();
        }else {
            sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }

        sonidoID = sp.load(TimerActivity.this, R.raw.relax,1);
        volumen = 100;

        //Recibe desde un intent un objeto Temporizador con los parametros que va a utilizar
        Intent intentTemporizador = getIntent();
        if (intentTemporizador.getSerializableExtra("Temporizador") != null) {
            Temporizador temporizador = (Temporizador) intentTemporizador.getSerializableExtra("Temporizador");
            //Si tiene id de perfil, viene desde Perfiles
            if (temporizador.getId_perfil() != 0) {
                //Comprueba tablas datos
                Usuario user = Usuario.selectUsuarioByMail(Login.userLog.getEmail(), db.getReadableDatabase());
                AjustesUsuario a_user = AjustesUsuario.selectAjustesUsuarioByID(user.getId_usuario(), db.getReadableDatabase());
                Perfil perfil = Perfil.selectPerfilByID(temporizador.getId_perfil(), user.getId_usuario(), db.getReadableDatabase());
                AjustesPerfil a_perfil = AjustesPerfil.selectAjustesPerfilById(perfil.getId_perfil(), db.getReadableDatabase());

                //Inicializa las variables con la configuracion del usuario
                tiempo_Trabajo = perfil.getTiempo_trabajo();
                tiempo_Descanso = perfil.getTiempo_descanso();
                tiempo_Preparacion = perfil.getTiempo_preparacion();
                numero_Rondas = perfil.getRondas();
                nombreTrabajo = perfil.getNombre_perfil();

                //Si existen ajustes de usuario
                if(a_user.getId_ajustes()!=0){
                    volumen = a_user.getVolumen();
                }

                //Si existen ajustes de perfil
                if(a_perfil.getId_ajustes_perfil()!=0){
                    sonido = a_perfil.getSonido();
                    if(sonido==0)
                        sonidoID = sp.load(TimerActivity.this, R.raw.relax,1);
                    if(sonido==1)
                        sonidoID = sp.load(TimerActivity.this, R.raw.tono,1);
                    if(sonido==2)
                        sonidoID = sp.load(TimerActivity.this, R.raw.boxeo,1);

                    colorTrabajo = Color.parseColor(a_perfil.getColor_trabajo());
                    colorDescanso = Color.parseColor(a_perfil.getColor_descanso());
                    colorPreparacion = Color.parseColor(a_perfil.getColor_preparacion());
                }

            } else {
                //Viene desde Quickstart
                tiempo_Trabajo = temporizador.getTiempo_trabajo();
                tiempo_Descanso = temporizador.getTiempo_descanso();
                tiempo_Preparacion = temporizador.getTiempo_preparacion();
                numero_Rondas = temporizador.getNumero_rondas();
            }
        }

        //Prepara la logica del Temporizador
        tiempo_Inicial = tiempo_Preparacion;
        rondas_Iniciales = numero_Rondas;
        tiempo_Restante= tiempo_Inicial;
        rondas_Restantes = rondas_Iniciales;
        progressBar.setProgress(100);
        progressBar.setIndicatorColor(colorPreparacion);
        textView_TituloPerfilTimer.setText(nombrePreparacion);

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
            return true;
        });

        //Back
        iconButton_Back = findViewById(R.id.iconButton_Back);
        iconButton_Back.setOnClickListener(h -> {
            stopTimer();
            Intent intent = new Intent(TimerActivity.this, com.timesplit.Controlador.MainActivity.class);
            startActivity(intent);
        });


        iconButton_Previous_Ronda.setEnabled(false);
        iconButton_Next_Ronda.setEnabled(false);
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
        if(timer != null)
            timer.cancel();
        tiempo_Inicial = tiempo_Preparacion;
        isDescanso=false;
        isTrabajo = false;
        isPreparacion=true;
        progressBar.setIndicatorColor(colorPreparacion);
        textView_TituloPerfilTimer.setText(nombrePreparacion);
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
        timer = new CountDownTimer(tiempo_Restante, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                tiempo_Restante = millisUntilFinished;
                progress= (int) ((tiempo_Restante*100)/tiempo_Inicial);
                if(tiempo_Restante<1000){
                    progressBar.setProgressCompat(progress-10, true);
                }else{

                progressBar.setProgressCompat(progress-10, true);
                }
                actualizaTemporizadorTexto();
            }

            @Override
            public void onFinish() {
                //Si es la ultima ronda, para el temporizador
                if (rondas_Restantes==1){
                    //Reproduce sonido
                    sp.play(sonidoID, (float)volumen, (float)volumen, 1, 0, 1);
                    isTimerRunning = false;
                    //Si es un usuario logeado, actualiza estadisticas
                    if(Login.userLog.getId_usuario()!=0)
                        actualizaEstadisticas((int)tiempo_Trabajo, (int)tiempo_Descanso, 1);
                    textView_TituloPerfilTimer.setText("FINALIZADO");
                    iconButton_Play.setVisibility(View.VISIBLE);
                    iconButton_Pause.setVisibility(View.INVISIBLE);
                }else{
                    //Comienza con el tiempo de preparación
                    //A continuación, mientras haya rondas restantes, reproduce en bucle el tiempo de trabajo y el tiempo de descanso.
                    sp.play(sonidoID, (float)volumen, (float)volumen, 1, 0, 1);
                    progressBar.setProgressCompat(0, false);
                    if(isPreparacion){
                        isPreparacion = false;
                        isDescanso = false;
                        isTrabajo = true;
                        iconButton_Previous_Ronda.setEnabled(true);
                        iconButton_Next_Ronda.setEnabled(true);
                        tiempo_Inicial = tiempo_Trabajo;
                        textView_TituloPerfilTimer.setText(nombreTrabajo);
                        progressBar.setIndicatorColor(colorTrabajo);
                    }else if(isTrabajo){
                        isTrabajo = false;
                        isDescanso = true;
                        tiempo_Inicial = tiempo_Descanso;
                        textView_TituloPerfilTimer.setText(nombreDescanso);
                        progressBar.setIndicatorColor(colorDescanso);
                    }else if(isDescanso){
                        isDescanso = false;
                        isTrabajo = true;
                        tiempo_Inicial = tiempo_Trabajo;
                        textView_TituloPerfilTimer.setText(nombreTrabajo);
                        progressBar.setIndicatorColor(colorTrabajo);
                        rondas_Restantes--;
                        actualizarRondasTexto();
                        //Si es un usuario logeado, actualiza estadisticas
                        if(Login.userLog.getId_usuario()!=0)
                            actualizaEstadisticas((int)tiempo_Trabajo, (int)tiempo_Descanso, 1);
                    }

                    tiempo_Restante = tiempo_Inicial;
                    progressBar.setProgressCompat(100, false);
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

    private void actualizaEstadisticas(int tiempo_Trabajo, int tiempo_Descanso, int ronda){
        BD db = new BD(TimerActivity.this);
        Usuario user = Usuario.selectUsuarioByMail(Login.userLog.getEmail(), db.getReadableDatabase());
        Estadisticas estadisticas = Estadisticas.selectEstadisticasUsuario(user.getId_usuario(), db.getReadableDatabase());
        tiempo_Trabajo += estadisticas.getTotal_trabajo();
        tiempo_Descanso += estadisticas.getTotal_descanso();
        ronda += estadisticas.getTotal_rondas();
        int numeroPerfiles = Perfil.listaPerfiles(user.getId_usuario(), db.getReadableDatabase()).size();
        Estadisticas newStats = new Estadisticas(numeroPerfiles, tiempo_Trabajo, tiempo_Descanso, ronda, user.getId_usuario());
        Estadisticas.updateEstadisticas(newStats, db.getWritableDatabase());
    }
}