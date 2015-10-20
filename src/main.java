
public class main {
	public static void main(String[] args) {
		System.out.println("Hello World! This is a TEST");
		
		Customer customer001 = new Customer();
		DBconnect con = new DBconnect();
		
		customer001.setFirstName("Philip");
		
		System.out.println("The customers name is " + customer001.getFirstName());
		
		con.accessBD();
		
		System.out.println(con.queryDB("select * from customer"));
		
		;
	}
}
