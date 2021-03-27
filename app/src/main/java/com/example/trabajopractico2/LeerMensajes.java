package com.example.trabajopractico2;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class LeerMensajes extends Service {
    private ContentResolver contenedor;
    private Uri mensaje;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mensaje = Uri.parse("content://sms");
        contenedor = getContentResolver();

        while (true) {
            Mensajes();
            try {
                Thread.sleep(9000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return super.onStartCommand(intent, flags, startId);
            }
        }
    }

    private void Mensajes() {
    Uri mensaje = Uri.parse("content://sms");
    ContentResolver contenedor = getContentResolver();
        Cursor cursor = contenedor.query(mensaje, null, null, null, null);
        if(cursor.getCount() == 0) Log.d("Mensaje", "Mensaje");
        while (cursor.moveToNext() && cursor.getPosition() < 5){
            Log.d("mensaje", "" + "/n nro: "+ cursor.getString(2)+ "/n sms: " + cursor.getString(12));
        }
        cursor.close();

    }

}

