/**
 * Class which represents a 10x10 tracking board in Battleship using an
 * integer array of length 100. A value of -1 represents a non targeted
 * square, 0 represents a miss and 1 represents a successful hit.
 *
 * @author Kyle Burns, made for Simul8
 */
public class TrackingBoard extends Board {

	//Construct the array and set all squares to be unknown
	public TrackingBoard() {
		super();
		for(int i=0;i<100;i++)
			boardStatus[i] = -1;
	}

	/**
	 * Method which prints a visual representation of the player's
	 * current tracking board status in the form of a 10x10 grid. 
	 * "-" symbol represents a non targeted square, "X" represents 
	 * a miss and "O" represents a successful hit.
	 * 
	 * @param name
	 */	
	public void viewBoard(String name){
		//Print horizontal axis
		System.out.print("\n  0123456789");
		
		for(int i=0;i<100;i++){
			if(i == 50) System.out.print("    " + name + "'s tracking board");
			
			//Print vertical axis
			if(i%10 == 0) System.out.print("\n" + i/10 + " ");
			
			if(boardStatus[i] == -1)
				System.out.print("-");
			else if(boardStatus[i] == 0)
				System.out.print("X");
			else if(boardStatus[i] == 1)
				System.out.print("O");
		}
		System.out.println("\n");
	}
}
