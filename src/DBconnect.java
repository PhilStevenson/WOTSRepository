import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;


public class DBconnect {
	
	
	// DATABASE CONNECTION VARIABLES
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://10.50.15.15:8889/wots";
//	static final String DB_URL = "jdbc:mysql://localhost:8889/wots";
	
	static final String USER = "root";
//	static final String PASS = "root";
	static final String PASS = "Passw0rd";
	
	private Connection conn = null;
	private Statement stmt = null;

	
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	
	// Creates a connection to the database
	private void accessDB() {
	
		try {
			Class.forName( "com.mysql.jdbc.Driver");
			//System.out.println("AccessDB: Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			//System.out.println("AccessDB: Database Connected!");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("AccessDB: Connection Unsuccessful! :(");
			e.printStackTrace();
			
		} 
		
	}
	
	
	// Close the connection to the database
	private void closeDB() {
		try {
			conn.close();
			//System.out.println("CloseDB: Database Disconnected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	
	
	
	//query the database returning a set of records
	private ResultSet queryDB(String query) {
		
		accessDB();			//Access Database
		
		ResultSet result = null;
		
		try {
			Statement stmt = conn.createStatement();
			
	        //System.out.println("queryDB: The SQL query is: " + query); // Echo For debugging
	        //System.out.println();
	 
	        result = stmt.executeQuery(query); 		// Execute the query and store the result
			
		} catch (SQLException e) {
			e.printStackTrace(); 		// Catch SQL errors and print to console
		}
		
		return result;		// return the result of the query
		
	}
	
	// to query the database without a result to return
	private void updateDB(String query) {
		accessDB();		// Connect to the database
		
		try {
			Statement stmt = conn.createStatement();	
			
	        System.out.println("updateDB: The SQL query is: " + query); // Echo For debugging
	        System.out.println();
	        
	        stmt.executeUpdate(query); 		// query the database with the string query
			
		} catch (SQLException e) {
			e.printStackTrace();		// Catch SQL errors and print to console
		} finally {
			closeDB();		//Close the database
		}
	}
	
	// Get all IDs for a specific table
	public ArrayList<String> getIds(String table) {
		
		ArrayList<String> ids = new ArrayList<String>();
		
		ResultSet result = queryDB("SELECT id FROM " + table); 		// query the database

		try {
			
			while(result.next()) {   // for each record in the query
				
				String id = result.getString("id");		// get the id from the record	
				
				ids.add(id);	// add the id to the array of IDs
			}
		} catch (SQLException e) {
			e.printStackTrace(); 	// Catch the SQL errors and print them to the console
			ids = null;		// make IDs null as there won't be any to return
		} finally {
			closeDB(); 	// close the database connection
		}
	
		
		return ids;		// return the IDs
	}
	
	
	// Get customer object from the Customer ID
	public Customer getCustomer(String custID) {
		String query = "SELECT * FROM customer WHERE id = '" + custID + "';";		// query the database for details of a specific customer
		
		Customer cus = new Customer();			// create new customer object
		
		ResultSet res = queryDB(query);			// query the database
		
		try {
			
			// take all of the customer details from the database and store them in the customer object
			while(res.next()) {	
				cus.firstName = res.getString("firstName");
				cus.surName = res.getString("surName");
				cus.email = res.getString("email");
				cus.telephone = res.getString("telephone");
				cus.addressFirstLine = res.getString("addressFirstLine");
				cus.addressSecondLine = res.getString("addressSecondLine");
				cus.addressTownCity = res.getString("addressTownCity");
				cus.addressCounty = res.getString("addressCounty");
				cus.addressCountry = res.getString("addressCountry");
				cus.addressPostcode = res.getString("addressPostcode");
				cus.availableCredit = Double.valueOf(res.getString("availableCredit"));
				cus.cardNumber = res.getString("cardNumber");
				cus.cardName = res.getString("cardName");
				cus.cardExpiry = res.getString("cardExpiry");
				cus.cardSecNum = res.getString("cardSecNum");
			}
		} catch(SQLException e) {
			e.printStackTrace();		// Catch SQL errors and print to console
		} finally {
			closeDB();			// close database
		}
		return cus;		// return the customer object
	}
	
	// get all customer/sales orders from the database
	public ArrayList<CustOrder> getOrders() {
		
		String query = "SELECT * FROM custorder ;";		// query all from custorder table
		
		ArrayList<CustOrder> orders = new ArrayList<CustOrder>();		//create and arrayList of Customer Orders
		
		ResultSet res = queryDB(query);		// Query the database
		
		try {
			while(res.next()) {   // for each record in the database
				
				CustOrder cord = new CustOrder();	// create new customer order object
				
				// add all details from the database to the object
				cord.id = res.getString("id");		
				cord.custID = res.getString("custID");
				cord.dateTime = res.getString("dateTime");
				cord.zone = res.getString("zone");
				cord.status = res.getString("status");
				
				orders.add(cord);		// add the order to the array
			}
			
		} catch (SQLException e) {
			e.printStackTrace(); 		// Catch SQL erros and print to console
		} finally {
			closeDB();		// close database
		}
		return orders;		// return all of the orders
	}
	
	// gets a specific order and returns it as an object
	public CustOrder getOrder(String id) {
		
		String query = "SELECT * FROM custorder WHERE id = '" + id + "';"; 		// query the database for a particular order
		
		ResultSet res = queryDB(query);		// Execute the query
		
		CustOrder cord = new CustOrder(); 	// create a new object of customer order
		
		try {
			while(res.next()) { 
				
				// add the order details to the customer order object
				cord.id = res.getString("id");
				cord.custID = res.getString("custID");
				cord.dateTime = res.getString("dateTime");
				cord.zone = res.getString("zone");
				cord.status = res.getString("status");
				cord.stockAllocated = res.getBoolean("stockallocated");
			}
		} catch (SQLException e) {
			e.printStackTrace();	// Catch errors and print to console
		} finally {
			closeDB();		// close database connection
		}
		return cord;	// return the customer order
	}
	
	// get the order lines of a specific order
	public ArrayList<OrderLine> getOrderLines(String orderID) {
		
		String query = "SELECT * FROM orderline WHERE orderID = '" + orderID + "';";	// the query is getting bored now
		
		ResultSet res = queryDB(query);		// execute the query
		
		ArrayList<OrderLine> ols = new ArrayList<OrderLine>();		// create a arrayList of order lines
		
		try {
			while(res.next()) {   // for each order in the database table
				
				OrderLine ol = new OrderLine(); 	// create a new order line object
				
				// add the data from the database to the object
				ol.orderID = res.getString("orderID");
				ol.productID = res.getString("productID");
				ol.quantity = res.getString("quantity");
				
				ols.add(ol);	// add the orderline object to the array
			}
		} catch (SQLException e) {
			e.printStackTrace();	// Catch errors and print to console
		} finally {
			closeDB();		// close the database connection
		}
		return ols;		// return the orderlines
	}
	
	// gets the details of a specific product
	public Product getProduct(String productID) {
		String query = "SELECT * FROM product WHERE id = '" + productID + "';";		// this is a sql query, I know what it means
		
		ResultSet res = queryDB(query);		// execute the query and store the result
		
		Product prod = new Product();	// create a new product object
		
		try {
			while(res.next()) { 
				
				// add the data from the database into the object
				prod.ID = res.getString("id");
				prod.name = res.getString("name");
				prod.description = res.getString("description");
				prod.price = res.getDouble("price");
				prod.location = res.getString("location");
				prod.stockUnits = res.getInt("stockunits");
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();	// still does the same thing as last time
		} finally {
			closeDB();		// you guessed it, Closes the database connection
		}
		return prod;	// returns the product.
	}
	
	// used to add a new customer to the database
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
		
		ArrayList<String> ids = getIds("customer");		// get all the current IDs for the customer table
		
		if (!ids.contains(id))		// only if the id doesn't exist in the database
		{
			
			// query to add all the details
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
		
			updateDB(query); 	// execute the database
			
		} else {
			System.out.println("This record already exists!");	// if the ID already exists, print to console
		}
		closeDB();		// close database
	}
	
	// This method adds a new customer order
	public void addCustOrder(
							String id,
							String custID,
							String dateTime,
							String zone,
							String status) {
		
		ArrayList<String> ids = getIds("custorder"); 	// get all current IDs for custorder		
		
		if (!ids.contains(id))		// only if the IDs does not already exist in the database
		{
			// Insert query
			String query = "INSERT INTO custorder VALUES ('" 
											+ id + "', '"
											+ custID + "', '" 
											+ dateTime + "', '" 
											+ zone + "', '"
											+ status+ "');";
			updateDB(query);	// Execute query
			
		
		} else {
			//if the record already exists then print to console
			System.out.println("This record already exists!");
		}
	}

	// add products to a specific order
	public void addOrderItem(String orderID, String prodID, String quant) {
		
		// initialise variables
		String query = "SELECT orderID, productID FROM orderline";
		boolean exists = false;
		
		ResultSet result = queryDB(query);		// query the database and store the results

		try {
			
			// compare the current orderlines with the new orderline and if same then make exists=true
			while(result.next()) {  
				String oid = result.getString("orderID");
				String pid = result.getString("productID");
				
				if (oid.equals(orderID) && pid.equals(prodID)) {
					exists = true;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();	// Catch errors and print to console
		} finally {
			closeDB();		// close the database connection
		}
	
		if(exists) {
			System.out.println("Record Already Exsists!"); 	// if there is a duplicate record, print to console
		} else {
			
			// add the orderline to the database
			query = "INSERT INTO orderline VALUES ('" + orderID + "','" + prodID + "','" + quant + "');";
			updateDB(query);
		}
		
	}
	
	
	// get the postcode of a specific customer
	public String getPostcode(String custID) {

		// Initialise variables
		String postcode = null;
		String query = "SELECT addressPostcode FROM customer WHERE id = '" + custID + "';";
		
		
		ResultSet res = queryDB(query);		// execute the query and store the results
		
		try {
			while(res.next()) {   // Move the cursor to the next row
				postcode = res.getString("addressPostcode");	// add database value to a postcode String
			}
		} catch (SQLException e) {
			e.printStackTrace();	// catch the errors and print to console
		} finally {
			closeDB();		// close the database
		}
		
		//System.out.println(postcode); // print the post code to the console
		
		return postcode;	// Return the postcode
	
		
	}
	
	// updates the customer order status for a specific order
	public void updateCustOrder(String id, String status) {
		String query = "UPDATE custorder SET status = '" + status + "' WHERE id = '" + id + "'";
		
		updateDB(query);
		
	}
	
	// updates the stock level for a specific product
	public void updateStock(String id, int newStock){
		String query = "UPDATE product SET stockunits = '" + newStock + "' WHERE id = '" + id + "'";
		
		updateDB(query);
	}
	
	// updates the stockAllocated variable for a specific customer order
	public void updateStockAllocation(String orderID, boolean allocated) {
		
		String query;
		
		if(allocated){
				query = "UPDATE custorder SET stockallocated = 1 WHERE id = '" + orderID + "'";
		}else{
				query = "UPDATE custorder SET stockallocated = 0 WHERE id = '" + orderID + "'";
		}
		updateDB(query);
	}
	
	// returns the boolean value of stock allocated variable
	public boolean isStockAllocated(String orderID) {
		
		String query = "SELECT stockallocated FROM CustOrder WHERE id = '" + orderID + "'";
		
		ResultSet res = queryDB(query);
		
		boolean allocated = false;
		
		try {
			while(res.next()) { 
				allocated = res.getBoolean("stockallocated");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return allocated;
	}
	
	
	// adds a product to the database
	public void addProduct(String prodID, String name, String description, double price) {
		
		String query = "INSERT INTO product VALUES ('" + prodID + "', '" + name + "', '" + description + "', " + String.valueOf(price) + ");";
		
		updateDB(query);
		
	}

}