import java.util.ArrayList;
import java.util.Collections;


public class Order {
	
	protected String id;
	protected String custID;
	
	protected String dateTime;
	protected String zone;
	protected String status;
	
	protected enum orderStatus {
		Pending, Processing, Delivery, Arrived, Closed
	}
	
	DBconnect con = new DBconnect();
	
	public Order() {
		
	}
	
}
