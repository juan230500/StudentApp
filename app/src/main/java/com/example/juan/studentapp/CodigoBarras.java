package com.example.juan.studentapp;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.Result;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
/*
public void GoRegistro(View view){


    }
    public void GoCarpooling(View view){

    }public void GoDesplazamiento(View view){

    }public void GoCalificar(View view){

    }public void GoCalificacion(View view){

    }
    public void GoAmigos(View view){

    }
<Button
        android:id="@+id/desplazamiento"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="60dp"
        android:layout_marginBottom="52dp"
        android:text="Desplazamiento"
        android:textSize="14sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent" />

    <Button
        android:id="@+id/carpooling"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="60dp"
        android:layout_marginBottom="40dp"
        android:text="Carpooling"
        android:textSize="14sp"
        app:layout_constraintBottom_toTopOf="@+id/desplazamiento"
        app:layout_constraintStart_toStartOf="parent" />

    <Button
        android:id="@+id/registro"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="60dp"
        android:layout_marginBottom="40dp"
        android:text="Regristro"
        android:textSize="14sp"
        app:layout_constraintBottom_toTopOf="@+id/carpooling"
        app:layout_constraintStart_toStartOf="parent" />

    <Button
        android:id="@+id/calificar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginEnd="60dp"
        android:layout_marginBottom="40dp"
        android:text="Calificar"
        android:textSize="14sp"
        app:layout_constraintBottom_toTopOf="@+id/calificacion"
        app:layout_constraintEnd_toEndOf="parent" />

    <Button
        android:id="@+id/calificacion"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginEnd="60dp"
        android:layout_marginBottom="40dp"
        android:text="Calificación"
        android:textSize="14sp"
        app:layout_constraintBottom_toTopOf="@+id/amigos"
        app:layout_constraintEnd_toEndOf="parent" />

    <Button
        android:id="@+id/amigos"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginEnd="60dp"
        android:layout_marginBottom="52dp"
        android:text="Amigos"
        android:textSize="14sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />
 */

public class CodigoBarras extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private String codigoBarras;
    private ZXingScannerView scannerView;
    private int codigoPermiso = 1;
    private boolean registrado = false;


    public void Escanear(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            if (!registrado) {
                this.scannerView = new ZXingScannerView(this);
                setContentView(this.scannerView);
                scannerView.setResultHandler(this);
                scannerView.startCamera();
                registrado = true;
            } else {
                new AlertDialog.Builder(this)
                        .setTitle("Ya está registrado")
                        .setMessage("El carné " + codigoBarras + " ya ha sido registrado")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
            }
        } else {
            pedirPermiso();
        }

    }

    public void pedirPermiso() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permisos de cámara desactivados")
                    .setMessage("Se requieren permisos del uso de cámara para escanear el código de barras en su carné del TEC")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(CodigoBarras.this, new String[]{Manifest.permission.CAMERA}, codigoPermiso);
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, codigoPermiso);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_barras);
    }

    public void handleResult(Result result) {
        scannerView.stopCamera();
        setContentView(R.layout.activity_codigo_barras);
        if (result.getText().length() != 10) {
            new AlertDialog.Builder(this)
                    .setTitle("El codigo escaneado no pertenece a un carné TEC")
                    .setMessage("Por favor intentelo de nuevo")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();
        } else {
            this.codigoBarras = result.getText();
            new AlertDialog.Builder(this)
                    .setTitle("Su carné es")
                    .setMessage(this.codigoBarras)
                    .setPositiveButton("Correcto", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            guardar();
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            new AlertDialog.Builder(CodigoBarras.this)
                                    .setTitle("Vuelva a intentarlo")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    }).create().show();
                        }
                    })
                    .create().show();
        }
    }

    public void guardar(){
        registrar();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Se ha registrado el carné");
        builder.setMessage(codigoBarras);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Toast.makeText(this, "Amigo agregado!", Toast.LENGTH_LONG);
        try {
            OutputStreamWriter archivo_wr = new OutputStreamWriter(openFileOutput("micarne.txt", Activity.MODE_PRIVATE));
            archivo_wr.write(codigoBarras);
            archivo_wr.flush();
            archivo_wr.close();
        } catch (IOException e){}
    }

    public void registrar(){
        Toast.makeText(this, "Amigo agregado!", Toast.LENGTH_LONG);
        String REST_URI  = "http://192.168.100.13:8080/ServidorTEC/webapi/myresource/Carne";

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
                        Toast.makeText(CodigoBarras.this,
                                "Sent "+error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Carne", "" + codigoBarras);

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

}
