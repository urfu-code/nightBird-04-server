import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class MyWoodThreadServer implements Runnable {
	
//	private static final int port = 12345;
//	private int Index = 0;
//	public Vector <Thread> players;
//	private static HashMap <Point,Point> points = new HashMap<Point,Point>();
//	public LinkedList <Socket> clients;
//	private static ServerSocket server;
	public int Idx = 0;
	private ObjectInputStream reader = null;
	private ObjectOutputStream writer = null;
	private Socket socket = null;
	private MyPrintableWood wood;
	private ArrayList <Point> starts = new ArrayList<Point>();
	private ArrayList <Point> finishes = new ArrayList<Point>();
	private MyWoodServerSynchronizer synchronizer;
	
	public MyWoodThreadServer (Socket socket, MyPrintableWood wood, ArrayList<Point> points,
			MyWoodServerSynchronizer synchronizer) throws IOException {
				this.socket = socket;
		 		this.wood = wood;
		 		int i = 0;
		 		while (i < points.size()) {
		 			starts.add(points.get(i++));
		 			finishes.add(points.get(i++));
		 		}
		 		this.synchronizer = synchronizer;
		 		try {
		 			reader = new ObjectInputStream(socket.getInputStream());
		 			writer = new ObjectOutputStream(socket.getOutputStream());
		 		} catch (IOException e) {
		 			System.out.println("Oops, reader or writer has been crashed!");
		 			e.printStackTrace();
		 		}
		 }	
	
//	public static void main(String[] args) throws IOException {
//		File locations = new File("start&finish points");
//		FileInputStream stream = new FileInputStream(locations);
//		Scanner sc = new Scanner(locations);
//        while(sc.hasNext()) points.put(new Point (sc.nextInt(),sc.nextInt()),new Point (sc.nextInt(),sc.nextInt()));
//        stream.close();
//        stream = new FileInputStream("test_forest.txt");
//        MyWoodLoader wood_loader = new MyWoodLoader();
//		MyPrintableWood wood = (MyPrintableWood)wood_loader.Load(stream, System.out);
//		try {
//			server = new ServerSocket(port);
//  			System.out.println("Server started!");
//  			
//		}
//	}

	@Override
	public void run() {
		Chatbox message = null;
		Action action = Action.Ok;
		try {
			while (action != Action.WoodmanNotFound && action != Action.Finish) {
				 synchronized (synchronizer) {
					 synchronizer.wait();
				 }
				 message = (Chatbox) reader.readObject();
				 if (message.getCommand().equals("create mau5")) {
					 if (Idx == starts.size()) Idx -= starts.size();
					 wood.createWoodman(message.getName(), starts.get(Idx), finishes.get(Idx++));
				 } else
					 if (message.getCommand().equals("move mau5")) {
						 action = wood.move(message.getName(), message.getDirection());
				 }
				 message = new Chatbox(action);
				 writer.writeObject(message);
				 writer.flush();
			}
			if (action == Action.WoodmanNotFound) {
				 System.out.println("Mau5 " + message.getName() + " has just died.");
			} else {
				 System.out.println("Mau5 " + message.getName() + " has just finished!");
			}
		} catch (IOException e) {e.printStackTrace();
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (InterruptedException e) {e.printStackTrace();
		} finally {
			Destroyter d = new Destroyter();
			if (reader != null) d.close(reader);
			if (writer != null) d.close(writer);
			if (socket != null) d.close(socket);
		}
	}
}
