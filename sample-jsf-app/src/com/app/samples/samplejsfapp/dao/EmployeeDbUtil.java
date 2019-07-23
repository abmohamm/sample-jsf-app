package com.app.samples.samplejsfapp.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.app.samples.samplejsfapp.jsfbeans.Employee;
import static com.app.samples.samplejsfapp.jsfbeans.util.DBConstants.*;

// TODO: Auto-generated Javadoc
/**
 * The Class EmployeeDbUtil.
 */
public class EmployeeDbUtil {

	/** The logger. */
	Logger logger = Logger.getLogger(EmployeeDbUtil.class.getName());


	/** The instance. */
	private static EmployeeDbUtil instance;

	/** The data source. */
	private DataSource dataSource;

	/** The jndi name. */
	private String jndiName = "java:comp/env/jdbc/test";

	/**
	 * Gets the single instance of EmployeeDbUtil.
	 *
	 * @return single instance of EmployeeDbUtil
	 * @throws Exception the exception
	 */
	public static EmployeeDbUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new EmployeeDbUtil();
		}

		return instance;
	}

	/**
	 * Instantiates a new employee db util.
	 *
	 * @throws Exception the exception
	 */
	private EmployeeDbUtil() throws Exception {		
		dataSource = getDataSource();
	}

	/**
	 * Gets the data source.
	 *
	 * @return the data source
	 * @throws NamingException the naming exception
	 */
	private DataSource getDataSource() throws NamingException {
		Context context = new InitialContext();

		DataSource theDataSource = (DataSource) context.lookup(jndiName);

		return theDataSource;
	}

	/**
	 * Gets the employees.
	 *
	 * @return the employees
	 * @throws Exception the exception
	 */
	public List<Employee> getEmployees() throws Exception {
		Employee employee = null;
		List<Employee> employees = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			logger.info("Trying to establish connection");
			myConn = getConnection();
			logger.info("successfully established connection");

			String sql = "select * from employees order by last_name";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				employee = mapResultSetToEmployee(myRs);
				logger.info("Employee record for Employee Id : "+employee.getEmployeeid()+ " is "+employee.toString());
				// add it to the list of employeess
				employees.add(employee);
			}			
			return employees;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}

	/**
	 * Adds the employee.
	 *
	 * @param employee the employee
	 * @throws Exception the exception
	 */
	public void addEmployee(Employee employee) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		Date date = null;
		try {
			myConn = getConnection();

			String sql = "insert into employees (EMPLOYEE_ID,FIRST_NAME, LAST_NAME, EMAIL,PHONE_NUMBER,HIRE_DATE,JOB_ID,SALARY,COMMISSION_PCT,MANAGER_ID,DEPARTMENT_ID) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?)";

			myStmt = myConn.prepareStatement(sql);

			if(employee != null) {
				// set params
				myStmt.setInt(1, employee.getEmployeeid());
				myStmt.setString(2, employee.getFirstName());
				myStmt.setString(3, employee.getLastName());
				myStmt.setString(4, employee.getEmail());
				myStmt.setString(5, employee.getPhoneNumber());
				date = new Date(employee.getHireDate().getTime());
				myStmt.setDate(6, date);
				myStmt.setString(7, employee.getJobId());
				myStmt.setInt(8, employee.getSalary());				
				myStmt.setInt(9, employee.getCommissionPct());
				myStmt.setInt(10, employee.getManagerId());
				myStmt.setInt(11, employee.getDepartmentId());
			}			
			myStmt.execute();	
			logger.info("Employee record with "+employee.getEmployeeid()+" is inserted successfully ");
		}
		finally {
			close (myConn, myStmt,null);
		}

	}

	/**
	 * Gets the employee.
	 *
	 * @param empId the emp id
	 * @return the employee
	 * @throws Exception the exception
	 */
	public Employee getEmployee(int empId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Employee employee = null;

		try {
			myConn = getConnection();

			String sql = "select * from employees where EMPLOYEE_ID=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1,empId);

			myRs = myStmt.executeQuery();		

			// retrieve data from result set row
			if (myRs.next()) {
				employee = mapResultSetToEmployee(myRs);
				logger.info("Employee record for Employee Id : "+employee.getEmployeeid()+ " is "+employee.toString());
			}
			else {
				throw new Exception("Could not find employee with the id: " + empId);
			}

			return employee;
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}

	/**
	 * Update employee.
	 *
	 * @param employee the employee
	 * @throws Exception the exception
	 */
	public void updateEmployee(Employee employee) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;
		Date date = null;

		try {
			myConn = getConnection();

			String sql = "update employees "
					+ " set first_name=?, last_name=?, email=?,PHONE_NUMBER=?,HIRE_DATE=?,JOB_ID=?,SALARY=?,COMMISSION_PCT=?,MANAGER_ID=?,DEPARTMENT_ID?"
					+ " where EMPLOYEE_ID=?";

			myStmt = myConn.prepareStatement(sql);

			if(employee != null) {
				// set params
				myStmt.setString(1, employee.getFirstName());
				myStmt.setString(2, employee.getLastName());
				myStmt.setString(3, employee.getEmail());
				myStmt.setString(4, employee.getPhoneNumber());
				date = new Date(employee.getHireDate().getTime());
				myStmt.setDate(5, date);
				myStmt.setString(6,employee.getJobId());
				myStmt.setInt(7,employee.getSalary());
				myStmt.setInt(8, employee.getCommissionPct());
				myStmt.setInt(9, employee.getManagerId());
				myStmt.setInt(10, employee.getDepartmentId());
				myStmt.setInt(11, employee.getEmployeeid());
			}
			myStmt.execute();
			logger.info("Employee record with "+employee.getEmployeeid()+" is updated successfully ");
		}
		finally {
			close (myConn, myStmt,null);
		}		
	}

	/**
	 * Delete employee.
	 *
	 * @param employeeId the employee id
	 * @throws Exception the exception
	 */
	public void deleteEmployee(int employeeId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = getConnection();

			String sql = "delete from employees where employee_id=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, employeeId);

			myStmt.execute();
		}
		finally {
			close (myConn, myStmt,null);
		}		
	}	

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 * @throws Exception the exception
	 */
	private Connection getConnection() throws Exception {

		Connection theConn = dataSource.getConnection();

		return theConn;
	}

	/**
	 * Close.
	 *
	 * @param theConn the the conn
	 * @param theStmt the the stmt
	 * @param theRs the the rs
	 */
	private void close(Connection theConn, Statement theStmt, ResultSet theRs) {

		try {
			if (theRs != null) {
				theRs.close();
			}

			if (theStmt != null) {
				theStmt.close();
			}

			if (theConn != null) {
				theConn.close();
			}

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}	

	/**
	 * Map result set to employee.
	 *
	 * @param resultSet the result set
	 * @return the employee
	 */
	private Employee mapResultSetToEmployee(ResultSet resultSet) {
		Employee employee = new Employee();
		try {
			if(resultSet != null) {
				// retrieve data from result set row			
				employee.setEmployeeid((int)resultSet.getInt(EMPLOYEE_ID));
				employee.setFirstName((String)resultSet.getString(FIRST_NAME));
				employee.setLastName((String)resultSet.getString(LAST_NAME));
				employee.setEmail((String)resultSet.getString(EMAIL));
				employee.setPhoneNumber((String)resultSet.getString(PHONE_NUMBER));
				employee.setHireDate((Date)resultSet.getDate(HIRE_DATE));
				employee.setJobId((String)resultSet.getString(JOB_ID));
				employee.setSalary((int)resultSet.getInt(SALARY));
				employee.setCommissionPct((int)resultSet.getInt(COMMISSION_PCT));
				employee.setManagerId((int)resultSet.getInt(MANAGER_ID));
				employee.setDepartmentId((int)resultSet.getInt(DEPARTMENT_ID));
				logger.info("Employee Data : "+employee.toString());
			}
		}
		catch(SQLException exception) {
			exception.printStackTrace();
		}		
		return employee;
	}
	
}
