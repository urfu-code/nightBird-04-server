import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {	
	public static void main (String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(9874);
			Socket socket = serverSocket.accept();
			MyWoodLoader loader = new MyWoodLoader();
			PrintableWood pw = (PrintableWood) loader.prWood(System.out);
			ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
			try {
				while (true) {
					MessageClient newMessageClient = (MessageClient) ois.readObject();
					if (newMessageClient.getTask().equals("create")) {
						pw.createWoodman(newMessageClient.getName(), new Point (3, 3), new Point (8, 6));
						MessageServer newMessageServer = new MessageServer(Action.Ok);
						oos.writeObject(newMessageServer);
						oos.flush();
					}
					if (newMessageClient.getTask().equals("move")) {
						Action action = pw.move(newMessageClient.getName(), newMessageClient.getDirection());
						MessageServer newMessageServer = new MessageServer(action);
						oos.writeObject(newMessageServer);
						oos.flush();
						if (action == Action.Finish) {
							System.out.println("finish");
							break;
						}
						if (action == Action.WoodmanNotFound) {
							System.out.println("dead");
							break;
						}
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				close(serverSocket);
				close(socket);
				close(oos);
			}
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
