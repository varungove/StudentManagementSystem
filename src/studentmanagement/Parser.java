/**
 * 
 */
package studentmanagement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author varungove
 *
 *         Class that handles all writing and loading from file
 */
public class Parser {

	/**
	 * Student data is saved as a JSON Array of students that contain a JSON
	 * Array of subjects. Subjects are again a JSON array for each subject with
	 * 3 arrays in each of them for hw, exams & quizzes.
	 * 
	 * @param students
	 * @throws JSONException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void writeStudentToFile(ArrayList<Student> students) throws JSONException, IOException {
		JSONArray studentData = new JSONArray();
		ArrayList<Subject> subjects;
		int[] data;
		for (Student student : students) {
			JSONObject obj = new JSONObject();
			obj.put("StudentName", student.getStudentName());
			obj.put("ClassName", student.getClassName());
			obj.put("Value", student.getValue());
			JSONArray subArray = new JSONArray();
			subjects = student.getSubjects();
			for (Subject s : subjects) {
				JSONObject sub = new JSONObject();
				sub.put("Subject", s.getTypeString());
				sub.put("Value", s.getValue());
				JSONArray exams = new JSONArray();
				JSONArray homeworks = new JSONArray();
				JSONArray quizzes = new JSONArray();
				data = s.getExams();
				for (int i = 0; i < data.length; i++)
					exams.add(data[i]);
				data = s.getHomeworks();
				for (int i = 0; i < data.length; i++)
					homeworks.add(data[i]);
				data = s.getQuizzes();
				for (int i = 0; i < data.length; i++)
					quizzes.add(data[i]);
				sub.put("Exams", exams);
				sub.put("Homeworks", homeworks);
				sub.put("Quizzes", quizzes);
				subArray.add(sub);
			}
			obj.put("Subjects", subArray);
			studentData.add(obj);

		}

		FileWriter file = new FileWriter("studentData.txt");
		file.write(studentData.toString());
		file.close();
	}

	/**
	 * Class data is saved as a JSONArray of classes with each class containing
	 * a JSON array of students and another for subjects.
	 * 
	 * @param rooms
	 * @throws JSONException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void writeClassToFile(ArrayList<ClassRoom> rooms) throws JSONException, IOException {
		String className = "";
		ArrayList<Subject> subjects;
		ArrayList<Student> students;
		JSONArray classData = new JSONArray();
		for (ClassRoom room : rooms) {
			JSONObject obj = new JSONObject();
			JSONArray stud = new JSONArray();
			JSONArray sub = new JSONArray();

			className = room.getClassName();
			subjects = room.getSubjects();
			students = room.getStudents();

			obj.put("ClassName", className);

			for (Subject subject : subjects) {
				sub.add(subject.getTypeString());
			}
			for (Student student : students) {
				stud.add(student.getStudentName());
			}

			obj.put("Subject List", sub);
			obj.put("Student List", stud);
			classData.add(obj);
		}

		FileWriter file = new FileWriter("classData.txt");
		file.write(classData.toString());
		file.close();
		System.out.println("Data Saved!");

	}

	/**
	 * Loads data from the JSON File
	 * 
	 * @return ArrayList of Students
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static ArrayList<Student> loadStudent() throws FileNotFoundException, IOException, ParseException {
		ArrayList<Student> students = new ArrayList<Student>();
		ArrayList<Subject> subs = new ArrayList<Subject>();
		Subject subject;
		JSONParser parse = new JSONParser();
		JSONArray studentData = (JSONArray) parse.parse(new FileReader("studentData.txt"));
		JSONArray subjects;
		JSONArray exams;
		JSONArray homeworks;
		JSONArray quizzes;

		if (studentData.isEmpty()) {
			return students;
		}

		for (Object s : studentData) {
			Student newStudent = new Student();
			JSONObject studentObject = (JSONObject) s;
			subs = new ArrayList<Subject>();

			newStudent.setClassName((String) studentObject.get("ClassName"));
			newStudent.setStudentName((String) studentObject.get("StudentName"));
			newStudent.setStudentValue(((Long) studentObject.get("Value")).intValue());
			subjects = (JSONArray) studentObject.get("Subjects");

			for (Object sub : subjects) {
				JSONObject subjectObject = (JSONObject) sub;
				subject = new Subject();
				subject.setType((String) subjectObject.get("Subject"));
				subject.setValue(((Long) subjectObject.get("Value")).intValue());

				exams = (JSONArray) subjectObject.get("Exams");
				homeworks = (JSONArray) subjectObject.get("Homeworks");
				quizzes = (JSONArray) subjectObject.get("Quizzes");
				int e[] = new int[exams.size()];
				int h[] = new int[homeworks.size()];
				int q[] = new int[quizzes.size()];
				int ctr = 0;

				for (Object k : exams) {
					e[ctr] = ((Long) k).intValue();
					ctr = ctr + 1;
				}

				ctr = 0;
				for (Object k : homeworks) {
					h[ctr] = ((Long) k).intValue();
					ctr = ctr + 1;
				}

				ctr = 0;
				for (Object k : quizzes) {
					q[ctr] = ((Long) k).intValue();
					ctr = ctr + 1;
				}
				subject.setExams(e);
				subject.setHomeworks(h);
				subject.setQuizzes(q);
				subs.add(subject);
			}

			newStudent.setStudentSubjets(subs);
			students.add(newStudent);
		}

		return students;
	}

	/**
	 * Loads class data. We load students into system first as this makes
	 * loading classes easier.
	 * 
	 * @param studentList
	 *            that was just loaded from file
	 * @return ArrayList of classRooms
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static ArrayList<ClassRoom> loadClass(ArrayList<Student> studentList)
			throws FileNotFoundException, IOException, ParseException {
		JSONParser parse = new JSONParser();
		JSONArray classData = (JSONArray) parse.parse(new FileReader("classData.txt"));
		String className;
		ArrayList<ClassRoom> rooms = new ArrayList<ClassRoom>();
		ArrayList<Subject> subjects;
		ArrayList<Student> students;
		if (classData.isEmpty()) {
			return rooms;
		}

		for (Object o : classData) {
			JSONObject room = (JSONObject) o;
			ClassRoom classRoom = new ClassRoom();
			subjects = new ArrayList<Subject>();
			students = new ArrayList<Student>();
			className = (String) room.get("ClassName");
			classRoom.setClassName(className);

			for (Student s : studentList) {
				String c = s.getClassName();
				if (className.equalsIgnoreCase(c)) {
					students.add(s);
					subjects = s.getSubjects();
				}
			}
			classRoom.setStudents(students);
			classRoom.setSubjects(subjects);
			rooms.add(classRoom);

		}

		return rooms;
	}

}
