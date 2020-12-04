/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;

/**
 * Writes files with list of activities
 * @author Anisha Ponnapati
 *
 */
public class ActivityRecordIO {

	/**
	 * Writes records of Course to a file
	 * @param fileName name of file
	 * @param courses list of courses
	 * @throws IOException thrown if there is trouble in writing the file
	 */
	public static void writeActivityRecords(String fileName, ArrayList<Activity> courses) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));
	
		for (Activity c : courses) {
            fileWriter.println(c.toString());
        }
        
        fileWriter.close();
		
	}

}
