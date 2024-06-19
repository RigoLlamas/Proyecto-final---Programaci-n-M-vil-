package com.example.a21100298_lamichoacana;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import Adaptadores.AdaptadorVer;
import global.listas;


public class Recycle extends AppCompatActivity {
    RecyclerView rv;
    Toolbar toolbar;
    SharedPreferences archivo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        toolbar = findViewById(R.id.toolbar);
        rv = findViewById(R.id.rvlista);
        AdaptadorVer av = new AdaptadorVer();
        av.context = this;
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv.setAdapter(av);
        rv.setLayoutManager(llm);
        setSupportActionBar(toolbar);

        archivo=this.getSharedPreferences("sesion", Context.MODE_PRIVATE);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.op0)
        {
            Intent recycle = new Intent(this, MainActivity.class);
            Toast.makeText(this, "Visualizando", Toast.LENGTH_LONG).show();
            startActivity(recycle);
        }
        if(item.getItemId()==R.id.op1)
        {
            Toast.makeText(this, "Ya estas aqui", Toast.LENGTH_SHORT).show();

        }
        if(item.getItemId()==R.id.op2)
        {
            Intent modificar = new Intent(this, Modificar.class);
            Toast.makeText(this, "Visualizando", Toast.LENGTH_LONG).show();
            startActivity(modificar);
        }
        if(item.getItemId()==R.id.op3)
        {
            Intent eliminar = new Intent(this, Eliminar.class);
            Toast.makeText(this, "Visualizando", Toast.LENGTH_LONG).show();
            startActivity(eliminar);
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