package com.example.juan.studentapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class CodigoBarras extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private EditText codigoBarras;
    private ZXingScannerView scannerView;
    private int codigoPermiso = 1;


    public void Escanear(View view){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            this.scannerView = new ZXingScannerView(this);
            setContentView(this.scannerView);
            scannerView.setResultHandler(this);
            scannerView.startCamera();
        } else {
            pedirPermiso();
        }

    }

    public void pedirPermiso(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){

            new AlertDialog.Builder(this)
                    .setTitle("Permisos de cámara desactivados")
                    .setMessage("Se requieren permisos del uso de cámara para escanear el código de barras en su carné del TEC")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(CodigoBarras.this, new String[] {Manifest.permission.CAMERA}, codigoPermiso);
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
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, codigoPermiso);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == codigoPermiso){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permiso aprobado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_barras);
    }

   // @Override
   // protected void onPause() {
   //     super.onPause();
    //    scannerView.stopCamera();
   // }

    @Override
    public void handleResult(Result result) {
        scannerView.stopCamera();
        setContentView(R.layout.activity_codigo_barras);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Resultado de escaneo");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        //scannerView.resumeCameraPreview(this);
    }
}
