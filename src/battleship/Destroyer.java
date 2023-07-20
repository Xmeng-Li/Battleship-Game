package battleship;

//Extends Ship and stands for a destroyer
public class Destroyer extends Ship {
	private static final String SHIP_TYPE = "destroyer";
	private static final int SHIP_LENGTH = 2;
	
	//Create a destroyer with default length
	public Destroyer() {
		// call the constructor in the super class with the length of the ship
		super(Destroyer.SHIP_LENGTH);
	}	

	// Override getShipType() method and return a string representing destroyer
	@Override
	public String getShipType() {
		return Destroyer.SHIP_TYPE;
	}

}
