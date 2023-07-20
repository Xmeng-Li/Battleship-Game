package battleship;

// Extend ship and represents empty sea
public class EmptySea extends Ship {
	
	//Type and length of empty sea
	private static final String SHIP_TYPE = "empty";
	private static final int SHIP_LEN = 1;
	
	// Create an empty sea
	public EmptySea() {
		super(EmptySea.SHIP_LEN);
	}
	
	// Overrides isSunk() that is inherited from Ship, and always returns false to indicate that you did not sink anything
	@Override
	boolean isSunk() {
		return false;
	}
	
	// Overrides shootAt row, column) that is inherited from Ship, and always returns false to indicate that nothing was hit
	@Override
	boolean shootAt(int row, int column) {
		return false;
	}
	
	// This method just returns the string empty
	@Override
	public String getShipType() {
		return EmptySea.SHIP_TYPE;
	}
	
	// Returns the single-character - String to use in the Ocean is print method.
	@Override
	public String toString() {
		return "-";
	}
	
}
