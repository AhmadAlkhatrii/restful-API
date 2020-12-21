package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class userInfoDao {
	// check spelling of client table
	String url = "jdbc:mysql://localhost:3306/demo_test";
	String DB_username="root";
	String DB_password="123123";
	
	public ResultSet getClientInfo(String username) {
		System.out.println("entering recommondation films for users");
		// get user ID
		String sql="SELECT username,email,name FROM users where username=?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,DB_username,DB_password);
		
			PreparedStatement st= con.prepareStatement(sql);
			st.setString(1, username);
			
			ResultSet rs = st.executeQuery();			
			return rs;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResultSet temp = null;
		return temp;
		
	}
	
	public int getUserID(String username) {
		System.out.println("entering recommondation films for users");
		// get user ID
		String sql="SELECT userID FROM users where username=?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,DB_username,DB_password);
		
			PreparedStatement st= con.prepareStatement(sql);
			st.setString(1, username);
			
			ResultSet rs = st.executeQuery();
			while(rs.next())
				return rs.getInt("userID");
			
			
			return 0;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	
}
