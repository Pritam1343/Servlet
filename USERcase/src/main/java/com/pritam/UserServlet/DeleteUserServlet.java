package com.pritam.UserServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/deleteServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	    
   public void init() {
	   //Establishing the connection with database
	   // init calls only once in the servlet lifecycle hence we establishing the connection of database
	   try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		    connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Godavari@1343");
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
		try {
			Statement statement=connection.createStatement();
			int rows=statement.executeUpdate("delete from user where email='"+email+"'");
			PrintWriter out = response.getWriter();
			if(rows>0) {
				out.println("<h1>User deleted Successfully...</h1>");
			}
			else {
				out.println("<h1>User not found in the database !! </h1>");
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
