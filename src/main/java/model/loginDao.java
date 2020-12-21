package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class loginDao {
	
	
	 
	// check spelling of client table
	String url = "jdbc:mysql://localhost:3306/demo_test";
	String DB_username="root";
	String DB_password="123123";
	
	public boolean check_client(String username,String password) {
		
		String sql="SELECT * FROM users where username=? and password=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,DB_username,DB_password);
			
			con.isValid(0);
			
			System.out.print("entering database");
			
			PreparedStatement st= con.prepareStatement(sql);
			st.setString(1, username);
			st.setString(2, password);
			
			System.out.print(sql);
			ResultSet rs = st.executeQuery(); // exe the query and store the result in rs
			
			if(rs.next())
				return true;
			else 
				return false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean isItClient(String username,String password) {
		
		String sql="SELECT * FROM users where username=? and password=?";
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,DB_username,DB_password);
			con.isValid(0);
			
			PreparedStatement st= con.prepareStatement(sql);
			st.setString(1, username);
			st.setString(2, password);
			
			ResultSet rs = st.executeQuery(); // EXE the query and store the result in rs
			
			rs.next();
			String user_type = rs.getString("type");
			
			if(user_type.equals("C")) // if C Client OR E Employee
				return true;
			else
				return false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;

	}
}
