package com.example;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.boot.json.JacksonJsonParser;

import org.springframework.web.client.RestTemplate;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;


/**
 * Handles the Rule Session and insertions into the Rule Session
 * @author 212572312
 *
 */
@Service
public class RuleEngineService {
	
	ArrayList<Asset> allAssets = new ArrayList<Asset>();
	ArrayList<ParkingSpots> allLocations = new ArrayList<ParkingSpots>();
	ArrayList<Event> events = new ArrayList<Event>();
	
	long end;
	long start;
	
	String authToken = "Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6ImxlZ2FjeS10b2tlbi1rZXkiLCJ0eXAiOiJKV1QifQ.eyJqdGkiOiI3ZDkwZDBhNTJiYjA0ZTBiYjc2OTlhOGRjNzM1MTc3MSIsInN1YiI6InBhcmtpbmdfdGVzdCIsInNjb3BlIjpbInVhYS5yZXNvdXJjZSIsIm9wZW5pZCIsInVhYS5ub25lIiwiaWUtcGFya2luZy56b25lcy5kZWFkZjk5NC02ZDIxLTRlNmMtYWNjOC05N2RjM2QyOWU0ZjUudXNlciJdLCJjbGllbnRfaWQiOiJwYXJraW5nX3Rlc3QiLCJjaWQiOiJwYXJraW5nX3Rlc3QiLCJhenAiOiJwYXJraW5nX3Rlc3QiLCJncmFudF90eXBlIjoiY2xpZW50X2NyZWRlbnRpYWxzIiwicmV2X3NpZyI6ImViNTg0MjZjIiwiaWF0IjoxNDcyODYyODgzLCJleHAiOjE0NzI5MDYwODMsImlzcyI6Imh0dHBzOi8vNzk1ZTAwMmItNjVjYi00ODNiLWFkZmUtY2M2ZDdiMjMwYTVjLnByZWRpeC11YWEucnVuLmF3cy11c3cwMi1wci5pY2UucHJlZGl4LmlvL29hdXRoL3Rva2VuIiwiemlkIjoiNzk1ZTAwMmItNjVjYi00ODNiLWFkZmUtY2M2ZDdiMjMwYTVjIiwiYXVkIjpbInBhcmtpbmdfdGVzdCIsInVhYSIsIm9wZW5pZCIsImllLXBhcmtpbmcuem9uZXMuZGVhZGY5OTQtNmQyMS00ZTZjLWFjYzgtOTdkYzNkMjllNGY1Il19.wTuU9sRl3hwsXnvbs0VaeQp4fQEDjm5NBxwlkU1JeM_wFg89phhffrQj2vIjYIxvn9yk5EUxkfa8lgmCP2VFWT_hqu5gSOi5biFYdivShvMRTuxkPv2QUmdsemsag-Td8slibVay_-cliJ4lEpNRpjgxbRC3Nw1YIfqkzk0Ro0TrSXH0aP3jv1YB9P0ClpOzwd80PQRR3qVUkRkAU1EtWL7M9kTkfuMN_b0XhSYMshXJcWo1sAawryx1pfp1nrEYq_mVpB0xQlPHzcx4TbxhRVDmr1a-AtlSdQloR57xP5xwQ-QnxqcsAxdRfr8BST9k3gkF0f69UlFfcp69d9o_Mw";
//	private static ParkingSpotsRepository repo;

	
//	@Autowired
//	public RuleEngineService() {
//		// Connects to Repositories
////	    this.repo = repo;
//	    
//
//		System.out.println("Getting Assets");
//		HttpHeaders header = new HttpHeaders();
//		header.add("Authorization", "Bearer eyJhbGciOiJSUzI1NiJ9.eyJqdGkiOiI0ZWEwMmYwMC1kYjY0LTQ0ZGYtODU5My00NzJlMGQyZWM2ODkiLCJzdWIiOiJhZG1pbiIsInNjb3BlIjpbImNsaWVudHMucmVhZCIsImNsaWVudHMuc2VjcmV0IiwiaWRwcy53cml0ZSIsInVhYS5yZXNvdXJjZSIsImllLXBhcmtpbmcuem9uZXMuMzczYjMwZjUtYmQ4ZS00MGE4LTlkYzItYmM3M2IyN2QzYzI4LmFkbWluIiwiY2xpZW50cy5hZG1pbiIsImllLXRyYWZmaWMuem9uZXMuMTMzM2U5OTMtZWYwZS00NGRmLWEyMTMtMjRjMTYwMzJhZDJiLnVzZXIiLCJzY2ltLnJlYWQiLCJ6b25lcy4xNjJiNmVjZS04OTEwLTQ2NTAtOTJlYi00OGFhY2Q4YmM2Y2EuYWRtaW4iLCJjbGllbnRzLndyaXRlIiwiaWUtcGFya2luZy56b25lcy4zNzNiMzBmNS1iZDhlLTQwYTgtOWRjMi1iYzczYjI3ZDNjMjgudXNlciIsImlkcHMucmVhZCIsInNjaW0ud3JpdGUiXSwiY2xpZW50X2lkIjoiYWRtaW4iLCJjaWQiOiJhZG1pbiIsImF6cCI6ImFkbWluIiwiZ3JhbnRfdHlwZSI6ImNsaWVudF9jcmVkZW50aWFscyIsInJldl9zaWciOiI1YzYyZThmNyIsImlhdCI6MTQ3MDM2NzEyOCwiZXhwIjoxNDcwNDEwMzI4LCJpc3MiOiJodHRwczovLzE2MmI2ZWNlLTg5MTAtNDY1MC05MmViLTQ4YWFjZDhiYzZjYS5wcmVkaXgtdWFhLnJ1bi5hd3MtdXN3MDItcHIuaWNlLnByZWRpeC5pby9vYXV0aC90b2tlbiIsInppZCI6IjE2MmI2ZWNlLTg5MTAtNDY1MC05MmViLTQ4YWFjZDhiYzZjYSIsImF1ZCI6WyJhZG1pbiIsImNsaWVudHMiLCJpZHBzIiwidWFhIiwiaWUtcGFya2luZy56b25lcy4zNzNiMzBmNS1iZDhlLTQwYTgtOWRjMi1iYzczYjI3ZDNjMjgiLCJpZS10cmFmZmljLnpvbmVzLjEzMzNlOTkzLWVmMGUtNDRkZi1hMjEzLTI0YzE2MDMyYWQyYiIsInNjaW0iLCJ6b25lcy4xNjJiNmVjZS04OTEwLTQ2NTAtOTJlYi00OGFhY2Q4YmM2Y2EiXX0.GMbCMp8nJUWoJmoy-C4bfQbpHbH4f-s__Qe-ImZSjZ7eemNB7jifu4J-kgmi3BBFrYItPG8OAZD9r03PD7GpzodfYxYO5DeJR2D6HkNWW4HZA4EGAV8m0WIlvOEbV_52S5HBD4zWPWFxVQt7B5Hw2GBpnwS4aVvOxnzib3mJ3bS0idInSZwgOr1x8qPyjd3pGK1wpOQp8MlSZMhgrxH24hoUooh5uqbi9sd9Nxvef33mZgOhPCKtxhU66oApUV0YycyubF7wiWtxTgl2ysLrP0dxYIdUwxBFuUIqi0eOP3hJPmQO4_c_YsNM0ZFWUkVFXyGkoeq9MDRImNl-Po27cA");
//		header.add("Predix-Zone_Id", "373b30f5-bd8e-40a8-9dc2-bc73b27d3c28");
//		RestTemplate rest = new RestTemplate();
//		String shiet = rest.getForObject("https://ie-parking.run.aws-usw02-pr.ice.predix.io/v1/locations/search?q=location-type:PARKING_SPOT&bbox=0.716:-117.163,82.720:117.263&size=20&page=0", String.class);
//		
//		System.out.println("AYYLMAO " + shiet);
//	    
//	    
//	}

