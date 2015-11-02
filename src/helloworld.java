import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class helloworld {
	public void readtxt() {
		File f = new File("sql.txt");
		Scanner s = null;
		
		try {
			s = new Scanner(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(s.hasNextLine()){
			System.out.println(s.nextLine());
		}
	}
}
