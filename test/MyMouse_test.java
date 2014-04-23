import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;


public class MyMouse_test extends MyMouse {

	@Test
	public void testNextMove_finish() throws Exception {
		File file = new File ("test_MouseWood.txt");
		FileInputStream stream = new FileInputStream(file);
		MyWoodLoader loder = new MyWoodLoader();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		PrintableWood wood = loder.Load(stream, output);
		
		Action action = Action.Ok;
		wood.createWoodman("Armstrong", new Point(3, 1), new Point(4, 5));
		MyMouse mouse = new MyMouse();
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Fail,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Ok,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Fail,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Fail,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Ok,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Fail,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Ok,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Ok,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Fail,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Ok,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Ok,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Fail,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Fail,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Ok,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Fail,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Ok,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Fail,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Fail,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Ok,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Ok,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Fail,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Ok,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Ok,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Fail,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Ok,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Fail,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Ok,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Fail,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Life,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Life,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Ok,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Fail,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Ok,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Ok,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Fail,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Fail,action);
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.Finish,action);
	}

	@Test
	public void testNextMove_dead() throws Exception {
		File file = new File ("test_MouseWood.txt");
		FileInputStream stream = new FileInputStream(file);
		MyWoodLoader loder = new MyWoodLoader();
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		PrintableWood wood = loder.Load(stream, output);
		Action action = Action.Ok;
		wood.createWoodman("Gagarin", new Point(4, 4), new Point(8, 1));
		MyMouse mouse = new MyMouse();
		action = wood.move("Armstrong", mouse.NextMove(action));
		assertEquals(Action.WoodmanNotFound,action);
	}
}