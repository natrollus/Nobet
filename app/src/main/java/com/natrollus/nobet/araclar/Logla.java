package com.natrollus.nobet.araclar;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.natrollus.nobet.aktivite.LogActivite;

public class Logla {
    public static void logla(Object o){
        Log.v("bilgi",""+o);
    }

    public static void logla(Context context,Object log){
        Intent logcu = new Intent(context,LogActivite.class);
        logcu.putExtra("log",(String) log);
        logcu.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(logcu);
    }

    public static void tostla(Context context, Object o){
        Toast.makeText(context,""+o,Toast.LENGTH_LONG).show();
    }
}
