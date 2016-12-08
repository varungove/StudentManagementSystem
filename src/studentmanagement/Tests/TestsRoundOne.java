package studentmanagement.Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import studentmanagement.ClassRoom;
import studentmanagement.Student;

/**
 * @author varungove
 *
 *         Basic tests
 */
public class TestsRoundOne {

	/**
	 * Tests: Student.java: Get and Set Name
	 */
	@Test
	public void testStudentName() {
		Student student = new Student();
		student.setStudentName("Test");
		assertEquals(student.getStudentName(), "Test");
	}

	/**
	 * Tests: Student.java: Get and Set class Name
	 */
	@Test
	public void testStudentClassName() {
		Student student = new Student();
		student.setClassName("Test Class");
		assertEquals(student.getClassName(), "Test Class");
	}

	/**
	 * Tests: ClassRoom.java: Get and Set class name.
	 * 
	 */
	@Test
	public void testClassName() {
		ClassRoom room = new ClassRoom();
		room.setClassName("Test Class");
		assertEquals(room.getClassName(), "Test Class");
	}
	
	

}
