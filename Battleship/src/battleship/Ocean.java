package battleship;

import java.util.Random;

public class Ocean {
	
	//Indicate the ocean size(game board) and the number of ships
	static final int GAME_BOARD = 10;
	
	// Store different ships and indicate the number of each type of ship in the fleet
	private Ship[][] ships = new Ship[Ocean.GAME_BOARD][Ocean.GAME_BOARD];
	
	boolean[][] firedLoc = new boolean[10][10];
	
	static final int BATTLESHIPS = 1;
	static final int CRUISERS = 2;
	static final int DESTROYERS = 3;
	static final int SUBMARINES = 4;

	// The total number of shots fired by the user
	private int shotsFired;
	
	// The number of times a shot hit a ship.
	private int hitCount;
	
	// The number of ships sunk
	private int shipsSunk; 
	
	
	// Constructor
	/**
	 * Creates an empty ocean and fills the ships array with EmptySea objects.
	 * Initializes any game variables, such as how many shots have been fired.
	 */
	public Ocean() {
		this.fillEmptyOcean();
		this.shotsFired = 0;
		this.hitCount = 0;
		this.shipsSunk = 0;
	}
	
	//Fills the ships array with EmptySea objects
	private void fillEmptyOcean() {
		for (int i = 0; i < this.ships.length; i++) {
			for (int j = 0; j < this.ships[i].length; j++) {
				ships[i][j] = new EmptySea();
				firedLoc[i][j] = false;
			}
		}
	}

	
	//Methods
	/**
	 * Place all ten ships randomly on the initial empty ocean. 
	 * Place larger ships before smaller ones or end up with no legal place to put a large ship.
	 * Use the Random class in the java.util package,
	 */
	void placeAllShipsRandomly() {	
		
		Ship[] fleet = {new Battleship(), new Cruiser(), new Cruiser(), new Destroyer(), 
						new Destroyer(), new Destroyer(), new Submarine(), new Submarine(), 
						new Submarine(), new Submarine()};
		
		Random random = new Random();
		boolean horizontal;
		for (int i = 0; i < 10; i++) {
			Ship ship = fleet[i];
			while (true) {
				int row = random.nextInt(10);
				int column = random.nextInt(10);
				
				int rand = random.nextInt(2);
				if (rand == 0) {
					horizontal = true;
				}
				else {
					horizontal = false;
				}
				if (ship.okToPlaceShipAt(row, column, horizontal, this)) {
					ship.placeShipAt(row, column, horizontal, this);
					break;
				}
			}
		}
	}
	
	
	// Returns true if the given location contains a ship, otherwise false
	boolean isOccupied(int row, int column) {
		if (row < 0 || row > 9 || column < 0 || column > 9) {
	        return false;
	    }
		if (ships[row][column] instanceof EmptySea) {
		    return false;
		}
		return true;
	}
	
	
	/**
	 * Returns true if the given location contains a "real" ship, still afloat, (not an EmptySea), false if it does not.
	 * In addition, this method updates the number of shots that have been fired, and the number of hits.
	 * @param row of the ship to shoot at
	 * @param column of the ship to shoot at
	 * @return true if the given location contains a ship, still afloat, (not an EmptySea)
	 */
	boolean shootAt(int row, int column) {
		firedLoc[row][column] = true;
		shotsFired++;
		
		if (ships[row][column].shootAt(row, column)) {
		    hitCount++;
		    return true;
		} 
		else {
		    return false;   
		}	
	}
	
	//Getters
	// Returns the number of shots fired 
	int getShotsFired() {
		return shotsFired;
	}
	
	// Returns the number of hits recorded
	int getHitCount() {
		return hitCount;
	}
	
	// Returns the number of ships sunk
	int getShipsSunk() {
		return shipsSunk;
	}
	
	// Returns true if all ships have been sunk, otherwise false
	boolean isGameOver() {
		int count = 0;
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				if (ships[i][j].isSunk()) {
					count++;
				}       
			}
		if (count == 20) {
			return true;
		}
		return false;
	}
	
	// Returns the 10x10 array of Ships.
	Ship[][] getShipArray() {
		return ships;
	}
	
	// Prints the Ocean. To aid the user, row numbers should be displayed along the left edge of the array, 
	// and column numbers should be displayed along the top.
	void print() {
		System.out.println("    0 1 2 3 4 5 6 7 8 9");
		for (int i = 0; i < 10; i++) {
		    System.out.print(i + "  ");
		    for (int j = 0; j < 10; j++) {
		    	if (!firedLoc[i][j]) {
		            System.out.print(" .");
		        }
		        else {
		            System.out.print(" " + ships[i][j].toString());
		        }
		    }
		    System.out.println(" ");
		}
	}
	
	// Use for debugging purposes
	void printWithShips() {
		System.out.println("    0 1 2 3 4 5 6 7 8 9");
		for (int i = 0; i < 10; i++) {
			System.out.print(i + "  ");
			for (int j = 0; j < 10; j++) {
				if (ships[i][j].getShipType() == "battleship") {
					System.out.print(" b");
				}
				else if (ships[i][j].getShipType() == "cruiser") {
					System.out.print(" c");
				}
				else if (ships[i][j].getShipType() == "destroyer") {
					System.out.print(" d");
				}
				else if (ships[i][j].getShipType() == "submarine") {
					System.out.print(" s");
				}
				else {
					System.out.print("  ");
				}
			}
	    System.out.println(" ");
		}
	}			
}
