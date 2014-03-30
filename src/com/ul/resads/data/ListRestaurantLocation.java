package com.ul.resads.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.ul.resads.util.RESTClient;

public class ListRestaurantLocation {

	private List<RestaurantLocation> restaurantLocationList;

	
	public ListRestaurantLocation() {
		super();
		restaurantLocationList=new ArrayList<RestaurantLocation>();
	}

	public List<RestaurantLocation> getRestaurantLocationList() {
		return restaurantLocationList;
	}

	public void setRestaurantLocationList(
			List<RestaurantLocation> restaurantLocationList) {
		this.restaurantLocationList = restaurantLocationList;
	}
	public void excuteURL(String url) {
		String result = RESTClient.callRESTService(url);
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(result);
			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject jsonObject = jsonArray.getJSONObject(i);
				RestaurantLocation location=new RestaurantLocation(
										jsonObject.getString("res_name"),
										jsonObject.getString("address"), 
										jsonObject.getDouble("res_longtitude"),
										jsonObject.getDouble("res_latitude"));
				restaurantLocationList.add(location);
				
			}

		} catch (JSONException e) {
			Log.e("JSON Parser", e.toString());
		}

	}
	
}
