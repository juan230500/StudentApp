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

import java.util.HashMap;
import java.util.Map;

public class Calificacion extends AppCompatActivity {
private RatingBar Calificacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificacion);
        Calificacion=(RatingBar)findViewById(R.id.ratingBar);
    }
public void enviarCalificacion(View view){
    Toast toast1 =
            Toast.makeText(getApplicationContext(),
                    "la puntuacion a enviar es de  "+Calificacion.getRating(), Toast.LENGTH_SHORT);
    toast1.show();
    enviar();

    }
    public void regresar(View view){
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
    public void enviar(){
        Toast.makeText(this, "Ya se envio una calificacion!", Toast.LENGTH_LONG);
        String REST_URI  = "http://192.168.100.12:8080/ServidorTEC/webapi/myresource/Calificar";

        RequestQueue requestQueue=Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REST_URI,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Calificacion.this,
                                "Sent "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Carne", "" + 2018319311);
                params.put("Calificacion", "" + Calificacion.getRating());
                return params;
            }
        };

        requestQueue.add(stringRequest);
    }
}
