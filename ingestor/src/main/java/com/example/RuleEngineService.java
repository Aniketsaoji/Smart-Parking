package com.example;

import java.io.Reader;
import java.io.StringReader;

import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

/**
 * Handles the Rule Session and insertions into the Rule Session
 * @author 212572312
 *
 */
@Service
public class RuleEngineService {
	
	private static KnowledgeBase kbase;
	private static KieSession ksession;
	private static AggregatedDataRepository repo;
	private static RuleRepository rules;

	/**
	 * Initializes rule session from postgres, and connects to
	 * aggregated data persistence in postgres.
	 * 
	 * @param kieContainer
	 * @param c
	 */
	@Autowired
	public RuleEngineService(AggregatedDataRepository repository, RuleRepository rulerepo) {
		// Connects to Repositories
	    this.repo = repository;
	    this.rules = rulerepo;
	    
	    // Initializes Knowledge Base
	    kbase = KnowledgeBuilderFactory.newKnowledgeBuilder().newKnowledgeBase();
	    
	    // If there are existing rules in database, add them to knowledge base.
	    if(rules.count() > 0){
	    	for (Rule r : rules.findAll()){
	    		addRuleToSession(r);
        	}
	    }
	    
	 // Adds a required rule which deletes aggregated data that has been checked.
	    Rule deleteAggregates = new Rule("deleteAggregates", "package required; \n"
	    		+ " import com.example.*; \n global AggregatedDataRepository db;"
	    		+ " \n rule \"Delete Aggregates From Database\" salience -100 when a : AggregatedData($id: id) then retract(a); db.delete($id); end");
	    addRuleToSession(deleteAggregates);
	    
	    // Creates Rule session from Knowledge Base
	    this.ksession = kbase.newKieSession();
	    
	    // If there is any aggregated data that was recieved but not added to the rule 
	    // session, restores them now
	    long count = repo.count();
    	if(repo.count() > 0){
    		for (AggregatedData dt : repo.findAll()){
    			dt.parse();
        		this.ksession.insert(dt);
        	}
    	}
    	
    	
    	System.out.println("" + count + " items restored.");
    	// Allows access to aggregated data tabe from within rules.
    	ksession.setGlobal("db", repo);
    	ksession.fireAllRules();
	}

    /**
     * Lists alarms currently in the database
     */
    public String listAll(){
    	HttpEntity<String> entity = new HttpEntity<String>("select * from iepems_dev.em_rule_engine_alarms");
		RestTemplate rest = new RestTemplate();
		ResponseEntity<String> resp = rest.postForEntity("https://em-data-services.run.aws-usw02-pr.ice.predix.io/runStatement", entity, String.class);
		return resp.getBody();
    }
    
    /**
     * Lists aggregated data currently in the Persistance database
     */
    public String listData(){
    	System.out.println(repo.toString());
    	String output = "DATABASE:\n";
    	if(repo.count() > 0){
    		for (AggregatedData m : repo.findAll()){
    			m.parse();
        		output += ("\n DB: " + m);
        	}
    	}
    	return output;
    }
    
    /**
     * Lists rules
     */
    public String listRules(){
    	String output = "RULES:\n";
    	if(rules.count() > 0){
    		for (Rule m : rules.findAll()){
        		output += ("\n RULE: " + m.getRuleName() + "CONTENT: " + m.getRuleContents());
        	}
    	}
    	return output;
    }

    /**
     * Insert aggregated data into rule session. Also saves
     * Aggregated data in db, for persistence.
     * @param a
     */
	public void insertAggregatedData(AggregatedData a) {
		repo.save(a);
		this.ksession.insert(a);
		this.ksession.fireAllRules();
	}

	/**
	 * Adds a new rule to a database and updates rule session
	 * @param r
	 */
	public void addrule(Rule r) {
		rules.save(r);
		addRuleToSession(r);
		//ksession.fireAllRules();
	}
	
	/**
	 * Deletes a rule from the database and updates the
	 * rule session accordingly
	 * @param rulename
	 */
	public void deleterule(String rulename) {
		rules.delete(rulename);
		kbase.removeKiePackage(rulename);
	}
	
	/**
	 * Helper function to add a rule to the rule session
	 * @param r
	 */
	private void addRuleToSession(Rule r){
		KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newReaderResource((Reader) new StringReader(r.getRuleContents())), ResourceType.DRL);
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

	}


}
