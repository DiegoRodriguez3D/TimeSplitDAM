package com.timesplit.vista;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.timesplit.R;

import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity {
private Button iconButton_Back;
private CircularProgressIndicator progressBar;
private int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        //Databinding
        progressBar = findViewById(R.id.Progress_bar);
        progressBar.setIndeterminate(false);


        time = 200; // 10 = 1 segundo
        setProgressBar(time);




        //Back
        iconButton_Back = findViewById(R.id.iconButton_Back);
        iconButton_Back.setOnClickListener(h -> {
            Intent intent = new Intent(TimerActivity.this, com.timesplit.vista.MainActivity.class);
            startActivity(intent);
        });
    }

    private void setProgressBar(int time) {
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            int progress;
            @Override
            public void run() {
                progress += 1;
                progressBar.setProgressCompat(progress, true);

            }
        };
        timer.schedule(task, 100, time);
    }
}