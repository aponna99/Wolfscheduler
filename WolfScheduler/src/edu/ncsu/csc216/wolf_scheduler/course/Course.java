/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Course class works with a specific course and its details that are passed in
 * 
 * @author Anisha Ponnapati
 *
 */
public class Course extends Activity {

	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/** Course name minimum length of 4 */
	public static final int FOUR = 4;
	/** Course name max length of 6 */
	public static final int SIX = 6;
	/** The length of Course section is 3 */
	public static final int THREE = 3;
	/**
	 * Constructs a Course object with values for all fields
	 * 
	 * @param name course name
	 * @param title course title
	 * @param section course section
	 * @param credits number of credits for the course
	 * @param instructorId professor id
	 * @param meetingDays when the course meets
	 * @param startTime when the course starts
	 * @param endTime when the course ends
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
//		setTitle(title);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
//		setMeetingDays(meetingDays);
//		setCourseTime(startTime, endTime);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged
	 * 
	 * @param name course name
	 * @param title course title
	 * @param section course section
	 * @param credits number of credits for the course
	 * @param instructorId professor id
	 * @param meetingDays when the course meets
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns the Course's name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 4 or
	 * greater than 6, an IllegalArgumentException is thrown.
	 * 
	 * @param name the name to set
	 * @throws IllegalArgumentException if name is null or length is less than 4 or
	 *                                  greater than 6
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		if (name.length() < FOUR || name.length() > SIX) {
			throw new IllegalArgumentException();
		}
		this.name = name;
	}

	/**
	 * Gets the Course's section
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the Course's section. Invalid if section is null, not a digit, and not
	 * exactly 3 digits.
	 * 
	 * @param section the section to set
	 * @throws IllegalArgumentException if section is null and not 3 digits
	 */
	public void setSection(String section) {
		if (section == null) {
			throw new IllegalArgumentException();
		}

		if (section.length() != THREE) {
			throw new IllegalArgumentException();
		}
		char c;
		for (int i = 0; i < section.length(); i++) {
			c = section.charAt(i);
			if (!Character.isDigit(c)) {
				throw new IllegalArgumentException();
			}
		}

		this.section = section;
	}

	/**
	 * Gets the Course's credits
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the Course's credits. Invalid if credit hours are not a number, less
	 * than 1, or greater than 5.
	 * 
	 * @param credits the credits to set
	 * @throws IllegalArgumentException if credits are not int, less than 1, or
	 *                                  greater than 5
	 */
	public void setCredits(int credits) {
		if (credits < 1 || credits > 5) {
			throw new IllegalArgumentException();
		}
		this.credits = credits;
	}

	/**
	 * Gets the Course intructor's id
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the Course intructor's id.
	 * 
	 * @param instructorId the instructorId to set
	 * @throws IllegalArgumentException if id is null or empty string
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null) {
			throw new IllegalArgumentException();
		}
		if (instructorId.equals("")) {
			throw new IllegalArgumentException();
		}
		this.instructorId = instructorId;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * 
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if (getMeetingDays().equals("A")) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + ","
				+ getStartTime() + "," + getEndTime();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	@Override
	public String[] getShortDisplayArray() {
		String[] shortD = new String[4];
		shortD[0] = name;
		shortD[1] = section;
		shortD[2] = getTitle();
		shortD[3] = getMeetingString();
		return shortD;
	}

	@Override
	public String[] getLongDisplayArray() {
		String[] longD = new String[7];
		longD[0] = name;
		longD[1] = section;
		longD[2] = getTitle();
		longD[3] = Integer.toString(getCredits());
		longD[4] = instructorId;
		longD[5] = getMeetingString();
		longD[6] = "";
		return longD;
	}

	/**
	 * Sets meetings days and implements the same functionality as Activity set meeting days
	 * @see edu.ncsu.csc216.wolf_scheduler.course.Activity#setMeetingDays(java.lang.String)
	 * @throws IllegalArgumentException if it is not a day of the week or 'A'
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
			if (c != 'M' && c != 'T' && c != 'W' && c != 'H' && c != 'F' && c != 'A') {
				throw new IllegalArgumentException();
			}
	
			if (c == 'A' && meetingDays.length() != 1) {
				throw new IllegalArgumentException();
			}
		}
		super.setMeetingDays(meetingDays);
	}

	/**
	 * Checks to see there is duplicate Course
	 * @param activity the activity object passing through
	 * @return boolean if there is a duplicate or not
	 */
	public boolean isDuplicate(Activity activity) {
		if(activity instanceof Course) {
			Course c = (Course) activity;
			if(this.getName().equals(c.getName())) {
				return true;
			}
			}
			return false;
	}

}
