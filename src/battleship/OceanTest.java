package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	Ocean ocean;
	
	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}
	
	@Test
	void testEmptyOcean() {
		
		//tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	@Test
	void testPlaceAllShipsRandomly() {
		
		//tests that the correct number of each ship type is placed in the ocean
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();
		
		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);
		
		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(1, 5));
		
		//Test two different ships
		Cruiser cruiser = new Cruiser();
		row = 0;
		column = 6;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine2 = new Submarine();
		row = 2;
		column = 4;
		horizontal = false;
		submarine2.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(0, 6));
		assertTrue(ocean.isOccupied(2, 4));
		
		//Test not occupied
		Battleship battleship = new Battleship();
		row = 7;
		column = 3;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine3 = new Submarine();
		row = 5;
		column = 7;
		horizontal = true;
		submarine3.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(ocean.isOccupied(0, 1));				
	}

	@Test
	void testShootAt() {
	
		assertFalse(ocean.shootAt(0, 1));
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertFalse(ocean.shootAt(0, 5));
		
		//Test submarine
		Submarine submarine = new Submarine();
		row = 3;
		column = 7;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(ocean.shootAt(3, 5));
		assertFalse(submarine.isSunk());
		assertTrue(ocean.shootAt(3, 7));
		
		//Test cruiser
		Cruiser cruiser = new Cruiser();
		row = 8;
		column = 2;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(ocean.shootAt(4, 5));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(8, 2));
		
	}

	@Test
	void testGetShotsFired() {
		
		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertFalse(ocean.shootAt(0, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());
		
		//Test destroyer and battleship
		Destroyer destroyer2 = new Destroyer();
		row = 3;
		column = 7;
		horizontal = true;
		destroyer2.placeShipAt(row, column, horizontal, ocean);
		
		Ship battleship = new Battleship();
		row = 1;
		column = 2;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(3, 7));
		assertFalse(destroyer2.isSunk());
		assertTrue(ocean.shootAt(1, 2));
		assertFalse(battleship.isSunk());
		assertEquals(8, ocean.getShotsFired());
		
		//Test cruiser and battleship
		Cruiser cruiser = new Cruiser();
		row = 2;
		column = 4;
		horizontal = true;
		destroyer2.placeShipAt(row, column, horizontal, ocean);
		
		Ship battleship2 = new Battleship();
		row = 3;
		column = 3;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(3, 3));
		assertFalse(cruiser.isSunk());
		assertTrue(ocean.shootAt(3, 3));
		assertFalse(battleship2.isSunk());
		assertEquals(10, ocean.getShotsFired());
	}

	@Test
	void testGetHitCount() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		
		//Test if battleship is sunk
		Battleship battleship = new Battleship();
		row = 3;
		column = 9;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(3, 9));
		assertFalse(battleship.isSunk());
		assertEquals(2, ocean.getHitCount());
		
		//Test if cruiser is sunk
		Cruiser cruiser = new Cruiser();
		row = 0;
		column = 2;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(3, 9));
		assertFalse(cruiser.isSunk());
		assertNotEquals(2, ocean.getHitCount());		
	}
	
	@Test
	void testGetShipsSunk() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		//Test if cruiser is sunk
		Cruiser cruiser = new Cruiser();
		row = 8;
		column = 7;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(8, 7));
		assertFalse(cruiser.isSunk());
		assertEquals(2, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		//Test if submarine is sunk
		Submarine submarine = new Submarine();
		row = 3;
		column = 6;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(3, 6));
		assertTrue(submarine.isSunk());
		assertEquals(3, ocean.getHitCount());
		assertNotEquals(1, ocean.getShipsSunk());
	}

	@Test
	void testGetShipArray() {
		
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());
		
		//test ship array
		Ship[][] shipArray2 = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray2.length);
		assertEquals(OCEAN_SIZE, shipArray2[5].length);
		
		assertEquals("empty", shipArray[0][5].getShipType());
		
		//test ship array
		Ship[][] shipArray3 = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray3.length);
		assertEquals(OCEAN_SIZE, shipArray3[9].length);
		
		assertNotEquals("not empty", shipArray[3][9].getShipType());
	}
}
