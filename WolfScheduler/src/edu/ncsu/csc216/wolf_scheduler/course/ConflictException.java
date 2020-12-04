package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Custom exception class
 * @author Anisha Ponnapati
 *
 */
public class ConflictException extends Exception {

	/**
	 * ID used for serialization
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Parent constructor 
	 * @param message that is passed through
	 */
	public ConflictException (String message ) {
		super(message);
		
	}

	/**
	 * Child constructor and has message
	 */
	public ConflictException ( ) {
		this("Schedule conflict.");
	}
}
