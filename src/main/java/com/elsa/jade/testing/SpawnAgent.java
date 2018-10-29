package com.elsa.jade.testing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.behaviours.OneShotBehaviour;
import jade.wrapper.ControllerException;
import jade.wrapper.PlatformController;

public class SpawnAgent extends Agent {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	long startTimeSplice = System.currentTimeMillis();
	@Override
	protected void setup() {
		log.info("Started {}", startTimeSplice);
		log.info("Hello Jaders! {} Agent is ready",getLocalName());
		addBehaviour(new initiateBehaviour());
		long endTimeSplice = System.currentTimeMillis();
		long totalTimeSplice = endTimeSplice - startTimeSplice;
		log.info("Fetching spawn Agent time {} ", totalTimeSplice);
		int seconds = (int) (totalTimeSplice / 1000);
		log.info("total time taken {}",seconds );
	}

	@Override
	protected void takeDown() {
		
		log.info("123 KILLING INITIATOR AGENT {}", getAID().getName());
		long endTimeSplice = System.currentTimeMillis();
		long totalTimeSplice = endTimeSplice - startTimeSplice;
		log.info("Fetching After doDelete {} ", totalTimeSplice);
	}
}

class initiateBehaviour extends OneShotBehaviour {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void action() {
		jade.core.Runtime runtime = jade.core.Runtime.instance();
		String[] agentName = { "exercise", "still", "talk" };
		String[] agentClass = { "com.elsa.jade.testing.Talk", "com.elsa.jade.testing.Exercise",
				"com.elsa.jade.testing.Still" };
		// Create a Profile,
		// where the launch arguments are stored
		Profile profile = new ProfileImpl();
		int counter = 0;
		int counterNew = 0;
		while (true) {
			for (String s : agentName) {
				profile.setParameter(Profile.CONTAINER_NAME, s + "Container" + counterNew);
				profile.setParameter(Profile.MAIN_HOST, "localhost");
				profile.setParameter(Profile.LOCAL_PORT, "10099");
				long startTimeSplice = System.currentTimeMillis();
				log.info("Started {}{} {}",s,counterNew, startTimeSplice);
				
				PlatformController container = runtime.createAgentContainer(profile);
				
				long endTimeSplice = System.currentTimeMillis();
				long totalTimeSplice = endTimeSplice - startTimeSplice;
				log.info("Fetching Container initializing Time {} ", totalTimeSplice);
				
				try {
					long startTime = System.currentTimeMillis();
					log.info("Started {}", startTime);
					for (String s2 : agentClass) {	
						container.createNewAgent(agentName[counter], s2, null);
						counter++;
						long endTime= System.currentTimeMillis();
						long totalTime= endTime- startTime;
						log.info("Fetching Agent initializing time {} ", totalTime);
					}
				} catch (ControllerException e) {
					log.error("{}", e.getMessage());
				}
			}
			counter=0;
			counterNew++;
			if (counterNew == 3) {
				break;
			}
		}
		myAgent.doDelete();
	}
}
