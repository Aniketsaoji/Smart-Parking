package com.example;

import org.springframework.boot.json.JacksonJsonParser;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Arrays;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Transient;

@Entity
public class AggregatedData implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	
	private String aggregatedDataType;
	private String hierarchyLevel;
	private String assetId;
	private String siteId;
	private String segmentId;
	private String data;
	
	@Transient
	private Map<String, Object> parsedData;
	
	private Timestamp received;
	private String response;
	
	public void parse(){
		JacksonJsonParser parser = new JacksonJsonParser();
		this.parsedData = parser.parseMap(this.data);
		
	}
	
	@Override
	public String toString(){
		return "" + aggregatedDataType + ", " + 
				hierarchyLevel + ", " + 
				assetId + ", " + 
				siteId + ", " +
				segmentId + ", " + 
				parsedData.toString();
				
	}
	
	public String getAggregatedDataType() {
		return aggregatedDataType;
	}
	public void setAggregatedDataType(String aggregatedDataType) {
		this.aggregatedDataType = aggregatedDataType;
	}
	public String getHierarchyLevel() {
		return hierarchyLevel;
	}
	public void setHierarchyLevel(String hierarchyLevel) {
		this.hierarchyLevel = hierarchyLevel;
	}
	public String getAssetId() {
		return assetId;
	}
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getSegmentId() {
		return segmentId;
	}
	public void setSegmentId(String segmentId) {
		this.segmentId = segmentId;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Timestamp getReceived() {
		return received;
	}
	public void setReceived(Timestamp received) {
		this.received = received;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}

	@Transient
	public Map<String, Object> getParsedData() {
		return parsedData;
	}
	@Transient
	public void setParsedData(Map<String, Object> parsedData) {
		this.parsedData = parsedData;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	

}
