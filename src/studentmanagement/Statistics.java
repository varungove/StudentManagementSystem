package studentmanagement;

import java.util.ArrayList;

/**
 * @author varungove
 * 
 *         This class contains all the functionality to calculate desired
 *         statistics for classrooms and students.
 *
 */
public class Statistics {

	/**
	 * TODO: Calculate and display class attendance statistics.
	 * 
	 * @param classRoom
	 */
	public static void getClassAttendanceStats(ClassRoom classRoom) {
		System.out.println("This is a future functionality!");
	}

	/**
	 * Calculate and display class subject statistics.
	 * 
	 * @param classRoom
	 * @return double array of stats
	 */
	public static double[] getClassSubjectStats(ClassRoom classRoom) {
		ArrayList<Student> students = classRoom.getStudents();
		ArrayList<Subject> subjects = classRoom.getSubjects();

		double english = 0.0;
		double math = 0.0;
		double science = 0.0;
		int studentCounter = 0;

		for (Student s : students) {
			subjects = s.getSubjects();
			studentCounter++;
			for (Subject sub : subjects) {
				if (sub.getTypeString().equalsIgnoreCase("English")) {
					english = english + getClassSubjectStatsHelper(sub);
				}
				if (sub.getTypeString().equalsIgnoreCase("Math")) {
					math = math + getClassSubjectStatsHelper(sub);
				}
				if (sub.getTypeString().equalsIgnoreCase("Science")) {
					science = science + getClassSubjectStatsHelper(sub);
				}
			}
		}

		math = math / studentCounter;
		science = science / studentCounter;
		english = english / studentCounter;

		double[] results = new double[3];
		results[0] = math;
		results[1] = science;
		results[2] = english;
		return results;

	}

	/**
	 * Helper function that calculates the average for a subject
	 * 
	 * @param sub
	 * @return average value of a subject
	 */
	public static double getClassSubjectStatsHelper(Subject sub) {
		double calc = 0.0;
		int ctr = 0;
		int[] arr = sub.getExams();
		for (int i = 0; i < arr.length; i++) {
			calc = calc + arr[i];
			ctr++;
		}
		arr = sub.getHomeworks();
		for (int i = 0; i < arr.length; i++) {
			calc = calc + arr[i];
			ctr++;
		}

		arr = sub.getQuizzes();
		for (int i = 0; i < arr.length; i++) {
			calc = calc + arr[i];
			ctr++;
		}
		calc = calc / ctr;
		return calc;
	}

	/**
	 * Calculate and display student subject statistics.
	 * 
	 * @param student
	 */
	public static double[] getStudentSubjectStats(Student student) {

		double english = 0.0;
		double math = 0.0;
		double science = 0.0;

		ArrayList<Subject> subjects = student.getSubjects();
		for (Subject sub : subjects) {
			if (sub.getTypeString().equalsIgnoreCase("English")) {
				english = english + getClassSubjectStatsHelper(sub);
			}
			if (sub.getTypeString().equalsIgnoreCase("Math")) {
				math = math + getClassSubjectStatsHelper(sub);
			}
			if (sub.getTypeString().equalsIgnoreCase("Science")) {
				science = science + getClassSubjectStatsHelper(sub);
			}
		}

		double[] results = new double[3];
		results[0] = math;
		results[1] = science;
		results[2] = english;
		return results;

	}

	/**
	 * Calculate and display students strongest subject and strongest testing
	 * material.
	 * 
	 * @param student
	 */
	public static void getStudentStrengthStats(Student student) {
		ArrayList<Subject> subjects = student.getSubjects();
		double calc = 0.0;
		String sub = "";
		double max = 0.0;

		double exam = 0.0;
		int examCounter = 0;
		double hw = 0.0;
		int hwCounter = 0;
		double quiz = 0.0;
		int quizCounter = 0;
		int[] arr;

		for (Subject s : subjects) {
			calc = getClassSubjectStatsHelper(s);
			if (calc > max) {
				max = calc;
				sub = s.getTypeString();
			}
			arr = s.getExams();
			for (int i = 0; i < arr.length; i++) {
				exam = exam + arr[i];
				examCounter++;
			}
			arr = s.getHomeworks();
			for (int i = 0; i < arr.length; i++) {
				hw = hw + arr[i];
				hwCounter++;
			}

			arr = s.getQuizzes();
			for (int i = 0; i < arr.length; i++) {
				quiz = quiz + arr[i];
				quizCounter++;
			}

		}

		exam = exam / examCounter;
		hw = hw / hwCounter;
		quiz = quiz / quizCounter;

		System.out.println("Student's Strongest Subject is " + sub + " with a average score of " + max);

		max = Math.max(exam, hw);
		max = Math.max(max, quiz);

		if (exam == max) {
			sub = "Exams";
		} else if (quiz == max) {
			sub = "Quizzes";
		} else {
			sub = "Homeworks";
		}

		System.out.println("This student performs best in " + sub);

	}

	/**
	 * Calculate and display students weakest subject and weakest testing
	 * material.
	 * 
	 * @param student
	 */
	public static void getStudentWeaknessStats(Student student) {

		ArrayList<Subject> subjects = student.getSubjects();
		double calc = 0.0;
		String sub = "";
		double min = 100.0;

		double exam = 0.0;
		int examCounter = 0;
		double hw = 0.0;
		int hwCounter = 0;
		double quiz = 0.0;
		int quizCounter = 0;
		int[] arr;

		for (Subject s : subjects) {
			calc = getClassSubjectStatsHelper(s);
			if (calc < min) {
				min = calc;
				sub = s.getTypeString();
			}
			arr = s.getExams();
			for (int i = 0; i < arr.length; i++) {
				exam = exam + arr[i];
				examCounter++;
			}
			arr = s.getHomeworks();
			for (int i = 0; i < arr.length; i++) {
				hw = hw + arr[i];
				hwCounter++;
			}

			arr = s.getQuizzes();
			for (int i = 0; i < arr.length; i++) {
				quiz = quiz + arr[i];
				quizCounter++;
			}

		}

		exam = exam / examCounter;
		hw = hw / hwCounter;
		quiz = quiz / quizCounter;

		System.out.println("Student's Weakest Subject is " + sub + " with a average score of " + min);

		min = Math.min(exam, hw);
		min = Math.min(min, quiz);

		if (exam == min) {
			sub = "Exams";
		} else if (quiz == min) {
			sub = "Quizzes";
		} else {
			sub = "Homeworks";
		}

		System.out.println("This student performs worst in " + sub);

	}

	/**
	 * Calculate an intrinsic value for each student based on performance.
	 * 
	 * @param student
	 * @return Value calculated
	 */
	public static int calculateStudentValue(Student student, ClassRoom room) {

		double studentValues[] = getStudentSubjectStats(student);
		double classValues[] = getClassSubjectStats(room);

		int studentAverage = 0;
		int classAverage = 0;
		int ctr = 0;

		for (int i = 0; i < 3; i++) {
			if (studentValues[i] != 0.0 && classValues[i] != 0.0) {
				studentAverage = studentAverage + (int) studentValues[i];
				classAverage = classAverage + (int) classValues[i];
				ctr++;
			}
		}

		studentAverage = studentAverage / ctr;
		classAverage = classAverage / ctr;
		return (studentAverage - classAverage);

	}

}
