package com.example.a21100298_lamichoacana;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import POJO.Pedidos;
import global.listas;


public class Inicio extends AppCompatActivity {
    EditText usuario, contraseña;
    Button ingresar;
    String susuario, scontraseña;
    SharedPreferences archivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        setTheme(R.style.Theme__21100298_LaMichoacana);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        usuario = findViewById(R.id.usuario);
        contraseña = findViewById(R.id.contraseña);
        ingresar = findViewById(R.id.ingresar);

        archivo=this.getSharedPreferences("sesion", Context.MODE_PRIVATE);

        String cargaurl = "http://192.168.56.1/lamichoacana/cargar.php";
        JsonArrayRequest carga = new JsonArrayRequest(Request.Method.GET, cargaurl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i = 0; i < response.length(); i++){
                        JSONObject json_data = response.getJSONObject(i);
                        Pedidos Agua = new Pedidos();
                        Agua.setNombre(json_data.getString("nombre"));
                        Agua.setCantidad(json_data.getString("cantidad"));
                        Agua.setTamaño(json_data.getString("tamano"));
                        Agua.setSabor(json_data.getString("sabor"));
                        Agua.setTelefono(json_data.getString("telefono"));
                        Agua.setDomicilio(json_data.getString("domicilio"));
                        Agua.setHora(json_data.getString("hora"));
                        Agua.setFecha(json_data.getString("fecha"));
                        Agua.setId(json_data.getInt("id"));
                        listas.lista.add(Agua);
                        Log.d("Carga", "Cargado el id:" + json_data.getInt("id"));
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getMessage() != null) {
                    Log.d("Carga", error.getMessage());
                } else {
                    Log.d("Carga", "Archivo desconocido");
                }
            }
        });
        RequestQueue cargardatos = Volley.newRequestQueue(this);
        cargardatos.add(carga);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                susuario = usuario.getText().toString();
                scontraseña = contraseña.getText().toString();
                onclick_ingresar();
            }
        });
    }

    private void onclick_ingresar() {
        String url = "http://192.168.56.1/lamichoacana/ingreso.php?usr="; //La primera IPv4 que aparezca
        url = url + susuario;
        url = url + "&pass=";
        url = url + scontraseña;
        JsonObjectRequest pet = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getInt("usr") != -1) {
                        Intent in = new Intent(Inicio.this, MainActivity.class);
                        SharedPreferences.Editor editor = archivo.edit();
                        editor.putInt("id_usuario", response.getInt("usr"));
                        editor.commit();
                        Toast.makeText(Inicio.this, "Hola", Toast.LENGTH_SHORT).show();
                        startActivity(in);
                        finish();
                    } else {
                        usuario.setText("");
                        contraseña.setText("");
                        Toast.makeText(Inicio.this, "Usuario/Contraseña incorrecto", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Inicio", error.getMessage());
            }
        });
        RequestQueue lazarPeticion = Volley.newRequestQueue(this);
        lazarPeticion.add(pet);
    }
}