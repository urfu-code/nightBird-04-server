package Tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Before;
import org.junit.Test;

import wood01.Action;
import wood01.Direction;
import wood01.Point;
import wood01.PrintableTheWood;
import wood01.TheWoodLoader;
import wood03.TheMouse;

public class testMouse {

	TheMouse mouse;
	PrintableTheWood wood;
	String name;
	Point startPoint;
	Point finishPoint;
	
	@Before
	public void setUp() throws Exception {
		startPoint = new Point(1,1);
		finishPoint = new Point(1,3);
		File file = new File("wood1.txt");
		TheWoodLoader loader = new TheWoodLoader();
		name = "Kolya";
		wood = (PrintableTheWood) loader.Load(new FileInputStream(file),System.out);
		mouse = new TheMouse("kolya");
	}

	@Test
	public void testMouseFoundPath() throws Exception {
		Action currentAction = Action.Ok;
		Direction currentDirection;
		wood.createWoodman(name, finishPoint, startPoint);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.None, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.Down, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.Left, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.Up, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.Right, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.Up, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.Up, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.Down, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.Down, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.None, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.None, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.None, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.None, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.None, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.None, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.Up, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.Up, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.Left, currentDirection);
		currentAction = wood.move(name, currentDirection);
		assertEquals(Action.ExitFound, currentAction);
	}
	
	@Test
	public void testMouseDontFoundPath() throws Exception {
		name = "Petya";
		wood.createWoodman(name, startPoint, finishPoint);
		Action currentAction = Action.Ok;
		Direction currentDirection = mouse.NextMove(currentAction);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.Down, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.Left, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.Up, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.Right, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.Up, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.Right, currentDirection);
		currentAction = wood.move(name, currentDirection);
		currentDirection = mouse.NextMove(currentAction);
		assertEquals(Direction.Down, currentDirection);
		currentAction = wood.move(name, currentDirection);
		assertEquals(Action.WoodmanNotFound, currentAction);
	}

}
