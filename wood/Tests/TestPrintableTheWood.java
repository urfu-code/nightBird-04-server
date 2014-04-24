package Tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.junit.Before;
import org.junit.Test;

import wood01.Direction;
import wood01.Point;
import wood01.PrintableTheWood;
import wood01.TheWoodLoader;

public class TestPrintableTheWood {
	
	private TheWoodLoader loader;
	private String testString;
	private ByteArrayInputStream testStream;
	private PrintableTheWood testWood;
	private ByteArrayOutputStream testOutput;
	
	@Before
	public void setUp() throws Exception {
		loader = new TheWoodLoader();
		testString = "111111\n1001L1\n101111\n100101\n101001\n1000K1\n111111\n";
		testOutput = new ByteArrayOutputStream();
		testStream = new ByteArrayInputStream(testString.getBytes());
		testWood =  loader.Load(testStream,testOutput);
	}

	@Test
	public void testCreateWoodman() throws Exception {
		testWood.createWoodman("kolya", new Point(1,1), new Point(4,4));
		String test2String = 
		"┌──┬─┐\n" +
		"│α │♥│\n" +
		"│ ─┼─┤\n" +
		"│  │ │\n" +
		"│ □  │\n" +
		"│   ѻ│\n" +
		"└────┘\n" +
		"------------\n" +
		"♥ - live\n" +
		"ѻ - trap\n" +
		"------------\n" +
		"kolya (α) - 3 live(s)\n";
		assertEquals(test2String, testOutput.toString());
		
	}

	@Test
	public void testMove() throws Exception {
		String test3String = 
		"┌──┬─┐\n" +
		"│  │♥│\n" +
		"│α─┼─┤\n" +
		"│  │ │\n" +
		"│ □  │\n" +
		"│   ѻ│\n" +
		"└────┘\n" +
		"------------\n" +
		"♥ - live\n" +
		"ѻ - trap\n" +
		"------------\n" +
		"kolya (α) - 3 live(s)\n";
		testWood.createWoodman("kolya", new Point(1,1), new Point(4,4));
		testOutput.reset();
		testWood.move("kolya", Direction.Down);
		assertEquals(test3String, testOutput.toString());
	}

}
