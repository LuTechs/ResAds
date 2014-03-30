package com.ul.resads.ui;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ul.resads.data.Restaurant;
import com.ul.resads.util.RESTClient;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class RestaurantFragment extends DialogFragment{
	private TextView textViewName;
	private TextView textViewAddress;
	private int resid;
	
	public RestaurantFragment(){}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_restuarant, container);
		textViewName=(TextView)view.findViewById(R.id.textViewName);
		textViewAddress=(TextView)view.findViewById(R.id.textViewAddress);
		getDialog().setTitle("Restaurant Detail");
		new LoadDetailTask().execute("http://phone-price.info/res_ads/getdata.php?action=resbyid&resid="+resid);
		return view;
	}
	
	
	
	public int getResid() {
		return resid;
	}

	public void setResid(int resid) {
		this.resid = resid;
	}


	private  class LoadDetailTask extends AsyncTask<String, Long,Restaurant>{
		protected void onPreExecute() {
			
		}
		@Override
		protected Restaurant doInBackground(String... url) {
			String result=RESTClient.callRESTService(url[0]);
			JSONArray jsonArray = null;
			Restaurant restaurant=null;
			try {
				jsonArray=new JSONArray(result);
				JSONObject jsonObject=jsonArray.getJSONObject(0);
				String imageUrl = "http://ul-soft.com/mobile-service"+jsonObject.getString("res_logo");
				StringBuilder address = new StringBuilder();
				address.append(jsonObject.getString("res_street_num"));
				address.append(jsonObject.getString("res_street_name"));
				address.append(" ");
				address.append(jsonObject.getString("res_suburb"));
				address.append(",");
				address.append(jsonObject.getString("res_postcode"));
				address.append(",");
				address.append(jsonObject.getString("res_state"));
				
				restaurant=new Restaurant(jsonObject.getString("res_name"), address.toString(), imageUrl);
			} catch (JSONException e) {
				Log.e("JSON Parser", e.toString());
			}
			return restaurant;
		}
		
		protected void onPostExecute(Restaurant result){
			textViewName.setText(result.getName());
			textViewAddress.setText(result.getAddress());

		}

	}

	
	
	
	
}
