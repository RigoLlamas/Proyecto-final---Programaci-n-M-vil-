package com.example.a21100298_lamichoacana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
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

public class Modificar extends AppCompatActivity {

    Toolbar toolbar;
    EditText modnombre,modsabor,modcantidad,moddomicilio,modtelefono, modhora, modfecha;
    String stamaño;
    Button modanterior,modguardar,modsiguiente;
    Spinner modtamaño;
    int posi, iminuto, ihora, iaño, imes, idia;
    Pedidos Persona;
    SharedPreferences archivo;
    private static final String notificacion = "canal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        toolbar = findViewById(R.id.toolbar);
        modtamaño = findViewById(R.id.modtamaño);
        modnombre = findViewById(R.id.modnombre);
        modsabor = findViewById(R.id.modsabor);
        modcantidad = findViewById(R.id.modcantidad);
        moddomicilio = findViewById(R.id.moddomicilio);
        modtelefono = findViewById(R.id.modtelefono);
        modhora = findViewById(R.id.modhora);
        modfecha = findViewById(R.id.modfecha);
        modanterior = findViewById(R.id.modanterior);
        modguardar = findViewById(R.id.modguardar);
        modsiguiente = findViewById(R.id.modsiguiente);
        setSupportActionBar(toolbar);

        archivo = this.getSharedPreferences("sesion", Context.MODE_PRIVATE);

        modanterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickanterior();
            }
        });
        modguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickguardar();

            }
        });
        modsiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclicksiguiente();
            }
        });
        posi = 0;
        if(listas.lista.size() > 0)
        {
            Persona = listas.lista.get(0);
            imprime(Persona);
        }
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
                modfecha.setText("" + iaño +" / " + imes + " / " + idia);
            }
        }, iaño, iaño, idia);
        dpd.show();
    }

    private void hora() {
        Calendar actual = Calendar.getInstance();
        ihora = actual.get(Calendar.HOUR_OF_DAY);
        iminuto = actual.get(Calendar.MINUTE);
        TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() { // muestra un reloj y permite al usuario seleccionar una hora.
            @Override
            public void onTimeSet(TimePicker timePicker, int timehoras, int timeminutos) { //proporciona una interfaz gráfica de usuario para seleccionar una hora.
                iminuto = timeminutos;
                ihora = timehoras;
                if(ihora >= 10) {
                    modhora.setText("" + ihora + ":" + iminuto);
                    if(iminuto >= 10){
                        modhora.setText("" + ihora + ":" + iminuto);
                    }else {
                        modhora.setText("" + ihora + ":0" + iminuto);
                    }
                }else {
                    modhora.setText("0" + ihora + ":" + iminuto);
                    if(iminuto >= 10){
                        modhora.setText("0" + ihora + ":" + iminuto);
                    }else {
                        modhora.setText("0" + ihora + ":0" + iminuto);
                    }
                }
            }
        }, ihora,iminuto,true);
        tpd.show();
    }

    private void imprime(Pedidos persona) {
        modnombre.setText(persona.getNombre());
        modsabor.setText(persona.getSabor());
        stamaño = Persona.getTamaño();
        ArrayAdapter<CharSequence> tamaños = ArrayAdapter.createFromResource(this, R.array.tamaños, android.R.layout.simple_spinner_item);
        modtamaño.setAdapter(tamaños);
        int posicionSpinner = tamaños.getPosition(stamaño);
        modtamaño.setSelection(posicionSpinner);
        modcantidad.setText(persona.getCantidad());
        modtelefono.setText(persona.getTelefono());
        moddomicilio.setText(persona.getDomicilio());
        modhora.setText(persona.getHora());
        modfecha.setText(persona.getFecha());
        modhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hora();
            }
        });

        modfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fecha();
            }
        });
    }

    private void onclickanterior() {
        if(listas.lista.size() > 0){
            if (posi == 0){
                posi = listas.lista.size() - 1;
            }
            else {
                posi -= 1;
            }
            Persona = listas.lista.get(posi);
            imprime(Persona);
        }
    }

    private void onclicksiguiente() {
        if(listas.lista.size() > 0){
            if (posi == listas.lista.size() - 1){
                posi = 0;
            }
            else {
                posi += 1;
            }
            Persona = listas.lista.get(posi);
            imprime(Persona);
        }
    }
    private void onclickguardar() {
        if (listas.lista.size() > 0){
            String url = "http://192.168.56.1/lamichoacana/ingreso.php?";
            url += "nombre=" + modnombre.getText().toString();
            url += "&sabor=" + modsabor.getText().toString();
            url += "&tamaño=" + modtamaño.getSelectedItem().toString();
            url += "&cantidad=" + modcantidad.getText().toString();
            url += "&domicilio=" + moddomicilio.getText().toString();
            url += "&telefono=" + modtelefono.getText().toString();
            url += "&hora=" + modhora.getText().toString();
            url += "&fecha=" + modfecha.getText().toString();
            url += "&id=" + Persona.getId().toString();

            JsonObjectRequest modifica = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        Persona.setNombre(response.getString("nombre"));
                        Persona.setCantidad(response.getString("cantidad"));
                        Persona.setTamaño(response.getString("tamaño"));
                        Persona.setSabor(response.getString("sabor"));
                        Persona.setTelefono(response.getString("telefono"));
                        Persona.setDomicilio(response.getString("domicilio"));
                        Persona.setFecha(response.getString("fecha"));
                        Persona.setHora(response.getString("hora"));
                        Log.d("Modificacion", "Exitosa id: "+response.getInt("id") );
                        Toast.makeText(Modificar.this, "Guardado", Toast.LENGTH_LONG).show();
                        Notificar notificacion = new Notificar();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            notificacion.Notificacion(getApplicationContext(),"Se modifico el pedido ",response.getString("id"));
                        } else {
                            notificacion.NuevaNotificacion(getApplicationContext(),"Se modifico el pedido ",response.getString("id"));
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Modificacion", "Fallida: " + error.getMessage());
                }
            });
            RequestQueue lanzarPeticion = Volley.newRequestQueue(this);
            lanzarPeticion.add(modifica);
        }
    }
    private void Notification() {
        NotificationChannel channel = new NotificationChannel(notificacion, "NEW", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        NewNotification();
    }

    private void NewNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), notificacion)
                .setSmallIcon(R.drawable.baseline_add_alert_24)
                .setContentTitle("La Michoacana")
                .setContentText("Se modifico el pedido " + Persona.getId().toString())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getApplicationContext());
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        managerCompat.notify(1, builder.build());
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
            Intent modificar = new Intent(this, MainActivity.class);
            Toast.makeText(this, "Visualizando", Toast.LENGTH_LONG).show();
            startActivity(modificar);
        }
        if(item.getItemId()==R.id.op1)
        {
            Intent recycle = new Intent(this, Recycle.class);
            Toast.makeText(this, "Visualizando", Toast.LENGTH_LONG).show();
            startActivity(recycle);
        }
        if(item.getItemId()==R.id.op2)
        {
            Toast.makeText(this, "Ya estas aqui", Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId()==R.id.op3)
        {
            Intent modificar = new Intent(this, Eliminar.class);
            Toast.makeText(this, "Visualizando", Toast.LENGTH_LONG).show();
            startActivity(modificar);
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