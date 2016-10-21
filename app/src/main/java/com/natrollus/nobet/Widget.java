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
import static com.natrollus.nobet.araclar.Ayarlar.*;
import static com.natrollus.nobet.araclar.Ortak.*;
import android.preference.*;
import com.natrollus.nobet.araclar.*;

public class Widget extends AppWidgetProvider {
	RemoteViews rv;
	AppWidgetManager awm;
	ComponentName cn;
	SharedPreferences ayarlar;
	Context context;
	
	
    @Override
    public void onReceive(Context context, Intent intent) {
        String aksiyon = intent.getAction();
		this.context = context;
        ayarla();
        switch (aksiyon){
            case "getir":
                //testing naber la testing
                getir();
                break;
            case AppWidgetManager.ACTION_APPWIDGET_UPDATE:
                sayac(0);
				tostla(context,"güncelledi");
                break;
			case "yap":
				konumGetir(context);
				break;
			case "yan":
				tostla(context,"yan gider gelir ahaha");
				break;
            default:
                tostla(context,"aks:"+aksiyon);
        }
		butonAyarla("yan",R.id.yan);
        butonAyarla("getir",R.id.getir);
		butonAyarla("yap",R.id.yap);
        tazele();
    }

	private void konumGetir(Context context) {
		try {
			Konum konum = new Konum(context);
			konum.getir();
		} catch (Exception hata){
			tostla(context,"konum getir hata:" + hata.toString());
		}
	}

	private void getir() {
		try {
			aktiviteBaslat(context, ResmiGetir.class);
			sayac(1);
		}
		catch (Exception hata) {
			tostla(context, "getirde hata:" + hata.toString());
		}
	}

	private void sayac(int artim) {
		int kac = (int) degerGetir(context,"kac",0);
		degerEkle(context,"kac",(int) degerGetir(context,"kac",0)+artim);
		rv.setTextViewText(R.id.sayac, "" + kac);
	}

    private void butonAyarla(String aksiyon,int id) {
        Intent hazirlik = new Intent(aksiyon,null,context,Widget.class);
        PendingIntent bekleyen = PendingIntent.getBroadcast(context,0,hazirlik,0);
        rv.setOnClickPendingIntent(id,bekleyen);
    }

    private void ayarla() {
        awm = AppWidgetManager.getInstance(context);
        cn = new ComponentName(context,getClass());
        rv = new RemoteViews(context.getPackageName(), R.layout.widget);
    }

    private void tazele() {
        awm.updateAppWidget(cn,rv);
    }
	
}
