package com.example;

import java.sql.Timestamp;


public class Event {
	
	
	private int num_filled;
	private int num_open;
	private Timestamp ts;
	
	
	public int getNum_filled() {
		return num_filled;
	}
	public void setNum_filled(int num_filled) {
		this.num_filled = num_filled;
	}
	public int getNum_open() {
		return num_open;
	}
	public void setNum_open(int num_open) {
		this.num_open = num_open;
	}
	public Timestamp getTs() {
		return ts;
	}
	public void setTs(Timestamp ts) {
		this.ts = ts;
	}
	
	
	

}
