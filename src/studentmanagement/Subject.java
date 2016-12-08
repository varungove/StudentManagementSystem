package studentmanagement;

import java.util.Arrays;

/**
 * @author varungove
 * 
 *         Class that contains information for each type of subject.
 *
 */
public class Subject {

	/**
	 * @author varungove Possible types for subjects.
	 */
	enum type {
		ENGLISH, MATH, SCIENCE
	}

	private type type;
	private int[] exams;
	private int[] homeworks;
	private int[] quizzes;
	private int value;

	/**
	 * Constructor for a subject.
	 * 
	 * @param type
	 *            Type of subject
	 * @param exams
	 *            Number of exams
	 * @param homeworks
	 *            Number of homeworks
	 * @param quizzes
	 *            Number of quizzes
	 */
	public Subject(type type, int exams, int homeworks, int quizzes) {
		this.type = type;
		this.exams = new int[exams];
		Arrays.fill(this.exams, -1);
		this.homeworks = new int[homeworks];
		Arrays.fill(this.homeworks, -1);
		this.quizzes = new int[quizzes];
		Arrays.fill(this.quizzes, -1);
		this.value = 0;

	}

	/**
	 * Default Constructor
	 */
	public Subject() {

	}

	/**
	 * Getter for type of subject
	 * 
	 * @return type
	 */
	public type getType() {
		return this.type;
	}

	/**
	 * Getter for type of subject in String format
	 * 
	 * @return type in string
	 */
	@SuppressWarnings("static-access")
	public String getTypeString() {
		if (this.type == type.ENGLISH)
			return "ENGLISH";

		if (this.type == type.MATH)
			return "MATH";

		if (this.type == type.SCIENCE)
			return "SCIENCE";
		return "";
	}

	/**
	 * Setter for type of subject
	 * 
	 * @param type
	 *            in string format
	 */
	@SuppressWarnings("static-access")
	public void setType(String str) {
		this.type = type.valueOf(str);
	}

	/**
	 * Getter for value of subject
	 * 
	 * @return value
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Setter for value of subject
	 * 
	 * @param value
	 */
	public void setValue(int val) {
		this.value = val;
	}

	/**
	 * Getter for exams
	 * 
	 * @return exam array
	 */
	public int[] getExams() {
		return this.exams;
	}

	/**
	 * Setter for exams
	 * 
	 * @param int
	 *            array
	 */
	public void setExams(int[] data) {
		this.exams = data;
	}

	/**
	 * Getter for homeworks
	 * 
	 * @return homeworks array
	 */
	public int[] getHomeworks() {
		return this.homeworks;
	}

	/**
	 * Setter for homeworks
	 * 
	 * @param int
	 *            array
	 */
	public void setHomeworks(int[] data) {
		this.homeworks = data;
	}

	/**
	 * Getter for quizzes
	 * 
	 * @return quiz array
	 */
	public int[] getQuizzes() {
		return this.quizzes;
	}

	/**
	 * Setter for quizzes
	 * 
	 * @param int
	 *            array
	 */
	public void setQuizzes(int[] data) {
		this.quizzes = data;
	}

}
