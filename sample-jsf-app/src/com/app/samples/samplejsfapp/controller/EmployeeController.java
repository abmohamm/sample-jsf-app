package com.app.samples.samplejsfapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
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

	private String searchName;

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
			//check if searchName is null or not. if null return all the employees else return the required employee details
			
			if(searchName != null && searchName.trim().length()>0) {
				employees = employeeDbUtil.searchEmployees(searchName);				
			}
			else {
				// get all employees from database
				employees = employeeDbUtil.getEmployees();
			}
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "exception while loading employees", exc.getMessage());

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
			logger.log(Level.SEVERE, "exception while adding employees", exc.getMessage());

			// add error message for JSF page
			addErrorMessage(exc);

			return null;
		}

		return "list-employees?faces-redirect=true";
	}



	/**
	 * Load employee.
	 *
	 * @param employeeId the employee id
	 * @return the string
	 */
	public String loadEmployee(int employeeId) {

		logger.info("loading employee : " + employeeId);

		try {
			// get employee from database
			Employee employee = employeeDbUtil.getEmployee(employeeId);

			// put in the request attribute ... so we can use it on the form page
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();		

			Map<String, Object> requestMap = externalContext.getRequestMap();
			requestMap.put("employee", employee);	

		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "exception while loading employee id:" + employeeId, exc.getMessage());

			// add error message for JSF page
			addErrorMessage(exc);

			return null;
		}

		return "update-employee-form.xhtml";
	}
	
	/**
	 * Update employee.
	 *
	 * @param employee the employee
	 * @return the string
	 */
	public String updateEmployee(Employee employee) {

		logger.info("updating Employee: " + employee);
		
		try {
			
			// update Employee in the database
			employeeDbUtil.updateEmployee(employee);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "exception while updating Employee: " + employee, exc.getMessage());
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
		
		return "list-employees?faces-redirect=true";		
	}
	
	/**
	 * Delete employee.
	 *
	 * @param employeeId the employee id
	 * @return the string
	 */
	public String deleteEmployee(int employeeId) {

		logger.info("Deleting Employee id: " + employeeId);
		
		try {

			// delete the Employee from the database
			employeeDbUtil.deleteEmployee(employeeId);
			
		} catch (Exception exc) {
			// send this to server logs
			logger.log(Level.SEVERE, "exception while deleting Employee id: " + employeeId, exc.getMessage());
			
			// add error message for JSF page
			addErrorMessage(exc);
			
			return null;
		}
		
		return "list-employees";	
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

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	
	

}

