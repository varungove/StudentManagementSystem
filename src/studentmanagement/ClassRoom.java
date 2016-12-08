package studentmanagement;

import java.util.ArrayList;

/**
 * @author varungove
 *
 *         This class stores the information for each class room. The class name
 *         and the roster of students.
 */
public class ClassRoom {

	private String className;
	private ArrayList<Student> students;
	private ArrayList<Subject> subjects;

	/**
	 * Default Constructor
	 */
	public ClassRoom() {
		className = "";
		students = new ArrayList<Student>();
		subjects = new ArrayList<Subject>();
	}

	/**
	 * Setter for class name
	 * 
	 * @param className
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * Setter for subjects
	 * 
	 * @param Arraylist
	 *            of subs
	 */
	public void setSubjects(ArrayList<Subject> subs) {
		this.subjects = subs;
	}

	/**
	 * Setter for student list
	 * 
	 * @param ArrayList
	 *            of students
	 */
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	/**
	 * Adds a subject to the ClassRooms subjects
	 * 
	 * @param subject
	 */
	public void addSubject(Subject subject) {
		this.subjects.add(subject);
	}

	/**
	 * Adds a student to the class
	 * 
	 * @param student
	 */
	public void addStudent(Student student) {
		this.students.add(student);
	}

	/**
	 * Removes a student from the class
	 * 
	 * @param student
	 */
	public void deleteStudent(Student student) {
		this.students.remove(student);
	}

	/**
	 * Getter for the arrayList of students
	 * 
	 * @return students
	 */
	public ArrayList<Student> getStudents() {
		return this.students;
	}

	/**
	 * Getter for the ArrayList of Subjects
	 * 
	 * @return subjects
	 */
	public ArrayList<Subject> getSubjects() {
		return this.subjects;
	}

	/**
	 * @return Class Name
	 */
	public String getClassName() {
		return this.className;
	}

	/**
	 * Displays the class room overview
	 */
	public void displayInfo() {
		System.out.println("Class: " + className);
		System.out.println("Number of Students: " + students.size());
		System.out.println("****************************");
	}

}
