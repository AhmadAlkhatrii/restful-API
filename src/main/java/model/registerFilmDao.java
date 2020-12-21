package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import objects.film;




public class registerFilmDao {
	String url = "jdbc:mysql://localhost:3306/demo_test";
	String DB_username="root";
	String DB_password="123123";
	
	public int getCrewID(film f) { // now it is only for Dir
		
		String sql="SELECT * FROM crews where name=? "; 
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,DB_username,DB_password);
 
			
			PreparedStatement st= con.prepareStatement(sql);
			st.setString(1, f.getDirName());
			
			ResultSet rs = st.executeQuery();  
			
			if(rs.next())				
				return rs.getInt("CID");
			else 
				return 0;
				
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return 0;			
	}
	public boolean isFilmExist(film f) { // now it is only for Dir (delete me once u add this feature)
		
		String sql="SELECT * FROM films where title=? and dirName=? and genre=? and year=? "; 
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,DB_username,DB_password);
 
			
			PreparedStatement st= con.prepareStatement(sql);
			st.setString(1, f.getTitle());
			st.setString(2, f.getDirName());
			st.setString(3, f.getGenre());
			st.setString(4, f.getYear());
			
			ResultSet rs = st.executeQuery();  
			
			if(rs.next())
				return true;
			else 
				return false;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
		
	}
	
	private int addFilm(film f) {
		
		String sql_insert =String.format("INSERT INTO films VALUES(default,'%s','%s','%s','%s','%s')", f.getTitle(),f.getDirName(),this.getCrewID(f),f.getGenre(),f.getYear() );
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,DB_username,DB_password);
			
			PreparedStatement stmt = con.prepareStatement(sql_insert,Statement.RETURN_GENERATED_KEYS);
			
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
			  int newID = rs.getInt(1);
			  System.out.println("the FID is "+newID);
			  return newID;
			}
			
			return 0;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
			
	}
	
	private boolean addFilmSeats(int FID) {
		
		String sql_insert = String.format("INSERT INTO films_seats VALUES( '%s' , default )",FID );
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,DB_username,DB_password);
			
			Statement stmt = con.createStatement();
			
			int result = stmt.executeUpdate(sql_insert);
			
			if(result == 0)  // if no added it is zero
				return  false;
			else	
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
			
	}
	
	
	public boolean registerFilm(film f) {
		if(this.isFilmExist(f)) 
			return false;		
		else {
			
			return addFilmSeats(addFilm(f)); }
			// if it success in insert user data --> true 
			
		

	}

}
