package com.natrollus.nobet;

import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.*;
import android.app.*;
import android.appwidget.*;
import android.content.*;
import android.widget.RemoteViews.*;

import static com.natrollus.nobet.araclar.Logla.tostla;

public class Widget extends AppWidgetProvider {
	RemoteViews rv;
	AppWidgetManager awm;
	ComponentName cn;
	
	
    @Override
    public void onReceive(Context context, Intent intent) {

        ayarla(context);
        tostla(context,"s:"+intent.getAction());
		Intent hazirlik = new Intent("selam",null,context,Widget.class);
		PendingIntent bekleyen = PendingIntent.getBroadcast(context,0,hazirlik,0);
		rv.setOnClickPendingIntent(R.id.resim,bekleyen);
		awm.updateAppWidget(cn,rv);

    }

    private void ayarla(Context context) {
        awm = AppWidgetManager.getInstance(context);
        cn = new ComponentName(context,getClass());
        rv = new RemoteViews(context.getPackageName(), R.layout.anasayfa);
    }
}
