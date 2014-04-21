
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PrintableWood extends MyWood {

	List<StringBuilder> printList = new LinkedList<StringBuilder>(); 
	private OutputStreamWriter ostream;	
	private Map <String,Character> m_woodmans = new HashMap<String,Character>();
	private Set <Character> m_woodmanListOfSymbols =  new HashSet<Character>();
	int wl;
	int ww;
	private char[][] printableWood;

	public PrintableWood(char[][] wood,OutputStream stream) throws IOException, CodeException {
		super(wood);
		m_wood = wood;
		wl = wood.length;
		ww = wood[0].length;
		ostream = new OutputStreamWriter(stream);
		m_woodmanListOfSymbols.addAll(Arrays.asList('☃','★','☆','☺','☻','♔','♕','♘','♞','♟','♚','⛷','♈','♉','♊','♋','♌','♍','♎','♏','♐','♑','♒','♓','✈','♦'));	 	
		printableWood = new char[wl][ww];
		for (int i=0; i<wl; i++) {
			for (int j=0; j<ww; j++) {
				printableWood[i][j] = getSymbolOfWood(i,j);
			}
		}
	}

	@Override
	public void createWoodman(String name, Point start,Point finish) throws CodeException, IOException{
		super.createWoodman(name, start, finish);	
		Iterator<Character> i = m_woodmanListOfSymbols.iterator();
		if (!i.hasNext()) throw new CodeException("You're extra woodman!");
		char symbolOfWoodman = i.next();	
		m_woodmanListOfSymbols.remove(symbolOfWoodman);
		m_woodmans.put(name, symbolOfWoodman);
		printableWood[start.getX()][start.getY()] = symbolOfWoodman;
		PrintWood();
	}

	@Override
	public Action move(String name, Direction direction) throws IOException, CodeException {
		Point startLocation = super.getLocation(name);
		printableWood[startLocation.getX()][startLocation.getY()] = getSymbolOfWood(startLocation.getX(), startLocation.getY());
		Action result = super.move(name, direction);
		Point newLocation = super.getLocation(name);
		printableWood[newLocation.getX()][newLocation.getY()] = m_woodmans.get(name);
		if (result == Action.WoodmanNotFound) {
			m_woodmanListOfSymbols.add(m_woodmans.remove(name));
		}
		PrintWood();
		return result;
	}

	private char getSymbolOfWood(int x, int y) {
		char life='♥';
		char trap='✖';
		char space='○';
		char wall='✿';
		switch (m_wood[x][y]) {	
		case '0':
			return space;
		case '1':
			return wall;
		case 'L':
			return life;
		case 'K':
			return trap;
		}
		return 0;
	}

	public void PrintWood() throws IOException, CodeException {
		try {
			for (int i=0; i<wl; i++) {
				for (int j=0; j<ww; j++) {
					ostream.write(printableWood[i][j]);
				}
				ostream.write(System.lineSeparator());
			}
			ostream.write(System.lineSeparator());
			ostream.write('♥' + " - life" + System.lineSeparator()); 
			ostream.write('✖' + " - death" + System.lineSeparator());
			for (MyWoodman newWoodman : m_woodmanList.values())
				ostream.write((printableWood[newWoodman.GetLocation().getX()][newWoodman.GetLocation().getY()] + " - " + newWoodman.GetName() + '(' + newWoodman.GetLifeCount() + " lives)"+ System.lineSeparator()));
			ostream.flush();
		} 
		catch (IOException e) 
		{
			ostream.close();
			e.printStackTrace();
		}
	}

}
