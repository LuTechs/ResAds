package com.ul.resads.data;

import java.net.URL;


import android.os.Parcel;
import android.os.Parcelable;


public class Food implements Parcelable{
	private int resId;
	private String foodImageUrl;
	private String foodName;
	private int resdId;
	

	
	public Food(int resId, String foodImage, String foodName, int resdId) {
		super();
		this.resId = resId;
		this.foodImageUrl = foodImage;
		this.foodName = foodName;
		this.resdId = resdId;
	}
	public int getResId() {
		return resId;
	}
	public void setResId(int resId) {
		this.resId = resId;
	}

	public String getFoodImageUrl() {
		return foodImageUrl;
	}
	public void setFoodImageUrl(String foodImage) {
		this.foodImageUrl = foodImage;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public int getResdId() {
		return resdId;
	}
	public void setResdId(int resdId) {
		this.resdId = resdId;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(resId);
		dest.writeString(foodImageUrl);
		dest.writeString(foodName);
		dest.writeInt(resdId);
	}
	public static final Parcelable.Creator<Food> CREATOR=
			new Parcelable.Creator<Food>() {
		public Food createFromParcel(Parcel in) 
        {
			return new Food(in);
        }

		@Override
		public Food[] newArray(int size) {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	private Food(Parcel in){
		
		resdId=in.readInt();
		foodImageUrl=in.readString();
		foodName=in.readString();
		resdId=in.readInt();
		
		
		
	}
	
	
	
}
