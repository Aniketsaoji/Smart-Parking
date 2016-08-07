package com.example;

import java.util.Date;

public class ParkingSpots {
	
	private String locationuid;
	private float latitude;
	private float longitude;
	private String numeric_id;
	private long last_ts;
	private Boolean status;
	private String asset;
	
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public String getLocationuid() {
		return locationuid;
	}
	public void setLocationuid(String locationuid) {
		this.locationuid = locationuid;
	}
	public String getAsset() {
		return asset;
	}
	public void setAsset(String asset) {
		this.asset = asset;
	}
	public String getNumeric_id() {
		return numeric_id;
	}
	public void setNumeric_id(String numeric_id) {
		this.numeric_id = numeric_id;
	}

    @Override
    public String toString() {
        return "ParkingSpots{" +
                "locationuid='" + locationuid + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", numeric_id='" + numeric_id + '\'' +
                ", status=" + status +
                ", asset='" + asset + '\'' +
                '}';
    }

    public String toJson() {
        return "{\"locationUid\" : " + "\"" + locationuid + "\", " +
                "\"lat\" : " + "" + latitude + ", " +
                "\"lng\" : " + "" + longitude + ", " +
                "\"state\" : " + "\"" + status + "\"," +  
                "\"chance\" : " + "" + get_chance() + "" + "}";
    }
    public Double get_chance(){
    	if(!status){
    		return 10.0;
    	}
    	Date date = new Date();
    	return Math.min(date.getTime() - last_ts, 60000*15.0) / (6000.0 * 15.0);
    }
	public long getLast_ts() {
		return last_ts;
	}
	public void setLast_ts(long last_ts) {
		this.last_ts = last_ts;
	}
}
