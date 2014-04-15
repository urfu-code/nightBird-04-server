import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;


public class PrintableWoodLoaderTest {

	@Test
	public void testPrintWood() throws IOException, CodeException {
		File file=new File("world.txt");
		FileInputStream stream=new FileInputStream(file);
		MyWoodLoader W =new MyWoodLoader();	
		Wood wood=W.Load(stream);
		System.out.println(wood);
		//Point k=new Point(1,1);
		//wood.createWoodman("A", k);
		//assertEquals(wood.move("A",Direction.Down) , Action.Ok);
	}

}
