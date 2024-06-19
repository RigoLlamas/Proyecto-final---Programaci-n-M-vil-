package com.example.a21100298_lamichoacana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import Adaptadores.AdaptadorEliminar;
import POJO.Pedidos;
import global.listas;

import Notificar.Notificar;

public class Eliminar extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView rv;
    Button elimina;
    SharedPreferences archivo;
    private static final String notificacion = "canal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        elimina = findViewById(R.id.eliminar);
        toolbar = findViewById(R.id.toolbar);
        rv = findViewById(R.id.rveliminar);
        AdaptadorEliminar av = new AdaptadorEliminar();
        av.context = this;
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false); //Dice como se va a ver la lista del holder
        rv.setAdapter(av);
        rv.setLayoutManager(llm);
        setSupportActionBar(toolbar);

        archivo=this.getSharedPreferences("sesion", Context.MODE_PRIVATE);

        elimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminar();
            }
        });
    }
    private void eliminar() {
        for(int i = 0; i < listas.listabajas.size(); i++)
        {
            Pedidos Persona = listas.listabajas.get(i);
            String url = "http://192.168.56.1/lamichoacana/ingreso.php?id=" + Persona.getId().toString();
            url += "&elimina=1";
            JsonObjectRequest elimina = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {} catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Eliminado", "Fallido" );
                }
            });
            RequestQueue lanzarPeticion = Volley.newRequestQueue(this);
            Notificar notificacion = new Notificar();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificacion.Notificacion(getApplicationContext(),"Se elimino el pedido ",Persona.getId().toString());
            } else {
                notificacion.NuevaNotificacion(getApplicationContext(),"Se elimino el pedido ",Persona.getId().toString());
            }
            lanzarPeticion.add(elimina);
            listas.lista.remove(Persona);
            listas.listabajas.clear();
            rv.getAdapter().notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {

        if(item.getItemId()==R.id.op0)
        {
            Intent registrar = new Intent(this, MainActivity.class);
            Toast.makeText(this, "Visualizando", Toast.LENGTH_LONG).show();
            startActivity(registrar);
        }
        if(item.getItemId()==R.id.op1)
        {
            Intent recycle = new Intent(this, Recycle.class);
            Toast.makeText(this, "Visualizando", Toast.LENGTH_LONG).show();
            startActivity(recycle);
        }
        if(item.getItemId()==R.id.op2)
        {
            Intent modificar = new Intent(this, MainActivity.class);
            Toast.makeText(this, "Visualizando", Toast.LENGTH_LONG).show();
            startActivity(modificar);
        }
        if(item.getItemId()==R.id.op3)
        {
            Toast.makeText(this, "Ya estas aqui", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.op4)
        {
            if(archivo.contains("id_usuario")){
                SharedPreferences.Editor editor = archivo.edit();
                editor.remove("id_usuario");
                editor.apply();
                listas.lista.clear();
                Intent fin = new Intent(this,Inicio.class);
                startActivity(fin);
            }
        }
        return super.onOptionsItemSelected(item);
    }


}