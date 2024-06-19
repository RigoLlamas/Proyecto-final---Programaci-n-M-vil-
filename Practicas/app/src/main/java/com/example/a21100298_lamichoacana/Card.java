package com.example.a21100298_lamichoacana;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import global.listas;

import Notificar.Notificar;

public class Card extends AppCompatActivity
{
    TextView cnombre,csabor,ctamaño,ccantidad,ctelefono,cdomicilio,chora, cfecha,cid,csalir;
    Button cllamar;
    int posicion;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        cnombre = findViewById(R.id.cnombre);
        csabor = findViewById(R.id.csabot);
        ctamaño = findViewById(R.id.ctamaño);
        ccantidad = findViewById(R.id.ccantidad);
        ctelefono = findViewById(R.id.ctelefono);
        cdomicilio = findViewById(R.id.cdomicilio);
        chora = findViewById(R.id.chora);
        cfecha = findViewById(R.id.cfecha);
        cid = findViewById(R.id.cid);
        cllamar = findViewById(R.id.cllamar);
        csalir = findViewById(R.id.csalir);

        cllamar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onclickllamar();
            }
        });

        csalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onclicksalir();

            }
        });
        posicion=getIntent().getIntExtra("pos",-1);
        cnombre.setText(listas.lista.get(posicion).getNombre());
        csabor.setText(listas.lista.get(posicion).getSabor());
        ctamaño.setText(listas.lista.get(posicion).getTamaño());
        ccantidad.setText(listas.lista.get(posicion).getCantidad());
        ctelefono.setText(listas.lista.get(posicion).getTelefono());
        cdomicilio.setText(listas.lista.get(posicion).getDomicilio());
        cid.setText(String.valueOf(listas.lista.get(posicion).getId()));
        chora.setText(listas.lista.get(posicion).getHora());
        cfecha.setText(listas.lista.get(posicion).getFecha());
        Notificar notificador =new Notificar();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificador.Notificacion(getApplicationContext(),"Visualizando pedido ",listas.lista.get(posicion).getId().toString());
        } else {
            notificador.NuevaNotificacion(getApplicationContext(),"Visualizando pedido ",listas.lista.get(posicion).getId().toString());
        }

    }

    private void onclicksalir() {
        Intent recycle = new Intent(this, Recycle.class);
        startActivity(recycle);
    }

    private void onclickllamar() {
        Intent llamar = new Intent(Intent.ACTION_CALL);
        llamar.setData(Uri.parse("tel:"+ctelefono.getText().toString()));
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},10);
            return;
        }
        startActivity(llamar);
    }
}