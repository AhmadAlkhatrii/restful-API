package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class filmInfoDao {
		// check spelling of client table
		String url = "jdbc:mysql://localhost:3306/demo_test";
		String DB_username="root";
		String DB_password="123123";

		
		public int getSeats(int FID) {
			System.out.println("getting the available seats for movie with ID = "+FID);
			// get user ID
			String sql="SELECT seats FROM films_seats where FID=?";
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection(url,DB_username,DB_password);
			
				PreparedStatement st= con.prepareStatement(sql);
				st.setInt(1, FID);
				ResultSet rs = st.executeQuery();
 
				while(rs.next()) {
					int seats = rs.getInt("seats");
					return seats;
				}
				
				return 0;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
			
		}
		
		

}
