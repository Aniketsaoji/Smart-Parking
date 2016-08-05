package com.example;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;

/**
 * Alarms Object
 * @author 212572312
 *
 */
public class em_rule_engine_alarms {
	    
		private String asset_id;
		private String site_name;
        private String alarm_name;
		private String status;
		private String owner;
		private int duration;
		private int instances;
        private int severity;
        
        private long first_occurance = 0;
        private long last_occurance = 0; 
        
        private String metadata;
        
        private long customDate = 0;
       
        public em_rule_engine_alarms(String name, String status, String owner,
        							String id, String site_name, int duration, int instances, 
        							int severity, String meta){
        	this.alarm_name = name;
        	this.status = status;
        	this.owner = owner;
        	this.asset_id = id;
        	this.site_name = site_name;
        	this.duration = duration;
        	this.instances = instances;
        	this.severity = severity;
        	this.metadata = meta;
        }
     

		public em_rule_engine_alarms(){
		
		}

		public void save(){
			String date;
			if(this.getCustomDate() == 0){
				date = "dateOf(now())";
			}
			else{
				date = "" + this.getCustomDate();
			}
			
			String res = "INSERT INTO iepems_dev.em_rule_engine_alarms"
					+ "(asset_id,site_name,alarm_name,status,owner,duration,instances,severity, ts, first_occurence, last_occurence, metadata)"
					+ " VALUES ('"  + this.getAsset_id() + "','" + this.getSite_name() 
					+ "','" + this.getAlarm_name() + "','" + this.getStatus() + "','" + this.getOwner()  
					+ "',"+ this.getDuration() + "," + this.getInstances() + ","
					 + this.getSeverity() + "," + date + "," 
					+ this.getFirst_occurance() + "," + this.getLast_occurance() + ",'" + this.getMetadata() + "')";
			System.out.println("SAVING ALARM");
			System.out.println(res);
			HttpEntity<String> entity = new HttpEntity<String>(res);
			RestTemplate rest = new RestTemplate();
			rest.postForEntity("https://em-data-services.run.aws-usw02-pr.ice.predix.io/runStatement", entity, String.class);
		}


        @Override
        public String toString() {
        	String s = "{";
        	 s += ("alarm_name: " + this.alarm_name + " ,asset_id: " +  asset_id + " ,site_name: " 
        			 + site_name + " ,severity: " + severity + " ,duration: " + duration
        			 + " ,instances: " + instances + "}");
        	return s;

        }

		public int getSeverity() {
			return severity;
		}

		public void setSeverity(int severity) {
			this.severity = severity;
		}
		
		public String getAsset_id() {
			return asset_id;
		}

		public void setAsset_id(String asset_id) {
			this.asset_id = asset_id;
		}
		
		public int getInstances() {
			return instances;
		}

		public void setInstances(int instances) {
			this.instances = instances;
		}

		public int getDuration() {
			return duration;
		}

		public void setDuration(int duration) {
			this.duration = duration;
		}

		public String getSite_name() {
			return site_name;
		}

		public void setSite_name(String site_name) {
			this.site_name = site_name;
		}

		public String getOwner() {
			return owner;
		}

		public void setOwner(String owner) {
			this.owner = owner;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
		
		public String getAlarm_name() {
			return alarm_name;
		}

		public void setAlarm_name(String name) {
			this.alarm_name = name;
		}

		public long getCustomDate() {
			return customDate;
		}

		public void setCustomDate(long customDate) {
			this.customDate = customDate;
		}


		public long getFirst_occurance() {
			return first_occurance;
		}


		public void setFirst_occurance(long first_occurance) {
			this.first_occurance = first_occurance;
		}


		public long getLast_occurance() {
			return last_occurance;
		}


		public void setLast_occurance(long last_occurance) {
			this.last_occurance = last_occurance;
		}


		public String getMetadata() {
			return metadata;
		}


		public void setMetadata(String metadata) {
			this.metadata = metadata;
		}



    }