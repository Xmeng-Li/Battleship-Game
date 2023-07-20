package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		// Test the length of Cruiser 
		ship = new Cruiser();
		assertEquals(3, ship.getLength());
		
		// Test the length of Destroyer
		ship = new Destroyer();
		assertEquals(2, ship.getLength());
		
		// Test the length of Submarine
		ship = new Submarine();
		assertEquals(1, ship.getLength());
	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		// Test bow row of the second ship
		Ship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertNotEquals(row, battleship2.getBowRow());
		
		// Test bow row of the third ship
		Ship battleship3 = new Battleship();
		row = 2;
		column = 5;
		horizontal = true;
		battleship3.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship3.getBowRow());		
	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());	
		
		//Test bow column of the second ship
		Ship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.setBowColumn(column);
		assertNotEquals(column, battleship2.getBowColumn());
		
		////Test bow column of the third ship
		Ship battleship3 = new Battleship();
		row = 2;
		column = 5;
		horizontal = true;
		battleship3.placeShipAt(row, column, horizontal, ocean);
		battleship3.setBowColumn(column);
		assertEquals(column, battleship3.getBowColumn());
	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		//Test cruiser
		ship = new Cruiser();
		hits = new boolean[3];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[1]);
		assertFalse(ship.getHit()[1]);
		
		//Test destroyer
		ship = new Destroyer();
		hits = new boolean[2];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);		
	}
	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		//Test cruiser
		ship = new Cruiser();
		assertEquals("cruiser", ship.getShipType());
		
		//Test destroyer
		ship = new Destroyer();
		assertNotEquals("battleship", ship.getShipType());		
	}
	
	@Test
	void testIsHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		//Test horizontal of the second ship
		Ship battleship2 = new Battleship();
		row = 0;
		column = 4;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertFalse(battleship2.isHorizontal());
		
		//Test horizontal of the third ship
		Ship battleship3 = new Battleship();
		row = 2;
		column = 5;
		horizontal = true;
		battleship3.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship3.isHorizontal());
	}
	
	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		//Test bow row of the second ship
		Ship battleship2 = new Battleship();
		row = 0;
		column = 4;
		horizontal = true;
		battleship2.setBowRow(row);
		assertEquals(row, battleship2.getBowRow());
		
		// Test bow row of the third ship
		Ship cruiser = new Cruiser();
		row = 1;
		column = 5;
		horizontal = true;
		cruiser.setBowRow(row);
		assertEquals(row, cruiser.getBowRow());
	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		//Test bow column of the second ship
		Ship battleship2 = new Battleship();
		row = 0;
		column = 4;
		horizontal = true;
		battleship2.setBowColumn(column);
		assertEquals(column, battleship2.getBowColumn());
		
		//Test bow column of the third ship
		Ship battleship3 = new Battleship();
		row = 0;
		column = 5;
		horizontal = true;
		battleship.setBowColumn(column);
		assertNotEquals(column, battleship3.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		//Test horizontal of the second ship
		Ship battleship2 = new Battleship();
		row = 1;
		column = 6;
		horizontal = true;
		battleship2.setHorizontal(horizontal);
		assertTrue(battleship2.isHorizontal());
		
		//Test horizontal of destroyer
		Ship destroyer = new Destroyer();
		row = 7;
		column = 9;
		horizontal = false;
		destroyer.setHorizontal(horizontal);
		assertFalse(destroyer.isHorizontal());
	}

	@Test
	void testOkToPlaceShipAt() {
		
		//test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		//Test if it's OK to put destroyer
		Ship destroyer = new Destroyer();
		row = 2;
		column = 7;
		horizontal = true;
		ok = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		//Test if it's OK to put cruiser
		Ship cruiser = new Cruiser();
		row = 3;
		column = 5;
		horizontal = false;
		ok = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		//test third ship
		Battleship battleship3 = new Battleship();
		row = 2;
		column = 4;
		horizontal = true;
		boolean ok3 = battleship3.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok3, "OK to place ship here.");
	}

	@Test
	void testPlaceShipAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertNotEquals(battleship, ocean.getShipArray()[0][1]);
		

		//test submarine
		Ship submarine = new Submarine();
		row = 7;
		column = 4;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, submarine.getBowRow());
		assertEquals(column, submarine.getBowColumn());
		assertFalse(submarine.isHorizontal());
		
		//test destroyer
		Ship destroyer = new Destroyer();
		row = 4;
		column = 3;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, destroyer.getBowRow());
		assertEquals(column, destroyer.getBowColumn());
		assertTrue(destroyer.isHorizontal());
	}

	@Test
	void testShootAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());
		
		//TODO
		//More tests
	}
	
	@Test
	void testIsSunk() {
		
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.isSunk());
		
		//test cruiser
		Ship cruiser = new Cruiser();
		row = 5;
		column = 1;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(cruiser.isSunk());
		assertTrue(cruiser.shootAt(5, 1));
		assertFalse(cruiser.isSunk());
		
		//test destroyer
		Ship destroyer = new Destroyer();
		row = 7;
		column = 2;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(destroyer.isSunk());
		assertTrue(destroyer.shootAt(7, 2));
		assertFalse(destroyer.isSunk());
		
	}

	@Test
	void testToString() {
		
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 0;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(0, 1);
		assertEquals("x", battleship.toString());
		
		//test submarine
		Ship submarine = new Submarine();
		assertEquals("x", submarine.toString());
		
		row = 5;
		column = 2;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(5, 2);
		assertEquals("x", battleship.toString());
		
		//test cruiser
		Ship cruiser = new Cruiser();
		assertNotEquals("-", cruiser.toString());
		
		row = 4;
		column = 6;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(4, 6);
		assertEquals("x", battleship.toString());
	}
}
