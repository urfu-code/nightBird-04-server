package defpac;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyWoodServer {

	private static ObjectInputStream reader;
	private static ObjectOutputStream writer;

	public static void main (String[] args) throws IOException, ClassNotFoundException {

		File file = new File("simple forest.txt");
		Point start = new Point(1,1);
		Point finish = new Point(5,5);
		Action action = Action.Ok;
		try {
			ServerSocket serverSocket = new ServerSocket(4004);
			Socket socket = serverSocket.accept();
			System.out.println("Hello!\n"+System.getProperty("line.separator"));
			FileInputStream stream = new FileInputStream(file);
			MyWoodLoader wood_loader = new MyWoodLoader();
			MyPrintableWood wood = (MyPrintableWood)wood_loader.Load(stream, System.out);
			Destroyter d = new Destroyter();
			d.close(stream);		
			reader = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));;
			try {
				while((action != Action.WoodmanNotFound) && (action != Action.Finish)) {
					Chatbox messageServer = (Chatbox)reader.readObject();
					if (messageServer.getCommand().equals("create mau5")) {
						wood.createWoodman(messageServer.getName(), start, finish);
					}
					if (messageServer.getCommand().equals("move mau5")) {
						action = wood.move(messageServer.getName(), messageServer.getDirection());
						writer = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
						Chatbox messageClient = new Chatbox(action);
						writer.writeObject(messageClient);
						writer.flush();
					}
				}	
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			} finally {
				d.close(socket);
				d.close(serverSocket);
				d.close(writer);
				d.close(reader);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Goodbye!\n"+System.getProperty("line.separator"));
	}
}