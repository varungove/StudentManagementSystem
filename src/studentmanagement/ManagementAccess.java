package studentmanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;
import org.json.simple.parser.ParseException;
import studentmanagement.Subject.type;

/**
 * @author varungove
 *
 *         UI for the student management application.
 */
public class ManagementAccess {

	static ArrayList<Student> students = new ArrayList<Student>();
	static int studentCount = students.size();
	static ArrayList<ClassRoom> classRooms = new ArrayList<ClassRoom>();
	static int classCount = classRooms.size();

	/**
	 * Main Method
	 * 
	 * @param args
	 * @throws IOException
	 * @throws JSONException
	 * @throws ParseException
	 */
	public static void main(String[] args) throws IOException, JSONException, ParseException {

		students = Parser.loadStudent();
		studentCount = students.size();
		classRooms = Parser.loadClass(students);
		classCount = classRooms.size();
		while (true) {

			mainMenu();
		}

	}

	/**
	 * Prints the main menu
	 * 
	 * @return choice User's selection
	 * @throws IOException
	 * @throws JSONException
	 */
	public static void mainMenu() throws IOException, JSONException {

		System.out.println("Welcome to Student Managemet!");
		System.out.println("1) Access Class data");
		System.out.println("2) Access Student data");
		System.out.println("3) Save");
		System.out.println("4) Exit");

		int choice = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (choice != 1 && choice != 2 && choice != 3 && choice != 4) {

			choice = Integer.parseInt(br.readLine());
			if (choice != 1 && choice != 2 && choice != 3 && choice != 4) {
				System.out.println("Error, Please Try Again!");
			}

		}

		if (choice == 1 || choice == 2) {
			subMenu(choice);
		}

		if (choice == 3) {
			saveData();
		}
	}

