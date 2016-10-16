package com.natrollus.nobet.araclar;
import android.content.*;
import com.natrollus.nobet.aktivite.*;

public class Ortak {
	public static void aktiviteBaslat(Context context,Class<?> sinif){
		Intent resim = new Intent(context,sinif);
		resim.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(resim);
	}
	public static void aktiviteLogla(Context context,Object log){
		Intent logcu = new Intent(context,LogActivite.class);
		logcu.putExtra("log",(String) log);
		logcu.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(logcu);
	}
}
