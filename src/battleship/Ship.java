package battleship;

public abstract class Ship {
	
	//Instance variables
	//The row that contains the bow (front part of the ship)
	private int bowRow;
	
	//The column that contains the bow (front part of the ship)
	private int bowColumn;
	
	//The length of the ship
	private int length;
	
	// boolean that represents whether the ship is going to be placed horizontally or vertically
	private boolean horizontal;
	
	// An array of booleans that indicate whether that part of the ship has been hit or not
	private boolean[] hit;
	
	//constructor
	/**
	 * sets the length of the ship
	 * initializes the hit array based on the ship length
	 * @param length of the ship
	 */
	public Ship(int length) {
		this.length = length;		
		this.hit = new boolean[length];	
	}
	
	//Getters	
	//Returns the ship length
	public int getLength() {
		return this.length;		
	}
	
	//Returns the row corresponding to the position of the bow
	public int getBowRow() {
		return this.bowRow;
	}
	
	// Returns the bow column location
	public int getBowColumn() {
		return this.bowColumn;
	}
	
	//Returns the hit array
	public boolean[] getHit() {
		return this.hit;
	}
	
	// Returns whether the ship is horizontal or not
	public boolean isHorizontal() {
		return this.horizontal;
	}
	
	
	// Setters
	// Sets the value of bowRow
	public void setBowRow(int row) {
		this.bowRow = row;
	}
	
	// Sets the value of bowColumn
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}
	
	// Sets the value of the instance variable horizontal
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	/**
	 * Returns the type of ship as a String
	 * Every type of ship override and implement this method
	 * @return string with ship type
	 */
	public abstract String getShipType();
	
	
	/**
	 * Based on the given row, column, and orientation, returns true if it is okay to put a ship of this length with its bow in this location; false otherwise
	 * @param row of the ship location
	 * @param column of the ship location
	 * @param horizontal location of the ship
	 * @param ocean
	 * @return true if the ship can be placed in a certain location, otherwise return false
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		
		if (horizontal) {
			int len = getLength();
		    if (column + len > 10) {
		        return false;
		    }
			for (int y = column - 1; y <= column + len; y++) {
				if (ocean.isOccupied(row - 1, y) == true) {
					return false;
				}
				if (ocean.isOccupied(row, y) == true) {
					return false;
				}
				if (ocean.isOccupied(row + 1, y) == true) {
					return false;
				}
			}
			return true;
		} 
		else {
			int len = getLength();
		    if (row + len > 10) {
		        return false;
		    }
			for (int x = row - 1; x <= row + len; x++) {
				if (ocean.isOccupied(x, column - 1) == true) {
				    return false;
				}
				if (ocean.isOccupied(x, column) == true) {
					return false;
				}
				if (ocean.isOccupied(x, column + 1) == true) {
					return false;
				}	
			}
			return true;
		}
	}
	
	/**
	 * Puts the ship in the ocean.
	 * @param row of ship location 
	 * @param column of ship location
	 * @param horizontal of ship location
	 * @param ocean to put the ship on
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		
		this.setBowRow(row);
		this.setBowColumn(column);
		this.setHorizontal(horizontal);
		
		Ship[][] ships = ocean.getShipArray();
		int len = getLength();
		
		if (horizontal) {
		    for (int i = column; i < column + len; i++) {
	            ships[row][i] = this;
	        }
		} 
		else {
		    for (int i = row; i < row + len; i++) {
		        ships[i][column] = this;
		    }
		}
	}
	
	
	/**
	 * If a part of the ship occupies the given row and column, and the ship has not been sunk, mark that part of the ship as hit
	 * @param row to shoot at
	 * @param column to shoot at
	 * @return true if the ship was hit, otherwise false
	 */
	boolean shootAt(int row, int column) {
		if (!this.isSunk()) {
		    if (this.horizontal) {
		    	hit[column - this.bowColumn] = true;
		    	return true;
		    } 
		    else {
		    	hit[row - this.bowRow] = true;
		    	return true;
		    }
	    } 
		else {
		    return false;
	    }
	}
	
	
	/**
	 * Return true if every part of the ship has been hit, false otherwise
	 * @return true if the ship is sunk
	 */
	boolean isSunk() {
		int len = this.getLength();
		for (int i = 0; i < len; i++) {
			if (this.hit[i] == false) {
				return false;
			}
		}
		return true;
	}
	
	@Override 
	//Returns a single-character String to use in the Ocean is print method
	public String toString() {
		String shipChar = "";
		
		if (this.isSunk()) {
			shipChar = "s";
		} 
		else {
			shipChar = "x";
		}		
		return shipChar;
	}
}
