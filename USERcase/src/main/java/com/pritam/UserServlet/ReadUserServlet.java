package com.pritam.UserServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
@WebServlet("/readServlet")
public class ReadUserServlet extends HttpServlet {
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()");
		try {
			Statement statement=connection.createStatement();
			PrintWriter out = response.getWriter();
			ResultSet rs=statement.executeQuery("select * from user");
			out.println("<table>");
			out.println("<tr>");
			out.println("<th>");
			out.println("First Name");
			out.println("</th>");
			out.println("<th>");
			out.println("Last Name");
			out.println("</th>");
			out.println("<th>");
			out.println("Email");
			out.println("</th>");
			out.println("</tr>");
			while(rs.next()) {
				out.println("<tr>");
				out.println("<td>");
				out.println(rs.getString(1));
				out.println("</td>");
				out.println("<td>");
				out.println(rs.getString(2));
				out.println("</td>");
				out.println("<td>");
				out.println(rs.getString(3));
				out.println("</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			}
		 catch (SQLException e) {
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
