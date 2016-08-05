package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;


/**
 * 
 * @author 212572312
 */
@RestController
public class RuleEngineController {

    private final RuleEngineService service;
    private final Date date = new Date();

    
    @Autowired
    public RuleEngineController(RuleEngineService service) {
        this.service = service;
//        service.initialize();
//        service.getParkingEvents();
    }
    
    @RequestMapping(value = "/getParkingEvents", method = RequestMethod.GET)
    public void getParkingEvents() {
        service.getParkingEvents();
    }

//
//    /** Adds Aggregated Data to Rule Session
//     * Requires json body of 
//     * {"aggregatedDataType": <String>, "hierarchyLevel": <Asset/Site/Segment>, 
//     * "assetId": <String>, "siteId": <String>, "segmentId":<Sting>,
//     * "data": {JSONString} }
//     */
//    @RequestMapping(value = "/aggregateddata", method = RequestMethod.POST, consumes = {"application/json"})
//    public String aggregateddata(
//    		@RequestBody(required = true) AggregatedData a){
//
//    	a.parse();
//    	a.setReceived(new Timestamp(this.date.getTime()));
//    	service.insertAggregatedData(a);
//    	return "Inserted";
//    }
//    
//    /** Adds new Rule to Rule table
//     * Requires json body of 
//     * {"ruleName": <String>, "ruleContents": <String>}
//     */
//    @RequestMapping(value = "/addrule", method = RequestMethod.POST, consumes = {"application/json"})
//    public String addrule(
//    		@RequestBody(required = true) Rule r){
//    	service.addrule(r);
//    	return "Added rule " + r.getRuleName();
//    }
//    
    @CrossOrigin(origins = "https://bettertraffic-test-app.run.aws-usw02-pr.ice.predix.io")
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String insert(
            @RequestParam(required = true) long start,
            @RequestParam(required = true) long end){

        return service.getEvents(start, end);  
    }
//    
//    /**
//     * Lists all alarms in database
//     * @return
//     */
    @RequestMapping(value = "/initialize", method = RequestMethod.GET)
    public void init(){
    	service.initialize();
    }

    @RequestMapping(value = "/parkingDetails", method = RequestMethod.GET)
    public void getParkingDetails() {
        service.getParkingDetails();
    }
    
//    /**
//     * Lists all data in database
//     * @return
//     */
//    @RequestMapping(value = "/listdata", method = RequestMethod.GET)
//    public String listData(){
//    	return service.listData();
//    }
//    
//    /**
//     * Lists all rules in database
//     * @return
//     */
//    @RequestMapping(value = "/listrules", method = RequestMethod.GET)
//    public String listRules(){
//    	return service.listRules();
//    }
}
