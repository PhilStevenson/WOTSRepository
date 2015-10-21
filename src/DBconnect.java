import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DBconnect {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:8889/wots";
	
	static final String USER = "root";
	static final String PASS = "Passw0rd";
	
	private Connection conn = null;
	private Statement stmt = null;

	
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	
	
	private void accessDB() {
	
		try {
			Class.forName( "com.mysql.jdbc.Driver");
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Database Connected");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Connection Unsuccessful");
			e.printStackTrace();
			
		} 
		
	}
	
	private void closeDB() {
		try {
			conn.close();
			System.out.println("Database Disconnected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	
	
	
	
	private ResultSet queryDB(String query) {
		
		accessDB();
		
		ResultSet result = null;
		
		try {
			Statement stmt = conn.createStatement();
			
	        System.out.println("queryDB: The SQL query is: " + query); // Echo For debugging
	        System.out.println();
	 
	        result = stmt.executeQuery(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	private void updateDB(String query) {
		accessDB();
		try {
			Statement stmt = conn.createStatement();
			
	        System.out.println("updateDB: The SQL query is: " + query); // Echo For debugging
	        System.out.println();
	 
	        //result = stmt.executeQuery(query);
	        stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeDB();
	}
	
	
	public ArrayList<String> getIds(String table) {
	
		
		ArrayList<String> ids = new ArrayList<String>();
		
		ResultSet result = queryDB("SELECT id FROM " + table);
		

		

		try {
			
			while(result.next()) {   // Move the cursor to the next row
				String id = result.getString("id");
				ids.add(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ids = null;
		}
	
		
		
		
		closeDB();
		return ids;
	}
	
	public void addCustomer(
							String id,
							String firstName,
							String surName,
							String email,
							String telephone,
							String addressFirstLine,
							String addressSecondLine,
							String addressTownCity,
							String addressCounty,
							String addressCountry,
							String addressPostcode,
							double availableCredit,
							String cardNumber,
							String cardName,
							String cardExpiry,
							String cardSecNum) {
		
		String query = "INSERT INTO customer VALUES ('" 
													+ id + "', '"
													+ firstName + "', '" 
													+ surName + "', '" 
													+ email + "', '" 
													+ telephone + "', '" 
													+ addressFirstLine + "', '" 
													+ addressSecondLine + "', '" 
													+ addressTownCity + "', '"
													+ addressCounty + "', '"
													+ addressCountry + "', '"
													+ addressPostcode +"', '"
													+ String.valueOf(availableCredit) + "', '"
													+ cardNumber + "', '"
													+ cardName + "', '"
													+ cardExpiry + "', '"
													+ cardSecNum + "');";
		
		ArrayList<String> ids = getIds("customer");
		
		if (!ids.contains(id))
		{
			updateDB(query);
		}
		
		//System.out.println(result);
		
//		System.out.println("The records selected are:");
//		int rowCount = 0;
//		while(rset.next()) {   // Move the cursor to the next row
//			String title = rset.getString("title");
//			double price = rset.getDouble("price");
//			int    qty   = rset.getInt("qty");
//			System.out.println(title + ", " + price + ", " + qty);
//			++rowCount;
//		}
//		System.out.println("Total number of records = " + rowCount);
	}

	

}