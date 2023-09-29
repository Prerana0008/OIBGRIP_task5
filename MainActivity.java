package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends AppCompatActivity {
    private boolean isRunning = false;
    private long pauseOffset = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Chronometer chronometer = findViewById(R.id.custom_chronometer);
        Button b1,b2,b3;
        b1=findViewById(R.id.btn_start);
        b2=findViewById(R.id.btn_hold);
        b3=findViewById(R.id.btn_stop);

        chronometer.setFormat("Timer : %s");
        chronometer.setBase(SystemClock.elapsedRealtime());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isRunning){
                    //it helps to start the timer from zero
                    chronometer.setBase(SystemClock.elapsedRealtime()-pauseOffset);
                    chronometer.start();
                    isRunning= true; // it'll stop the timer after starting
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isRunning){
                    chronometer.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();  //pause = current time - time where it started setbase
                    isRunning = false;  //it'll start again the timer after pausing
                }
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.stop();
                pauseOffset = 0;
                isRunning = false;
            }
        });
    }
}
