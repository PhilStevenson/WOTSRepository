import java.util.Scanner;


public class main {
	public static void main(String[] args) {
		System.out.println("Hello World! This is a TEST");
		
		
		
		Customer cus = new Customer();
		
		CustOrder ord = new CustOrder();
		Product pro = new Product();
		
		GUI gui = new GUI();

		
		
		
//		pro.newProduct("Halfpint Ben Gnome","A Gnome designed from the famous character Halfpint Ben." , 0.56);
//		pro.newProduct("Physics Joe Gnome", "A Gnome of Joe who studied physics", 69);
//		pro.newProduct("The Rampant Mabbett Gnome", "oh Mabbett", 22.5);
//		pro.newProduct("Mediterranean Dan Gnome", "Disclaimer: NOT Mediterranean", 45.21);
//		pro.newProduct("The Colm it Down Gnome", "Colm the gnome is a fictional charachter who works the long lost trade of NETbuilder", 500.5);
//		pro.newProduct("Bill and Ben Gnomes", "Inspired by the ledgend of bilal and Ben from Coding Tiger Compiling Dragon", 79.99);
//		
		
		//ord.newCustOrder("CUS00002");
		
//		ord.newOrderItem("COR00001", "PRO00005", 1);
//		ord.newOrderItem("COR00001", "PRO00001", 50);
		
		ord.printOrders();
		
		//DBconnect con = new DBconnect();
		
		//cus.setFirstName("Philip");
		
		//System.out.println("The customers name is " + customer001.getFirstName());
		
		
		
		
		//con.addCustomer("Philip", "Stevenson", "philstevenson@live.co.uk", "07754319562", "Beech House", "Brucefield Road", "Blairgowrie", "Perthshire", "Scotland", "PH106LA", 10000, "1234123412341234", "MR PHILIP A STEVENSON", "0915", "123");
		
		;
	}
	
}
