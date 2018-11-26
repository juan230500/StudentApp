package com.example.juan.studentapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * Esta calse corresponde a la pantalla en donde se mostraran todas las opciones a las que se puede acceder
 * */

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

    /**
     * Este método permite ingresar a la actividad de registro
     * @param view
     */
    public void registro(View view){
        Intent i = new Intent(getApplicationContext(),CodigoBarras.class);
        startActivity(i);
    }
    /**
     * Este método recibe los datos extras de validación
     */
    public void recibirDatos(){
        Bundle extras=getIntent().getExtras();
        registroCarnet=extras.getBoolean("RegistroCarnet");
        regsitroFacebook=extras.getBoolean("RegistroFace");
        Carnet=extras.getString("Carnet");
    }

    /**
     * Este método permite ingresar a la actividad de top 5
     * @param view
     */
    public void top5(View view) {
        Intent i = new Intent(getApplicationContext(), Top5.class);
        i.putExtra("Carnet", Carnet);
        startActivity(i);
        if (true) {

        } else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Por favor registrese primero", Toast.LENGTH_SHORT);

            toast1.show();
        }
    }

    /**
     * Este método permite ingresar a la actividad de carpooling
     * @param view
     */
    public void carpooling(View view){
        if (true){
            Intent i=new Intent(getApplicationContext(),OpcionesViaje.class);
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

    /**
     * Este método permite ingresar a la actividad de calificar
     * @param view
     */
    public void calificar(View view){
        Intent i = new Intent(getApplicationContext(),Calificar.class);
        startActivity(i);
    }

    /**
     * Este método permite ingresar a la actividad de calificación
     * @param view
     */
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

    /**
     * Este método permite ingresar a la actividad de lista de amigos
     * @param view
     */
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
