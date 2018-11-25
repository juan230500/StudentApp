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

public class OpcionesViaje extends AppCompatActivity {

    private String viaje="20";
    private String Carnet="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones_viaje);
        recibirDatos();
    }
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
    public void guardarCA(View view){
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
    public void guardarCE(View view){
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
    public void abrir(){
        try {
            InputStreamReader archivo_rd = new InputStreamReader(openFileInput("miviaje.txt"));
            BufferedReader br = new BufferedReader(archivo_rd);
            viaje = br.readLine();
        } catch (IOException e){}
    }
}
