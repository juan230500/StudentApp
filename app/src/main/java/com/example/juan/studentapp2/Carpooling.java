package com.example.juan.studentapp2;

import android.app.Activity;
import android.graphics.PointF;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Carpooling extends AppCompatActivity {
    private String Carnet="";
    private EditText entrada;
    private Button Button0;
    private Button Button1;
    private Button Button2;
    private Button Button3;
    private Button Button4;
    private Button Button5;
    private Button Button6;
    private Button Button7;
    private Button Button8;
    private Button Button9;
    private Button Button10;
    private Button Button11;
    private Button Button12;
    private Button Button13;
    private Button Button14;
    private Button Button15;
    private Button Button16;
    private Button Button17;
    private Button Button18;
    private Button Button19;
    private Button Button20;
    private Button Button21;
    private Button Button22;
    private Button Button23;
    private Button Button24;
    private Button Button25;
    private Button Button26;
    private Button Button27;
    private Button Button28;
    private Button Button29;
    private Button Button30;
    private Handler handler=new Handler();
    private Timer timer=new Timer();
    private LineView linea;
    private int[][] Mapa;
    private String Matriz="";
    private Button img;
    private float y;
    private float x;
    private float xf;
    private float yf;
    private float m;
    private float b;
    private Button[]botones;
    int posicionLugar;
    private int posActual;
    private String viaje;
    private String carne;
    private String conductor;
    private int[] Ruta;
    private int[] Tiempos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carpooling);
        entrada=(EditText)findViewById(R.id.editText);
        abrir();
        Toast.makeText(Carpooling.this,
                "Preferencia de viaje: "+viaje+", Carne: "+carne, Toast.LENGTH_SHORT).show();
        String REST_URI  = "http://192.168.100.12:8080/ServidorTEC/webapi/myresource/Mapa";
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, REST_URI,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Gson gson=new Gson();
                        Mapa=gson.fromJson(response,int[][].class);
                        Matriz=response;
                        Toast.makeText(Carpooling.this,
                                "Sent "+response, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Carpooling.this,
                                "Sent "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(stringRequest);
        recibirDatos();
        img=(Button)findViewById(R.id.buttonCarro);
        Button0=(Button)findViewById(R.id.button0);
        Button1=(Button)findViewById(R.id.button1);
        Button2=(Button)findViewById(R.id.button2);
        Button3=(Button)findViewById(R.id.button3);
        Button4=(Button)findViewById(R.id.button4);
        Button5=(Button)findViewById(R.id.button5);
        Button6=(Button)findViewById(R.id.button6);
        Button7=(Button)findViewById(R.id.button7);
        Button8=(Button)findViewById(R.id.button8);
        Button9=(Button)findViewById(R.id.button9);
        Button10=(Button)findViewById(R.id.button10);
        Button11=(Button)findViewById(R.id.button11);
        Button12=(Button)findViewById(R.id.button12);
        Button13=(Button)findViewById(R.id.button13);
        Button14=(Button)findViewById(R.id.button14);
        Button15=(Button)findViewById(R.id.button15);
        Button16=(Button)findViewById(R.id.button16);
        Button17=(Button)findViewById(R.id.button17);
        Button18=(Button)findViewById(R.id.button18);
        Button19=(Button)findViewById(R.id.button19);
        Button20=(Button)findViewById(R.id.button20);
        Button21=(Button)findViewById(R.id.button21);
        Button22=(Button)findViewById(R.id.button22);
        Button23=(Button)findViewById(R.id.button23);
        Button24=(Button)findViewById(R.id.button24);
        Button25=(Button)findViewById(R.id.button25);
        Button26=(Button)findViewById(R.id.button26);
        Button27=(Button)findViewById(R.id.button27);
        Button28=(Button)findViewById(R.id.button28);
        Button29=(Button)findViewById(R.id.button29);
        Button30=(Button)findViewById(R.id.button30);
          Button []B={Button0,Button1,Button2,Button3,Button4,Button5,Button6,Button7,Button8,Button9,
                Button10,Button11,Button12,Button13,Button14,Button15,Button16,Button17,Button18,
                Button19,Button20,Button21,Button22,Button23,Button24,Button25,Button26,Button27,
                Button28,Button29,Button30};
          botones=B;
    }
    /*
    1.Registrar residencia
    2.Toast con caminos por el mapa
    3.arrays con xy de los nodos
    4.Bitacora XD
     */
    public void recibirDatos(){
        Bundle extras=getIntent().getExtras();
        Carnet=extras.getString("Carnet");
    }
    public void giveMeConexiones(View view){
        int  response=Mapa[1][1];
        if (!entrada.getText().equals("")){
            String valor=entrada.getText().toString();
            int nodo_a_Verificar=Integer.parseInt(valor);
            String resultado="";
            int Fila=30;
            while (Fila!=0){
                if (Mapa[Fila][nodo_a_Verificar]!=0){
                    resultado+="=>"+Fila+"("+Mapa[Fila][nodo_a_Verificar]+")\n";
                }
                Fila-=1;
            }
            Toast.makeText(Carpooling.this,
                    resultado, Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(Carpooling.this,
                    "Porfa llene el espacio de texto", Toast.LENGTH_LONG).show();
        }
    }
    public void prueba(View view){
        int Tiempos[]={1,9,3,0};
        int Rutas[]={23,12,1,0};
        go(Rutas,Tiempos);
    }
    public int calcularSumando(float posInicial,float posFinal,int tiempo){

        if (posFinal>posInicial){
            return 10-tiempo;
        }
        else{
            return -10+tiempo;
        }
    }


    public void go( final int lugares[],final int tiempos[]) {
        posicionLugar=0;
        posActual=lugares[posicionLugar];
        y = botones[posActual].getY();
        x=botones[posActual].getX();
        xf=botones[lugares[posicionLugar+1]].getX();
        yf=botones[lugares[posicionLugar+1]].getY();
        m=(yf-y)/(xf-x);
        b=y-m*x;
        Toast toast1 =
                Toast.makeText(getApplicationContext(),
                        "buton inicio"+botones[posActual].getText()+"el y es"+botones[posActual].getY(), Toast.LENGTH_SHORT);

        toast1.show();
        //https://www.youtube.com/watch?v=UxbJKNjQWD8
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (posicionLugar<lugares.length-1){
                            if (Math.abs(xf-x)>10){
                                x+=calcularSumando(x,xf,tiempos[posicionLugar]);
                                y=x*m+b;
                                img.setX(x);
                                img.setY(y);
                                PointF pointA = new PointF(img.getX()+25, img.getY()+25);
                                PointF pointB = new PointF(xf, yf);
                                linea=(LineView) findViewById(R.id.lineView);
                                linea.setPointA(pointA);
                                linea.setPointB(pointB);
                                linea.draw();
                            }
                            else if(posicionLugar+1<lugares.length-1){
                                PointF pointA = new PointF(0, 0);
                                PointF pointB = new PointF(0, 0);
                                linea=(LineView) findViewById(R.id.lineView);
                                linea.setPointA(pointA);
                                linea.setPointB(pointB);
                                linea.draw();
                                posicionLugar=posicionLugar+1;
                                posActual=lugares[posicionLugar];
                                y = botones[posActual].getY();
                                x=botones[posActual].getX();
                                xf=botones[lugares[posicionLugar+1]].getX();
                                yf=botones[lugares[posicionLugar+1]].getY();
                                m=(yf-y)/(xf-x);
                                b=y-m*x;

                            }
                            else{
                                PointF pointA = new PointF(0, 0);
                                PointF pointB = new PointF(0, 0);
                                linea=(LineView) findViewById(R.id.lineView);
                                linea.setPointA(pointA);
                                linea.setPointB(pointB);
                                linea.draw();

                            }

                        }


                    }
                });
            }
        },0,50);
    }
    public void abrir(){
        try {
            InputStreamReader archivo_rd = new InputStreamReader(openFileInput("micarne.txt"));
            BufferedReader br = new BufferedReader(archivo_rd);
            carne = br.readLine();
        } catch (IOException e){}
        try {
            InputStreamReader archivo_rd = new InputStreamReader(openFileInput("miviajeS.txt"));
            BufferedReader br = new BufferedReader(archivo_rd);
            viaje = br.readLine();
        } catch (IOException e){}
    }

    public void SentGPS(View view){
       /* Button0=(Button)findViewById(R.id.button0);
        Button1=(Button)findViewById(R.id.button1);
        Button2=(Button)findViewById(R.id.button2);
        Button3=(Button)findViewById(R.id.button3);
        Button4=(Button)findViewById(R.id.button4);
        Button5=(Button)findViewById(R.id.button5);
        Button6=(Button)findViewById(R.id.button6);
        Button7=(Button)findViewById(R.id.button7);
        Button8=(Button)findViewById(R.id.button8);
        Button9=(Button)findViewById(R.id.button9);
        Button10=(Button)findViewById(R.id.button10);
        Button11=(Button)findViewById(R.id.button11);
        Button12=(Button)findViewById(R.id.button12);
        Button13=(Button)findViewById(R.id.button13);
        Button14=(Button)findViewById(R.id.button14);
        Button15=(Button)findViewById(R.id.button15);
        Button16=(Button)findViewById(R.id.button16);
        Button17=(Button)findViewById(R.id.button17);
        Button18=(Button)findViewById(R.id.button18);
        Button19=(Button)findViewById(R.id.button19);
        Button20=(Button)findViewById(R.id.button20);
        Button21=(Button)findViewById(R.id.button21);
        Button22=(Button)findViewById(R.id.button22);
        Button23=(Button)findViewById(R.id.button23);
        Button24=(Button)findViewById(R.id.button24);
        Button25=(Button)findViewById(R.id.button25);
        Button26=(Button)findViewById(R.id.button26);
        Button27=(Button)findViewById(R.id.button27);
        Button28=(Button)findViewById(R.id.button28);
        Button29=(Button)findViewById(R.id.button29);
        Button30=(Button)findViewById(R.id.button30);
        final Button[] B={Button0,Button1,Button2,Button3,Button4,Button5,Button6,Button7,Button8,Button9,
                Button10,Button11,Button12,Button13,Button14,Button15,Button16,Button17,Button18,
                Button19,Button20,Button21,Button22,Button23,Button24,Button25,Button26,Button27,
                Button28,Button29,Button30};*/
        //Toast.makeText(Carpooling.this,"hola"+B[2].getX(), Toast.LENGTH_LONG).show();

        String REST_URI  = "http://192.168.100.12:8080/ServidorTEC/webapi/myresource/Espera";

        RequestQueue requestQueue=Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REST_URI,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Toast.makeText(Carpooling.this,response, Toast.LENGTH_LONG).show();
                        ConsultarEspera();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Carpooling.this,
                                "Sent "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Residencia", ""+entrada.getText());
                params.put("Carne",carne);
                params.put("SoloAmigos",viaje);

                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    public void ConsultarEspera(){
        String REST_URI  = "http://192.168.100.12:8080/ServidorTEC/webapi/myresource/SeguirEsperando";

        RequestQueue requestQueue=Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REST_URI,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(final String response) {
                        if (response.length() < 2) {
                            new CountDownTimer(1000, 1000) {

                                public void onTick(long millisUntilFinished) {

                                }
                                public void onFinish() {
                                    ConsultarEspera();
                                }
                            }.start();

                        }
                        else{
                            Toast.makeText(Carpooling.this,"ASIGNADO"+response, Toast.LENGTH_SHORT).show();
                            parsear(response);
                            go(Ruta,Tiempos);
                        }




                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Carpooling.this,
                                "Sent "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Carne",carne);
                params.put("SoloAmigos",viaje);

                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    public void parsear(String response){
        JsonParser parser = new JsonParser();
        JsonElement rootNode = parser.parse(response);


        if (rootNode.isJsonObject()) {
            JsonObject details = rootNode.getAsJsonObject();

            JsonElement conductor=details.get("Conductor");
            this.conductor=conductor.getAsString();
            guardarConductor();

            JsonArray RutaDetails = details.getAsJsonArray("Ruta");

            Ruta=new int[RutaDetails.size()];
            Tiempos=new int[RutaDetails.size()];

            for (int i = 0; i < RutaDetails.size(); i++) {
                JsonPrimitive value = RutaDetails.get(i).getAsJsonPrimitive();
                Ruta[i]=value.getAsInt();
            }

            JsonArray TiemposDetails = details.getAsJsonArray("Tiempos");

            for (int i = 0; i < TiemposDetails.size(); i++) {
                JsonPrimitive value = TiemposDetails.get(i).getAsJsonPrimitive();
                Tiempos[i]=value.getAsInt();
            }

        }
    }

    public void guardarConductor(){
        try {
            OutputStreamWriter archivo_wr = new OutputStreamWriter(openFileOutput("miconductor.txt", Activity.MODE_PRIVATE));
            archivo_wr.write(conductor);
            archivo_wr.flush();
            archivo_wr.close();
        } catch (IOException e){}
    }
}
