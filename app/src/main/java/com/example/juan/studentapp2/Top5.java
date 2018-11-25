package com.example.juan.studentapp2;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;

public class Top5 extends AppCompatActivity {

    private ArrayList<Amigo> amigos = new ArrayList<Amigo>();
    private LinearLayout contenedor;
    private String carne = "2018135360";
    private String ip = "192.168.100.12";
    private String [] carnes;
    private String [] calif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_amigos);
        this.contenedor = (LinearLayout)findViewById(R.id.lloCont);
        getLista();



    }
    public void mostrarAmigos (){
        int index = 0;
        for (String b: carnes){
            if (b != null) {
                TextView txt = new TextView(this);
                txt.setText((index+1) + ": " + b.substring(1) + "\n" + "Viajes: " + calif[index]);
                txt.setBackgroundColor(Color.parseColor("#e1e1e1"));
                txt.setTextColor(Color.parseColor("#000000"));
                txt.setTextSize(26);
                contenedor.addView(txt);
                index++;
            } else {
                return;
            }
        }
    }
    public void getLista (){

        RequestQueue requestQueue=Volley.newRequestQueue(this);
        String REST_URI  = "http://" + ip + ":8080/ServidorTEC/webapi/myresource/Top5";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, REST_URI,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        parsear(response);
                        mostrarAmigos();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Top5.this,
                                "Sent "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );

        requestQueue.add(stringRequest);
    }
    public void parsear(String response){
        Gson gson=new Gson();
        String [][] S = gson.fromJson(response, String [][].class);
        carnes = S[0];
        calif = S[1];
    }
}

