

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MyWoodClient {

	private static ObjectInputStream reader;
	private static ObjectOutputStream writer;
	private static Socket socket;
	private static final int port = 12345;

	public static void main(String[] args) throws ClassNotFoundException, UnknownHostException, IOException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Write the player's name: ");
		String name = sc.nextLine();
		Deadmau5 deadmau5 = new Deadmau5();
		try {
			socket = new Socket("localhost",port);
			writer = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			Chatbox message = new Chatbox("create mau5",name);
			writer.writeObject(message);
			writer.flush();
			//writer.writeObject(new Chatbox(Action.Ok));
			//writer.flush();
			//reader = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			//reader = createStream();
			Action action = Action.Ok;
			while (action != Action.Finish && action != Action.WoodmanNotFound) {
				Direction direction = deadmau5.NextMove(action);
				message = new Chatbox("move mau5",name,direction);
				writer.writeObject(message);
				writer.flush();
				reader = createStream();
				//reader = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				message = (Chatbox) reader.readObject();
				action = message.getAction();
			}
			if (action == Action.WoodmanNotFound) System.out.println("Mau5 was destroyed.");
			if (action == Action.Finish) System.out.println("Mau5 finished!");
		} catch(ClassNotFoundException ex){
			ex.printStackTrace();
		} finally {
			Destroyter d = new Destroyter();
			if (writer != null) d.close(writer);
			if (reader != null) d.close(reader);
			if (socket != null) d.close(socket);
		}
	}

	private static ObjectInputStream createStream() throws IOException {
		ObjectInputStream OIreader;
		if (reader == null) {
			writer.writeObject(new Chatbox(Action.Ok));
			writer.flush();
			OIreader = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			reader = OIreader;
		}
		else OIreader = reader;
		return OIreader;
	}
}