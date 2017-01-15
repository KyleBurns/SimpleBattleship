/**
 * Abstract class which represents a 10x10 board in Battleship using an
 * integer array of length 100. Its subclasses are the Player Board and
 * Tracking Board.
 * 
 * @author Kyle Burns, made for Simul8
 */
public abstract class Board {

	protected int[] boardStatus;
	
	//Construct the array
	public Board(){
		 boardStatus = new int[100];
	} 
	
	//Getter
	public int getSquare(int coord){
		return boardStatus[coord];
	}
	
	//Setter
	public void setSquare(int coord, int i){
		boardStatus[coord] = i;
	}
	
	//Output a visual representation of the board's status
	abstract void viewBoard(String name);
}
