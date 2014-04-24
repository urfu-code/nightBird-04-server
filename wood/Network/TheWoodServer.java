package Network;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import wood01.Point;
import wood01.PrintableTheWood;
import wood01.TheWoodLoader;

public class TheWoodServer {
	
	public static void main(String[] Args) {
		ServerSocket server = null;
		Thread thread;
		File file = new File("wood.txt");
		ArrayList<Point>startEndPoints = new ArrayList<Point>();
		TheWoodLoader loader = new TheWoodLoader();
		startEndPoints.add(new Point(11,6));
		startEndPoints.add(new Point(1,1));
		startEndPoints.add(new Point(11,1));
		startEndPoints.add(new Point(1,6));
		startEndPoints.add(new Point(7,4));
		startEndPoints.add(new Point(7,5));
		try {
			server = new ServerSocket(6789);
			System.out.println("Server started!");
			try {
				PrintableTheWood wood = (PrintableTheWood) loader.Load(new FileInputStream(file),System.out);
				System.out.println("Waiting for connection...");
				ArrayList<Thread> clients = new ArrayList<Thread>();
				TheWoodServerThreadSyncronizer sync = new TheWoodServerThreadSyncronizer(clients);
				thread = new Thread(sync);
				thread.start();
				while (true) {
					thread = new Thread(new TheWoodServerThread(server.accept(), wood, startEndPoints, sync));
					clients.add(thread);
					thread.start();
					System.out.println(clients.size() + " clients connected!");
				}
			}
			catch (ClassNotFoundException e) {
				System.out.println("тестова€ беда");
			}
			catch (IOException e) {
				System.out.println("всЄ, хватило с вуд лиента пакетов");
				e.printStackTrace();
			}
			catch (Exception e) {
				System.out.println("беда с лесом");
			}
			finally {
				if (server != null) {
					server.close();
				}
			}
		}
		catch (IOException e) {
			System.out.println("port is busy");
			System.exit(-1);
		}


	}
}
