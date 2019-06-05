package com.example.tpdm_u5_tarea1_vallejo;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Receiver receiver;
    IntentFilter filter;
    Button permisos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permisos = findViewById(R.id.permisos);

        permisos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(solicitarPermisos()) mensaje("PERMISOS SOLICITADOS");
            }
        });
        if (solicitarPermisos()) {
            mensaje("PERMISOS SOLICITADOS");
        }

        filter=new IntentFilter();
        filter.addAction(Manifest.permission.RECEIVE_SMS);

        receiver = new Receiver();

    }

    @Override
    protected void onResume() {
        registerReceiver(receiver,filter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        unregisterReceiver(receiver);
        super.onPause();
    }

    private boolean solicitarPermisos() {
        boolean valor = true;
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},1);
            mensaje("No hay permiso de enviar mensajes");
            valor = false;
        }
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE},2);
            mensaje("No hay permiso de leer el estado del telefono");
            valor = false;
        }
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_SMS},3);
            mensaje("No hay permiso de leer los sms");
            valor = false;
        }
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECEIVE_SMS},4);
            mensaje("No hay permiso de recibir mensajes");
            valor = false;
        }
        return valor;
    }

    public void mensaje(String texto){
        Toast.makeText(this,texto,Toast.LENGTH_SHORT).show();
    }

}
