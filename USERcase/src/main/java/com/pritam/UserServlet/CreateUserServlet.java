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
@WebServlet("/addServlet")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;
	private String url="jdbc:mysql://localhost:3306/mydb";
	private String uname="root";
	private String pwd="Godavari@1343";
    
    
   public void init() {
	   System.out.println("init method called..");
	   //Establishing the connection with database
	   // init calls only once in the servlet lifecycle hence we establishing the connection of database
	   try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		   //System.out.println("init method called..");
		    connection=DriverManager.getConnection(url,uname,pwd);
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
		String firstname=request.getParameter("firstName");
		String lastname=request.getParameter("lastName");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		try {
			Statement statement=connection.createStatement();
			int rows=statement.executeUpdate("insert into user values('"+firstname+"','"+lastname+"','"+email+"','"+password+"')");
			PrintWriter out = response.getWriter();
			if(rows>0) {
				out.println("<h1>User Created Successfully...</h1>");
			}
			else {
				out.println("<h1>Error while creating User ...</h1>");
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
