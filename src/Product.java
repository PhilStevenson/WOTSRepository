import java.util.ArrayList;
import java.util.Collections;

public class Product {
	
	// Product Variables
	protected String ID;
	protected String name;
	protected String description;
	protected double price;
	protected String location;
	protected int stockUnits;
	
	// create new instance of DBconnect
	DBconnect con = new DBconnect();
	
	public Product() {
		
	}
	
	// generates new ID for the Product table
	// see CustOrder.genID() for comments
	private String genID() {
		String newId = "PRO";
		
		ArrayList<String> ids = con.getIds("product");
		ArrayList<Integer> idNum = new ArrayList();
		
		if(!ids.isEmpty()) {
			for (String id : ids) {
				
				idNum.add(Integer.valueOf(id.substring(3)));
			}
			
			String nxtNum = String.valueOf(Collections.max(idNum)+1);
				
			int zeros = 5 - nxtNum.length();
			String zero = "";
			for( int i=0; i<zeros; i++) {
				zero = zero + '0';
			}
				
			newId = newId + zero + nxtNum;
			System.out.println("Product/genID: New ID Generated: " + newId);
			System.out.println();
		} else {
			newId = newId + "00001";
			System.out.println("Product/genID: First ID Generated: " + newId);
			System.out.println();
		}

		return newId;
	}
	
	
	// create a new product in the database
	public void newProduct(String name, String description, double price) {
		
		con.addProduct(genID(), name, description, price); 
		
	}
	
	// get the details of a specific product
	public Product getProductDetails(String id) {
		Product prod = new Product();
		
		prod = con.getProduct(id);
		
		return prod;
	}
	
	// update stock level of specific product
	public void updateStock(String id, int newStock) {
		con.updateStock(id, newStock);
		
	}
	
	// allocates the stock needed to fufill an order, so that the stock cannot be ordered twice
	public void allocateStock(String orderID) {
		
		if(!con.isStockAllocated(orderID)){
			ArrayList<OrderLine> lines = con.getOrderLines(orderID);
		
			for(OrderLine l : lines) {
				Product p = getProductDetails(l.productID);
				int curQ = p.stockUnits;
				
				int newQ = curQ - Integer.parseInt(l.quantity);
				
				con.updateStockAllocation( orderID, true);
				updateStock(l.productID, newQ);
			}
		}		
	}
	
	// unAllocates the stock that was used to fufill and order, used when orders are cancelled or changed to a state where stock should not be allocated
	public void unAllocateStock(String orderID) {
		if(con.isStockAllocated(orderID)){
			ArrayList<OrderLine> lines = con.getOrderLines(orderID);
			
			for(OrderLine l : lines) {
				Product p = getProductDetails(l.productID);
				int curQ = p.stockUnits;
				
				int newQ = curQ + Integer.parseInt(l.quantity);
				
				con.updateStockAllocation( orderID, false);
				updateStock(l.productID, newQ);
			}
		}
	}
}
