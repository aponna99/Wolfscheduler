package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Abstract super class that feeds into Course
 * 
 * @author Anisha Ponnapati
 *
 */
public abstract class Activity implements Conflict {

	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;
	/** 100 in order to get the minutes and hour from military time */
	public static final int HUNNA = 100;
	/** Minimum minutes before 0 must be added: 10 */
	public static final int TEN = 10;
	/** The highest number used in digital time for hours */
	public static final int TWELVE = 12;
	/** The lowest number a time can be */
	public static final int ZERO = 0;
	/** Highest military time */
	public static final int UPPER_TIME = 2359;
	/** Highest number of minimum */
	public static final int UPPER_HOUR = 59;

	/**
	 * Gets the short version of information
	 * 
	 * @return String of basic information for activity
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Gets the long version of information
	 * 
	 * @return string of extended information for activity
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Abstract method to check for duplicates regarding course and events
	 * 
	 * @param activity the Activity object
	 * @return boolean to see if it is a boolean or not
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Constructor that sets fundamental information for activity
	 * 
	 * @param title       activity title
	 * @param meetingDays activity meeting days
	 * @param startTime   when activity starts
	 * @param endTime     when activity ends
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);
	}

	/**
	 * Gets the Course's title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Course's title. If title is null or empty then it invalid.
	 * 
	 * @param title the title to set
	 * @throws IllegalArgumentException if title is null or empty string
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException();
		}
		if (title.equals("")) {
			throw new IllegalArgumentException();
		}
		this.title = title;
	}

	/**
	 * Gets the Course's meeting days
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets the Course's meeting days
	 * 
	 * @param meetingDays the meetingDays to set
	 * @throws IllegalArgumentException if meeting days isn't in the weekday or 'A'
	 *                                  and if it is 'A' then it must be the only
	 *                                  character
	 */
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null) {
			throw new IllegalArgumentException();
		}

		if (meetingDays.equals("")) {
			throw new IllegalArgumentException();
		}
		this.meetingDays = meetingDays;
	}

	/**
	 * Gets the Course's start time
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Gets the Course's end time
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the Course start and end time
	 * 
	 * @param startTime the start time
	 * @param endTime   the end time
	 * @throws IllegalArgumentException if start time or end time in an invalid
	 *                                  military time, end time is less than start
	 *                                  time, and there is a time listed for 'A'
	 */
	public void setActivityTime(int startTime, int endTime) {
		if (meetingDays.equals("A") && startTime != ZERO && endTime != ZERO) {
			throw new IllegalArgumentException();
		}
		int startHour = startTime / HUNNA;
		int startMin = startTime % HUNNA;
		int endHour = endTime / HUNNA;
		int endMin = endTime % HUNNA;

		if (startTime < ZERO || startTime > UPPER_TIME || startMin > UPPER_HOUR) {
			throw new IllegalArgumentException();
		}

		if (endTime < ZERO || endTime > UPPER_TIME || endMin > UPPER_HOUR) {
			throw new IllegalArgumentException();
		}

		if (endHour < startHour) {
			throw new IllegalArgumentException();
		}

		if (endHour == startHour && endMin < startMin) {
			throw new IllegalArgumentException();
		}

		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Returns a string with meeting days and time information
	 * 
	 * @return String representation of meeting days and time
	 */
	public String getMeetingString() {
		int startHour = startTime / HUNNA;
		int startMin = startTime % HUNNA;
		int endHour = endTime / HUNNA;
		int endMin = endTime % HUNNA;

		String start = "";
		String end = "";

		if (startHour >= ZERO && startHour < TWELVE) {
			if (startHour == ZERO) {
				start += "12:";
				if (startMin < TEN) {
					start += "0" + startMin + "AM";
				} else {
					start += startMin + "AM";
				}
			} else {
				start += startHour + ":";
				if (startMin < TEN) {
					start += "0" + startMin + "AM";
				} else {
					start += startMin + "AM";
				}
			}
		} else {
			startHour = startHour % TWELVE;
			if (startHour == ZERO) {
				start += "12:";
				if (startMin < TEN) {
					start += "0" + startMin + "PM";
				} else {
					start += startMin + "PM";
				}
			} else {
				start += startHour + ":";
				if (startMin < TEN) {
					start += "0" + startMin + "PM";
				} else {
					start += startMin + "PM";
				}
			}
		}

		if (endHour >= ZERO && endHour < TWELVE) {
			if (endHour == ZERO) {
				end += "12:";
				if (endMin < TEN) {
					end += "0" + endMin + "AM";
				} else {
					end += endMin + "AM";
				}
			} else {
				end += endHour + ":";
				if (endMin < TEN) {
					end += "0" + endMin + "AM";
				} else {
					end += endMin + "AM";
				}
			}
		} else {
			endHour = endHour % TWELVE;
			if (endHour == ZERO) {
				end += "12:";
				if (endMin < TEN) {
					end += "0" + endMin + "PM";
				} else {
					end += endMin + "PM";
				}
			} else {
				end += endHour + ":";
				if (endMin < TEN) {
					end += "0" + endMin + "PM";
				} else {
					end += endMin + "PM";
				}
			}
		}

		if (meetingDays.equals("A")) {
			return "Arranged";
		} else {
			return meetingDays + " " + start + "-" + end;
		}
	}

	/**
	 * Checks the code against others
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/**
	 * Checks objects
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.ncsu.csc216.wolf_scheduler.course.Conflict#checkConflict(edu.ncsu.csc216.
	 * wolf_scheduler.course.Activity)
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		boolean conflict = false;
		
		if(this.getMeetingDays().equals(possibleConflictingActivity.getMeetingDays())) {
			conflict = true;
		}

		if (this.getMeetingDays().contains("A") || possibleConflictingActivity.getMeetingDays().contains("A")) {
			conflict = false;
		}
		

		int start = possibleConflictingActivity.getStartTime();
		int end = possibleConflictingActivity.getEndTime();
		int startA = this.getStartTime();
		int endA = this.getEndTime();

		if (conflict) {
			if (start <= startA && end >= endA) {
				throw new ConflictException();
			} else if (start >= startA && start <= endA) {
				throw new ConflictException();
			} else if (end >= startA && end <= endA) {
				throw new ConflictException();
			}
		}
	}

}