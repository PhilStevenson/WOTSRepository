import java.util.ArrayList;
import java.util.Collections;


public class Order {
	
	private String id;
	private String custID;
	
	private String addressFirstLine;
	private String addressSecondLine;
	private String addressTownCity;
	private String addressCounty;
	private String addressCountry;
	private String addressPostcode;
	
	private String[] products;
	private String dateTime;
	private String zone;
	
	private enum orderStatus {
		Pending, Processing, Delivery, Arrived, Closed
	}
	
	public Order() {
		
	}
	
}
