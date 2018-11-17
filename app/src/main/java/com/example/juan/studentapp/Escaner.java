package com.example.juan.studentapp;

import android.app.AlertDialog;
import android.view.View;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Escaner implements ZXingScannerView.ResultHandler {

    private ZXingScannerView escanerView;
    private MainActivity mainActivity;

    public Escaner (MainActivity m){
        this.mainActivity = m;
    }

    public void EscanearBarras(View view){
        escanerView = new ZXingScannerView(this.mainActivity);
        this.mainActivity.setContentView(escanerView);
        escanerView.setResultHandler(this);
        escanerView.startCamera();
    }



    @Override
    public void handleResult(Result result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mainActivity);
        builder.setTitle("Carné escaneado");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        escanerView.resumeCameraPreview(this);
    }

    public ZXingScannerView getEscanerView() {
        return escanerView;
    }
}
