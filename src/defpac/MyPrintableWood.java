

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class MyPrintableWood extends MyWood {
	//private OutputStream output;
	private PrintWriter out;

	public MyPrintableWood(char[][] m_wood, OutputStream output) {
		super(m_wood);
		//this.output = output;
		out = new PrintWriter(
				new OutputStreamWriter(
						new BufferedOutputStream(output)));
	}

	public void printWood() throws IOException {
		//try {
		Map<String, String> code = new HashMap<String, String>();
		String symbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		boolean[] emptySymbol = new boolean[symbols.length()];
		for (int Idx = 0; Idx < symbols.length(); Idx++) {
			emptySymbol[Idx] = true;
		}
		for (MyWoodman man : m_woodmanList.values()) {
			int Idx = 0;
			while (emptySymbol[Idx] != true) {
				Idx++;
			}
			code.put(man.GetName(), symbols.substring(Idx, Idx+1));
			emptySymbol[Idx] = false;
		}
		for (int j = 0; j < m_wood.length; j++) {
			for (int i = 0; i < m_wood[0].length; i++) {
				boolean player = false;
				Vector <MyWoodman> m_woodmanListClone = new Vector <MyWoodman>();
				for (MyWoodman woodman : m_woodmanList.values())
					m_woodmanListClone.add(woodman);
				for (int k = 0; k < m_woodmanListClone.size(); k++) {
					for (int l = 0; l < m_woodmanListClone.size(); l++) {
						MyWoodman man1 = m_woodmanListClone.get(k);
						MyWoodman man2 = m_woodmanListClone.get(l);
						if (man1.GetLocation().equals(man2.GetLocation())&&
								!man1.GetName().equals(man2.GetName()))
						{ m_woodmanListClone.remove(man2); l--; }
					}
				}
				for (int k = 0; k < m_woodmanListClone.size(); k++) {
					MyWoodman man = m_woodmanListClone.get(k);
					Point point = new Point(i, j);
					if (man.GetLocation().equals(point)) {
						System.out.print(code.get(man.GetName()));
						out.print((code.get(man.GetName())).getBytes());
						player = true;
					}
				}
				if (player == false) {
					switch (m_wood[j][i]) {
					case '1':
						String symbol = "";
						String map_Symbols = "╬║║║═╔╚╠═╗╝╣═╦╩╬";
						int wall = 0;
						if (i != 0 && m_wood[j][i-1] == '1') wall += 8;
						if (i != m_wood[0].length - 1 && m_wood[j][i+1] == '1') wall += 4;
						if (j != 0 && m_wood[j-1][i] == '1') wall += 2;
						if (j != m_wood.length - 1 && m_wood[j+1][i] == '1') wall += 1;
						symbol += map_Symbols.substring(wall,wall+1);
						System.out.print(symbol);
						out.print(symbol.getBytes());
						break;
					case '0':
						System.out.print(" ");
						out.print((" ").getBytes());
						break;
					case 'K':
						System.out.print("†");
						out.print(("†").getBytes());
						break;
					case 'L':
						System.out.print("♥");
						out.print(("♥").getBytes());
						break;
					default:
						break;
					}
				}
			}
			System.out.print("\n");
			out.print(("\n").getBytes());
		}
		System.out.print("\n♥ - life\n† - death\n");
		out.print(("\n♥ - life\n† - death\n").getBytes());
		// предполагаем, что игроков не более 52 :-)
		for (MyWoodman man : m_woodmanList.values()) {
			System.out.print(code.get(man.GetName()) + " - " + man.GetName()
					+ " , lifes: " + man.GetLifeCount() + "\n");
			out.print((code.get(man.GetName()) + " - " + man.GetName()
					+ " , lifes: " + man.GetLifeCount() + "\n").getBytes());
		}
		for (int Idx = 0; Idx < symbols.length(); Idx++) {
			emptySymbol[Idx] = true;
		}
		for (MyWoodman man : m_woodmanList.values()) {
			char letter = code.get(man.GetName()).charAt(0);
			int Idx = 0;
			while (letter != symbols.charAt(Idx))
				Idx++;
			emptySymbol[Idx] = false;
		}
		//} finally {
		//	out.close();
		//}
	}

	@Override
	public void createWoodman(String name, Point start, Point finish) throws IOException {
		super.createWoodman(name, start, finish);
		printWood();
	}

	@Override
	public Action move(String name, Direction direction) throws IOException {
		Action action = super.move(name, direction);
		printWood();
		return action;
	}
	static void main (String[] args) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		char[][] forest = {{'1','1','1','1'},{'1','0','K','1'},{'1','0','L','1'},{'1','0','1','1'},{'1','1','1','1'}};
		MyPrintableWood wood = new MyPrintableWood (forest, out);
		wood.createWoodman("aaa", new Point(1,1), new Point(1,3));
		wood.createWoodman("losyash", new Point(1,3), new Point(1,2));
		wood.createWoodman("myash", new Point(1,1), new Point(1,3));
		wood.createWoodman("hyash", new Point(1,1), new Point(1,3));
		wood.createWoodman("syash", new Point(1,1), new Point(1,3));
	}

}