import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {
	public static void main (String[] args) {
		String name = "Venera";
		try {
			Socket socket = new Socket("localhost", 9874);
			MyMouse mouse = new MyMouse();
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			MessageClient newMessageClient = new MessageClient(name, "create");
			oos.writeObject(newMessageClient);
			oos.flush();
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			Action action = Action.Ok;
			try {
				while (true) {
					Direction direction = mouse.NextMove(action);
					MessageClient newMessageClient2 = new MessageClient(name, "move", direction);
					oos.writeObject(newMessageClient2);
					oos.flush();
					MessageServer newMessageServer = (MessageServer) ois.readObject();
					if (newMessageServer.getAction() == Action.Finish || 
							newMessageServer.getAction() == Action.WoodmanNotFound) {
						break;
					}					 
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				close(socket);
				close(oos);
				close(ois);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void close(Closeable obj) {
		try {
			obj.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
