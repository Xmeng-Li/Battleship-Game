package battleship;

import java.util.Scanner;

/**
 * Set up the game; 
 * Accept "shots" from the user; 
 * Display the results; 
 * Print final scores.
 */
public class BattleshipGame {
	
	// Starts the game
	public static void main(String[] args) {
		Ocean ocean = new Ocean();
		ocean.placeAllShipsRandomly();
		
		//debugging method
		ocean.printWithShips();
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		while (!ocean.isGameOver()) {
			
			//print the ocean
			ocean.print();
			System.out.println("Hit count: " + ocean.getHitCount());
			
			//get user input of row and column
			System.out.print("Please enter the row and column you want to hit: ");
			
			String rowColumn = scanner.nextLine();
			String[] rowColumnArrary = rowColumn.split(",");
			
			String rowNum = rowColumnArrary[0].trim();
			String columnNum = rowColumnArrary[1].trim();
			
			try {
				int row = Integer.parseInt(rowNum);
				int column = Integer.parseInt(columnNum);
				
				boolean fireShot = ocean.shootAt(row, column);
				
				//check if the ship is hit and sinks or not
				if (fireShot) {
					System.out.println("Hit");
					
					Ship[][] shipArray = ocean.getShipArray();
					Ship ship = shipArray[row][column];
					
					// print a message when the ship is sunk
					if (ship.isSunk()) {
						System.out.println("You just sank a ship - " + ship.getShipType());
					}
				} 
				else {
					System.out.println("Miss");
				}
			} 
			catch (NumberFormatException e) {
				e.printStackTrace();		
			}
		}
		System.out.println("Game Over");
	}
}
