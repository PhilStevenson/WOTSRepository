import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


public class CustOrder extends Order {

	protected boolean stockAllocated = false;  // local stock allocated varaiable
	
	// generate new id for the customer order
	private String genID() {
		String newId = "COR";	// Set the ID prefix
		
		ArrayList<String> ids = con.getIds("custorder");	// get current IDs from custorder Table
		ArrayList<Integer> idNum = new ArrayList();			// get a new int arrayList
		
		// if there are IDs in the table
		if(!ids.isEmpty()) {
			
			// for each id in ids
			for (String id : ids) {
				
				idNum.add(Integer.valueOf(id.substring(3)));	// gets the numerical suffix of the ID
			}
			
			String nxtNum = String.valueOf(Collections.max(idNum)+1);		// get the next number from taking the list of numbers and taking the max and adding 1
				
			// works out number of zeros to prepend to the nxtNum
			int zeros = 5 - nxtNum.length();
			String zero = "";
			for( int i=0; i<zeros; i++) {
				zero = zero + '0';
			}
			
			newId = newId + zero + nxtNum; 	// format the ID to be in the format COR00001
			
			System.out.println("CustOrder/genID: New ID Generated: " + newId);  	// print new id to console
			System.out.println();
			
		} else {
			
			// else if there are no IDs already then create the fist one
			newId = newId + "00001"; 
			
			System.out.println("CustOrder/genID: First ID Generated: " + newId);	// print the new id to console
			System.out.println();
		}

		return newId; 	// return the new ID
	}

	// create a new customer order using the customer id
	public void newCustOrder(String custID) {
		
		Date d = new Date();	// get the date time
		
		dateTime = String.valueOf(d); 	// store the date time as a String
		
		String postcode = con.getPostcode(custID);		// Get the customers postcode
		
		zone = postcode.substring(0,4);		// create a zone from the first 4 characters of the postcode
		
		// Print troubleshooting data
		System.out.println("Custorder/newCustorder: Zone: " + zone);
		System.out.println("Custorder/newCustorder: Order Created " + dateTime);
		
		
		con.addCustOrder(genID(), custID, dateTime, zone, "Incomplete"); 	//add the order to the database
	}
	
	// add new product to an order
	public void newOrderItem(String orderID, String prodID, int quant) {
		con.addOrderItem(orderID, prodID, String.valueOf(quant));
	}
	
	// Print all orders to the console
	public void printOrders() {
		ArrayList<CustOrder> orders = con.getOrders();
		Customer cus = new Customer();
		
			System.out.println(" Order\t\tCustomer Name\t\tZone\tDate Time\tStatus");
		for(CustOrder o : orders) {
			cus = cus.getCusDetails(o.custID);
			System.out.println(" " + o.id + "\t" + cus.firstName+" "+cus.surName + "\t" + o.zone + "\t\t" + o.dateTime + "\t\t" + o.status);
		}
		
	}
	
	// get orders ready to be put into a JTABLE, 2D array
	public String[][] getOrders() {
		ArrayList<CustOrder> orders = con.getOrders();
								
		String[][] orderLists = new String[orders.size()][4];
		
			int i = 0;
		for(CustOrder o : orders) {
			orderLists[i][0] = o.id;
			orderLists[i][1] = o.zone;
			orderLists[i][2] = o.dateTime;
			orderLists[i][3] = o.status;
			i++;
		}
		return orderLists;
		
	}

	// get the details of a specific order
	public String[] getOrderDetails(String id) {
		
		String[] orderDetails = new String[6];
		Customer cus = new Customer();
		CustOrder co = con.getOrder(id);
		
		cus = cus.getCusDetails(co.custID);
		
		orderDetails[0] = co.id;
		orderDetails[1] = cus.firstName;
		orderDetails[2] = cus.surName;
		orderDetails[3] = co.dateTime;
		orderDetails[4] = co.zone;
		orderDetails[5] = co.status;
		
		//Could Add delivery if different from customer address
		
		
		return orderDetails;
	}
	
	// gets all orderlines ready to be used in a JTABLE, 2D array
	public String[][] getOrderItems(String id) {
		
		ArrayList<OrderLine> orderLines = con.getOrderLines(id);
		
		String[][] lines = new String[orderLines.size()][7];
		
		int i = 0;
		for(OrderLine ol : orderLines) {
			
			Product prod = con.getProduct(ol.productID);
			
			lines[i][0] = ol.productID;
			lines[i][1] = prod.name;
			lines[i][2]	= prod.description;
			lines[i][3] = ol.quantity;
			lines[i][4] = String.valueOf(prod.stockUnits);
			lines[i][5] = String.valueOf(prod.price);
			lines[i][6] = prod.location;
			i++;
		}
		return lines;
	}
	
	
	// updates the ORDER status
	public void updateStatus(String id, String status) {
		con.updateCustOrder(id, status);
		
	}
	
}
