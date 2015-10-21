import java.util.ArrayList;
import java.util.Collections;


public class Customer {



	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		
		this.firstName = firstName;

	}


	private String firstName;
	private String surName;
	private String email;
	private String telephone;
	
	private String addressFirstLine;
	private String addressSecondLine;
	private String addressTownCity;
	private String addressCounty;
	private String addressCountry;
	private String addressPostcode;
	
	private double availableCredit;
	
	private String cardNumber;
	private String cardName;
	private String cardExpiry; 
	private String cardSecNum;
	

	
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
	
	private void newCustomer(){
		DBconnect con = new DBconnect();
		con.addCustomer(genID(), "Philip", "Stevenson", "philstevenson@live.co.uk", "07754319562", "Beech House", "Brucefield Road", "Blairgowrie", "Perthshire", "Scotland", "PH106LA", 10000, "1234123412341234", "MR PHILIP A STEVENSON", "0915", "123");
	}
	
}
