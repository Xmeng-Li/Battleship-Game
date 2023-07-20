package battleship;

//Extends Ship and stands for a Cruiser
public class Cruiser extends Ship {
	
	private static final String SHIP_TYPE = "cruiser";
	private static final int SHIP_LENGTH = 3;
	
	//Create a cruiser with default length
	public Cruiser() {
		// call the constructor in the super class with the length of the ship
		super(Cruiser.SHIP_LENGTH);
	}

	// Override getShipType() method and return a string representing Cruiser
	@Override
	public String getShipType() {
		return Cruiser.SHIP_TYPE;
	}
}
