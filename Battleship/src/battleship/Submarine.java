package battleship;

////Extends Ship and stands for Submarine
public class Submarine extends Ship {
	
	private static final String SHIP_TYPE = "submarine";
	private static final int SHIP_LENGTH = 1;
	
	//Create a submarine with default length
	public Submarine() {
		// call the constructor in the super class with the length of the ship
		super(Submarine.SHIP_LENGTH);
	}	

	// Override getShipType() method and return a string representing Submarine
	@Override
	public String getShipType() {
		return Submarine.SHIP_TYPE;
	}
}
