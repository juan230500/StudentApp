package com.example.juan.studentapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registro(View view){
        Intent i=new Intent(getApplicationContext(),CodigoBarras.class);
        startActivity(i);
    }
    public void carpooling(View view){

    }public void desplazamiento(View view){

    }public void calificar(View view){

    }public void calificacion(View view){

    }
    public void amigos(View view){

    }
}
