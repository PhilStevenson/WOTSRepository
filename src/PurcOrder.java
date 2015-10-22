import java.util.ArrayList;
import java.util.Collections;

public class PurcOrder extends Order{
	
	private String genID() {
		String newId = "POR";
		
		ArrayList<String> ids = con.getIds("purchorder");
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
			System.out.println("PurcOrder/genID: New ID Generated: " + newId);
			System.out.println();
		} else {
			newId = newId + "00001";
			System.out.println("PurcOrder/genID: First ID Generated: " + newId);
			System.out.println();
		}

		return newId;
	}
	
}
