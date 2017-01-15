--Simple Battleship game by Kyle Burns for Simul8--

Java with the Eclipse IDE was used to create the solution for this challenge. The start.bat file should compile the java files
and run the main program.

The solution provided is a fully-working command-line version of Battleship, allowing two players to fill up their boards with the five
different ships before taking turns firing at the squares on each other's board until one player sinks all of their opponent's ships.

The given files are:

Battleship.java - The main program which controls the flow of the game
Player.java - Class which represents a single player instance
Board.java - Abstract class representing a 10x10 board
PlayerBoard.java - Representation of a player's board, extends Board.java
TrackingBoard.java - Representation of a player's tracking board, extends Board.java

This was a very enjoyable challenge to undertake and I hope you like my solution.

--Building a board--

Initially I represented the board structure as a 2D array of booleans, but came to realise that this was unnecessary, since any operation
to move up or down a square could be achieved by simply incrementing/decrementing the array index by 10 in a 1D array. 

I also changed the values from boolean to integer, as the tracking board created later on required 3 different values - making a boolean
data type unsuitable.

In my given solution, the board is an integer array of length 100 representing squares 0-99 in left-to-right, top-to-bottom order, with the value
0 indicating an empty square and 1 a full square.

--Tracking board--

As I now had two very similar classes in my program, I decided to create an abstract board class with the player board and tracking board as
subclasses. The abstract board class initialises a 10x10 integer array and contains the getSquare() and setSquare() methods as well as the 
abstract method viewBoard().

In order to access the other player's board, I decided to store the opposing player instance using the setEnemy() method, setting p1 to p2's
enemy and p2 to p1's enemy. This allowed me to use the appropriate getter methods to access the other player's board and match the tracking
board square to the corresponding enemy player board square.

In my given solution, the value -1 indicates an untargeted square, 0 is a missed target and 1 is a successful hit.

--Validate Layout--

The layout is validated whenever an attempt to place a ship onto the board is made in the set-up stage of the game. The player chooses their
desired position by entering the square where the "head" of the ship will go, and the orientation of the ship, either across (horizontal) or 
down (vertical) the grid.

This process loops five times, and I used a small trick with float rounding so that with each loop, the length of the ship being placed is 5, 4,
3, 3 and 2 squares in order.

The validation checks for 3 possible violations:
	1: The input square value or orientation is invalid - check if the input square value is between 0-99 and the orientation is either horizontal
	 or vertical.
	2 - The input square value causes the ship to go past the edge of the board - the modulo operation is used to perform a check on
	whether the "tail" of the ship goes beyond 9 on the x or y axis.
	3 - The input position overlaps with an already existing ship placement - each square that the ship wants to occupy is checked to see if it
	is empty before placing the ship. If one square is already full, the placement is invalid.

--Determine Winner--

In the main method, each player takes their turn and a check is performed afterward to determine whether their score is 17. Once one player successfully
hits all 17 enemy squares, the game ends and that player is declared the winner.