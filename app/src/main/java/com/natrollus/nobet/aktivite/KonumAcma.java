package com.natrollus.nobet.aktivite;
import android.app.*;
import android.os.*;
import com.google.android.gms.common.api.*;
import com.google.android.gms.location.*;

import static com.natrollus.nobet.araclar.Logla.*;
import static com.natrollus.nobet.araclar.Ortak.*;
import android.content.*;
import com.google.android.gms.common.ConnectionResult;
import android.location.*;

public class KonumAcma extends Activity implements GoogleApiClient.ConnectionCallbacks,
GoogleApiClient.OnConnectionFailedListener
{

	@Override
	public void onConnected(Bundle p1)
	{
		// TODO: Implement this method
	}

	@Override
	public void onConnectionSuspended(int p1)
	{
		// TODO: Implement this method
	}

	@Override
	public void onConnectionFailed(ConnectionResult p1)
	{
		// TODO: Implement this method
	}
	

	
	

GoogleApiClient client;
Context context;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getApplicationContext();
		try {
		if (client==null){
			client = new GoogleApiClient.Builder(getParent())
			.addOnConnectionFailedListener(this)
			.addOnConnectionFailedListener(this)
			.addApi(LocationServices.API).build();
			
			tostla(context,"bagli="+client.isConnected());
			client.connect();
			
			LocationRequest lr = LocationRequest.create();
			lr.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
			lr.setInterval(30 * 1000);
			lr.setFastestInterval(5 * 1000);
			LocationSettingsRequest.Builder lsbuilder = new LocationSettingsRequest.Builder()
				.addLocationRequest(lr);
			lsbuilder.setAlwaysShow(true);
			
			PendingResult<LocationSettingsResult> sonuc = LocationServices.SettingsApi.checkLocationSettings(client,lsbuilder.build());
			sonuc.setResultCallback(new ResultCallback<LocationSettingsResult> (){
					@Override
					public void onResult(LocationSettingsResult result) {
						final Status status = result.getStatus();
						try{final LocationSettingsStates state = result.getLocationSettingsStates();
						switch (status.getStatusCode()) {
							case LocationSettingsStatusCodes.SUCCESS:
								// All location settings are satisfied. The client can initialize location
								// requests here.
								break;
							case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
								// Location settings are not satisfied. But could be fixed by showing the user
								// a dialog.
								try {
									// Show the dialog by calling startResolutionForResult(),
									// and check the result in onActivityResult().
									status.startResolutionForResult(getParent(), 1000);
								} catch (IntentSender.SendIntentException e) {
									// Ignore the error.
								}
								break;
							case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
								// Location settings are not satisfied. However, we have no way to fix the
								// settings so we won't show the dialog.
								break;
						}}catch(Exception hata){
							tostla(context,"hata=="+hata);
						}
					}
			});
			
		}} catch (Exception hata){
			//tostla(context,"hata="+hata);
			//aktiviteLogla(context,"hata"+hata);
		}
		finish();
	}
	
}
