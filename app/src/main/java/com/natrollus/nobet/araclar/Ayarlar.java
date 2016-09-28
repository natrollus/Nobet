package com.natrollus.nobet.araclar;
import android.content.*;
import android.preference.*;

public class Ayarlar {
	public static SharedPreferences ayarGetir(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}
	
	public static void degerEkle(Context context,String adi,String deger) {
		ayarGetir(context).edit().putString(adi,deger).apply();
	}
	
	public static void degerEkle(Context context,String adi,int deger) {
		ayarGetir(context).edit().putInt(adi,deger).apply();
	}
	
	public static Object degerGetir(Context context,String adi,Object ontanimli) {
		if (ontanimli instanceof Integer){
			return ayarGetir(context).getInt(adi,(int) ontanimli);
		} else if (ontanimli instanceof String){
			return ayarGetir(context).getString(adi, (String) ontanimli);
		} else {
			return null;
		}
	}
}
