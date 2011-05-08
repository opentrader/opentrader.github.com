package org.neuroph.core.exceptions;

/**
 * Base exception type for Neuroph.
 * @author jheaton
 */
public class NeurophError extends RuntimeException {
	/**
	 * The version ID.
	 */
	private static final long serialVersionUID = 0L;

	
	/**
	 * Default constructor.
	 */
	public NeurophError() {
		
	}
	
	/**
	 * Construct a message exception.
	 * 
	 * @param msg
	 *            The exception message.
	 */
	public NeurophError(final String msg) {
		super(msg);
	}

	/**
	 * Construct an exception that holds another exception.
	 * 
	 * @param t
	 *            The other exception.
	 */
	public NeurophError(final Throwable t) {
		super(t);
	}
	
	/**
	 * Construct an exception that holds another exception.
	 * 
	 * @param msg
	 *            A message.
	 * @param t
	 *            The other exception.
	 */
	public NeurophError(final String msg, final Throwable t) {
		super(msg, t);
	}
}
