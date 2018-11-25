package com.example.juan.studentapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Desplazamiento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //keytool -exportcert -alias androiddebugkey -keystore C:\Users\Dell\.android\debug.keystore | C:\Users\Dell\Desktop\htry\bin\openssl sha1 -binary | C:\Users\Dell\Desktop\htry\bin\openssl" base64
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desplazamiento);
    }
}
