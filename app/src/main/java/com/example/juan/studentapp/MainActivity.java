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
    private int y=0;
    private int x=0;
    private Handler handler=new Handler();
    private Timer timer=new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=findViewById(R.id.image);
    }

    public void go(View view) {
        //https://www.youtube.com/watch?v=UxbJKNjQWD8
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        x+=1;
                        img.setX(x);
                    }
                });
            }
        },0,5);
    }

}
