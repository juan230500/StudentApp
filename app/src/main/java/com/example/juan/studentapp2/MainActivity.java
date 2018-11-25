package com.example.juan.studentapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private boolean registroCarnet=true;
    private boolean regsitroFacebook=true;
    private String Carnet="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recibirDatos();
    }

    public void registro(View view){
        Intent i = new Intent(getApplicationContext(),CodigoBarras.class);
        startActivity(i);
    }
    public void recibirDatos(){
        Bundle extras=getIntent().getExtras();
        registroCarnet=extras.getBoolean("RegistroCarnet");
        regsitroFacebook=extras.getBoolean("RegistroFace");
        Carnet=extras.getString("Carnet");
    }

    public void top5(View view) {
        Intent i = new Intent(getApplicationContext(), Top5.class);
        i.putExtra("Carnet", Carnet);
        startActivity(i);
        if (registroCarnet && regsitroFacebook) {

        } else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Por favor registrese primero", Toast.LENGTH_SHORT);

            toast1.show();
        }
    }

    public void carpooling(View view){
        if (registroCarnet&&regsitroFacebook){
            Intent i=new Intent(getApplicationContext(),Carpooling.class);
            i.putExtra("Carnet",Carnet);
            startActivity(i);
        }
        else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Por favor registrese primero", Toast.LENGTH_SHORT);

            toast1.show();
        }

    }

    public void desplazamiento(View view){

        if (registroCarnet&&regsitroFacebook){
            Intent i=new Intent(getApplicationContext(),Desplazamiento.class);
            i.putExtra("Carnet",Carnet);
            startActivity(i);
        }
        else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Por favor registrese primero", Toast.LENGTH_SHORT);

            toast1.show();
        }

    }

    public void calificar(View view){
        Intent i = new Intent(getApplicationContext(),Calificar.class);
        startActivity(i);
    }

    public void calificacion(View view){

        if (true){
            Intent i=new Intent(getApplicationContext(),Calificacion.class);
            i.putExtra("Carnet",Carnet);
            startActivity(i);
        }
        else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Por favor registrese primero", Toast.LENGTH_SHORT);

            toast1.show();
        }

    }

    public void amigos(View view){
        Intent i=new Intent(getApplicationContext(),ListaAmigos.class);
        i.putExtra("Carnet",Carnet);
        startActivity(i);
        if (registroCarnet&&regsitroFacebook){

        }
        else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Por favor registrese primero", Toast.LENGTH_SHORT);

            toast1.show();
        }
    }
}
