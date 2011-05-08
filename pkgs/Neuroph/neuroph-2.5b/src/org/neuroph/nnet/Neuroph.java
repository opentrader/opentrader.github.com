package org.neuroph.nnet;

import org.encog.engine.EncogEngine;

/**
 * This singleton holds global settings for the whole framework
 * @author Jeff Heaton
 */
public class Neuroph {
	
	private static Neuroph instance;

        /**
         * Flag to determine if flat network support from Encog is turned on
         */
	private boolean flattenNetworks = false;
	
	public static Neuroph getInstance() {
		if( instance==null )
			instance = new Neuroph();
		return instance;
	}
		
	/**
         * Get setting for flatten network (from Encog engine)
	 * @return the flattenNetworks
	 */
	public boolean shouldFlattenNetworks() {
		return flattenNetworks;
	}

	/**
         * Turn on/off flat networ support from Encog
	 * @param flattenNetworks the flattenNetworks to set
	 */
	public void setFlattenNetworks(boolean flattenNetworks) {
		this.flattenNetworks = flattenNetworks;
	}

        /**
         * Shuts down the Encog engine
         */
	public void shutdown() {
		EncogEngine.getInstance().shutdown();
	}
}
