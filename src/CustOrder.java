import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


public class CustOrder extends Order {
	
	private String genID() {
		String newId = "COR";
		
		ArrayList<String> ids = con.getIds("custorder");
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
			System.out.println("CustOrder/genID: New ID Generated: " + newId);
			System.out.println();
		} else {
			newId = newId + "00001";
			System.out.println("CustOrder/genID: First ID Generated: " + newId);
			System.out.println();
		}

		return newId;
	}

	public void newCustOrder(String custID) {
		
		Date d = new Date();
		
		dateTime = String.valueOf(d);
		
		String postcode = con.getPostcode(custID);
		
		zone = postcode.substring(0,4);
		
		
		System.out.println("Custorder/newCustorder: Zone: " + zone);
		System.out.println("Custorder/newCustorder: Order Created " + dateTime);
		
		con.addCustOrder(genID(), custID, dateTime, zone, "Incomplete");
	}
	
	public void newOrderItem(String orderID, String prodID, int quant) {
		con.addOrderItem(orderID, prodID, String.valueOf(quant));
	}
	
	public void printOrders() {
		ArrayList<CustOrder> orders = con.getOrders();
		Customer cus = new Customer();
		
			System.out.println(" Order\t\tCustomer Name\t\tZone\tDate Time\tStatus");
		for(CustOrder o : orders) {
			cus = cus.getCusDetails(o.custID);
			System.out.println(" " + o.id + "\t" + cus.firstName+" "+cus.surName + "\t" + o.zone + "\t\t" + o.dateTime + "\t\t" + o.status);
		}
		
	}
}