	/**
	 * Calls appropriate function based on user's previous input
	 * 
	 * @param choice
	 *            User's previous selections
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void subMenu(int choice) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int input = 0;
		if (choice == 1) {
			System.out.println("Welcome to Class Menu!");
			System.out.println("1) Access a class");
			System.out.println("2) Add a class");
			System.out.println("3) Delete a class");
			System.out.println("4) Go back");
			input = Integer.parseInt(br.readLine());
			if (input == 1) {
				classMenu();
			} else if (input == 2) {
				addClass();
			} else if (input == 3) {
				deleteClass();
			} else if (input == 4) {
				// Do Nothing
			} else {
				System.out.println("Invalid Input");
				subMenu(choice);
			}
		} else {

			System.out.println("Welcome to Student Menu!");
			System.out.println("1) View Students");
			System.out.println("2) Add a Student");
			System.out.println("3) Delete a Student");
			System.out.println("4) Go back");
			input = Integer.parseInt(br.readLine());
			if (input == 1) {
				studentMenu();
			} else if (input == 2) {
				addStudent();
			} else if (input == 3) {
				deleteStudent();
			} else if (input == 4) {
				// Do Nothing
			} else

			{
				System.out.println("Invalid Input");
				subMenu(choice);
			}

		}

	}

	/**
	 * Adds a new ClassRoom, it's subjects, details, and students.
	 * 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void addClass() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String subject = "";
		int exams;
		int homeworks;
		int quizzes;

		System.out.println("Enter Name of Class. (Ex: 6A)");
		String name = br.readLine();

		ClassRoom newClass = new ClassRoom();
		newClass.setClassName(name);

		System.out.println(
				"These are the available Subjects. Enter name of subject to add to class. Enter 'done' when finished");
		System.out.println(java.util.Arrays.asList(type.values()));

		while (!(subject.equals("done"))) {
			subject = br.readLine();

			if (subject.toLowerCase().equals("done")) {
				break;
			}
			System.out.println("How many exams associated with this class?");
			exams = Integer.parseInt(br.readLine());

			System.out.println("How many homeworks associated with this class?");
			homeworks = Integer.parseInt(br.readLine());

			System.out.println("How many quizzes associated with this class?");
			quizzes = Integer.parseInt(br.readLine());

			if ((subject.toLowerCase()).equals("english")) {
				Subject newSubject = new Subject(type.ENGLISH, exams, homeworks, quizzes);
				newClass.addSubject(newSubject);
				System.out.println("Added!");
			} else if ((subject.toLowerCase()).equals("math")) {
				Subject newSubject = new Subject(type.MATH, exams, homeworks, quizzes);
				newClass.addSubject(newSubject);
				System.out.println("Added!");
			} else if ((subject.toLowerCase()).equals("science")) {
				Subject newSubject = new Subject(type.SCIENCE, exams, homeworks, quizzes);
				newClass.addSubject(newSubject);
				System.out.println("Added!");
			} else {
				System.out.println("Invalid Input!");
			}

			System.out.println("Enter Subject: (or enter 'done' if finished)");
		}

		System.out.println("How many students do you want to add to this class? (Enter 0 if you wish to add later)");
		int count = Integer.parseInt(br.readLine());

		for (int i = 0; i < count; i++) {
			addStudent(name, newClass.getSubjects(), newClass);
		}

		classRooms.add(newClass);
		classCount++;
		System.out.println("Added Class!");
		System.out.println("Done!");
		subMenu(1);

	}

	/**
	 * Deletes a ClassRoom and all students in that Class
	 * 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void deleteClass() throws NumberFormatException, IOException {
		System.out.println("WARNING: Deleting a class also delets all students in that class!");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter Name of Class to Delete. (Ex: 6A)");
		String name = br.readLine();
		int flag = 0;

		for (ClassRoom room : classRooms) {
			if (room.getClassName().equals(name)) {
				flag = 1;
				classRooms.remove(room);
				deleteStudent(name);
				System.out.println("ClassRoom Removed!");
				classCount--;
				break;
			}
		}

		if (flag == 0) {
			System.out.println("Invalid Input");
			deleteClass();
		}

		subMenu(1);

	}

	/**
	 * Add's a new Student to DB and to a valid ClassRoom.
	 * 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void addStudent() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int flag = 0;

		System.out.println("Enter Name of Student. (Ex: Mark John)");
		String name = br.readLine();

		Student newStudent = new Student();
		newStudent.setStudentName(name);

		while (flag >= 0) {

			System.out.println("Enter Class Name. (Ex: 7A)");
			name = br.readLine();
			newStudent.setClassName(name);

			for (ClassRoom room : classRooms) {

				if (room.getClassName().equals(name)) {
					flag = -1;
					newStudent.setStudentSubjets(room.getSubjects());
					students.add(newStudent);
					room.addStudent(newStudent);
					System.out.println("Student Added!");
					studentCount++;
					break;
				}
			}

			if (flag >= 0) {
				flag++;
				System.out.println("Class Not Found");
				if (flag == 3) {
					System.out.println("Too many tries. Student not added!");
					flag = -1;
				}
			}

		}
		subMenu(2);

	}

	/**
	 * Overloaded function that adds a new Student to a Class
	 * 
	 * @param className
	 * @param subjects
	 * @param room
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void addStudent(String className, ArrayList<Subject> subjects, ClassRoom room)
			throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter Name of Student. (Ex: Mark John)");
		String name = br.readLine();

		Student newStudent = new Student();
		newStudent.setStudentName(name);
		newStudent.setClassName(className);

		newStudent.setStudentSubjets(subjects);
		students.add(newStudent);
		room.addStudent(newStudent);
		System.out.println("Student Added!");
		studentCount++;

	}

	/**
	 * Deletes a student from the DB and from ClassRoom's students
	 * 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void deleteStudent() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter Name of Student to Delete. (Ex: Mark John)");
		String name = br.readLine();
		int flag = 0;

		flag = deleteStudentHelper(name);

		if (flag == 1) {
			System.out.println("Student Removed!");
			studentCount--;
		}

		if (flag == 0) {
			System.out.println("Invalid Student Name");
			deleteStudent();
		}

		subMenu(2);
	}

	/**
	 * Overloaded function that deletes a student from DB and from classRoom's
	 * students. Called when a classRoom is deleted only.
	 * 
	 * @param className
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void deleteStudent(String className) throws NumberFormatException, IOException {

		Iterator<Student> iter = students.iterator();
		int flag = 0;
		while (iter.hasNext()) {
			Student student = iter.next();

			if (student.getClassName().equals(className)) {
				flag = deleteStudentHelper(student.getStudentName());
				if (flag == 0) {
					System.out.println("Error in deleting student");
				}
				studentCount--;
			}

		}

	}

	/**
	 * Helper function for both delete Students. Completely removes the student.
	 * 
	 * @param studentName
	 * @return 0 for Failure 1 for Success
	 */
	public static int deleteStudentHelper(String studentName) {
		Iterator<Student> iterStudent = students.iterator();
		int flag = 0;
		while (iterStudent.hasNext()) {
			Student student = iterStudent.next();

			if (student.getStudentName().equals(studentName)) {
				flag = 1;
				iterStudent.remove();
			}
		}

		for (ClassRoom room : classRooms) {

			iterStudent = (room.getStudents()).iterator();
			while (iterStudent.hasNext()) {
				Student student = iterStudent.next();

				if (student.getStudentName().equals(studentName)) {
					iterStudent.remove();
				}
			}
		}

		return flag;
	}

