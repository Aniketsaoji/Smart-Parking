package com.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rule {
	@Id
	private String ruleName;
	@Column( length = 100000 )
	private String ruleContents;
	
	Rule(String name, String content){
		ruleName = name;
		ruleContents = content;
	}
	
	Rule(){
		ruleName = "";
		ruleContents = "";
	}
	
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getRuleContents() {
		return ruleContents;
	}
	public void setRuleContents(String ruleContents) {
		this.ruleContents = ruleContents;
	}
	
	
}