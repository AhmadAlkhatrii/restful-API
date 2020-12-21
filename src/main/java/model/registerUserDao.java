package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import objects.user;




public class registerUserDao {
	
	
	
	String url = "jdbc:mysql://localhost:3306/demo_test";
	String DB_username="root";
	String DB_password="123123";
	
	public boolean isUserExist(user u) {
		
		String sql="SELECT * FROM users where username=? or email=?"; 
		// check spelling of client table
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,DB_username,DB_password);
			//con.isValid(0);
			//check if the connection is ok
			
			PreparedStatement st= con.prepareStatement(sql);
			st.setString(1, u.getUsername());
			st.setString(2, u.getEmail());
			
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
	
	public boolean isUsernameExist(user u) {
		
		String sql="SELECT * FROM users where username=? "; 
		// check spelling of client table
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,DB_username,DB_password);
			//con.isValid(0);
			//check if the connection is ok
			
			PreparedStatement st= con.prepareStatement(sql);
			st.setString(1, u.getUsername());
			
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
	public boolean isEmailExist(user u) {
		
		String sql="SELECT * FROM users where email=? "; 
		// check spelling of client table
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,DB_username,DB_password);
			//con.isValid(0);
			//check if the connection is ok
			
			PreparedStatement st= con.prepareStatement(sql);
			st.setString(1, u.getEmail());
			
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
	
	private boolean addUser(user u) {
		
		String sql =String.format("INSERT INTO users VALUES(default,'%s','%s','%s','%s','%s')", u.getUsername(), u.getPassword(), u.getEmail(), u.getName(), "C");
		// prepare SQL query
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,DB_username,DB_password);
			
			Statement stmt = con.createStatement();
			
			int result = stmt.executeUpdate(sql);
			
			if(result == 0)  // if no added it is zero
				return  false;
			else	
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
			
	}
	
	public boolean registerUser(user u) {
		
		if(this.isUserExist(u)) 
			return false;		
		else
			{
			return this.addUser(u);
			// if it success in insert user data --> true 
			}
		

	}

}
