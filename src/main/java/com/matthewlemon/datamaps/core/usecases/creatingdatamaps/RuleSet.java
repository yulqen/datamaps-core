package com.matthewlemon.datamaps.core.usecases.creatingdatamaps;

import java.util.HashSet;
import java.util.Set;

public class RuleSet {

	private Set<DatamapLineRule> rules;
	
	public Set<DatamapLineRule> getRules() {
		return rules;
	}

	public RuleSet() {
		this.rules = new HashSet<DatamapLineRule>();
	}

	public void addRule(DatamapLineRule rule) {
		this.rules.add(rule);
	}
}
