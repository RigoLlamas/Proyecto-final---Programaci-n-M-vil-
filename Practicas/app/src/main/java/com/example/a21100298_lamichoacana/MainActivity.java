package com.example.a21100298_lamichoacana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Calendar;

import POJO.Pedidos;
import global.listas;

import Notificar.Notificar;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText nombre, sabor, cantidad, domicilio, telefono, hora, fecha;
    String snombre, ssabor, stamaño, scantidad, sdomicilio, stelefono, shora, sfecha;
    int ihora, iminuto, iaño, imes, idia;

    Spinner tamaño;
    Button agregar;
    SharedPreferences archivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        tamaño = findViewById(R.id.tamaño);
        nombre = findViewById(R.id.nombre);
        sabor = findViewById(R.id.sabor);
        cantidad = findViewById(R.id.cantidad);
        domicilio = findViewById(R.id.domicilio);
        telefono = findViewById(R.id.telefono);
        hora = findViewById(R.id.hora);
        fecha = findViewById(R.id.fecha);
        agregar = findViewById(R.id.agregar);
        setSupportActionBar(toolbar);

        archivo = this.getSharedPreferences("sesion", Context.MODE_PRIVATE);

        ArrayAdapter<CharSequence> tamaños = ArrayAdapter.createFromResource(this, R.array.tamaños, android.R.layout.simple_spinner_item);
        tamaños.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tamaño.setAdapter(tamaños);

        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hora();
            }
        });

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fecha();
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stamaño = tamaño.getSelectedItem().toString();
                snombre = nombre.getText().toString();
                ssabor = sabor.getText().toString();
                scantidad = cantidad.getText().toString();
                sdomicilio = domicilio.getText().toString();
                stelefono = telefono.getText().toString();
                shora = hora.getText().toString();
                sfecha = fecha.getText().toString();
                if (!stamaño.isEmpty() && !snombre.isEmpty() && !ssabor.isEmpty() && !scantidad.isEmpty() && !sdomicilio.isEmpty() && !stelefono.isEmpty() && !shora.isEmpty()) {
                    Double numero = ((Double.parseDouble(stelefono) / 1000000) / 1000);
                    if (numero >= 3.3 && numero < 3.4) {
                        agregar();
                        Toast.makeText(MainActivity.this, "Agregado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Numero invalido", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Llene los espacios solicitados", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fecha() {
        Calendar actual = Calendar.getInstance();
        iaño = actual.get(Calendar.YEAR);
        imes = actual.get(Calendar.MONTH);
        idia = actual.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                iaño = year;
                imes = month + 1;
                idia = dayOfMonth;
                if (imes >= 10) {
                    if (idia >= 10) {
                        fecha.setText("" + iaño + " / " + imes + " / " + idia);
                    } else
                        fecha.setText("" + iaño + " / " + imes + " / 0" + idia);
                } else {
                    if (idia >= 10) {
                        fecha.setText("" + iaño + " / 0" + imes + " / " + idia);
                    } else
                        fecha.setText("" + iaño + " / 0" + imes + " / 0" + idia);
                }
            }
        }, iaño, iaño, idia);
        dpd.show();
    }

    private void hora() {
        Calendar actual = Calendar.getInstance();
        ihora = actual.get(Calendar.HOUR_OF_DAY);
        iminuto = actual.get(Calendar.MINUTE);
        TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int timehoras, int timeminutos) {
                iminuto = timeminutos;
                ihora = timehoras;
                if (ihora >= 10) {
                    hora.setText("" + ihora + ":" + iminuto);
                    if (iminuto >= 10) {
                        hora.setText("" + ihora + ":" + iminuto);
                    } else {
                        hora.setText("" + ihora + ":0" + iminuto);
                    }
                } else {
                    hora.setText("0" + ihora + ":" + iminuto);
                    if (iminuto >= 10) {
                        hora.setText("0" + ihora + ":" + iminuto);
                    } else {
                        hora.setText("0" + ihora + ":0" + iminuto);
                    }
                }
            }
        }, ihora, iminuto, true);
        tpd.show();
    }

    private void agregar() {
        String url = "http://192.168.56.1/lamichoacana/ingreso.php?";
        url += "nombre=" + snombre;
        url += "&sabor=" + ssabor;
        url += "&tamaño=" + stamaño;
        url += "&cantidad=" + scantidad;
        url += "&domicilio=" + sdomicilio;
        url += "&telefono=" + stelefono;
        url += "&hora=" + shora;
        url += "&fecha=" + sfecha;

        JsonObjectRequest agregar = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Pedidos Agua = new Pedidos();
                    Agua.setNombre(response.getString("nombre"));
                    Agua.setCantidad(response.getString("cantidad"));
                    Agua.setTamaño(response.getString("tamaño"));
                    Agua.setSabor(response.getString("sabor"));
                    Agua.setTelefono(response.getString("telefono"));
                    Agua.setDomicilio(response.getString("domicilio"));
                    Agua.setHora(response.getString("hora"));
                    Agua.setFecha(response.getString("fecha"));
                    Agua.setId(response.getInt("id"));
                    listas.lista.add(Agua);
                    Log.d("Insersion", "Exitosa id: " + response.getInt("id"));
                    Notificar notificador = new Notificar();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        notificador.Notificacion(getApplicationContext(),"Se agrego el pedido ",response.getString("id"));
                    } else {
                        notificador.NuevaNotificacion(getApplicationContext(),"Se agrego el pedido ",response.getString("id"));
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Insersion", "Fallida" + error.getMessage());
            }
        });
        RequestQueue lanzarPeticion = Volley.newRequestQueue(this);
        lanzarPeticion.add(agregar);
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
            Toast.makeText(this, "Ya estas aqui", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.op1)
        {
            Intent recycle = new Intent(this, Recycle.class);
            Toast.makeText(this, "Visualizando", Toast.LENGTH_LONG).show();
            startActivity(recycle);
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