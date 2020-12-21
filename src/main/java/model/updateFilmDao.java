package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;





public class updateFilmDao {
	String url = "jdbc:mysql://localhost:3306/demo_test";
	String DB_username="root";
	String DB_password="123123";
	
	
	
	// not checked yet -- create table please
	private boolean decrementSeats(int FID) {
		
		String sql_insert =String.format("UPDATE films_seats set seats = seats -1 where seats > 0 and FID ='%s'",FID );
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,DB_username,DB_password);
			
			Statement stmt = con.createStatement();
			
			int result = stmt.executeUpdate(sql_insert);
			
			if(result == 0)  // if no added it is zero
				{
				System.out.println("No seats remainings");
				return  false;}
			else	
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	//not checked yet -- table need to be created
	private boolean assignTicketToUser(String username,int FID) {
		
		
		
		userInfoDao Dao = new userInfoDao();
		int userID = Dao.getUserID(username);
		
		String sql_insert =String.format("INSERT INTO watched VALUES('%s','%s')", userID,FID);
		
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
	
	public boolean getTicket(String username,int FID) {
		
		if (decrementSeats(FID))
			return assignTicketToUser(username, FID);
		else 
			return false;
		
		
			
	}
	
	
}
