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

public class WoodServer {

	private static ObjectInputStream reader;
	private static ObjectOutputStream writer;
	
	public static void main (String[] args) throws IOException, ClassNotFoundException {
		try {
			ServerSocket serverSocket = new ServerSocket(4000);
			Socket socket = serverSocket.accept();
			System.out.println("Hello!\n"+System.getProperty("line.separator"));
			File file = new File("simple forest.txt");
			FileInputStream stream = new FileInputStream(file);
			MyWoodLoader wood_loader = new MyWoodLoader();
			MyPrintableWood wood = (MyPrintableWood)wood_loader.Load(stream,System.out);
			Point start = new Point(1,1);
			Point finish = new Point(5,5);
			//ObjectOutputStream writer = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			//ObjectInputStream reader = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));;
			reader = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));;
			Action action = Action.Ok;
			try {
				while (action != Action.Finish && action != Action.WoodmanNotFound) {
					
					Chatbox message = (Chatbox) reader.readObject();
					if (message.getCommand().equals("create mau5"))
						wood.createWoodman(message.getName(), start, finish);
					if (message.getCommand().equals("move mau5")) {
						writer = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
						action = wood.move(message.getName(), message.getDirection());
						message = new Chatbox(action);
						wood.printWood();
						writer.writeObject(message);
						writer.flush();
					}
				}
			} finally {
				System.out.println("Oops, something got wrong...\n"+System.getProperty("line.separator"));
				Destroyter d = new Destroyter();
				d.close(writer);
				d.close(reader);
				d.close(socket);
				d.close(serverSocket);
				//tryClose(writer);
				//tryClose(reader);
				//tryClose(socket);
				//tryClose(serverSocket);
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Goodbye!\n"+System.getProperty("line.separator"));
	}
	
}