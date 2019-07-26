package com.app.samples.samplejsfapp.dao;

import static com.app.samples.samplejsfapp.jsfbeans.util.DBConstants.COMMISSION_PCT;
import static com.app.samples.samplejsfapp.jsfbeans.util.DBConstants.CREAT_UPD_DATE;
import static com.app.samples.samplejsfapp.jsfbeans.util.DBConstants.DEPARTMENT_ID;
import static com.app.samples.samplejsfapp.jsfbeans.util.DBConstants.EMAIL;
import static com.app.samples.samplejsfapp.jsfbeans.util.DBConstants.EMPLOYEE_ID;
import static com.app.samples.samplejsfapp.jsfbeans.util.DBConstants.FIRST_NAME;
import static com.app.samples.samplejsfapp.jsfbeans.util.DBConstants.HIRE_DATE;
import static com.app.samples.samplejsfapp.jsfbeans.util.DBConstants.JOB_ID;
import static com.app.samples.samplejsfapp.jsfbeans.util.DBConstants.LAST_NAME;
import static com.app.samples.samplejsfapp.jsfbeans.util.DBConstants.MANAGER_ID;
import static com.app.samples.samplejsfapp.jsfbeans.util.DBConstants.PHONE_NUMBER;
import static com.app.samples.samplejsfapp.jsfbeans.util.DBConstants.SALARY;
import static com.app.samples.samplejsfapp.jsfbeans.util.DBConstants.YYYY_MM_DD_HH_MM_SS;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.joda.time.format.DateTimeFormat;
import org.springframework.dao.DataAccessException;

import com.app.samples.samplejsfapp.jsfbeans.Employee;

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
	 */
	public List<Employee> getEmployees() {
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
				// add it to the list of employees
				employees.add(employee);
			}					
		}
		catch(DataAccessException dataAccessException) {
			logger.log(Level.SEVERE, "exception while loading employees", dataAccessException.getMessage());
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		finally {
			close (myConn, myStmt, myRs);
		}
		return employees;
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
		Timestamp timestamp = null;
		Date date = null;
		try {
			myConn = getConnection();
			timestamp = getCurrentTimestamp();
			logger.info("current  time : "+timestamp);

			String sql = "insert into employees (EMPLOYEE_ID,FIRST_NAME, LAST_NAME, EMAIL,PHONE_NUMBER,HIRE_DATE,JOB_ID,SALARY,COMMISSION_PCT,MANAGER_ID,DEPARTMENT_ID,CREAT_UPD_DATE) "
					+ "values (?,?,?,?,?,?,?,?,?,?,?,?)";

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
				myStmt.setTimestamp(12, timestamp);
			}			
			myStmt.execute();	
			logger.info("Employee record with "+employee.getEmployeeid()+" is inserted successfully ");
		}
		catch(DataAccessException dataAccessException) {
			logger.log(Level.SEVERE, "exception while adding employees", dataAccessException.getMessage());
		}
		catch(Exception exception) {
			exception.printStackTrace();
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
		catch(DataAccessException dataAccessException) {
			logger.log(Level.SEVERE, "exception while loading employee id:" + empId, dataAccessException.getMessage());
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		finally {
			close (myConn, myStmt, myRs);
		}
		return employee;
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
		Timestamp timestamp = null;

		try {
			myConn = getConnection();
			timestamp = getCurrentTimestamp();
			logger.info("current time : "+timestamp);

			String sql = "update employees "
					+ " set first_name=?, last_name=?, email=?,PHONE_NUMBER=?,HIRE_DATE=?,JOB_ID=?,SALARY=?,COMMISSION_PCT=?,MANAGER_ID=?,DEPARTMENT_ID?,DATE_UPDATED=?"
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
				myStmt.setTimestamp(12, timestamp);
			}
			myStmt.execute();
			logger.info("Employee record with "+employee.getEmployeeid()+" is updated successfully ");
		}
		catch(DataAccessException dataAccessException) {
			logger.log(Level.SEVERE, "exception while updating Employee: " + employee, dataAccessException.getMessage());
		}
		catch(Exception exception) {
			exception.printStackTrace();
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
			logger.info("deleting employee with employee id : "+employeeId);

			String sql = "delete from employees where employee_id=?";

			myStmt = myConn.prepareStatement(sql);

			// set params
			myStmt.setInt(1, employeeId);

			myStmt.execute();
			logger.info("employee record with "+employeeId+" deleted successfully ");
		}
		catch(DataAccessException dataAccessException) {
			logger.log(Level.SEVERE, "exception while deleting Employee id: " + employeeId, dataAccessException.getMessage());
		}
		catch(Exception exception) {
			exception.printStackTrace();
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
		Timestamp dateUpdated = null;
		String timeStamp = null;
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
				dateUpdated = resultSet.getTimestamp(CREAT_UPD_DATE);
				if(dateUpdated != null) {
					timeStamp = DateTimeFormat.forPattern(YYYY_MM_DD_HH_MM_SS).print(dateUpdated.getTime());
					logger.info("time stamp for the record "+employee.getEmployeeid()+" is "+timeStamp);
					employee.setDateUpdated(timeStamp);
				}
				else {
					employee.setDateUpdated("-");
				}
				
				logger.info("Employee Data : "+employee.toString());
			}
		}
		catch(SQLException exception) {
			exception.printStackTrace();
		}		
		catch(DataAccessException dataAccessException) {
			logger.log(Level.SEVERE, "exception while mapping the record with Employee id: " + employee.getEmployeeid(), dataAccessException.getMessage());
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		return employee;
	}
	
	public List<Employee> searchEmployees(String searchName)  throws Exception {

		List<Employee> employees = new ArrayList<>();
		Employee employee = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			
			// get connection to database
			myConn = dataSource.getConnection();
		
	        	// create sql to search for Employees by name
				String sql = "select * from employees where lower(first_name) like ? or lower(last_name) like ?";

				// create prepared statement
				myStmt = myConn.prepareStatement(sql);

				// set params
				String theSearchNameLike = "%" + searchName.toLowerCase() + "%";
				myStmt.setString(1, theSearchNameLike);
				myStmt.setString(2, theSearchNameLike);
				 	        
			// execute statement
			myRs = myStmt.executeQuery();
			
			// retrieve data from result set row
			while (myRs.next()) {
				
				employee = mapResultSetToEmployee(myRs);
				
				// add it to the list of Employees
				employees.add(employee);			
			}
		}
		catch(DataAccessException dataAccessException) {
			logger.log(Level.SEVERE, "exception while searching for employee : " + employee.toString(), dataAccessException.getMessage());
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		finally {
			// clean up JDBC objects
			close(myConn, myStmt, myRs);
		}
		return employees;
	}
	
	public Timestamp getCurrentTimestamp() {
		Timestamp timeStamp = null;
		try {
			java.util.Date date = new java.util.Date();
			long time = date.getTime();
			timeStamp = new Timestamp(time);
		}
		catch(Exception exception) {
			exception.printStackTrace();
		}
		logger.info("Current Timestamp is : "+timeStamp);
		return timeStamp;		
	}
	
}
