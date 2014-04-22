import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;

public class MyWoodLoaderTest {

	@Test
	public void testLoad() throws IOException, CodeException {
		File file=new File("world.txt");
		FileInputStream stream=new FileInputStream(file);
		MyWoodLoader W =new MyWoodLoader();	
		Wood wood=W.Load(stream);
		Point k=new Point(1,7);
		wood.createWoodman("A", k,k);
		assertEquals(wood.move("A",Direction.Down) , Action.Ok);
		}
}