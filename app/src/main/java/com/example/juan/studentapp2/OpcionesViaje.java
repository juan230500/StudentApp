package com.example.juan.studentapp2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/**
 * Esta calse permite visualizar las opciones de viajes disponibles
 */
public class OpcionesViaje extends AppCompatActivity {

    private String viaje="20";
    private String Carnet="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_viaje);
        recibirDatos();
    }
    /**
     * Este método recibe los datos extras de validación
     */
    public void recibirDatos(){
        Bundle extras=getIntent().getExtras();
        Carnet=extras.getString("Carnet");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            OutputStreamWriter archivo_wr = new OutputStreamWriter(openFileOutput("miviajeS.txt", Activity.MODE_PRIVATE));
            archivo_wr.write("20");
            archivo_wr.flush();
            archivo_wr.close();
        } catch (IOException e){}
    }
    /**
     * Este método almacena en ficheros la opción de viaje con un estudiante cualquiera
     * @param view
     */
    public void guardarCE(View view){
        Toast.makeText(this, "Ha seleccionado viaje con un estudiante cualquiera.", Toast.LENGTH_SHORT).show();
        try {
            OutputStreamWriter archivo_wr = new OutputStreamWriter(openFileOutput("miviajeS.txt", Activity.MODE_PRIVATE));
            archivo_wr.write("0");
            archivo_wr.flush();
            archivo_wr.close();
        } catch (IOException e){}
        Intent i=new Intent(getApplicationContext(),Carpooling.class);
        i.putExtra("Carnet",Carnet);
        startActivity(i);
    }
    /**
     * Este método almacena en ficheros la opción de viaje con amigo
     * @param view
     */
    public void guardarCA(View view){
        Toast.makeText(this, "Ha seleccionado viaje con un amigo.", Toast.LENGTH_SHORT).show();
        try {
            OutputStreamWriter archivo_wr = new OutputStreamWriter(openFileOutput("miviajeS.txt", Activity.MODE_PRIVATE));
            archivo_wr.write("1");
            archivo_wr.flush();
            archivo_wr.close();
        } catch (IOException e){}
        Intent i=new Intent(getApplicationContext(),Carpooling.class);
        i.putExtra("Carnet",Carnet);
        startActivity(i);
    }
    /**
     * Este método lee el viaje escogido guardado en ficheros
     */
    public void abrir(){
        try {
            InputStreamReader archivo_rd = new InputStreamReader(openFileInput("miviaje.txt"));
            BufferedReader br = new BufferedReader(archivo_rd);
            viaje = br.readLine();
        } catch (IOException e){}
    }
}
