package com.example;

import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.boot.json.JacksonJsonParser;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;


/**
 * Handles the Rule Session and insertions into the Rule Session
 * @author 212572312
 *
 */
@Service
public class RuleEngineService {
	
	private HashMap<String, String> map = new HashMap<String, String>();
	
//	private static ParkingSpotsRepository repo;

	/**
	 * Initializes rule session from postgres, and connects to
	 * aggregated data persistence in postgres.
	 * 
	 * @param kieContainer
	 * @param c
	 */
	
	
	
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

	
	public void getAssets(){
		System.out.println("Getting Assets");
		
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", "Bearer eyJhbGciOiJSUzI1NiJ9.eyJqdGkiOiJkMjcxOGFhZi04N2I4LTQ0YWMtYjcwZS00YjhmNGE0NGIyN2QiLCJzdWIiOiJhZG1pbiIsInNjb3BlIjpbImNsaWVudHMucmVhZCIsImNsaWVudHMuc2VjcmV0IiwiaWRwcy53cml0ZSIsInVhYS5yZXNvdXJjZSIsImllLXBhcmtpbmcuem9uZXMuMzczYjMwZjUtYmQ4ZS00MGE4LTlkYzItYmM3M2IyN2QzYzI4LmFkbWluIiwiY2xpZW50cy5hZG1pbiIsImllLXRyYWZmaWMuem9uZXMuMTMzM2U5OTMtZWYwZS00NGRmLWEyMTMtMjRjMTYwMzJhZDJiLnVzZXIiLCJzY2ltLnJlYWQiLCJ6b25lcy4xNjJiNmVjZS04OTEwLTQ2NTAtOTJlYi00OGFhY2Q4YmM2Y2EuYWRtaW4iLCJjbGllbnRzLndyaXRlIiwiaWUtcGFya2luZy56b25lcy4zNzNiMzBmNS1iZDhlLTQwYTgtOWRjMi1iYzczYjI3ZDNjMjgudXNlciIsImlkcHMucmVhZCIsInNjaW0ud3JpdGUiXSwiY2xpZW50X2lkIjoiYWRtaW4iLCJjaWQiOiJhZG1pbiIsImF6cCI6ImFkbWluIiwiZ3JhbnRfdHlwZSI6ImNsaWVudF9jcmVkZW50aWFscyIsInJldl9zaWciOiI1YzYyZThmNyIsImlhdCI6MTQ3MDM2OTUwOCwiZXhwIjoxNDcwNDEyNzA4LCJpc3MiOiJodHRwczovLzE2MmI2ZWNlLTg5MTAtNDY1MC05MmViLTQ4YWFjZDhiYzZjYS5wcmVkaXgtdWFhLnJ1bi5hd3MtdXN3MDItcHIuaWNlLnByZWRpeC5pby9vYXV0aC90b2tlbiIsInppZCI6IjE2MmI2ZWNlLTg5MTAtNDY1MC05MmViLTQ4YWFjZDhiYzZjYSIsImF1ZCI6WyJhZG1pbiIsImNsaWVudHMiLCJpZHBzIiwidWFhIiwiaWUtcGFya2luZy56b25lcy4zNzNiMzBmNS1iZDhlLTQwYTgtOWRjMi1iYzczYjI3ZDNjMjgiLCJpZS10cmFmZmljLnpvbmVzLjEzMzNlOTkzLWVmMGUtNDRkZi1hMjEzLTI0YzE2MDMyYWQyYiIsInNjaW0iLCJ6b25lcy4xNjJiNmVjZS04OTEwLTQ2NTAtOTJlYi00OGFhY2Q4YmM2Y2EiXX0.nH5uw0CASACa1Xft1Z8Mptj8qo2huC3l5PpAtxOMwcTUPmBdba0RObahpHuPoQZ0kHolTCMtcSl_D1YE7lU5NdMlXKp4BuyyCOt4JGtE8OhPlZj1ro4cR6P1ckEE3yJ1GWa9kHFhE3fGvc-wRRo5srsmgHzxibnjfx2IRDF5t3q9yG4dhZ_vVdVUGUSrIVRZJxOkcmI3L5fI182ISO8NgvaA-PWaAoUAm_9Q4SFzz8wTxiDOAwuSlYpDe-fEuSH0xbRGAQmPBayZtH-MRE5cCXeFytZIadtF9d2PXy93RfEm8SbhOGT3GYXRuivNoNXDZ218TG_0js6voyrwUqfJiw");
		header.add("Predix-Zone-Id", "373b30f5-bd8e-40a8-9dc2-bc73b27d3c28");
		HttpEntity entity  = new HttpEntity(header);
		RestTemplate rest = new RestTemplate();
		String url = "https://ie-parking.run.aws-usw02-pr.ice.predix.io/v1/locations/search?q=location-type:PARKING_SPOT&bbox=0.716:-117.163,82.720:117.263&size=40&page=0";
		String shiet = rest.exchange(url, HttpMethod.GET, entity, String.class).getBody();
		
		//System.out.println("AYYLMAO " + shiet);
		
		JacksonJsonParser parser = new JacksonJsonParser();
		Map<String, Object> parsedData = parser.parseMap(shiet);
		LinkedHashMap locations = (LinkedHashMap)parsedData.get("_embedded");
		//System.out.println(" " + locations);
		
		ArrayList<LinkedHashMap> allLocs = (ArrayList<LinkedHashMap>)locations.get("locations");
//		
		//System.out.println(" " + allLocs);
		System.out.println("_____________ ");
		for(int i = 0; i < allLocs.size(); i++){
			LinkedHashMap lnks = (LinkedHashMap) allLocs.get(i).get("_links");
			String s = ((LinkedHashMap)lnks.get("self")).get("href").toString();
			String[] s_arr = s.split("/");
			System.out.println("LOCATION: " + s_arr[s_arr.length - 1]);
			
			
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
			map.put(s_arr[s_arr.length - 1].toString(), asset);

		}
		ArrayList<String> assets = new ArrayList<String>();
		for (String s: map.values()){
			if(!assets.contains(s)){
				assets.add(s);
				String webs_url = "https://ie-parking.run.aws-usw02-pr.ice.predix.io/v1/assets/"+s+"/live-events?assetId="+s+"&event-types=PKIN,PKOUT";
				String res2 = rest.exchange(webs_url, HttpMethod.GET, entity, String.class).getBody();
				Map<String, Object> fin = parser.parseMap(res2);
				String websocket_url = fin.get("url").toString();
				System.out.println(websocket_url);
				
			}
		}
    }
		
//    /**
//     * Lists alarms currently in the database
//     */
//    public String listAll(){
//    	HttpEntity<String> entity = new HttpEntity<String>("select * from iepems_dev.em_rule_engine_alarms");
//		RestTemplate rest = new RestTemplate();
//		ResponseEntity<String> resp = rest.postForEntity("https://em-data-services.run.aws-usw02-pr.ice.predix.io/runStatement", entity, String.class);
//		return resp.getBody();
//    }
//    
//    /**
//     * Lists aggregated data currently in the Persistance database
//     */
//    public String listData(){
//    	System.out.println(repo.toString());
//    	String output = "DATABASE:\n";
//    	if(repo.count() > 0){
//    		for (AggregatedData m : repo.findAll()){
//    			m.parse();
//        		output += ("\n DB: " + m);
//        	}
//    	}
//    	return output;
//    }
//    
//    /**
//     * Lists rules
//     */
//    public String listRules(){
//    	String output = "RULES:\n";
//    	if(rules.count() > 0){
//    		for (Rule m : rules.findAll()){
//        		output += ("\n RULE: " + m.getRuleName() + "CONTENT: " + m.getRuleContents());
//        	}
//    	}
//    	return output;
//    }
//
//    /**
//     * Insert aggregated data into rule session. Also saves
//     * Aggregated data in db, for persistence.
//     * @param a
//     */
//	public void insertAggregatedData(AggregatedData a) {
//		repo.save(a);
//		this.ksession.insert(a);
//		this.ksession.fireAllRules();
//	}
//
//	/**
//	 * Adds a new rule to a database and updates rule session
//	 * @param r
//	 */
//	public void addrule(Rule r) {
//		rules.save(r);
//		addRuleToSession(r);
//		//ksession.fireAllRules();
//	}
//	
//	/**
//	 * Deletes a rule from the database and updates the
//	 * rule session accordingly
//	 * @param rulename
//	 */
//	public void deleterule(String rulename) {
//		rules.delete(rulename);
//		kbase.removeKiePackage(rulename);
//	}
//	
//	/**
//	 * Helper function to add a rule to the rule session
//	 * @param r
//	 */
//	private void addRuleToSession(Rule r){
//		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
//        kbuilder.add(ResourceFactory.newReaderResource((Reader) new StringReader(r.getRuleContents())), ResourceType.DRL);
//        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
//
//	}


}
