package com.example.juan.studentapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
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

public class Calificacion extends AppCompatActivity {

    private TextView txtv;
    private String ip = "192.168.100.12";
    private String carne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calificacion);
        abrir();
        this.txtv = (TextView)findViewById(R.id.txtCalif);
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        String REST_URI  = "http://" + ip + ":8080/ServidorTEC/webapi/myresource/CalificacionPropia";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REST_URI,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        Double num = (Double.parseDouble(response)) * 0.01;
                        txtv.setText(num.toString());
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
                params.put("Carne", "" + carne);

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void abrir(){
        try {
            InputStreamReader archivo_rd = new InputStreamReader(openFileInput("micarne.txt"));
            BufferedReader br = new BufferedReader(archivo_rd);
            carne = br.readLine();
        } catch (IOException e){}
    }

    public void getCalif (){

        RequestQueue requestQueue=Volley.newRequestQueue(this);
        String REST_URI  = "http://" + ip + ":8080/ServidorTEC/webapi/myresource/CalificacionPropia";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REST_URI,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {

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
                params.put("Carne", "" + carne);

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }


}
