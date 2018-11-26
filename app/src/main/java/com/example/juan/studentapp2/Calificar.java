package com.example.juan.studentapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
/**
 * Esta clase corresponde a la pantalla en donde se calificará el últio viaje
 */
public class Calificar extends AppCompatActivity {
    private RatingBar Calificacion;
    private String conductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificar);
        abrirConductor();
        Calificacion = (RatingBar) findViewById(R.id.ratingBar);
    }

    /**
     * Este método se encarga de validar si ya se ha calificado o no el viaje para poder enviar al servidor
     * @param view corresponde al view de la aplicacion
     */
    public void enviarCalificacion(View view) {
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "la puntuacion a enviar es de  " + Calificacion.getRating(), Toast.LENGTH_SHORT);
        toast1.show();
        enviar();

    }

    /**
     * Este método se encarga de regresar a la pantalla principal
     * @param view este corresponde al view de la aplicación
     */
    public void regresar(View view) {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    /**
     * Este método se encarga de enviar la calificación correspondiente a los carnes de los pasajeros en el servidor
     */
    public void enviar() {
        Toast.makeText(this, "Ya se envio una calificacion!", Toast.LENGTH_LONG);
        String REST_URI = "http://192.168.100.12:8080/ServidorTEC/webapi/myresource/Calificar";

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REST_URI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Calificar.this,
                                "Sent " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Carne", "" + conductor);
                params.put("Calificacion", "" + Calificacion.getRating());
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    /**
     * Esta clase se encarga de abrir el fichero en donde se encuentran los carnes de los pasajeros que realizaron el último viaje
     */
    public void abrirConductor(){
        try {
            InputStreamReader archivo_rd = new InputStreamReader(openFileInput("miconductor.txt"));
            BufferedReader br = new BufferedReader(archivo_rd);
            conductor = br.readLine();
        } catch (IOException e){}
    }
}