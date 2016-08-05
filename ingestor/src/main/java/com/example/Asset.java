package com.example;

import org.springframework.boot.json.JacksonJsonParser;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Transient;

public class Asset {
	
	
	private String asset_id;
	private ArrayList<ParkingSpots> locs = new ArrayList<ParkingSpots>();
	
	
	
	public ArrayList<ParkingSpots> getLocs() {
		return locs;
	}
	public void setLocs(ArrayList<ParkingSpots> locs) {
		this.locs = locs;
	}
	public String getAsset_id() {
		return asset_id;
	}
	public void setAsset_id(String asset_id) {
		this.asset_id = asset_id;
	}
	
	
	

}
