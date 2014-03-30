package com.ul.resads.data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.ul.resads.util.RESTClient;

public class ListPromotion {
	private List<Promotion> promotionList;

	public ListPromotion() {
		super();
		promotionList=new ArrayList<Promotion>();
	}

	public List<Promotion> getListPromotion() {
		return promotionList;
	}

	public void setListPromotion(List<Promotion> listPromotion) {
		this.promotionList = listPromotion;
	}

	public void excuteURL(String url) {
		String result = RESTClient.callRESTService(url);
		JSONArray jsonArray = null;
		try {
			jsonArray = new JSONArray(result);
			for (int i = 0; i < jsonArray.length(); i++) {

				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Promotion promotion=new Promotion(jsonObject.getString("pro_des"), 
												jsonObject.getString("res_name"),
												jsonObject.getString("address"));
				promotionList.add(promotion);

			}

		} catch (JSONException e) {
			Log.e("JSON Parser", e.toString());
		}

	}

}
