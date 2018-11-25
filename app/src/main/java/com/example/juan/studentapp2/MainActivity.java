package com.example.juan.studentapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean registroCarnet=false;
    private boolean regsitroFacebook=false;
    private String Carnet="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recibirDatos();
    }

    public void registro(View view){
        Intent i=new Intent(getApplicationContext(),CodigoBarras.class);
        startActivity(i);
    }
    public void recibirDatos(){
        Bundle extras=getIntent().getExtras();
        registroCarnet=extras.getBoolean("RegistroCarnet");
        regsitroFacebook=extras.getBoolean("RegistroFace");

    }
    public void carpooling( View view){
        if (registroCarnet&&regsitroFacebook){
            Intent i=new Intent(getApplicationContext(),Carpooling.class);
            startActivity(i);
        }
        else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Por favor registrese primero", Toast.LENGTH_SHORT);

            toast1.show();
        }

    }public void desplazamiento(View view){

        if (registroCarnet&&regsitroFacebook){
            Intent i=new Intent(getApplicationContext(),Desplazamiento.class);
            startActivity(i);
        }
        else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Por favor registrese primero", Toast.LENGTH_SHORT);

            toast1.show();
        }

    }public void calificar(View view){

    }public void calificacion(View view){

    }
    public void amigos(View view){

        if (registroCarnet&&regsitroFacebook){
            Intent i=new Intent(getApplicationContext(),ListaAmigos.class);
            startActivity(i);
        }
        else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Por favor registrese primero", Toast.LENGTH_SHORT);

            toast1.show();
        }
    }
}
