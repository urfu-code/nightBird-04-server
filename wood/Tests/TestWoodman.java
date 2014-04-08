package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import wood01.Point;
import wood01.TheWoodman;

public class TestWoodman {
	
	private TheWoodman testWoodman;
	
	@Before
	public void setUp() throws Exception {
		testWoodman = new TheWoodman("testWoodman",new Point(3,3));
	}


	@Test
	public void testGetLifeCount() {
		assertEquals(3, testWoodman.GetLifeCount());
	}

	@Test
	public void testGetName() {
		assertEquals("testWoodman", testWoodman.GetName());
	}

	@Test
	public void testKill() {
		assertEquals(true, testWoodman.Kill());
		assertEquals(2, testWoodman.GetLifeCount());
		assertEquals(true, testWoodman.Kill());
		assertEquals(1, testWoodman.GetLifeCount());
		assertEquals(true, testWoodman.Kill());
		assertEquals(0, testWoodman.GetLifeCount());
		assertEquals(false, testWoodman.Kill());
	}

	@Test
	public void testAddLife() {
		testWoodman = new TheWoodman("testWoodman",new Point(3,3));
		testWoodman.AddLife();
		assertEquals(4, testWoodman.GetLifeCount());
	}

	@Test
	public void testGetLocation() {
		assertEquals(new Point(3,3), testWoodman.GetLocation());
	}

	@Test
	public void testSetLocation() {
		testWoodman.SetLocation(new Point(2,2));
		assertEquals(new Point(2,2), testWoodman.GetLocation());
	}

	@Test
	public void testMoveToStart() {
		testWoodman = new TheWoodman("testWoodman", new Point(3,3));
		testWoodman.SetLocation(new Point(2,2));
		testWoodman.MoveToStart();
		assertEquals(new Point(3,3), testWoodman.GetLocation());
	}

}
