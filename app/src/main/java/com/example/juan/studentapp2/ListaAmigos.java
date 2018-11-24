package com.example.juan.studentapp2;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ListaAmigos extends AppCompatActivity {

    private ArrayList<Amigo> amigos = new ArrayList<Amigo>();
    private LinearLayout contenedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_amigos);
        this.contenedor = (LinearLayout)findViewById(R.id.lloCont);
        setAmigos("2018102753", "478");
        mostrarAmigos();
    }

    public void setAmigos(String carne, String calificacion){
        amigos.add(new Amigo(carne, calificacion));

        /*for (int i = 0; carnes.length > i; i++){
            amigos.add(new Amigo(carnes [i], calificaciones [i]));
        }*/
    }

    public void mostrarAmigos (){
        for (Amigo b: amigos){
            Double num1 = (Double.parseDouble(b.getCalificacion())) * 0.01;
            TextView txt = new TextView(this);
            txt.setText(b.getCarne() + "\n" + num1.toString());
            txt.setBackgroundColor(Color.parseColor("#e1e1e1"));
            txt.setTextColor(Color.parseColor("#000000"));
            txt.setTextSize(26);
            contenedor.addView(txt);
        }
    }

}
