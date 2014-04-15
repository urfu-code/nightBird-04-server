import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;


public class MyWoodTest {


	@Test(expected=CodeException.class)
	public void testException() throws IOException, CodeException { //createWoodman on a wall
		char[][]wood = new char[4][4];
		for (int j=0;j<4;j++) {
			wood[0][j]='1';
			wood[3][j]='1';
		}
		for (int i=0;i<4;i++) {
			wood[i][0]='1';
			wood[i][3]='1';
		}
		wood[1][1]='0';
		wood[2][1]='L';
		wood[1][2]='0';
		wood[2][2]='K';
		MyWood W=new MyWood(wood);
		Point k=new Point(0,0);
		W.createWoodman("A", k, k);
	}
	
	@Test
	public void testMove1() throws CodeException, IOException {
		char[][]wood = new char[4][4];
		for (int j=0;j<4;j++) {
			wood[0][j]='1';
			wood[3][j]='1';
		}
		for (int i=0;i<4;i++) {
			wood[i][0]='1';
			wood[i][3]='1';
		}
		wood[1][1]='0';
		wood[2][1]='L';
		wood[1][2]='0';
		wood[2][2]='K';
		MyWood W=new MyWood(wood);
		Point k=new Point(1,1);
		W.createWoodman("A", k, k);
		assertEquals(W.move("B", Direction.Down) , Action.WoodmanNotFound);
	}
	
	@Test
	public void testMove2() throws CodeException, IOException {
		char[][]wood = new char[4][4];
		for (int j=0;j<4;j++) {
			wood[0][j]='1';
			wood[3][j]='1';
		}
		for (int i=0;i<4;i++) {
			wood[i][0]='1';
			wood[i][3]='1';
		}
		wood[1][1]='0';
		wood[2][1]='L';
		wood[1][2]='0';
		wood[2][2]='K';
		MyWood W=new MyWood(wood);
		Point k=new Point(1,1);
		W.createWoodman("A", k, k);
		assertEquals(W.move("A",Direction.Up) , Action.Fail);
	}

	@Test
	public void testMove3() throws CodeException, IOException {
		char[][]wood = new char[4][4];
		for (int j=0;j<4;j++) {
			wood[0][j]='1';
			wood[3][j]='1';
		}
		for (int i=0;i<4;i++) {
			wood[i][0]='1';
			wood[i][3]='1';
		}
		wood[1][1]='0';
		wood[2][1]='L';
		wood[1][2]='0';
		wood[2][2]='K';
		MyWood W=new MyWood(wood);
		Point k=new Point(1,1);
		W.createWoodman("A", k, k);
		assertEquals(W.move("A",Direction.Down) , Action.Life);
	}

	@Test
	public void testMove4() throws CodeException, IOException {
		char[][]wood = new char[4][4];
		for (int j=0;j<4;j++) {
			wood[0][j]='1';
			wood[3][j]='1';
		}
		for (int i=0;i<4;i++) {
			wood[i][0]='1';
			wood[i][3]='1';
		}
		wood[1][1]='0';
		wood[2][1]='L';
		wood[1][2]='0';
		wood[2][2]='K';
		MyWood W=new MyWood(wood);
		Point k=new Point(1,1);
		W.createWoodman("A", k, k);
		assertEquals(W.move("A",Direction.Right) , Action.Ok);
	}

	@Test
	public void testMove5() throws CodeException, IOException {
		char[][]wood = new char[4][4];
		for (int j=0;j<4;j++) {
			wood[0][j]='1';
			wood[3][j]='1';
		}
		for (int i=0;i<4;i++) {
			wood[i][0]='1';
			wood[i][3]='1';
		}
		wood[1][1]='0';
		wood[2][1]='L';
		wood[1][2]='0';
		wood[2][2]='K';
		MyWood W=new MyWood(wood);
		Point k=new Point(1,1);
		W.createWoodman("A", k, k);
		W.move("A",Direction.Down); //ok
		assertEquals(W.move("A",Direction.Right) , Action.Dead);
	}

	@Test
	public void testMove6() throws CodeException, IOException {
		char[][]wood = new char[4][4];
		for (int j=0;j<4;j++) {
			wood[0][j]='1';
			wood[3][j]='1';
		}
		for (int i=0;i<4;i++) {
			wood[i][0]='1';
			wood[i][3]='1';
		}
		wood[1][1]='0';
		wood[2][1]='L';
		wood[1][2]='0';
		wood[2][2]='K';
		MyWood W=new MyWood(wood);
		Point k1=new Point(1,1);
		W.createWoodman("A", k1, k1);
		W.move("A",Direction.Right); //ok
		W.move("A",Direction.Down); //dead
		W.move("A",Direction.Up);  //ok
		W.move("A",Direction.Down); //dead
		W.move("A",Direction.Up); //ok
		W.move("A",Direction.Down); //dead
		W.move("A",Direction.Up); //ok
		assertEquals(W.move("A",Direction.Down) , Action.WoodmanNotFound);
	}
	@Test
	public void testMove7() throws CodeException, IOException {
		char[][]wood = new char[4][4];
		for (int j=0;j<4;j++) {
			wood[0][j]='1';
			wood[3][j]='1';
		}
		for (int i=0;i<4;i++) {
			wood[i][0]='1';
			wood[i][3]='1';
		}
		wood[1][1]='0';
		wood[2][1]='L';
		wood[1][2]='0';
		wood[2][2]='K';
		MyWood W=new MyWood(wood);
		Point k=new Point(2,1);
		W.createWoodman("A", k, k);
		assertEquals(W.move("A",Direction.None) , Action.Finish);
	}
	@Test
	public void testMove8() throws CodeException, IOException {
		char[][]wood = new char[4][4];
		for (int j=0;j<4;j++) {
			wood[0][j]='1';
			wood[3][j]='1';
		}
		for (int i=0;i<4;i++) {
			wood[i][0]='1';
			wood[i][3]='1';
		}
		wood[1][1]='0';
		wood[2][1]='L';
		wood[1][2]='0';
		wood[2][2]='K';
		MyWood W=new MyWood(wood);
		Point k=new Point(1,1);
		W.createWoodman("A", k, k);	
		assertEquals(W.move("A",Direction.Up) , Action.Fail);
	}
	@Test
	public void testMove9() throws CodeException, IOException {
		char[][]wood = new char[4][4];
		for (int j=0;j<4;j++) {
			wood[0][j]='1';
			wood[3][j]='1';
		}
		for (int i=0;i<4;i++) {
			wood[i][0]='1';
			wood[i][3]='1';
		}
		wood[1][1]='0';
		wood[2][1]='L';
		wood[1][2]='0';
		wood[2][2]='K';
		MyWood W=new MyWood(wood);
		Point k=new Point(2,2);
		W.createWoodman("A", k, k);	
		assertEquals(W.move("A",Direction.Right) , Action.Fail);
	}
	@Test
	public void testMove10() throws CodeException, IOException {
		char[][]wood = new char[4][4];
		for (int j=0;j<4;j++) {
			wood[0][j]='1';
			wood[3][j]='1';
		}
		for (int i=0;i<4;i++) {
			wood[i][0]='1';
			wood[i][3]='1';
		}
		wood[1][1]='0';
		wood[2][1]='L';
		wood[1][2]='0';
		wood[2][2]='K';
		MyWood W=new MyWood(wood);
		Point k=new Point(2,2);
		W.createWoodman("A", k, k);	
		W.move("A",Direction.Right);
		W.move("A",Direction.Right);
		W.move("A",Direction.Right);
		assertEquals(W.move("A",Direction.Right) , Action.Fail);
	}
}