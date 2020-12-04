/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Reads records for Course from files and writes it to a file
 * @author Anisha Ponnapati
 * @author Sarah Heckman
 */
public class CourseRecordIO {

	/**
	 * Reads records for course from a file
	 * @param fileName file name
	 * @return a list of courses
	 * @throws FileNotFoundException thrown if file can't be found or read
	 */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
	    ArrayList<Course> courses = new ArrayList<Course>();
	    while (fileReader.hasNextLine()) {
	        try {
	            Course course = readCourse(fileReader.nextLine());
	            boolean duplicate = false;
	            for (int i = 0; i < courses.size(); i++) {
	                Course c = courses.get(i);
	                if (course.getName().equals(c.getName()) &&
	                        course.getSection().equals(c.getSection())) {
	                    //it's a duplicate
	                    duplicate = true;
	                }
	            }
	            if (!duplicate) {
	                courses.add(course);
	            }
	        } catch (IllegalArgumentException e) {
	            //skip the line
	        }
	    }
	    fileReader.close();
	    return courses;
	}

	private static Course readCourse(String nextLine) {
		Course course;
		Scanner s = new Scanner(nextLine);
		s.useDelimiter(",");
		
		try {
			String name = s.next();
			String title = s.next();
			String section = s.next();
			int credits = s.nextInt();
			String instructor = s.next();
			String meetingDays = s.next();
			
			if(name == null || name.equals("")) {
				s.close();
				throw new IllegalArgumentException();
			}
			if(title == null || title.equals("")) {
				s.close();
				throw new IllegalArgumentException();
			}
			if(section == null || section.equals("")) {
				s.close();
				throw new IllegalArgumentException();
			}
			if(instructor == null || instructor.equals("")) {
				s.close();
				throw new IllegalArgumentException();
			}
			if(meetingDays == null || meetingDays.equals("")) {
				s.close();
				throw new IllegalArgumentException();
			}
			
			if (meetingDays.contains("A")) {
				if (s.hasNext()) {
					s.close();
					throw new IllegalArgumentException();
				} else {
					course = new Course(name, title, section, credits, instructor, meetingDays);
				}
			} else {
				int start = s.nextInt();
				int end = s.nextInt();
				course = new Course(name, title, section, credits, instructor, meetingDays, start, end);
			}
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}

		s.close();
		return course;
		
	}

}
