package com.ul.resads.ui;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ul.resads.data.ListRestaurantLocation;
import com.ul.resads.data.RestaurantLocation;
import com.ul.resads.util.MyLocationListner;

public class MyMap extends SupportMapFragment {
	LocationManager locationManager;
	LocationListener locationListener;
	Location lastKnownlocation;
	Double latitude, longtitude;
	private GoogleMap map;
	private ViewGroup vg;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		vg = container;
		return inflater.inflate(R.layout.mymap, container, false);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		getMap(vg);
		AddPinTask task=new AddPinTask();
		task.execute("http://phone-price.info/res_ads/getdata.php?action=reslocation");
	}

	private void getMap(ViewGroup container) {

		locationManager = (LocationManager) container.getContext()
				.getSystemService(Context.LOCATION_SERVICE);

		for (String prov : locationManager.getAllProviders()) {
			Log.i("Error", prov);
		}
		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		List<String> providers = locationManager.getProviders(criteria, true);
		if (providers == null || providers.size() == 0) {
			Toast.makeText(container.getContext(),
					"Could not open GPS service", Toast.LENGTH_LONG).show();
			return;
		}
		String preferred = providers.get(0);

		if (preferred != null) {
			locationListener = new MyLocationListner();
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 0, 0, locationListener);

			lastKnownlocation = locationManager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);

			Geocoder gc = new Geocoder(container.getContext());
			if (gc.isPresent()) {
				List<Address> list = null;
				try {
					list = gc.getFromLocation(lastKnownlocation.getLatitude(),
							lastKnownlocation.getLongitude(), 1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Address address = list.get(0);
				StringBuffer str = new StringBuffer();
				str.append("Name: " + address.getLocality() + "\n");
				str.append("Post Code: " + address.getPostalCode() + "\n");
				str.append("Country: " + address.getCountryName() + "\n");
				String strAddress = str.toString();

				map = ((SupportMapFragment) getFragmentManager()
						.findFragmentById(R.id.map)).getMap();
				if (map != null) {
					LatLng current = new LatLng(
							lastKnownlocation.getLatitude(),
							lastKnownlocation.getLongitude());
					Marker c = map
							.addMarker(new MarkerOptions()
									.position(current)
									.title(strAddress)
									.icon(BitmapDescriptorFactory
											.fromResource(R.drawable.ic_location_pin3)));

					map.moveCamera(CameraUpdateFactory.newLatLngZoom(current,
							15));
					
				}
			}

		}

	}

	public void onDestroyView() {
		try {
			SupportMapFragment fragment = ((SupportMapFragment) getFragmentManager()
					.findFragmentById(R.id.map));
			FragmentTransaction ft = getActivity().getSupportFragmentManager()
					.beginTransaction();
			ft.remove(fragment);
			ft.commit();
		} catch (Exception e) {
		}
		super.onDestroyView();
	}

	private class AddPinTask extends
			AsyncTask<String, Integer, List<RestaurantLocation>> {

		@Override
		protected List<RestaurantLocation> doInBackground(String... params) {
			ListRestaurantLocation listRestaurantLocation = new ListRestaurantLocation();
			listRestaurantLocation.excuteURL(params[0]);

			return listRestaurantLocation.getRestaurantLocationList();
		}

		protected void onPostExecute(List<RestaurantLocation> result) {

			for (RestaurantLocation l : result) {
				if (map != null) {
					LatLng location = new LatLng(
							l.getLatitude(),
							l.getLongtitude());
					map.addMarker(new MarkerOptions()
							.position(location)
							.title(l.getName()+"\n"+l.getAddress())
							.icon(BitmapDescriptorFactory
									.fromResource(R.drawable.ic_location_pin3)));
				}
			}

		}

	}

}