    public double getParkingPrice() {
        double total = 22.0;
        double filled = 0.0;
        for (ParkingSpots parkingSpots : allLocations) {
            if (parkingSpots.getStatus() == true) {
                filled += 1;
            }
        }
        System.out.println(filled);
        System.out.println(total);
        double percentFilled = filled/total;
        double price = 3 + percentFilled*5;
        return price;
    }

    public String getParkingDetails() {
        String json2Return = "{ \"data\":[";
        for(ParkingSpots parkingSpots : allLocations) {
            json2Return = json2Return + parkingSpots.toJson() + ",";
//            System.out.println(parkingSpots.toJson());
        }
        json2Return = json2Return.substring(0, json2Return.length() - 1);
        json2Return = json2Return + "]}";
        System.out.println(json2Return);
        return json2Return;
    }
	
	public String getEvents(long start, long end){
		String s = "{\"events\": [";
		ArrayList<Event> recent = new ArrayList<Event>();
		for(Event e: events){
			if(e.getTs() >= start && e.getTs() <= end){
				s += "{\"ts\":" + e.getTs() + "," + "\"filled\":" + e.getNum_filled() + ",\"open\":" + e.getNum_open() + "},";
			}
			if((new Date()).getTime() - e.getTs() < 86400000){
				recent.add(e);
			}
		}
		s = s.substring(0, s.length()-1);
		s += "]}";
		System.out.println(s);
		events = recent;
		return s;
	}

