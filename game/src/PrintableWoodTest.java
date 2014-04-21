import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.Test;

public class PrintableWoodTest {


	@Test(expected=CodeException.class)
	public void testException() throws IOException, CodeException { //createWoodman on a wall
		File file=new File("world.txt");
		InputStream instream=new FileInputStream(file);
		ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		PrintableWoodLoader W=new PrintableWoodLoader();
		PrintableWood wood=W.PrintableWoodLoad(instream,outstream);
		wood.createWoodman("Vincent", new Point(0, 0), new Point(0, 0));
	}


	@Test
	public void testCreateWoodman() throws IOException, CodeException {  //createWoodman on a life
		File file=new File("world.txt");
		InputStream instream=new FileInputStream(file);
		ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		PrintableWoodLoader W=new PrintableWoodLoader();
		PrintableWood wood=W.PrintableWoodLoad(instream,outstream);
		wood.createWoodman("Chris", new Point(6, 7), new Point(6, 7));
		String s="✿✿✿✿✿✿✿✿✿\r\n" +
				 "✿○○○○○✿✖✿\r\n" +
				 "✿✿✿✿✿○✿○✿\r\n" +
				 "✿○○○✿○✿○✿\r\n" +
				 "✿○✿○○○✿○✿\r\n" +
				 "✿○✿✿✿✿✿○✿\r\n" +
				 "✿○○○○○○♦✿\r\n" +
				 "✿✿✿✿✿✿✿✿✿\r\n" +	
				 "\r\n" +
				 "♥ - life\r\n" +
				 "✖ - death\r\n" +
				 "♦ - Chris(3 lives)\r\n";
		assertEquals(s, outstream.toString());
	}

	
	@Test
	public void testMove() throws IOException, CodeException { //simply move
		File file=new File("world.txt");
		InputStream instream=new FileInputStream(file);
		ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		PrintableWoodLoader W=new PrintableWoodLoader();
		PrintableWood wood=W.PrintableWoodLoad(instream,outstream);
		wood.createWoodman("Dorian", new Point(2, 7), new Point(1, 7));
	   String s1="✿✿✿✿✿✿✿✿✿\r\n" +
				 "✿○○○○○✿✖✿\r\n" +
				 "✿✿✿✿✿○✿♦✿\r\n" +
				 "✿○○○✿○✿○✿\r\n" +
				 "✿○✿○○○✿○✿\r\n" +
				 "✿○✿✿✿✿✿○✿\r\n" +
				 "✿○○○○○○♥✿\r\n" +
				 "✿✿✿✿✿✿✿✿✿\r\n" +	
				 "\r\n" +
				 "♥ - life\r\n" +
				 "✖ - death\r\n" +
				 "♦ - Dorian(3 lives)\r\n";			
		wood.move("Dorian", Direction.Up);	
		String s="✿✿✿✿✿✿✿✿✿\r\n" +
				 "✿○○○○○✿♦✿\r\n" +
				 "✿✿✿✿✿○✿○✿\r\n" +
				 "✿○○○✿○✿○✿\r\n" +
				 "✿○✿○○○✿○✿\r\n" +
				 "✿○✿✿✿✿✿○✿\r\n" +
				 "✿○○○○○○♥✿\r\n" +
				 "✿✿✿✿✿✿✿✿✿\r\n" +	
				 "\r\n" +
				 "♥ - life\r\n" +
				 "✖ - death\r\n" +
				 "♦ - Dorian(2 lives)\r\n";			
		assertEquals(s1+s, outstream.toString());
	}
	}

