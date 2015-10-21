import java.util.ArrayList;
import java.util.Collections;


public class Order {
	
	private String ID;
	private String custID;
	private String[] products;
	private String dateTime;
	private String zone;
	
	private enum orderStatus {
		Pending, Processing, Delivery, Closed
	}
	
	public Order() {
		
	}
	
}
