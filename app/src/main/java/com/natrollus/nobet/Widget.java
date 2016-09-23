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
            case "getir":
                //testing naber la
                getir(context);
                break;
            case AppWidgetManager.ACTION_APPWIDGET_UPDATE:
                sayac(0);
				tostla(context,"güncelledi");
                break;
			case "yap":
				tostla(context,"ne yapcam?");
				break;
            default:
                tostla(context,"aks:"+aksiyon);
        }

        butonAyarla(context,"getir",R.id.getir);
		butonAyarla(context,"yap",R.id.yap);
        tazele();
    }

	private void getir(Context context)
	{
		try
		{
			Intent resim = new Intent(context, ResmiGetir.class);
			resim.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(resim);
			sayac(1);
		}
		catch (Exception hata)
		{
			tostla(context, "hata:" + hata.toString());
		}
	}

	private void sayac(int artim) {
		int kac = ayarlar.getInt("kac", 0);
		ayarlar.edit().putInt("kac", (kac + artim)).apply();
		rv.setTextViewText(R.id.sayac, "" + kac);
	}

    private void butonAyarla(Context context,String aksiyon,int id) {
        Intent hazirlik = new Intent(aksiyon,null,context,Widget.class);
        PendingIntent bekleyen = PendingIntent.getBroadcast(context,0,hazirlik,0);
        rv.setOnClickPendingIntent(id,bekleyen);
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
