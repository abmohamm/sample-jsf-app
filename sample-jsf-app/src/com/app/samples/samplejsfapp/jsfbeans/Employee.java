package com.app.samples.samplejsfapp.jsfbeans;

import java.util.ArrayList;
import java.util.Arrays;
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

	/** The full name. */
	private String fullName;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;	
	
	/** The email. */
	private String email;

	/** The country. */
	private String country;
	
	/** The zip code. */
	private String zipCode;
	
	/** The free passes. */
	private int freePasses;

	/** The country options. */
	private List<String> countryOptions;

	/** The favorite language. */
	private String favoriteLanguage;

	/** The favorite games. */
	private String[] favoriteGames;
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
		if(firstName != null) {
			logger.info("first name : "+firstName);
		}
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
		if(lastName != null) {
			logger.info("last name : "+lastName);
		}
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
		if(country != null) {
			logger.info("country : "+country);
		}
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
		if(countryOptions != null) {
			logger.info("list of countries : "+countryOptions);
		}
		return countryOptions;
	}	


	/**
	 * Gets the favorite language.
	 *
	 * @return the favorite language
	 */
	public String getFavoriteLanguage() {
		if(favoriteLanguage != null) {
			logger.info("favorite language : "+favoriteLanguage);
		}
		return favoriteLanguage;
	}

	/**
	 * Sets the favorite language.
	 *
	 * @param favoriteLanguage the new favorite language
	 */
	public void setFavoriteLanguage(String favoriteLanguage) {
		this.favoriteLanguage = favoriteLanguage;
	}

	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName() {
		logger.info("full name : "+firstName+" "+lastName);
		return firstName+" "+lastName;
	}

	/**
	 * Sets the full name.
	 *
	 * @param fullName the new full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	/**
	 * Gets the favorite games.
	 *
	 * @return the favorite games
	 */
	public String[] getFavoriteGames() {
		if(favoriteGames != null) {
			logger.info("Favorite Games : ");
			for(String str : favoriteGames) {
				logger.info(str+",");
			}
		}
		return favoriteGames;
	}

	/**
	 * Sets the favorite games.
	 *
	 * @param favoriteGames the new favorite games
	 */
	public void setFavoriteGames(String[] favoriteGames) {
		this.favoriteGames = favoriteGames;
	}
	
	

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	

	/**
	 * Gets the zip code.
	 *
	 * @return the zip code
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * Sets the zip code.
	 *
	 * @param zipCode the new zip code
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * Gets the free passes.
	 *
	 * @return the free passes
	 */
	public int getFreePasses() {
		return freePasses;
	}

	/**
	 * Sets the free passes.
	 *
	 * @param freePasses the new free passes
	 */
	public void setFreePasses(int freePasses) {
		this.freePasses = freePasses;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Employee [logger=" + logger + ", fullName=" + fullName + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", country=" + country + ", zipCode=" + zipCode + ", freePasses="
				+ freePasses + ", countryOptions=" + countryOptions + ", favoriteLanguage=" + favoriteLanguage
				+ ", favoriteGames=" + Arrays.toString(favoriteGames) + "]";
	}		

}
