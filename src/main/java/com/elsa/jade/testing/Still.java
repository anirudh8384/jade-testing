package com.elsa.jade.testing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.Agent;

public class Still extends Agent{
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Override
	protected void setup() {
		doWait(1000);
		doDelete();
	}
	
	protected void takeDown() {
		System.out.println("Killing the agent " + getAID().getName() + "with AID = "+ getAID());
	}
}