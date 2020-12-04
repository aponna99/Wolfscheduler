package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Event class that makes use of the Abstract class Activity
 * @author Anisha Ponnapati
 *
 */
public class Event extends Activity {

	/** How often an event repeats */
	private int weeklyRepeat;
	/** Details of the event */
	private String eventDetails;
	
	/**
	 * Constructor for creating an Event
	 * @param title title of the event
	 * @param meetingDays when the event meets
	 * @param startTime when the event starts
	 * @param endTime when the event ends
	 * @param weeklyRepeat how many time a week does the event occur
	 * @param eventDetails the details of the event
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, int weeklyRepeat, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setWeeklyRepeat(weeklyRepeat);
		setEventDetails(eventDetails);
	}

	/**
	 * Gets the number of time an event occurs in a week
	 * @return weeklyRepeat the number it occurs
	 */
	public int getWeeklyRepeat() {
		return weeklyRepeat;
	}

	/**
	 * Sets the weekly repeat
	 * @param weeklyRepeat how many times in occurs
	 * @throws IllegalArgumentException if number is less than 1 or more than 4
	 */
	public void setWeeklyRepeat(int weeklyRepeat) {
		if(weeklyRepeat < 1 || weeklyRepeat > 4) {
			throw new IllegalArgumentException("Invalid weekly repeat");
		}
		this.weeklyRepeat = weeklyRepeat;
	}

	/**
	 * Returns the event details
	 * @return eventDetails details of the event
	 */
	public String getEventDetails() {
		return eventDetails;
	}

	/**
	 * Sets the details for the event 
	 * @param eventDetails String of event details
	 * @throws IllegalArgumentException if String is null
	 */
	public void setEventDetails(String eventDetails) {
		if(eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details");
		}
		this.eventDetails = eventDetails;
	}

	/**
	 * Gets the short display of information
	 * @return shortd information with title and meeting string
	 */
	public String[] getShortDisplayArray() {
		String[] shortd = new String[4];
		shortd[0] = "";
		shortd[1] = "";
		shortd[2] = getTitle();
		shortd[3] = getMeetingString();
		return shortd;
	}

	/**
	 * Gets the long display of information
	 * @return longd information with title, meeting string, and event details
	 */
	public String[] getLongDisplayArray() {
		String[] longd = new String[7];
		longd[0] = "";
		longd[1] = "";
		longd[2] = getTitle();
		longd[3] = "";
		longd[4] = "";
		longd[5] = getMeetingString();
		longd[6] = eventDetails;
		return longd;
	}

	/**
	 * Returns a meeting string of the number of times it is repeated
	 * @see edu.ncsu.csc216.wolf_scheduler.course.Activity#getMeetingString()
	 * @return s the String including weekly repeats
	 */
	@Override
	public String getMeetingString() {
		String s = " (every " + weeklyRepeat + " weeks)";
		s = super.getMeetingString() + s;
		return s;
	}

	/**
	 * Returns the entire String with all information
	 * @see java.lang.Object#toString()
	 * @return s String with all information
	 */
	@Override
	public String toString() {
		String s = getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + "," + weeklyRepeat + "," + eventDetails;
		return s;
	}

	/**
	 * Sets meeting days for event and it excludes checking for 'A' and instead checks for Sunday and Saturday
	 * @see edu.ncsu.csc216.wolf_scheduler.course.Activity#setMeetingDays(java.lang.String)
	 * @throws IllegalArgumentException if meeting days isn't one of the days of the week
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null) {
			throw new IllegalArgumentException();
		}
	
		if (meetingDays.equals("")) {
			throw new IllegalArgumentException();
		}
		char c;
		for (int i = 0; i < meetingDays.length(); i++) {
			c = meetingDays.charAt(i);
			if (c != 'M' && c != 'T' && c != 'W' && c != 'H' && c != 'F' && c != 'S' && c != 'U') {
				throw new IllegalArgumentException();
			}
		}
		super.setMeetingDays(meetingDays);
	}

	/**
	 * Checks to see if there is a duplicate Event or not
	 * @param activity the activity object passing through
	 * @return boolean if duplicate exists or not
	 */
	public boolean isDuplicate(Activity activity) {
		if(activity instanceof Event) {
			Event e = (Event) activity;
			if(this.getTitle().equals(e.getTitle())) {
				return true;
			}
			}
		return false;
	}

}
