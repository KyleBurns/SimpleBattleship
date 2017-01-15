import java.util.Scanner;

/**
 * Class which represents a player in the game of Battleship.
 * 
 * @author Kyle Burns, made for Simul8
 */
public class Player {

	//Variables
	private PlayerBoard playerBoard;
	private TrackingBoard trackingBoard;
	private String name;
	private int score;
	private Player enemy;
	
	//Constructor which instantiates a new board and tracking board.
	public Player(){
		playerBoard = new PlayerBoard();
		trackingBoard = new TrackingBoard();
		score = 0;
	}

	/**
	 * Method which allows the player to target a square on their opponent's
	 * board. The outcome will either be a HIT or MISS, incrementing the score
	 * by 1 if a target is successfully hit.
	 * 
	 * @param scanner
	 */	
	public void takeTurn(Scanner scanner){
		//Show the current tracking board status
		trackingBoard.viewBoard(name);
		
		//Take an input square value to target, setting coord to -1 if input is invalid
		int coord;
		System.out.print(name + ", choose a square to target: ");
		try{
			coord = Integer.parseInt(scanner.nextLine());
		}
		catch (NumberFormatException e){
			coord = -1;
		}

		//If input is invalid or the target square value has already been targeted in a previous
		//turn, prompt player to re-enter until input is valid
		while(coord < 0 || coord > 99 || trackingBoard.getSquare(coord) != -1){
			System.out.print("Invalid input. Please re-enter: ");
			try{
				coord = Integer.parseInt(scanner.nextLine());
			}
			catch (NumberFormatException e){
				coord = -1;
			}
		}
		
		//Update tracking board once enemy square has been targeted
		int targetSquareStatus = enemy.getBoard().getSquare(coord);
		trackingBoard.setSquare(coord, targetSquareStatus);
		
		//Print out outcome of the turn and increment score if hit is successful
		if(targetSquareStatus == 0)
			System.out.println("MISS! " + score + "/17 squares hit.");
		else if (targetSquareStatus == 1)
			System.out.println("HIT! " + ++score + "/17 squares hit.");
	}

	/**
	 * Method which allows the player to place the five different ships of varying
	 * length onto their board by taking their starting square and position as an
	 * input. Checking is done to ensure that the input position is valid and the
	 * ship is not placed outside of the board boundaries or on top of another ship.
	 * 
	 * @param scanner
	 */	
	public void populateBoard(Scanner scanner){
		int square;
		int n;
		String c;
		//Loop five times for the five ships - by using some rounding trickery, the length
		//of each ship in turn will be 5, 4, 3, 3 and 2 squares as required
		for(float k=5;k>1;k -= 0.8){
			playerBoard.viewBoard(name);
			n = Math.round(k);
			
			//Take an input starting square value to place the ship, if invalid then set value to -1
			System.out.println(name + ", choose the square and position (across/down) of your " + n + "-length ship. ");
			System.out.print("Square: ");
			try{
				square = Integer.parseInt(scanner.nextLine());
			}
			catch(NumberFormatException e) {
				square = -1;
			}
			
			//Take in string "a" for across or "d" for down to indicate orientation of the ship
			System.out.print("Position (a for across, d for down): ");
			c = scanner.nextLine();
			
			//If input square value is invalid or the input position is invalid for any other
			//reason, prompt player to re-enter until input is valid
			while(!(playerBoard.verifySquare(square, n, c))){
				System.out.print("Invalid input, please re-enter. ");
				System.out.print("Square: ");
				try{
					square = Integer.parseInt(scanner.nextLine());
				}
				catch(NumberFormatException e) {
					square = -1;
				}
				System.out.print("Position (a for across, d for down): ");
				c = scanner.nextLine();
			}	
		}
		//Show the board once the ship has been placed.
		playerBoard.viewBoard(name);
	}
	
	//Setters
	
	public void setName(String n){
		name = n;
	}
	
	public void setEnemy(Player p){
		enemy = p;
	}
	
	//Getters
	
	public Board getBoard(){
		return playerBoard;
	}

	public int getScore() {
		return score;
	}
	
	public String getName() {
		return name;
	}

}
