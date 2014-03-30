package com.ul.resads.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ul.resads.util.RESTClient;


import android.util.Log;

public class ListCategory {

	private List<Category>categoryList;
	

	public ListCategory() {
		super();
		categoryList=new ArrayList<Category>();
	}


	


	public List<Category> getCategoryList() {
		return categoryList;
	}





	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}





	public void excuteURL(String url){
		String result=RESTClient.callRESTService(url);
		JSONArray jsonArray = null;
		try {
			jsonArray=new JSONArray(result);
		} catch (JSONException e) {
			Log.e("JSON Parser", e.toString());
		}
		for(int i=0;i<jsonArray.length();i++){
			try {
				JSONObject jsonObject=jsonArray.getJSONObject(i);
				Category category=new Category(jsonObject.getInt("cat_id"), jsonObject.getString("cat_name"));
				categoryList.add(category);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
	}
	
	
	
}
