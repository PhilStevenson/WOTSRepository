import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;


public class DBconnect {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://10.50.15.23:8889/wots";
	
	static final String USER = "root";
//	static final String PASS = "root";
	static final String PASS = "Passw0rd";
	
	private Connection conn = null;
	private Statement stmt = null;

	
	/////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////
	
	
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
	
	
	
	
	private ResultSet queryDB(String query) {
		
		accessDB();
		
		ResultSet result = null;
		
		try {
			Statement stmt = conn.createStatement();
			
	        //System.out.println("queryDB: The SQL query is: " + query); // Echo For debugging
	        //System.out.println();
	 
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
	
	public Customer getCustomer(String custID) {
		String query = "SELECT * FROM customer WHERE id = '" + custID + "';";
		Customer cus = new Customer();
		ResultSet res = queryDB(query);
		
		try {
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
			e.printStackTrace();
		}
		
		return cus;
	}
	
	public ArrayList<CustOrder> getOrders() {
		
		
		String query = "SELECT * FROM custorder ;";
		ArrayList<CustOrder> orders = new ArrayList<CustOrder>();
		ResultSet res = queryDB(query);
		
		try {
			while(res.next()) {   // Move the cursor to the next row
				CustOrder cord = new CustOrder();
				
				
				cord.id = res.getString("id");
				cord.custID = res.getString("custID");
				cord.dateTime = res.getString("dateTime");
				cord.zone = res.getString("zone");
				cord.status = res.getString("status");
				
				orders.add(cord);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return orders;
	}
	
	public CustOrder getOrder(String id) {
		String query = "SELECT * FROM custorder WHERE id = '" + id + "';";
		ResultSet res = queryDB(query);
		CustOrder cord = new CustOrder();
		
		try {
			while(res.next()) {   // Move the cursor to the next row
				
				cord.id = res.getString("id");
				cord.custID = res.getString("custID");
				cord.dateTime = res.getString("dateTime");
				cord.zone = res.getString("zone");
				cord.status = res.getString("status");
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cord;
	}
	
	public ArrayList<OrderLine> getOrderLines(String orderID) {
		String query = "SELECT * FROM orderline WHERE orderID = '" + orderID + "';";
		ResultSet res = queryDB(query);
		ArrayList<OrderLine> ols = new ArrayList<OrderLine>();
		
		try {
			while(res.next()) {   // Move the cursor to the next row
				OrderLine ol = new OrderLine();
				ol.orderID = res.getString("orderID");
				ol.productID = res.getString("productID");
				ol.quantity = res.getString("quantity");
				ols.add(ol);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ols;
	}
	
	public Product getProduct(String productID) {
		String query = "SELECT * FROM product WHERE id = '" + productID + "';";
		ResultSet res = queryDB(query);
		Product prod = new Product();
		
		try {
			while(res.next()) {   // Move the cursor to the next row
				prod.ID = res.getString("id");
				prod.name = res.getString("name");
				prod.description = res.getString("description");
				prod.price = res.getDouble("price");
				prod.location = res.getString("location");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prod;
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
		
		ArrayList<String> ids = getIds("customer");
		
		if (!ids.contains(id))
		{
		
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
		
			updateDB(query);
			
		} else {
			System.out.println("This record already exists!");
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
	
	public void addCustOrder(
							String id,
							String custID,
							String dateTime,
							String zone,
							String status) {
		
		//orderline = new HashMap<String,String>();
		
		ArrayList<String> ids = getIds("custorder");		
		
		if (!ids.contains(id))
		{
			
			String query = "INSERT INTO custorder VALUES ('" 
											+ id + "', '"
											+ custID + "', '" 
											+ dateTime + "', '" 
											+ zone + "', '"
											+ status+ "');";
			updateDB(query);
			
			
			
			/**
			 * create 2 dimensional array to store products and quantity for an order 
			 * then insert into orderline DB
			 *  
			 */
		
		} else {
			
			System.out.println("This record already exists!");
		}
		
		
		
	}

	public void addOrderItem(String orderID, String prodID, String quant) {
		String query;
		boolean ex = false;
		ResultSet result = queryDB("SELECT orderID, productID FROM orderline");

		try {
			
			while(result.next()) {   // Move the cursor to the next row
				String oid = result.getString("orderID");
				String pid = result.getString("productID");
				
				if (oid.equals(orderID) && pid.equals(prodID)) {
					ex = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeDB();
		}
	
		if(ex) {
			System.out.println("Record Already Exsists!");
		} else {
			query = "INSERT INTO orderline VALUES ('" + orderID + "','" + prodID + "','" + quant + "');";
			updateDB(query);
		}
		
	}
	
	public String getPostcode(String custID) {

		String postcode = null;
		String query = "SELECT addressPostcode FROM customer WHERE id = '" + custID + "';";
		
		ResultSet res = queryDB(query);
		
		try {
			while(res.next()) {   // Move the cursor to the next row
				postcode = res.getString("addressPostcode");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(postcode);
		
		return postcode;
		
	}

	
	public void addProduct(String prodID, String name, String description, double price) {
		
		String query = "INSERT INTO product VALUES ('" + prodID + "', '" + name + "', '" + description + "', " + String.valueOf(price) + ");";
		
		updateDB(query);
		
	}

}