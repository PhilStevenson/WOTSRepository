import java.util.ArrayList;
import java.util.Collections;

public class Product {
	
	protected String ID;
	protected String name;
	protected String description;
	protected double price;
	protected String location;
	protected int stockUnits;
	
	DBconnect con = new DBconnect();
	
	public Product() {
		
	}
	
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
	
	public void newProduct(String name, String description, double price) {
		
		con.addProduct(genID(), name, description, price); 
		
	}

	public Product getProductDetails(String id) {
		Product prod = new Product();
		
		prod = con.getProduct(id);
		
		return prod;
	}
	
	public void updateStock(String id, int newStock) {
		con.updateStock(id, newStock);
		
	}
	
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
