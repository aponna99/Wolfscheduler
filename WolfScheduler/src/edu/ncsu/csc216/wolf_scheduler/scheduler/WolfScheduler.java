/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * Reads and stores as a list of the Course records stored in a file
 * @author Anisha Ponnapati
 *
 */
public class WolfScheduler {

	/** Course catalog list */
	private ArrayList<Course> catalog;
	/** Schedule of the courses */
	private ArrayList<Activity> schedule;
	/** Schedule title */
	private String title;
	
	/**
	 * Constructs course catalog, schedule, and title. Tries to add Course objects to catalog from file
	 * @param fileName name of the file
	 * @throws IllegalArgumentException if there is an error
	 */
	public WolfScheduler(String fileName) {
		schedule = new ArrayList<Activity>();
		title = "My Schedule";
		
		try {
			catalog = new ArrayList<Course>(CourseRecordIO.readCourseRecords(fileName));
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Cannot find file.");
		}
	}

	/**
	 * Gets entire Course catalog
	 * @return array of all courses in catalog and details or empty string
	 * array if there are no Courses
	 */
	public String[][] getCourseCatalog() {
		String[][] catalogArray;
		if(catalog.size() <= 0) {
			catalogArray = new String[0][0];
			return catalogArray;
		} else {
			catalogArray = new String[catalog.size()][4];
			for(int i = 0; i < catalog.size(); i++) {
				catalogArray[i][0] = catalog.get(i).getName();
				catalogArray[i][1] = catalog.get(i).getSection();
				catalogArray[i][2] = catalog.get(i).getTitle();
				catalogArray[i][3] = catalog.get(i).getMeetingString();
			}
			return catalogArray;
		}
	}

	/**
	 * Gets an ArrayList of the courses in the schedule with basic details
	 * @return An ArrayList of courses that are on a schedule
	 */
	public String[][] getScheduledActivities() {
		String[][] scheduleArray;
		if(schedule.size() <= 0) {
			scheduleArray = new String[0][0];
			return scheduleArray;
		} else {
			scheduleArray = new String[schedule.size()][3];
			for(int i = 0; i < schedule.size(); i++) {
				scheduleArray[i] = schedule.get(i).getShortDisplayArray();
			}
			return scheduleArray;
		}
	}

	/**
	 * Gets an ArrayList of the courses in the schedule with all the details
	 * @return An ArrayList of courses from schedule 
	 */
	public String[][] getFullScheduledActivities() {
		String[][] scheduleArray;
		if(schedule.size() <= 0) {
			scheduleArray = new String[0][0];
			return scheduleArray;
		} else {
			scheduleArray = new String[schedule.size()][6];
			for(int i = 0; i < schedule.size(); i++) {
				scheduleArray[i] = schedule.get(i).getLongDisplayArray();
			}
			return scheduleArray;
		}
	}

	/**
	 * Takes in a String parameter in order to save student's schedule
	 * @param fileName name of the file
	 * @throws IllegalArgumentException if CourseRecordsIO runs into errors
	 */
	public void exportSchedule(String fileName) {
		try {
			ActivityRecordIO.writeActivityRecords(fileName, schedule);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved.");
		}
		
	}

	/**
	 * Gets Course object from catalog
	 * @param name Course name
	 * @param section Course section
	 * @return Course if found or else null
	 */
	public Course getCourseFromCatalog(String name, String section) {
		for(int i = 0; i < catalog.size(); i++) {
			if(catalog.get(i).getName().equals(name) && catalog.get(i).getSection().equals(section) ) {
				return catalog.get(i);
			}
		}
		return null;
	}

	/**
	 * Adds Course after checking for duplicates in schedule
	 * @param name Course name
	 * @param section Course section
	 * @return true or false based off whether it could add the class
	 * @throws IllegalArgumentException if name of course matches
	 */
	public boolean addCourse(String name, String section) {
		if(getCourseFromCatalog(name, section) == null) {
			return false;
		}
		Course course;
		course = getCourseFromCatalog(name, section);
		for(int i = 0; i < schedule.size(); i++) {
			if(schedule.get(i).isDuplicate(course)) {
				throw new IllegalArgumentException("You are already enrolled in " + name);
			}
		}
		
		for(int i = 0; i < schedule.size(); i++) {
			try {
				schedule.get(i).checkConflict(course);
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The course cannot be added due to a conflict.");
			}
		}
		
		if(schedule.add(course)) {
			return true;
		}
		return false;
	}

	/**
	 * Removes course from schedule
	 * @param idx TODO
	 * @return true or false based off of whether it removed the course
	 */
	public boolean removeActivity(int idx) {
		if(idx > -1 && idx < schedule.size()) {
			schedule.remove(idx);
			return true;
		} else { 
			return false;
		}
	}

	/**
	 * Resets schedule to empty ArrayList
	 */
	public void resetSchedule() {
		schedule = new ArrayList<Activity>();
		
	}

	/**
	 * Sets schedule title
	 * @param title schedule title
	 * @throws IllegalArgumentException if title is null or empty string 
	 */
	public void setTitle(String title) {
		if(title == null || title.equals("")) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		
		this.title = title;
	}

	/**
	 * Gets the title of the schedule
	 * @return title of the schedule
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Checks Events to make sure it is unique and then will add
	 * @param eventTitle title of event
	 * @param eventMeetingDays when the event meets
	 * @param eventStartTime event start time
	 * @param eventEndTime event end time
	 * @param eventWeeklyRepeat how many times an event will repeat
	 * @param eventDetails event details 
	 * @throws IllegalArgumentException if event can not be added
	 */
	public void addEvent(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime,
			int eventWeeklyRepeat, String eventDetails) {
		Event event = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventWeeklyRepeat, eventDetails);
		for(int i = 0; i < schedule.size(); i++) {
			if(schedule.get(i).isDuplicate(event)) {
				throw new IllegalArgumentException("You have already created an event called " + eventTitle);
			}
		}
		
		for(int i = 0; i < schedule.size(); i++) {
			try {
				schedule.get(i).checkConflict(event);
			} catch (ConflictException e) {
				throw new IllegalArgumentException("The event cannot be added due to a conflict.");
		 }
		}
		schedule.add(event);
	}

	
}
