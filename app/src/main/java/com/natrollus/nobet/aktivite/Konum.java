package com.natrollus.nobet.aktivite;
import android.app.Activity;
import android.location.Location;
import android.location.LocationManager;
import android.os.*;
import android.content.*;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;

import static com.natrollus.nobet.araclar.Logla.logla;
import static com.natrollus.nobet.araclar.Logla.tostla;

public class Konum extends Activity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,LocationListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final LocationManager locationManager = (LocationManager) getSystemService(Konum.LOCATION_SERVICE);
		if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
			new KonumIslem(Konum.this).execute();
			logla("gps acik degil");
		} else {
			new KonumIslem(Konum.this).execute();
			logla("gps acik");
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		logla("rqc:"+requestCode+" rsc:"+resultCode+" int:"+data.getExtras());
	}

	@Override
	public void onLocationChanged(Location location) {
		tostla(getApplicationContext(),"loc_a:"+location.getAltitude()+" loc_long:"+location.getLongitude()+" loc_lat:"+location.getLatitude());
		logla("konum loc:"+location.getLongitude());
	}


	@Override
	public void onConnected(@Nullable Bundle bundle) {
		logla("konumdan baglandi b:"+bundle);
	}

	@Override
	public void onConnectionSuspended(int i) {
		logla("durduruldu:"+i);
	}

	@Override
	public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
		logla("baglanamadi:"+connectionResult.toString());
	}
}
