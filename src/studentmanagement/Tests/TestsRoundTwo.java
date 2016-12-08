package studentmanagement.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import studentmanagement.ClassRoom;
import studentmanagement.Student;

/**
 * @author varungove
 *
 *         Round two tests
 */
public class TestsRoundTwo {

	
	
	/**
	 * Tests: addStudent function
	 * 
	 */
	@Test
	public void testAddStudent() {
		ClassRoom room = new ClassRoom();
		room.setClassName("Test Class");
		Student student = new Student();
		room.addStudent(student);
		assertEquals((room.getStudents()).size(), 1);
	}
	
	/**
	 * Tests: deleteStudent function
	 * 
	 */
	@Test
	public void testDeleteStudent() {
		ClassRoom room = new ClassRoom();
		Student student = new Student();
		room.addStudent(student);
		room.deleteStudent(student);
		assertEquals((room.getStudents()).size(), 0);
	}

}
