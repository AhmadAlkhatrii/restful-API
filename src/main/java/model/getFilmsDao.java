package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class getFilmsDao {
	
	// check spelling of client table
	String url = "jdbc:mysql://localhost:3306/demo_test";
	String DB_username="root";
	String DB_password="123123";
	public ResultSet getRecommondedFilms_user(String username) {
		
		System.out.println("entering recommondation films for users");
		
		// get user ID
		
		String sql="SELECT userID FROM users where username=?";
		String sql_getFilms="SELECT * FROM films f join films_rate r on r.FID = f.FID  where f.FID not in (select FID from watched where watched.UID=?) order by rates desc";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,DB_username,DB_password);
			 
			
			
			
			PreparedStatement st= con.prepareStatement(sql);
			st.setString(1, username);
			
			ResultSet rs = st.executeQuery();
			rs.next();
			String userID=rs.getString("userID");
			
			PreparedStatement st1= con.prepareStatement(sql_getFilms);
			st1.setString(1, userID);
			//st.setString(2, password);
			System.out.println("the sql is "+ st1);
			
			ResultSet rs1 = st1.executeQuery(); // exe the query and store the result in rs
			
			return rs1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResultSet temp = null;
		return temp;
		
		
	}
	public ResultSet getRecommondedFilms_guest() {
		
		String sql="SELECT title,films.FID,dirName,genre,year,rates FROM films join films_rate where films.FID = films_rate.FID order by rates desc";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,DB_username,DB_password);
			con.isValid(0);
			
			
			
			PreparedStatement st= con.prepareStatement(sql);
			//st.setString(1, username);
			//st.setString(2, password);
			
			ResultSet rs = st.executeQuery(); // exe the query and store the result in rs
			
			return rs;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResultSet temp = null;
		return temp;
		
		
	}
	public ResultSet getFilm(String FID) {
		
		String sql="SELECT title,films.FID,dirName,genre,year,rates FROM films join films_rate where films.FID = films_rate.FID and films.FID=? ";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,DB_username,DB_password);
			 
			PreparedStatement st= con.prepareStatement(sql);
			st.setString(1, FID);
	
			ResultSet rs = st.executeQuery(); // exe the query and store the result in rs
			
			return rs;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResultSet temp = null;
		return temp;
		
		
	}
	
	
	public ResultSet getFilms() {
		
		String sql="SELECT * FROM films";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,DB_username,DB_password);
			 
			
			
			
			PreparedStatement st= con.prepareStatement(sql);
			//st.setString(1, username);
			//st.setString(2, password);
			
			ResultSet rs = st.executeQuery(); // exe the query and store the result in rs
			
			return rs;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		ResultSet temp = null;
		return temp;
		
		
	}
	
}
