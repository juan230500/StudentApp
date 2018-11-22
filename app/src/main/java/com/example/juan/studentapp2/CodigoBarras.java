package com.example.juan.studentapp2;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.zxing.Result;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CodigoBarras extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private String codigoBarras;
    private ZXingScannerView scannerView;
    private int codigoPermiso = 1;
    private boolean registrado = false;
    private LoginButton loginButton;
    private CallbackManager callbackManager;


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

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(getApplicationContext(),"Login existoso",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(),R.string.com_facebook_smart_login_confirmation_cancel,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(getApplicationContext(),"Error en inicio de sesión",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
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

