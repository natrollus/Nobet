package com.natrollus.nobet;

import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.*;
import android.app.*;
import android.appwidget.*;
import android.content.*;

import com.natrollus.nobet.aktivite.ResmiGetir;

import static com.natrollus.nobet.araclar.Logla.tostla;
import android.preference.*;

public class Widget extends AppWidgetProvider {
	RemoteViews rv;
	AppWidgetManager awm;
	ComponentName cn;
	SharedPreferences ayarlar;
	
	
    @Override
    public void onReceive(Context context, Intent intent) {

        String aksiyon = intent.getAction();
		
        ayarla(context);
        switch (aksiyon){
            case "selam":
                try {
                    Intent resim = new Intent(context, ResmiGetir.class);
                    resim.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(resim);
					sayac();
                } catch (Exception hata){
                    tostla(context,"hata:"+hata.toString());
                }
                break;
            case AppWidgetManager.ACTION_APPWIDGET_UPDATE:
                tostla(context,"güncelledi");
                break;
            default:
                tostla(context,"aks:"+aksiyon);
        }

        butonAyarla(context);
        tazele();

    }

	private void sayac() {
		int kac = ayarlar.getInt("kac", 0);
		ayarlar.edit().putInt("kac", (kac + 1)).apply();
		rv.setTextViewText(R.id.sayac, "" + kac);
	}

    private void butonAyarla(Context context) {
        Intent hazirlik = new Intent("selam",null,context,Widget.class);
        PendingIntent bekleyen = PendingIntent.getBroadcast(context,0,hazirlik,0);
        rv.setOnClickPendingIntent(R.id.getir,bekleyen);
    }

    private void ayarla(Context context) {
        awm = AppWidgetManager.getInstance(context);
        cn = new ComponentName(context,getClass());
        rv = new RemoteViews(context.getPackageName(), R.layout.widget);
		ayarlar = PreferenceManager.getDefaultSharedPreferences(context);
    }

    private void tazele() {
        awm.updateAppWidget(cn,rv);
    }


}