	/**
	 * Displays the Menu for the Class Option
	 * 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void classMenu() throws NumberFormatException, IOException {

		System.out.println("Welcome to Class Menu!");
		System.out.println("0) Go back");
		System.out.println("Choose a class:");

		int counter = 1;
		for (ClassRoom room : classRooms) {
			System.out.println(counter + ")" + room.getClassName());
			counter++;
		}

		int choice = -1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (choice == -1 || choice > classCount) {
			choice = Integer.parseInt(br.readLine());

			if (choice == -1 || choice > classCount) {
				System.out.println("Error, Please Try Again!");
			}

		}

		if (choice == 0) {
			subMenu(1);
		} else {
			classSelection(choice);
		}
	}

	/**
	 * Displays options for the user's selected class
	 * 
	 * @param choice
	 *            Index value of user's selected class
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void classSelection(int choice) throws NumberFormatException, IOException {

		ClassRoom room = classRooms.get(choice - 1);
		room.displayInfo();

		System.out.println("1) View Class Attendance Statistics");
		System.out.println("2) View Class Subject Statistics");
		System.out.println("3) Go back");

		choice = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (choice != 1 && choice != 2 && choice != 3) {
			choice = Integer.parseInt(br.readLine());

			if (choice != 1 && choice != 2 && choice != 3) {
				System.out.println("Error, Please Try Again!");
			}

		}

		if (choice == 1) {
			Statistics.getClassAttendanceStats(room);
		} else if (choice == 2) {
			double results[] = Statistics.getClassSubjectStats(room);
			System.out.println("Statistics for this Class: ");
			if (results[0] != 0.0) {
				System.out.println("Average for Math: " + results[0]);
			}
			if (results[2] != 0.0) {
				System.out.println("Average for English: " + results[2]);
			}
			if (results[1] != 0.0) {
				System.out.println("Average for Science: " + results[1]);
			}

		} else {
			classMenu();
		}

	}

	/**
	 * Displays the menu for the Student option
	 * 
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void studentMenu() throws NumberFormatException, IOException {

		System.out.println("Welcome to Student Menu!");
		System.out.println("0) Go back");
		System.out.println("Choose a student:");

		int counter = 1;
		for (Student student : students) {
			System.out.println(counter + ")" + student.getStudentName());
			counter++;
		}

		int choice = -1;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (choice == -1 || choice > studentCount) {
			choice = Integer.parseInt(br.readLine());

			if (choice == -1 || choice > studentCount) {
				System.out.println("Error, Please Try Again!");
			}

		}

		if (choice == 0) {
			subMenu(2);
		} else {
			studentSelection(choice);
		}

	}

	/**
	 * Displays options for the users selected student
	 * 
	 * @param choice
	 *            Index of user selected student
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void studentSelection(int choice) throws NumberFormatException, IOException {

		Student student = students.get(choice - 1);
		int sel = choice - 1;
		student.displayInfo();

		System.out.println("1) View Subject Statistics");
		System.out.println("2) View Student Value");
		System.out.println("3) View Student Strengths");
		System.out.println("4) View Student Weaknesses");
		System.out.println("5) Edit Grades");
		System.out.println("6) Go back");

		choice = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6) {
			choice = Integer.parseInt(br.readLine());

			if (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice != 5 && choice != 6) {
				System.out.println("Error, Please Try Again!");
			}

		}

		if (choice == 1) {

			double results[] = Statistics.getStudentSubjectStats(student);
			System.out.println("Statistics for this Student: ");
			if (results[0] != 0.0) {
				System.out.println("Average for Math: " + results[0]);
			}
			if (results[2] != 0.0) {
				System.out.println("Average for English: " + results[2]);
			}
			if (results[1] != 0.0) {
				System.out.println("Average for Science: " + results[1]);
			}

		} else if (choice == 2) {
			ClassRoom studentRoom = null;
			for (ClassRoom room : classRooms) {
				if (room.getClassName().equalsIgnoreCase(student.getClassName())) {
					studentRoom = room;
				}
			}
			if (studentRoom != null) {
				int value = student.updateAndGetValue(studentRoom);
				System.out.println("This student's value is: " + value);
			} else {
				System.out.println("Internal Error, Contact Support");
			}
		} else if (choice == 3) {
			Statistics.getStudentStrengthStats(student);
		} else if (choice == 4) {
			Statistics.getStudentWeaknessStats(student);
		} else if (choice == 5) {
			students.remove(sel);
			students.add(editGrades(student));
		} else {
		}
		studentMenu();
	}

	/**
	 * Functionality to edit exam, hws, quiz grades of a certain student.
	 * 
	 * @param student
	 * @return student with updated values
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static Student editGrades(Student student) throws NumberFormatException, IOException {
		ArrayList<Subject> subjects = student.getSubjects();
		System.out.println("Choose a Subject: ");
		int ctr = 1;
		for (Subject s : subjects) {
			System.out.println(ctr + ")" + s.getTypeString());
			ctr++;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int choice = Integer.parseInt(br.readLine());

		if (choice < 1 || choice > (subjects.size())) {
			System.out.println("Error");
			return student;
		}

		Subject subject = subjects.get(choice - 1);
		subjects.remove(choice - 1);

		System.out.println("Which of the following would you like to edit?");
		System.out.println("1) Exams");
		System.out.println("2) Homeoworks");
		System.out.println("3) Quizzes");

		choice = Integer.parseInt(br.readLine());

		if (choice == 1) {
			System.out.println("Exam Grades: ");
			int arr[] = subject.getExams();
			for (int i = 0; i < arr.length; i++) {
				System.out.println("Exam " + (i + 1));
				System.out.println("Old Grade: " + arr[i]);
				System.out.println("Enter New Grade: ");
				arr[i] = Integer.parseInt(br.readLine());
				subject.setExams(arr);
			}
		} else if (choice == 2) {
			System.out.println("Homework Grades: ");
			int arr[] = subject.getHomeworks();
			for (int i = 0; i < arr.length; i++) {
				System.out.println("Homeowrk " + (i + 1));
				System.out.println("Old Grade: " + arr[i]);
				System.out.println("Enter New Grade: ");
				arr[i] = Integer.parseInt(br.readLine());
				subject.setHomeworks(arr);
			}
		} else {
			System.out.println("Quiz Grades: ");
			int arr[] = subject.getQuizzes();
			for (int i = 0; i < arr.length; i++) {
				System.out.println("Quiz " + (i + 1));
				System.out.println("Old Grade: " + arr[i]);
				System.out.println("Enter New Grade: ");
				arr[i] = Integer.parseInt(br.readLine());
				subject.setQuizzes(arr);
			}
		}

		subjects.add(subject);
		student.setStudentSubjets(subjects);
		System.out.println("Done");
		return student;

	}

	/**
	 * Saves data to File
	 * 
	 * @throws JSONException
	 * @throws IOException
	 */
	public static void saveData() throws JSONException, IOException {
		Parser.writeStudentToFile(students);
		Parser.writeClassToFile(classRooms);
	}

}
