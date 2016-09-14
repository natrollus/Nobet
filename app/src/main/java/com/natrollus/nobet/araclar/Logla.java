package com.natrollus.nobet.araclar;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Logla {
    public static void logla(Object o){
        Log.v("bilgi",""+o);
    }
    public static void tostla(Context context, Object o){
        Toast.makeText(context,""+o,Toast.LENGTH_LONG).show();
    }
}