	@Scheduled(fixedRate=15000)
	public void getParkingEvents() {
		Date d = new Date();
		System.out.println("GETTING DATA " + d.getTime());
		JacksonJsonParser parser = new JacksonJsonParser();
		
		HttpHeaders header = new HttpHeaders();
        header.add("Authorization", authToken);
        header.add("Predix-Zone-Id", "deadf994-6d21-4e6c-acc8-97dc3d29e4f5");
        HttpEntity entity = new HttpEntity(header);
        RestTemplate rest = new RestTemplate();
        
        
    	for(Asset a: allAssets){
//    		System.out.println("ASSET: " + a.getAsset_id());
    		String id = a.getAsset_id();
    		String url = "https://ie-parking.run.aws-usw02-pr.ice.predix.io/v1/assets/"+id+"/events?assetId="+id+"&event-types=PKIN,PKOUT&start-ts=" + start + "&end-ts=" + end;
    		String shiet = rest.exchange(url, HttpMethod.GET, entity, String.class).getBody();
//        		JacksonJsonParser parser = new JacksonJsonParser();
//        		Map<String, Object> parsedData = parser.parseMap(shiet);
//    		System.out.println(shiet);
    		if (shiet != null && !shiet.isEmpty()) {
    			Map<String, Object> eventsData = parser.parseMap(shiet);
        		
        		
        		ArrayList<LinkedHashMap> mp = ((ArrayList<LinkedHashMap>)((LinkedHashMap) eventsData.get("_embedded")).get("events"));
//        		System.out.println(mp);
        		for(LinkedHashMap l : mp){
        			long ts = (long) ((LinkedHashMap)l).get("timestamp");
        			String event_type = (String) ((LinkedHashMap)l).get("event-type");
        			String location_uid = (String) ((LinkedHashMap)l).get("location-uid");
        			
        			
        			for(ParkingSpots s: allLocations){
        				if(s.getLocationuid().equals(location_uid)){
        					if(event_type.equals("PKIN")){
        						if(!s.getStatus()){
        							s.setStatus(true);
        							s.setLast_ts(ts);
        							Event last = events.get(events.size() - 1);
            						Event e = new Event();
            						
            	        			e.setNum_filled(Math.min(22,last.getNum_filled() + 1));
            	        			e.setNum_open(22 - e.getNum_filled());
            	        			e.setTs(ts);
            	        			
            	        			System.out.println(s.getLocationuid() + " " +  s.get_chance() + " " + s.getLast_ts() + " " + s.getStatus());
            	        			
            	        			events.add(e);
        						}
        					}
        					else{
        						if(s.getStatus()){
        							s.setStatus(false);
        							s.setLast_ts(ts);
        							Event last = events.get(events.size() - 1);
            						Event e = new Event();
            	        			e.setNum_filled(Math.max(0, last.getNum_filled() - 1));
            	        			e.setNum_open(22 - e.getNum_filled());
            	        			e.setTs(ts);
            	        			
            	        			System.out.println(s.getLocationuid() + " " +  s.get_chance() + " " + s.getLast_ts() + " " + s.getStatus());
            	        			
            	        			events.add(e);
        						}
        					}
        					break;
        				}
        			}
        		}
    		}
    		
    	}
    	start = end;
    	end = start + 15000;
    
    }

	
	public void initialize(){
		System.out.println("INITIALIZING");
		
		
		
		resetBearer();
		System.out.println(authToken);
		
		Date date = new Date();
		
		end = date.getTime();
		start = end - 15000;
		
		Event init_event = new Event();
		init_event.setNum_filled(0);
		init_event.setNum_open(22);
		init_event.setTs(date.getTime());
		events.add(init_event);
		
		
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", authToken);
		header.add("Predix-Zone-Id", "deadf994-6d21-4e6c-acc8-97dc3d29e4f5");
		HttpEntity entity  = new HttpEntity(header);
		RestTemplate rest = new RestTemplate();
		String url = "https://ie-parking.run.aws-usw02-pr.ice.predix.io/v1/locations/search?q=location-type:PARKING_SPOT&bbox=0.716:-117.163,82.720:117.263&size=40&page=0";
        String locationQueryResponse = rest.exchange(url, HttpMethod.GET, entity, String.class).getBody();
        System.out.println(locationQueryResponse);
		JacksonJsonParser parser = new JacksonJsonParser();
		Map<String, Object> parsedData = parser.parseMap(locationQueryResponse);
		LinkedHashMap locations = (LinkedHashMap)parsedData.get("_embedded");
        System.out.println(locations);

        ArrayList<LinkedHashMap> allLocs = (ArrayList<LinkedHashMap>)locations.get("locations");
//
		System.out.println("_____________ ");
		for(int i = 0; i < allLocs.size(); i++){
            String locationUid = (String) ((LinkedHashMap) allLocs.get(i)).get("location-uid");
            LinkedHashMap geoCoordinates = (LinkedHashMap) allLocs.get(i).get("coordinates");
            String p1Coordinates = (String) geoCoordinates.get("P1");
            String [] p1CoordinatesArray = p1Coordinates.split(",");
            System.out.println(p1CoordinatesArray[0]);
            System.out.println(p1CoordinatesArray[1]);

            System.out.println(locationUid);
            System.out.println(geoCoordinates.toString());

			LinkedHashMap lnks = (LinkedHashMap) allLocs.get(i).get("_links");
			String s = ((LinkedHashMap)lnks.get("self")).get("href").toString();
			String[] s_arr = s.split("/");
            System.out.println("LOCATION: " + s_arr[s_arr.length - 1]);
			ParkingSpots new_loc = new ParkingSpots();
			new_loc.setNumeric_id(s_arr[s_arr.length - 1]);
            new_loc.setLocationuid(locationUid);
            new_loc.setLatitude(Float.parseFloat(p1CoordinatesArray[0]));
            new_loc.setLongitude(Float.parseFloat(p1CoordinatesArray[1]));
            new_loc.setStatus(false);


			String assetLoc = rest.exchange("https://ie-parking.run.aws-usw02-pr.ice.predix.io/v1/locations/" + s_arr[s_arr.length - 1], HttpMethod.GET, entity, String.class).getBody();
			Map<String, Object> pData = parser.parseMap(assetLoc);
//			System.out.println(((ArrayList<LinkedHashMap>)((LinkedHashMap)pData.get("_embedded")).get("assets")).toString());

			LinkedHashMap lc = ((ArrayList<LinkedHashMap>)((LinkedHashMap)pData.get("_embedded")).get("assets")).get(0);
//			System.out.println(lc);
			String assetURL = ((LinkedHashMap)((LinkedHashMap) lc.get("_links")).get("self")).get("href").toString();
//			System.out.println("ASSET: " + assetURL);
			String[] ls_asset = assetURL.split("/");
			String asset = ls_asset[ls_asset.length - 1];
			System.out.println("ASSET: " + asset);
			new_loc.setAsset(asset);
			Boolean found = false;
			for(Asset a : allAssets){
				if(a.getAsset_id().equals(asset)){
					ArrayList<ParkingSpots> spotsForAsset = a.getLocs();
					spotsForAsset.add(new_loc);
					a.setLocs(spotsForAsset);
					found = true;
					break;
				}
			}
			if(!found){
				Asset a = new Asset();
				ArrayList<ParkingSpots> Asset_spots = new ArrayList<ParkingSpots>();
				Asset_spots.add(new_loc);
				a.setLocs(Asset_spots);
				a.setAsset_id(asset);
				allAssets.add(a);
			}

			allLocations.add(new_loc);
            System.out.println("test" + new_loc);

		}
		
		
		
    }
	@Scheduled(fixedRate=10800000)
	public void resetBearer(){
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
		  .url("https://795e002b-65cb-483b-adfe-cc6d7b230a5c.predix-uaa.run.aws-usw02-pr.ice.predix.io/oauth/token?grant_type=client_credentials")
		  .get()
		  .addHeader("authorization", "Basic cGFya2luZ190ZXN0OnBhcmtpbmc=")
		  .addHeader("cache-control", "no-cache")
		  .addHeader("postman-token", "59d2d4b2-5d52-c3aa-9796-ab621287acfe")
		  .build();

		// Response response = client.newCall(request).execute();

		Response response;
		JacksonJsonParser parser = new JacksonJsonParser();
		try {
			
			response = client.newCall(request).execute();
			Map<String, Object> resp = parser.parseMap(response.body().string());
			this.authToken = ("Bearer " + (resp.get("access_token")).toString());
			System.out.println(this.authToken);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
}
