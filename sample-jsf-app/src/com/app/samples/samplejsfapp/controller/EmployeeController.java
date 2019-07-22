package com.app.samples.samplejsfapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.app.samples.samplejsfapp.dao.EmployeeDbUtil;
import com.app.samples.samplejsfapp.jsfbeans.Employee;

// TODO: Auto-generated Javadoc
/**
 * The Class EmployeeController.
 */
@ManagedBean
@SessionScoped
public class EmployeeController {

	/** The employees. */
	private List<Employee> employees;
	
	/** The employee db util. */
	private EmployeeDbUtil employeeDbUtil;


	/** The logger. */
	Logger logger = Logger.getLogger(EmployeeController.class.getName());

	/**
	 * Instantiates a new employee controller.
	 *
	 * @throws Exception the exception
	 */
	public EmployeeController() throws Exception {
		employees = new ArrayList<>();

		employeeDbUtil = EmployeeDbUtil.getInstance();
	}

	/**
	 * Gets the employees.
	 *
	 * @return the employees
	 */
	public List<Employee> getEmployees() {
		return employees;
	}

	/**
	 * Load employees.
	 */
	public void loadEmployees() {

		logger.info("Loading employees");

		employees.clear();

		try {

			// get all employees from database
			employees = employeeDbUtil.getEmployees();

		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error loading employees", exc);

			// add error message for JSF page
			addErrorMessage(exc);
		}
	}
	
	/**
	 * Adds the employee.
	 *
	 * @param employee the employee
	 * @return the string
	 */
	public String addEmployee(Employee employee) {

		logger.info("Adding Employee : " + employee.toString());

		try {
			
			// add employee to the database
			employeeDbUtil.addEmployee(employee);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "Error adding employees", exc);
			
			// add error message for JSF page
			addErrorMessage(exc);

			return null;
		}
		
		return "list-employees?faces-redirect=true";
	}

	/**
	 * Adds the error message.
	 *
	 * @param exc the exc
	 */
	private void addErrorMessage(Exception exc) {
		FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}

