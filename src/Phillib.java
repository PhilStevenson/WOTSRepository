import java.util.Scanner;


public class Phillib {
	public static void pause() {
		System.out.println("Press Enter To Continue...");
		Scanner keyboard = new Scanner(System.in);
		keyboard.nextLine();
	}
}
