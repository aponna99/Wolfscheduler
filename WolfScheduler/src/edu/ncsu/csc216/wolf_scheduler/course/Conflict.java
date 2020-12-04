/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Interface for Course and Activity
 * @author Anisha Ponnapati
 *
 */
public interface Conflict {

	/**
	 * Checks conflict before adding
	 * @param possibleConflictingActivity object getting passed to get checked for conflict
	 * @throws ConflictException throws an exception if needed
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;
}
