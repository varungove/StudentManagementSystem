package studentmanagement;

import java.util.ArrayList;

/**
 * @author varungove
 * 
 *         This class contains all information on a particular student.
 *
 */
public class Student {

	private String studentName;
	private ArrayList<Subject> subjects;
	private String className;
	private int value;

	/**
	 * Default Constructor
	 */
	public Student() {
		studentName = "";
		subjects = new ArrayList<Subject>();
		className = "";
		value = 0;
	}

	/**
	 * Setter for Name
	 * 
	 * @param name
	 */
	public void setStudentName(String name) {
		this.studentName = name;
	}

	/**
	 * Setter for Value
	 * 
	 * @param name
	 */
	public void setStudentValue(int value) {
		this.value = value;
	}

	/**
	 * Setter for class name
	 * 
	 * @param name
	 */
	public void setClassName(String name) {
		this.className = name;
	}

	/**
	 * Setter for students subjects.
	 * 
	 * @param subjects
	 *            subjects offered by student's classRoom.
	 */
	public void setStudentSubjets(ArrayList<Subject> subjects) {
		this.subjects = subjects;

	}

	/**
	 * Getter for student name
	 * 
	 * @return Student Name
	 */
	public String getStudentName() {
		return this.studentName;
	}

	/**
	 * Getter for class name
	 * 
	 * @return Class name
	 */
	public String getClassName() {
		return this.className;
	}

	/**
	 * Getter for subject list
	 * 
	 * @return ArrayList of Subjects
	 */
	public ArrayList<Subject> getSubjects() {
		return this.subjects;
	}

	/**
	 * Updates students value and returns it
	 * 
	 * @return value
	 */
	public int updateAndGetValue(ClassRoom room) {
		this.value = Statistics.calculateStudentValue(this, room);
		return this.value;
	}

	/**
	 * Returns student's value
	 * 
	 * @return value
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Displays the overview for the student.
	 * 
	 */
	public void displayInfo() {
		System.out.println("Name: " + studentName);
		System.out.println("Class: " + className);
		// Attendance future implementation
		System.out.println("Overall Attendance: ");
		System.out.println("Subjects: ");

		for (Subject subject : subjects) {
			System.out.println(subject.getType());
		}
		System.out.println("****************************");
	}

}
