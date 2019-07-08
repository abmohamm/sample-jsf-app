package com.app.samples.samplejsfapp.jsfbeans;

import java.util.ArrayList;
import java.util.List;
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

	/** The country. */
	private String country;

	/** The country options. */
	private List<String> countryOptions;
	/**
	 * Instantiates a new employee.
	 */
	public Employee() {
		super();
		countryOptions = new ArrayList<String>();
		logger.info("created class : "+Employee.class.getName());
		countryOptions.add("Brazil");
		countryOptions.add("France");
		countryOptions.add("Germany");
		countryOptions.add("India");
		countryOptions.add("Turkey");
		countryOptions.add("United Kingdom");
		countryOptions.add("United States");		
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
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}


	/**
	 * Gets the country options.
	 *
	 * @return the country options
	 */
	public List<String> getCountryOptions() {
		return countryOptions;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", country=" + country
				+ ", countryOptions=" + countryOptions + "]";
	}	

}
