

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MyWoodClient {

	private static ObjectInputStream reader;
	private static ObjectOutputStream writer;
	private static Socket socket;
	private static final int port = 1234;

	public static void main(String[] args) throws ClassNotFoundException, UnknownHostException, IOException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Write the player's name: ");
		String name = sc.nextLine();
		Deadmau5 deadmau5 = new Deadmau5();
		try {
			socket = new Socket("localhost",port);
			writer = new ObjectOutputStream(socket.getOutputStream());
			Chatbox messageToServer = new Chatbox("create mau5",name);
			writer.writeObject(messageToServer);
			writer.flush();
			//writer.writeObject(new Chatbox(Action.Ok));
			//writer.flush();
			//reader = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			reader = createStream();
			Action action = Action.Ok;
			while (action != Action.Finish && action != Action.WoodmanNotFound) {
				Direction direction = deadmau5.NextMove(action);
				messageToServer = new Chatbox("move mau5",name,direction);
				writer.writeObject(messageToServer);
				writer.flush();
				//reader = createStream();
				//reader = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				Chatbox messageToClient = (Chatbox) reader.readObject();
				action = messageToClient.getAction();
			}
			if (action == Action.WoodmanNotFound) System.out.println("Mau5 died.");
			if (action == Action.Finish) System.out.println("Mau5 finished!");
		} catch(ClassNotFoundException ex){
			ex.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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
			//writer.writeObject(new Chatbox(Action.Ok));
			//writer.flush();
			OIreader = new ObjectInputStream(socket.getInputStream());
			reader = OIreader;
		}
		else OIreader = reader;
		return OIreader;
	}
}