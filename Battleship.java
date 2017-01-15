import java.util.Scanner;

/**
 * Simple program that allows two users to play a game of Battleship with each other.
 * Each player populates their board with the five different ships of varying length,
 * before taking turns to fire at each other's boards. The winner is declared once one
 * player hits all 17 target squares on the opponent's board.
 * 
 * @author Kyle Burns, made for Simul8
 */

public class Battleship {

	//Create player instances
	private static Player p1 = new Player();
	private static Player p2 = new Player();
		
	/**
	 * Main program method which controls the flow of the game. One scanner is used 
	 * throughout the whole game to allow input from the IDE console.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("\nBATTLESHIP! By Kyle Burns, for Simul8\n");
		System.out.println("\nBoth players place their 5 ships onto their board before firing at each other's squares. First player to sink all their opponent's ships wins!\n");
		System.out.println("\nEach square in the grid is labelled 0-99 in left-to-right, top-to-bottom order.\n");
		//Set the name of each player and allow them to populate their board
		String n;
		Scanner scanner = new Scanner(System.in); 
		System.out.print("Player 1, enter your name: ");
		n = scanner.nextLine();
		p1.setName(n);
		p1.populateBoard(scanner);
		
		//Clear console to avoid revealing the player's ships
		for (int i = 0; i < 50; i++) System.out.println();
		
		System.out.print("Player 2, enter your name: ");
		n = scanner.nextLine();
		p2.setName(n);
		p2.populateBoard(scanner);
		
		//Set the players to be opponents
		p1.setEnemy(p2);
		p2.setEnemy(p1);
		
		//Clear console to avoid revealing the player's ships
		for (int i = 0; i < 50; i++) System.out.println();
		System.out.println("LET THE GAMES BEGIN!");
		
		//Each player continues to take turns until one player hits all 17 target 
		//squares on the opponent's board
		while(true){
			p1.takeTurn(scanner);
			if(p1.getScore() == 17){
				gameOver(p1.getName());
				break;
			}
			p2.takeTurn(scanner);
			if(p2.getScore() == 17){
				gameOver(p2.getName());
				break;
			}
		}
		
		//Once game is over, close scanner
		scanner.close();
	}

	/**
	 * Simple method which declares the winner of the game.
	 * 
	 * @param winnerName
	 */	
	public static void gameOver(String winnerName){
		System.out.println("\nGAME OVER! " + winnerName + " is the winner. Thank you for playing!");		
		
		//Wait 3 seconds before program ends
		try {
			Thread.sleep(3000);
		} 
		catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}
