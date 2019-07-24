package com.app.samples.samplejsfapp.jsfbeans;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


// TODO: Auto-generated Javadoc
/**
 * The Class DataSourceTestClass.
 *
 * @author Abid
 * The Class DataSourceTestClass.
 */
@SuppressWarnings("serial")
@WebServlet("/TestServlet")
public class DataSourceTestClass extends HttpServlet {

	/** The data source. */
	@Resource(name = "jdbc/test")
	private DataSource dataSource;
	
	/** The logger. */
	Logger logger = Logger.getLogger(DataSourceTestClass.class.getName());

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//BasicData

		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = dataSource.getConnection();

			String sql = "select * from customer;";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			while (myRs.next()) {
				String email = myRs.getString("email");
				out.println(email);
				System.out.println(email);
			}
		} catch (Exception exc) {
			exc.printStackTrace();
			out.println(exc.getMessage());
		}
	}

}
