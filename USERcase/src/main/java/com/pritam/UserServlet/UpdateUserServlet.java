package com.pritam.UserServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/updateServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	    
	 public void init(ServletConfig config) {
		   //Establishing the connection with database
		   // init calls only once in the servlet lifecycle hence we establishing the connection of database
		   try {
			   ServletContext context = config.getServletContext();
			   Class.forName("com.mysql.cj.jdbc.Driver");
			    connection=DriverManager.getConnection(context.getInitParameter("dburl"),context.getInitParameter("dbuname"),context.getInitParameter("dbpwd"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		   catch(ClassNotFoundException e) {
			   e.printStackTrace();
		   }
		   
		   
	   }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		try {
			Statement statement=connection.createStatement();
			int rows=statement.executeUpdate("update user set password='"+password+"'where email='"+email+"'");
			PrintWriter out = response.getWriter();
			if(rows>0) {
				out.println("<h1>password updated Successfully...</h1>");
			}
			else {
				out.println("<h1>Error while updating password ...</h1>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void destroy() {
		//destroying the connection Statement and resultset Objects.
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
