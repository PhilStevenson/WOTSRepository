import java.util.ArrayList;
import java.util.Collections;


public class Order {
	
	// ORDER superclass for stock order and customer order
	
	protected String id;
	protected String custID;
	
	protected String dateTime;
	protected String zone;
	protected String status;
	
	DBconnect con = new DBconnect();	// create and instance of DBconnect
	
	public Order() {
		
	}
	
}
