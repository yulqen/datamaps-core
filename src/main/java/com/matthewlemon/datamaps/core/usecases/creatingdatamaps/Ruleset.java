package com.matthewlemon.datamaps.core.usecases.creatingdatamaps;

import java.util.HashSet;
import java.util.Set;

public class Ruleset {

	private Set<DatamapLineRule> ruleset;
	
	public Ruleset() {
		this.ruleset = new HashSet<DatamapLineRule>();
	}

	public void addRule(DatamapLineRule rule) {
		this.ruleset.add(rule);
	}

}
