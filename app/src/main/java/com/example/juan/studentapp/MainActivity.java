package com.example.juan.studentapp;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class MainActivity extends AppCompatActivity {
    private ImageView img;
    private int y=0;
    private int x=0;
    private Handler handler=new Handler();
    private Timer timer=new Timer();
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=findViewById(R.id.image);


        btn=findViewById(R.id.button);

    }

    public void go(View view) {
        /*String REST_URI  = "http://192.168.100.4:8080/ServidorTEC/webapi/myresource/Mapa";

        Client client = ClientBuilder.newClient();

        String r=client
                .target(REST_URI)
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);*/

        String REST_URI  = "http://192.168.100.4:8080/ServidorTEC/webapi/myresource/Mapa";

        RequestQueue requestQueue=Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REST_URI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();;
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Response :" + error.toString(), Toast.LENGTH_LONG).show();
            }
        });


        requestQueue.add(stringRequest);








        //https://www.youtube.com/watch?v=UxbJKNjQWD8
        /*timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        x+=1;
                        img.setX(x);
                    }
                });
            }
        },0,5);*/
    }

}
