import java.util.ArrayList;
import java.util.Collections;


public class Customer {

	DBconnect con = new DBconnect();

	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		
		this.firstName = firstName;

	}


	protected String firstName;
	protected String surName;
	protected String email;
	protected String telephone;
	
	protected String addressFirstLine;
	protected String addressSecondLine;
	protected String addressTownCity;
	protected String addressCounty;
	protected String addressCountry;
	protected String addressPostcode;
	
	protected double availableCredit;
	
	protected String cardNumber;
	protected String cardName;
	protected String cardExpiry; 
	protected String cardSecNum;
	

	
	public Customer() {
		
	}
	
	private String genID() {
		String newId = "CUS";
		
		DBconnect con = new DBconnect();
		
		ArrayList<String> ids = con.getIds("customer");
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
			System.out.println("Customer/genID: New ID Generated: " + newId);
			System.out.println();
		} else {
			newId = newId + "00001";
			System.out.println("Customer/genID: First ID Generated: " + newId);
			System.out.println();
		}

		return newId;
		
	}
	
	public Customer getCusDetails(String custID) {
		
		
		
		
	}
	
	public void newCustomer(String fname, String surname, String email, String tel, String addfline,
			String addsline, String addtowncity, String addcounty, String addcountry, String addpostcode,
			double credit, String cardnum, String cardname, String cardexp, String secnum){
		
		con.addCustomer(genID(), fname, surname, email, tel, addfline, addsline, addtowncity,
				addcounty, addcountry, addpostcode, credit, cardnum, cardname, cardexp, secnum);
		
		
		//con.addCustomer(genID(), "Philip", "Stevenson", "philstevenson@live.co.uk", "07754319562", "Beech House", "Brucefield Road", "Blairgowrie", "Perthshire", "Scotland", "PH106LA", 10000, "1234123412341234", "MR PHILIP A STEVENSON", "0915", "123");
	}
	
}
