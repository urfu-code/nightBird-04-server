package Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import wood01.Action;
import wood01.Direction;
import wood01.Point;
import wood01.TheWood;

public class TestWood {
	
	TheWood testWood;
	char[][] wood;
	
	@Before
	public void testMove() throws Exception {
		wood = new char[3][];
		wood[0] = new char[]{'1','0','1'};
		wood[1] = new char[]{'L','0','1'};
		wood[2] = new char[]{'0','K','1'};
		testWood =  new TheWood(wood);
		testWood.createWoodman("kolya", new Point(1,1), new Point(1,1));
	}
	
	@Test
	public void testActionWoodmanNotFound() throws Exception {
		assertEquals(Action.WoodmanNotFound, testWood.move("nekolya", Direction.Up));
	}
	
	@Test 
	public void testActionFail() throws Exception {
		assertEquals(Action.Fail, testWood.move("kolya", Direction.Right));
	}
	@Test
	public void voidtestActionOk() throws Exception {
		assertEquals(Action.Ok, testWood.move("kolya", Direction.Up));
	};
	
	@Test
	public void testActionLife() throws Exception {
		assertEquals(Action.Life, testWood.move("kolya", Direction.Left));
	}
	@Test
	public void testActionDead() throws Exception {
		assertEquals(Action.Dead, testWood.move("kolya", Direction.Down));
	}
	
	@Test
	public void testRemoveWoodman() throws Exception {
		testWood.move("kolya", Direction.Down);
		testWood.move("kolya", Direction.Up);
		testWood.move("kolya", Direction.Down);
		testWood.move("kolya", Direction.Up);
		testWood.move("kolya", Direction.Down);
		testWood.move("kolya", Direction.Up);
		assertEquals(Action.WoodmanNotFound, testWood.move("kolya", Direction.Down));
	}
	
	@Test
	public void testMoveInWall() throws Exception {
		testWood.move("kolya", Direction.Left);
		testWood.move("kolya", Direction.Up);
		testWood.move("kolya", Direction.Down);
		assertEquals(Action.Dead, testWood.move("kolya", Direction.Right));
		assertEquals(Action.Fail, testWood.move("kolya", Direction.Right));
		assertEquals(Action.Fail, testWood.move("kolya", Direction.Right));
		assertEquals(Action.Fail, testWood.move("kolya", Direction.Right));
		assertEquals(Action.Fail, testWood.move("kolya", Direction.Right));
		assertEquals(Action.WoodmanNotFound, testWood.move("kolya", Direction.Right));
	}
	
	@Test(expected = Exception.class)
	public void testException() throws Exception {
		TheWood testWood = new TheWood(wood);
		testWood.createWoodman("vasya", new Point(-1,-1), new Point(1,1));
	}
	
	@Test(expected = Exception.class)
	public void test2Exception() throws Exception {
		TheWood test2Wood = new TheWood(wood);
		test2Wood.createWoodman("vasya", new Point(1,0), new Point(1,1));
		test2Wood.move("vasya", Direction.Up);
	}
	
	@Test(expected = Exception.class)
	public void test3Exception() throws Exception {
		TheWood test3Wood = new TheWood(wood);
		test3Wood.createWoodman("vasya", new Point(0,0), new Point(1,1));
	}
}
