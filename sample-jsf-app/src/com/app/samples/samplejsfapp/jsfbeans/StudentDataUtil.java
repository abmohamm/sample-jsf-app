package com.app.samples.samplejsfapp.jsfbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

// TODO: Auto-generated Javadoc
/**
 * The Class StudentDataUtil.
 */
@ManagedBean
@ApplicationScoped
public class StudentDataUtil {

	/** The logger. */
	Logger logger = Logger.getLogger(StudentDataUtil.class.getName());
	
	/** The students. */
	private List<Student> students;
	
	/**
	 * Instantiates a new student data util.
	 */
	public StudentDataUtil() {
		loadSampleData();
	}
	
	/**
	 * Load sample data.
	 */
	public void loadSampleData() {
		students = new ArrayList<Student>();
		students.add(new Student("Mary","public","mary@gmail.com"));
		students.add(new Student("John","Doe","john@gmail.com"));
		students.add(new Student("Ajay","Rao","ajay@gmail.com"));
	}
	
	/**
	 * Gets the students.
	 *
	 * @return the students
	 */
	public List<Student> getStudents(){
		if(students != null) {
			for(Student student : students )
			logger.info("Students List : "+student.toString());
		}
		return students;
	}
	
}
