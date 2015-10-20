
public class Order {
	
	private String ID;
	private String[] products;
	private String dateTime;
	private String zone;
	
	private enum orderStatus {
		Open, Processing, Delivery, Closed
	}
	
	public Order() {
		
	}
	
}
