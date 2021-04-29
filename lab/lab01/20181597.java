import java.util.*;

/**
 * Lab 001 
 * @version 1.0
 * @author Sanghwan Lee
 */

class LabTest {
	public static void main(String[] args) {
		// get the User Input
		Scanner in = new Scanner(System.in);
		while(true) {

			System.err.print("echo > ");
			String cmd = in.next();
			if (cmd.equals("quit"))
				break;
			
			// output
			System.out.println(cmd + " !!");
		}
		in.close();
	}
}
