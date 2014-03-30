package com.ul.resads.util;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class MyLocationListner  implements LocationListener{

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		location.getLongitude();
		location.getLatitude();
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
