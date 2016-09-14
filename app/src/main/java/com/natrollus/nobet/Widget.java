package com.natrollus.nobet;

import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.*;
import android.app.*;
import android.appwidget.*;
import android.content.*;
import android.widget.RemoteViews.*;

public class Widget extends AppWidgetProvider {
	RemoteViews rv;
	AppWidgetManager awm;
	ComponentName cn;
	
	
    @Override
    public void onReceive(Context context, Intent intent) {
		
		awm = AppWidgetManager.getInstance(context);
		cn = new ComponentName(context,getClass());
		rv = new RemoteViews(context.getPackageName(),R.layout.anasayfa);
		Toast.makeText(context,"s:"+intent.getAction(),Toast.LENGTH_LONG).show();
		Intent hazirlik = new Intent("selam",null,context,Widget.class);
		PendingIntent bekleyen = PendingIntent.getBroadcast(context,0,hazirlik,0);
		rv.setOnClickPendingIntent(R.id.resim,bekleyen);
		awm.updateAppWidget(cn,rv);

    }
}
