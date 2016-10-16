package com.natrollus.nobet.araclar;
import android.location.*;
import android.os.*;
import android.content.*;

import static com.natrollus.nobet.araclar.Logla.*;
import static com.natrollus.nobet.araclar.Ortak.*;
import android.provider.*;
import com.natrollus.nobet.aktivite.*;

public class Konum implements LocationListener {
	Context context;
	LocationManager lm;
	boolean aktif = false;
	
	public Konum(Context context){
		this.context = context;
		lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	}
	
	public void getir(){
		if (lm!=null){
			aktif = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
			if(aktif){
				//bos
			} else {
				new Calistir().execute();
			}
		}
	}
	
	@Override
	public void onLocationChanged(Location p1) {
		// TODO: Implement this method
	}

	@Override
	public void onStatusChanged(String p1, int p2, Bundle p3) {
		// TODO: Implement this method
	}

	@Override
	public void onProviderEnabled(String p1) {
		// TODO: Implement this method
	}

	@Override
	public void onProviderDisabled(String p1) {
		// TODO: Implement this method
	}
	
	private class Calistir extends AsyncTask<Void,Void,Void>
	{

		@Override
		protected Void doInBackground(Void[] p1) {
			aktiviteBaslat(context,KonumAcma.class);
			return null;
		}
		
	
	}

}
