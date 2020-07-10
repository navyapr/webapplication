package com.jspiders.webapplication.first;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Driver;

public class InsertPersonDetails extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String name=req.getParameter("name");
		String age=req.getParameter("age");
		String email=req.getParameter("email");
		String contact=req.getParameter("contact");
		
		Connection con = null;
		PreparedStatement pstmt = null;
			try {
				Driver driver = new Driver();
				DriverManager.registerDriver(driver);
				
				String dburl = "jdbc:mysql://localhost:3306/webapplication?user=root&password=root";
				con = DriverManager.getConnection(dburl);
				String query = " insert into person "
						+ " values(?,?,?,?) ";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, name);
				pstmt.setInt(2, Integer.parseInt(age));
				pstmt.setString(3, email);
				pstmt.setLong(4, Long.parseLong(contact));
				
				int update = pstmt.executeUpdate();
				if(update!=0)
				{
					out.println("Record inserted successfully...");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally
			{
				try 
				{
				  if(con!=null)
				  {
					 con.close();
				  }
				  if(pstmt!=null)
				  {
					  pstmt.close();
				  }
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}
}
