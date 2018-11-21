package com.example.juan.studentapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ImageView img;
    private float y;
    private float x;
    private float xf;
    private float yf;
    private float m;
    private float b;
    private Handler handler=new Handler();
    private Timer timer=new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=findViewById(R.id.image);
    }

    public void go(View view) {
        y=100;
        x=100;
        xf=200;
        yf=500;
        m=(yf-y)/(xf-x);
        b=y-m*x;
        //https://www.youtube.com/watch?v=UxbJKNjQWD8
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (x<xf){
                            x+=2;
                            y=x*m+b;
                            img.setX(x);
                            img.setY(y);
                        }
                    }
                });
            }
        },0,50);
    }

}
