package defpac;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class WoodClient {

	private static ObjectInputStream reader;
	private static ObjectOutputStream writer;
	
	private static void tryClose(Closeable closeable) {
		try {
			closeable.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Socket socket = new Socket("localhost",4000);
		//ObjectOutputStream writer = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		//ObjectInputStream reader = null;
		writer = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		Scanner sc = new Scanner(System.in);
		System.out.println("Write the player's name: ");
		String name = sc.nextLine();
		Deadmau5 deadmau5 = new Deadmau5();
		Chatbox message = new Chatbox("create mau5",name);
		writer.writeObject(message);
		writer.flush();
		Action action = Action.Ok;
		//ObjectInputStream reader = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		while (action != Action.Finish && action != Action.WoodmanNotFound) {
			Direction direction = deadmau5.NextMove(action);
			message = new Chatbox("move mau5",name,direction);
			writer.writeObject(message);
			writer.flush();
			reader = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			message = (Chatbox) reader.readObject();
			action = message.getAction();
		}
		if (action == Action.WoodmanNotFound) System.out.println("Mau5 was destroyed.");
		if (action == Action.Finish) System.out.println("Mau5 finished!");
		tryClose(writer);
		tryClose(reader);
		tryClose(socket);
	}
}
