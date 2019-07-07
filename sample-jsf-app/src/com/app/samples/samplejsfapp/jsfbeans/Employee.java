package com.app.samples.samplejsfapp.jsfbeans;

import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;

// TODO: Auto-generated Javadoc
/**
 * The Class Employee.
 */
@ManagedBean
public class Employee {

	/** The logger. */
	Logger logger = Logger.getLogger(Employee.class.getName());

	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/**
	 * Instantiates a new employee.
	 */
	public Employee() {
		super();
		logger.info("created class : "+Employee.class.getName());
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Employee [logger=" + logger + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() + "]";
	}

	
	
	
}
