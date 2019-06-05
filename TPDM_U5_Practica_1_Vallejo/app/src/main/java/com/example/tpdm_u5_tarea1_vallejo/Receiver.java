package com.example.tpdm_u5_tarea1_vallejo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class Receiver extends BroadcastReceiver {

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG = "SmsBroadcastReceiver";
    BaseDatos db;
    String respuesta="";
    @Override
    public void onReceive(Context context, Intent intent) {

            System.out.println("Mensaje recibido");
            Bundle dataBundle = intent.getExtras();
            if (dataBundle!=null)
            {
                Object[] mypdu = (Object[])dataBundle.get("pdus");
                SmsMessage mensaje=SmsMessage.createFromPdu((byte[])mypdu[0]);

                String contenidoMensaje = mensaje.getMessageBody();
                contenidoMensaje = contenidoMensaje.toUpperCase();
                if (contenidoMensaje.startsWith("HOROSCOPO") || contenidoMensaje.startsWith("HORÓSCOPO")) {
                    String[] palabras = contenidoMensaje.split(" ");
                    if (palabras.length == 2) {
                        String tipoHoroscopo = palabras[1];
                        //consulta
                        db = new BaseDatos(context, "Horoscopos", null, 1);
                        respuesta = consultar(tipoHoroscopo);
                        enviarMensaje(mensaje.getOriginatingAddress(),respuesta,context);
                    } else {
                        respuesta ="No cumple con el formato correcto. Intenta: Horoscopo Aries";
                        //No mando el mensaje correctamente
                    }
                }
                Toast.makeText(context, "Message: " +respuesta +"\nNumber: " +mensaje.getOriginatingAddress(), Toast.LENGTH_LONG).show();
            }

    }

    private void enviarMensaje(String t, String m, Context c) {
        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(t,null,m,null,null);
        }catch (Exception e){
            Toast.makeText(c, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public String consultar(String tipo){

        SQLiteDatabase cons = db.getReadableDatabase();
        String query = "SELECT * FROM HOROSCOPO WHERE NOMBRE = '"+tipo+"'";
        Cursor c = cons.rawQuery(query,null);
        if (c.moveToFirst()) {
            //Horoscopo no encontrado
            return("El Horóscopo de "+tipo+" es:\n"+c.getString(1));
        } else {
            return("Horóscopo no encontrado");
        }
    }
}
