package com.example.juan.studentapp2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private boolean registroCarnet=true;
    private boolean regsitroFacebook=true;
    private String Carnet="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recibirDatos();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.calificacion) {
            // Handle the camera action
            calificacion();
        } else if (id == R.id.calificar) {
            calificar();
        } else if (id == R.id.carpooling) {
            carpooling();
        } else if (id == R.id.top5) {
            top5();
        } else if (id == R.id.registro) {
            registro();
        } else if (id == R.id.amigos) {
            amigos();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /**
     * Este método permite ingresar a la actividad de registro
     */
    public void registro(){
        Intent i = new Intent(getApplicationContext(),CodigoBarras.class);
        startActivity(i);
    }
    /**
     * Este método recibe los datos extras de validación
     */
    public void recibirDatos(){
        Bundle extras=getIntent().getExtras();
        registroCarnet=extras.getBoolean("RegistroCarnet");
        regsitroFacebook=extras.getBoolean("RegistroFace");
        Carnet=extras.getString("Carnet");
    }

    /**
     * Este método permite ingresar a la actividad de top 5
     */
    public void top5() {
        Intent i = new Intent(getApplicationContext(), Top5.class);
        i.putExtra("Carnet", Carnet);
        startActivity(i);
        if (true) {

        } else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Por favor registrese primero", Toast.LENGTH_SHORT);

            toast1.show();
        }
    }

    /**
     * Este método permite ingresar a la actividad de carpooling
     */
    public void carpooling(){
        if (true){
            Intent i=new Intent(getApplicationContext(),OpcionesViaje.class);
            i.putExtra("Carnet",Carnet);
            startActivity(i);
        }
        else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Por favor registrese primero", Toast.LENGTH_SHORT);

            toast1.show();
        }

    }

    /**
     * Este método permite ingresar a la actividad de calificar
     */
    public void calificar(){
        Intent i = new Intent(getApplicationContext(),Calificar.class);
        startActivity(i);
    }

    /**
     * Este método permite ingresar a la actividad de calificación
     */
    public void calificacion(){

        if (true){
            Intent i=new Intent(getApplicationContext(),Calificacion.class);
            i.putExtra("Carnet",Carnet);
            startActivity(i);
        }
        else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Por favor registrese primero", Toast.LENGTH_SHORT);

            toast1.show();
        }

    }

    /**
     * Este método permite ingresar a la actividad de lista de amigos
     */
    public void amigos(){
        Intent i=new Intent(getApplicationContext(),ListaAmigos.class);
        i.putExtra("Carnet",Carnet);
        startActivity(i);
        if (registroCarnet&&regsitroFacebook){

        }
        else {
            Toast toast1 =
                    Toast.makeText(getApplicationContext(),
                            "Por favor registrese primero", Toast.LENGTH_SHORT);

            toast1.show();
        }
    }
}
