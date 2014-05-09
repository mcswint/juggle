/**
 * @author: Amanda Swinton
 * 
 * Test.java tests the AssignTeams program. 
 * The input parameters for AssignTeams() should be a
 * .txt file containing the circuits and jugglers data
 */
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AssignTeams at= new AssignTeams("jugglefest.txt");
		at.run();

	}

}
