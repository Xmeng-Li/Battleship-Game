package battleship;

// Extends Ship and stands for a battleship
public class Battleship extends Ship {
	
	// Type and number of the ship
	private static final String SHIP_TYPE = "battleship";
	private static final int SHIP_LENGTH = 4;
	
	//Create a battleship with default length
	public Battleship() {
		// call the constructor in the super class with the length of the ship
		super(Battleship.SHIP_LENGTH);
	}
	
	// Override getShipType() method and return a string representing Battleship
	@Override 
	public String getShipType() {
		return Battleship.SHIP_TYPE;
	}
}
