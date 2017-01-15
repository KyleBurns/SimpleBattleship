/**
 * Class which represents a 10x10 player board in Battleship using an
 * integer array of length 100. A value of 0 represents an empty square
 * and a value of 1 represents a square occupied by a ship.
 * 
 * @author Kyle Burns, made for Simul8
 */
public class PlayerBoard extends Board {
	
	//Construct the integer array
	public PlayerBoard(){
		 super();
	} 
	
	/**
	 * Method which prints a visual representation of the player's
	 * current board status in the form of a 10x10 grid. "-" symbol
	 * represents an empty square and "O" represents a square which
	 * a ship is occupying.
	 * 
	 * @param name
	 */	
	public void viewBoard(String name){
		//Print horizontal axis
		System.out.print("\n  0123456789");
		
		for(int i=0;i<100;i++){
			if(i == 50) System.out.print("    " + name + "'s board");
			
			//Print vertical axis
			if(i%10 == 0) System.out.print("\n" + i/10 + " ");
			
			if(boardStatus[i] == 0)
				System.out.print("-");
			else if(boardStatus[i] == 1)
				System.out.print("O");
		}
		System.out.println("\n");
	}

	/**
	 * Method which checks whether a ship of length l can be placed
	 * in the position indicated by the square value s and orientation c.
	 * Returns true and places the ship onto the board if the given 
	 * placement does not break any rules.
	 * 
	 * @param s
	 * @param l
	 * @param c
	 * @return boolean
	 */	
	public boolean verifySquare(int s, int l, String c){
		//If square value is invalid, return false
		if(s < 0 || s > 99) return false;
		
		//If placement is horizontal
		if(c.equals("a")){
			//If the ship breaks the board boundary's right hand side, return false
			if(s%10 + l > 10) return false;
			//For each square that the ship wants to occupy, if any square is already
			//occupied by another ship, return false
			for(int i=0;i<l;i++){
				if(boardStatus[s+i] != 0) return false;
			}
			//All squares are free and no boundary is broken so add the ship to the board
			//and return true
			for(int i=0;i<l;i++){
				boardStatus[s+i] = 1;
			}			
			return true;
		}
		//Else if placement is vertical
		else if(c.equals("d")){
			//If the ship breaks the bottom of the board boundary, return false
			if((s - s%10)/10 + l > 10) return false; 
			//For each square that the ship wants to occupy, if any square is already
			//occupied by another ship, return false
			for(int i=0;i<l;i++){
				if(boardStatus[s+(i*10)] != 0) return false;
			}	
			//All squares are free and no boundary is broken so add the ship to the board
			//and return true
			for(int i=0;i<l;i++){
				boardStatus[s+(i*10)] = 1;
			}			
			return true;
		}
		//If string c is neither "a" nor "d" then input is invalid
		return false;
	}

}
